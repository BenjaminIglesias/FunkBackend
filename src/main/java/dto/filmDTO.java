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

public class filmDTO {
 
    String title;
    String episode_id;
    String opening_crawl;
    List<String> characters = new ArrayList(); // Liste af urls til hver person
    List<String> planets = new ArrayList(); // Liste af urls til hver planet
    String release_date;

    public filmDTO(String title, String episode_id, String opening_crawl, String release_date) {
        this.title = title;
        this.episode_id = episode_id;
        this.opening_crawl = opening_crawl;
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEpisode_id() {
        return episode_id;
    }

    public void setEpisode_id(String episode_id) {
        this.episode_id = episode_id;
    }

    public String getOpening_crawl() {
        return opening_crawl;
    }

    public void setOpening_crawl(String opening_crawl) {
        this.opening_crawl = opening_crawl;
    }

    public List<String> getCharacterUrls() {
        return characters;
    }

    public void setCharacters(List<String> characters) {
        this.characters = characters;
    }

    public List<String> getPlanetUrls() {
        return planets;
    }

    public void setPlanets(List<String> planets) {
        this.planets = planets;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
    
    
    
}
