/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick
 */
public class combinedDTO {
    
    List<planetDTO> planetInfo = new ArrayList();
    List<characterDTO> characterInfo = new ArrayList();
    String title;
    String episode_id;
    String opening_crawl;
    String release_date;
    
    public combinedDTO(filmDTO film, List<characterDTO> ch, List<planetDTO> planet){
       
        this.planetInfo = planet;
        this.characterInfo = ch;
        this.title = film.getTitle();
        this.episode_id = film.getEpisode_id();
        this.opening_crawl = film.getOpening_crawl();
        this.release_date = film.getRelease_date();
    }
    
}
