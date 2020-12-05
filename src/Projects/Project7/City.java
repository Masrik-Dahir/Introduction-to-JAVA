/****************************************************************************
 * City Analysis
 ****************************************************************************
 * A program that accept two txt filenames directory in the command line (One with city data and another with a output file
 * The program would read the city data, conduct analysis, write analyzed data regarding on the output file
 * Output.txt file contains information of average population, average area, cities above the average population, city with largest distance and
 * whether a city is listed on the city data txt file or not
 *_____________________________________________________
 * Masrik Dahir
 * 11/21/2020
 * CMSC 255-C90
 ****************************************************************************/
package Projects.Project7;

import java.util.Objects;

public class City {
    /**
     * Declare the private variables
     */
    private String name;
    private double area;
    private int population;
    private double distance;

    /**
     * default constructor
     */
    public City() {
        this.name = null;
        this.area = 0;
        this.population = 0;
        this.distance = 0;
    }

    /**
     * parameter constructor
     *
     * @param name
     * @param area
     * @param population
     * @param distance
     */
    public City(String name, double area, int population, double distance) {
        this.name = name;
        this.area = area;
        this.population = population;
        this.distance = distance;
    }

    /**
     * getter method for name
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * getter method for area
     *
     * @return area
     */
    public double getArea() {
        return area;
    }

    /**
     * getter method for distance
     *
     * @return distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * getter method for population
     *
     * @return population
     */
    public int getPopulation() {
        return population;
    }

    /**
     * setter method for name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * setter method for area
     *
     * @param area
     */
    public void setArea(double area) {
        this.area = area;
    }

    /**
     * setter method for population
     *
     * @param population
     */
    public void setPopulation(int population) {
        this.population = population;
    }

    /**
     * setter method for distance
     *
     * @param distance
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * toString method for City class
     *
     * @return the value of the instances
     */
    @Override
    public String toString() {
        return name + " " +
                area + " "
                + population + " "
                + distance;
    }

    /**
     * equals method for City class
     *
     * @param o
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;
        City city = (City) o;
        return Objects.equals(name, city.name);
    }
}

