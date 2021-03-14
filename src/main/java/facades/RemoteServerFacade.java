/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dto.characterDTO;
import dto.filmDTO;
import dto.combinedDTO;
import dto.planetDTO;
import errorhandling.API_Exception;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.persistence.EntityManagerFactory;
import utils.HttpUtils;

/**
 *
 * @author Patrick
 */
public class RemoteServerFacade {
    
      private static EntityManagerFactory emf;
      private static RemoteServerFacade instance;
      private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    
   
    public RemoteServerFacade(){}
      /**
     *
     * @param _emf
     * @return the instance of this facade.
     */
    public static RemoteServerFacade getRemoteServerFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new RemoteServerFacade();
        }
        return instance;
    }
    
    
    
    // Sequntial metode til at hente film
    public String getAllFilms() throws IOException, API_Exception{
        
        String filmsJson = HttpUtils.fetchData("https://swapi.dev/api/films/1/");

        filmDTO filmsdto = GSON.fromJson(filmsJson, filmDTO.class);
        
         List<characterDTO> allCharacter = new ArrayList(); 
         List<planetDTO> allPlanets = new ArrayList(); 
         
        for (String ch : filmsdto.getCharacterUrls()){
           String ch2 = ch.replace("http", "https");
            String data = HttpUtils.fetchData(ch2);
             characterDTO c = GSON.fromJson(data, characterDTO.class);
           allCharacter.add(c);
        }
        
         for (String planet : filmsdto.getPlanetUrls()){
           String planet2 = planet.replace("http", "https");
            String data = HttpUtils.fetchData(planet2);
             planetDTO p = GSON.fromJson(data, planetDTO.class);
           allPlanets.add(p);
        }
         
       if (allCharacter.isEmpty() || allPlanets.isEmpty()) {
             throw new API_Exception("Internal failure, service is down.", 400);
         }
       
         combinedDTO combined = new combinedDTO(filmsdto, allCharacter, allPlanets);
         
        return GSON.toJson(combined);
    }
    
    
     public String getAllFilmsParallel() throws IOException, InterruptedException, ExecutionException, API_Exception{
         
        ExecutorService executor = Executors.newCachedThreadPool();
 
        String filmsJson = HttpUtils.fetchData("https://swapi.dev/api/films/1/");

        filmDTO filmsdto = GSON.fromJson(filmsJson, filmDTO.class);
        
         List<characterDTO> allCharacter = giveThreadsWorkGetCharacters(filmsdto.getCharacterUrls(), executor);
         List<planetDTO> allPlanets = giveThreadsWorkGetPlanets(filmsdto.getPlanetUrls(), executor);
        
         
         if (allCharacter.isEmpty() || allPlanets.isEmpty()) {
             throw new API_Exception("Internal failure, service is down.", 400);
         }
         
         combinedDTO combined = new combinedDTO(filmsdto, allCharacter, allPlanets);
        
        return GSON.toJson(combined);
    }
   
     
    /*
     * Denne metode anvender en executor og futures, til at tildele threads opgaver.
     * Tager argumenterne: urls som er en liste af alle de urls man vil hente data fra.
     * og et ExecutorService til at håndtere trådene.
     */
     private List<planetDTO> giveThreadsWorkGetPlanets(List<String> urls, ExecutorService executor) throws InterruptedException, ExecutionException {
         
          List<Future<String>> planetFutures = new ArrayList<>();
          List<planetDTO> allPlanets = new ArrayList<>();
          
          for (String url : urls){
            Future future = executor.submit(new PlanetHandler(url));
            planetFutures.add(future);
        }
             for (Future f : planetFutures){
                 allPlanets.add((planetDTO) f.get());        
         }
         
         return allPlanets;    
     }
     
     
     
    /*
     * Denne metode anvender en executor og futures, til at tildele threads opgaver.
     * Tager argumenterne: urls som er en liste af alle de urls man vil hente data fra.
     * og et ExecutorService til at håndtere trådene.
     */
    private List<characterDTO> giveThreadsWorkGetCharacters(List<String> urls, ExecutorService executor) throws InterruptedException, ExecutionException {
         
          List<Future<String>> characterFutures = new ArrayList<>();
          List<characterDTO> allCharacters = new ArrayList<>();
          
          for (String url : urls){
            Future future = executor.submit(new CharacterHandler(url));
            characterFutures.add(future);
        }
             for (Future f : characterFutures){
                 allCharacters.add((characterDTO) f.get());        
         }
         
         return allCharacters;    
     }
}
