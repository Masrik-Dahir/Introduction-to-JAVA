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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class CityAnalysis {
    public static void main(String[] args) {
        /**
         * calling File inputFile and File outputFile and
         * getting the directories from the console
         */
        File inputFile = new File(args[0]);
        File outputFile = new File(args[1]);
        /**
         * Calling the City city
         */
        City city = new City();
        /**
         * Calling ArrayList<String> lines and Arraylist<Sting> cities
         */
        ArrayList<String> lines = new ArrayList<String>();
        ArrayList<String> cities_above_pop_avg = new ArrayList<String>();
        /**
         * calling PrintWriter class
         */
        PrintWriter out;
        /**
         * call readFile method in a try catch block
         * catch FileNotFound Exception
         */
        try {
            lines = readFile(inputFile);
            /**
             * printing out "Input file correct"
             */
            System.out.println("Input file correct");
        } catch (FileNotFoundException e) {
            /**
             * printing out "Incorrect input filename"
             */
            System.out.println("Incorrect input filename");
        }
        /**
         * declare and initiate ArrayList<City> cities by the value of method parseData()
         */
        ArrayList<City> cities = parseData(lines);
        /**
         * declare and initiate double pop_avg by the value of method calcPopulationAverage()
         */
        double pop_avg = calcPopulationAverage(cities);
        /**
         * declare and initiate double area_avg by the value of method calcAreaAverage()
         */
        double area_avg = calcAreaAverage(cities);
        /**
         * declare and initiate String largest_dist_city by the value of method findLargestDistance()
         */
        String largest_dist_city = findLargestDistance(cities);
        /**
         * initiate cities_above_pop_avg by the value of method calcPopulationAboveAverage()
         */
        cities_above_pop_avg = calcPopulationAboveAverage(cities, pop_avg);
        /**
         * declare and initiate boolean exist by the value of method findCity()
         */
        boolean exist = findCity(cities, cities.get(4));
        /**
         * call PrintWriter class in a try catch block
         * and catch FileNotFoundException error
         */
        try {
            out = new PrintWriter(args[1]);

            String outputMessage = "The average population is: ";
            /**
             * calling method writeOutData(outputMessage, pop_avg, out)
             */
            writeOutData(outputMessage, pop_avg, out);

            outputMessage = "The average area is: ";
            /**
             * calling method writeOutData(outputMessage, area_avg, out)
             */
            writeOutData(outputMessage, area_avg, out);

            outputMessage = "The cities above the average population are: ";
            /**
             * calling method writeOutData(outputMessage, cities_above_pop_avg, out)
             */
            writeOutData(outputMessage, cities_above_pop_avg, out);

            outputMessage = "The largest distance is: ";
            /**
             * calling method writeOutData(outputMessage, largest_dist_city, out)
             */
            writeOutData(outputMessage, largest_dist_city, out);

            outputMessage = "Is Baton Rouge, LA in the data? ";
            /**
             * calling method writeOutData(outputMessage, exist, out)
             */
            writeOutData(outputMessage, exist, out);
            /**
             * Printing out "Output file correct"
             */
            System.out.println("Output file correct");
            out.close();
        } catch (FileNotFoundException e) {
            /**
             * Printing out "Incorrect output filename"
             */
            System.out.println("Incorrect output filename");
        }
    }

    /**
     * Method readFile(File inputFile)
     *
     * @param inputFile
     * @return
     * @throws FileNotFoundException
     */
    public static ArrayList<String> readFile(File inputFile) throws
            FileNotFoundException {
        /**
         * calling ArrayList<String> data
         */
        ArrayList<String> data = new ArrayList<String>();
        /**
         * Calling Scanner in to read the inputFile
         */
        Scanner in = new Scanner(inputFile);
        /**
         * creating a while loop to read every line on the inputFile
         */
        while (in.hasNextLine()) {
            String line = in.nextLine();
            data.add(line);
        }
        /**
         * closing the the inputFile reading
         */
        in.close();
        /**
         * return the ArrayList<String> data
         */
        return data;
    }

    /**
     * Method parseData(ArrayList<String> lines)
     *
     * @param lines
     * @return
     */
    public static ArrayList<City> parseData(ArrayList<String> lines) {
        /**
         * calling ArrayList<City> cityArray
         */
        ArrayList<City> cityArray = new ArrayList<City>();
        /**
         * Declaring String name, double area, int population, and double distance
         */
        String name;
        double area;
        int population;
        double distance;
        /**
         * Calling Class class and initializing it to null
         */
        City city = null;
        /**
         * creating a for loop to split the four part of each line
         * and initialize those values to name, area, population, and distance variable respectively
         */
        for (int i = 0; i < lines.size(); i++) {
            String[] split = lines.get(i).split("\t");
            /**
             * the first split value is assigned to name variable
             */
            name = split[0];
            /**
             * create a try catch block to assign the second, third and fourth
             * slip value to area, population, and distance variable respectively
             * catch block will set 0 to variables that try block fails to obtain number from
             */
            try {
                area = Double.parseDouble(split[1]);
                population = Integer.parseInt(split[2]);
                distance = Double.parseDouble(split[3]);
            } catch (NumberFormatException e) {
                area = 0;
                population = 0;
                distance = 0;
            }
            /**
             * IF area is less than 0, THEN area = 0
             */
            if (area < 0) {
                area = 0;
            }
            /**
             * IF population is less than 0, THEN population = 0
             */
            if (population < 0) {
                population = 0;
            }
            /**
             * IF distance is less than 0, THEN distance = 0
             */
            if (distance < 0) {
                distance = 0;
            }
            /**
             * initialize city variable by the obtained variables name, area, population, distant
             */
            city = new City(name, area, population, distance);
            /**
             * adding the object city into the Arraylist<City> cityArray
             */
            cityArray.add(city);
        }
        /**
         * return arrayList<City> cityArray
         */
        return cityArray;
    }

    /**
     * Method calcPopulationAverage(ArrayList<City> cities)
     *
     * @param cities
     * @return
     */
    public static double calcPopulationAverage(ArrayList<City>
                                                       cities) {
        /**
         * declare and initialize int total_pop to 0
         */
        int total_pop = 0;
        /**
         * creating a for loop to get population from all the City objects from ArrayList<City> cities
         */
        for (int i = 0; i < cities.size(); i++) {
            int population = cities.get(i).getPopulation();
            /**
             * adding all the found population to total_pop
             */
            total_pop += population;
        }
        /**
         * return the double value of total_pop divided by the total elements of cities (cities.size())
         */
        return (double) total_pop / cities.size();
    }

    /**
     * Method calcAreaAverage(ArrayList<City> cities)
     *
     * @param cities
     * @return
     */
    public static double calcAreaAverage(ArrayList<City> cities) {
        /**
         * declare and initialize double total_area to 0
         */
        double total_area = 0;
        /**
         * create a for loop to get area from all the City objects from ArrayList<City> cities
         */
        for (int i = 0; i < cities.size(); i++) {
            double area = cities.get(i).getArea();
            /**
             * adding all the found population to total_area
             */
            total_area += area;
        }
        /**
         * return the double value of total_area divided by the total elements of cities (cities.size())
         */
        return (double) total_area / cities.size();
    }

    /**
     * Method calcPopulationAboveAverage(ArrayList<City> cities, double avg)
     *
     * @param cities
     * @param avg
     * @return
     */
    public static ArrayList<String> calcPopulationAboveAverage(
            ArrayList<City> cities, double avg) {
        /**
         * Calling ArrayList<String> cities_above_avg
         */
        ArrayList<String> cities_above_avg = new ArrayList<>();
        /**
         * creating a for loop to identify the the population from the city object that exceed the avg
         */
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getPopulation() > avg) {
                /**
                 * find the variables name of the city object and add it to cities_above_avg
                 */
                cities_above_avg.add(cities.get(i).getName());
            }
        }
        /**
         * return the ArrayList<String> cities_above_avg
         */
        return cities_above_avg;
    }

    /**
     * Method findLargestDistance(ArrayList<City> cities)
     *
     * @param cities
     * @return
     */
    public static String findLargestDistance(ArrayList<City>
                                                     cities) {
        /**
         * declare and initialize largest_dist to 0
         */
        double largest_dist = 0;
        /**
         * declare and initialize largest_dist_city to null
         */
        String largest_dist_city = null;
        /**
         * creating a for loop to find the variables distance from City class
         */
        for (int i = 0; i < cities.size(); i++) {
            /**
             * IF the distance is greater than the largest_dist THEN
             * largest_dist is equals to distance and largest_dist_city equals to the variable Name of that City object
             */
            if (cities.get(i).getDistance() > largest_dist) {
                largest_dist = cities.get(i).getDistance();
                largest_dist_city = cities.get(i).getName();
            }
        }
        /**
         * return String largest_dist_city
         */
        return largest_dist_city;
    }

    /**
     * Method findCity(ArrayList<City> cities, City city)
     *
     * @param cities
     * @param city
     * @return
     */
    public static boolean findCity(ArrayList<City> cities, City
            city) {
        /**
         * declare and initialize boolean exist to flase
         */
        boolean exist = false;
        /**
         * creating a for loop to compare a city object variable name to the names of the objects from the City class
         */
        for (int i = 0; i < cities.size(); i++) {
            /**
             * IF city is equals to the parameterized city object THEN exist = true
             * ELSE exist = false
             */
            if (city.equals(cities.get(i))) {
                exist = true;
            } else {
                exist = false;
            }
        }
        /**
         * return boolean exist
         */
        return exist;
    }

    /**
     * Method writeOutData(String outputMessage, ArrayList<String> values, PrintWriter out)
     *
     * @param outputMessage
     * @param values
     * @param out
     * @throws FileNotFoundException
     */
    public static void writeOutData(String outputMessage,
                                    ArrayList<String> values, PrintWriter out) throws
            FileNotFoundException {
        /**
         * declare and initialize cities to a empty String
         */
        String cities = "";
        /**
         * create a for loop to get all the values from ArrayList<String> values
         */
        for (int i = 0; i < values.size(); i++) {
            /**
             * add the obtained values to cities
             */
            cities += values.get(i) + " ";
        }
        /**
         * Write on the output directory: (outputMessage + cities + "\n")
         */
        out.println(outputMessage + cities + "\n");
    }

    /**
     * Method writeOutData(String outputMessage, double value, PrintWriter out)
     *
     * @param outputMessage
     * @param value
     * @param out
     * @throws FileNotFoundException
     */
    public static void writeOutData(String outputMessage, double
            value, PrintWriter out) throws FileNotFoundException {
        /**
         * Write on the output directory: (outputMessage + value + "\n")
         */
        out.println(outputMessage + value + "\n");
    }

    /**
     * Method writeOutData(String outputMessage, String value, PrintWriter out)
     *
     * @param outputMessage
     * @param value
     * @param out
     * @throws FileNotFoundException
     */
    public static void writeOutData(String outputMessage, String
            value, PrintWriter out) throws FileNotFoundException {
        /**
         * Write on the output directory: (outputMessage + value + "\n")
         */
        out.println(outputMessage + value + "\n");
    }

    /**
     * Method writeOutData(String outputMessage, boolean value, PrintWriter out)
     *
     * @param outputMessage
     * @param value
     * @param out
     * @throws FileNotFoundException
     */
    public static void writeOutData(String outputMessage, boolean
            value, PrintWriter out) throws FileNotFoundException {
        /**
         * Write on the output directory: (outputMessage + value + "\n")
         */
        out.println(outputMessage + value + "\n");
    }


}

