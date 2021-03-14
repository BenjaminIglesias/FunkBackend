/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author Patrick
 */
public class planetDTO {
   
    String name;
    String orbital_period;
    String gravity; 
    String population;
    String terrain;
    String rotation_period;

    public planetDTO(String name, String orbital_period, String gravity, String population, String terrain, String rotation_period) {
        this.name = name;
        this.orbital_period = orbital_period;
        this.gravity = gravity;
        this.population = population;
        this.terrain = terrain;
        this.rotation_period = rotation_period;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrbital_period() {
        return orbital_period;
    }

    public void setOrbital_period(String orbital_period) {
        this.orbital_period = orbital_period;
    }

    public String getGravity() {
        return gravity;
    }

    public void setGravity(String gravity) {
        this.gravity = gravity;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getTerrain() {
        return terrain;
    }

    public void setTerrain(String terrain) {
        this.terrain = terrain;
    }

    public String getRotation_period() {
        return rotation_period;
    }

    public void setRotation_period(String rotation_period) {
        this.rotation_period = rotation_period;
    }
    
    
    
}
