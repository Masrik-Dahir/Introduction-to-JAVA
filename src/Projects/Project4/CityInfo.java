
/****************************************************************************
 * CityInfo
 ****************************************************************************
 * CityInfo perform some analysis on certain cities. It calculates the population density for each
 * cities, calculate average density, city with lowest density, and the distance of the city to the closed city
 *_____________________________________________________
 * Masrik Dahir
 * 10 October 2020
 * CMSC 255-C90
 * Copyright 2020, Masrik Dahir, All Right Reserved
 ****************************************************************************/
package Projects.Project4;

import java.util.Scanner;

public class CityInfo {
    public static void main(String[] args) {
        /*
         * Call processInput() method
         */
        processInput();
    }

    /*
     * Create method calcPopDensity() that returns double []
     */
    public static double[] calcPopDensity() {
        /*
         * declare and initiate double[] area, int[] population, and double[]
         * population_density
         */
        double[] area = { 188.87, 318.98, 62.57, 99.77, 88.65, 37.17, 195.36, 79.56 };
        int[] population = { 545852, 459787, 204214, 466488, 229493, 210565, 416427, 296945 };
        double[] population_density = new double[8];
        /*
         * Create a for loop to find population_density FOR(int i equals 0; i until the
         * length of population; increment i by 1)
         */
        for (int i = 0; i < population.length; i++) {
            population_density[i] = (double) (population[i] / area[i]);
        }
        /*
         * return population_density
         */
        return population_density;
    }

    /*
     * Create method calcPopAvgDensity(double[] populationDensity) that returns
     * double
     */
    public static double calcAvgPopDensity(double[] populationDensity) {
        /*
         * declare and initiate total_population_density variable to 0
         */
        double total_population_density = 0;
        /*
         * Create a for loop to find total_population_density FOR(int i equals 0; i
         * until the length of populationDensity; increment i by 1)
         */
        for (int i = 0; i < populationDensity.length; i++) {
            total_population_density += populationDensity[i];
        }
        /*
         * Return total_population_density divided by the length of populationDensity
         */
        return total_population_density / (double) populationDensity.length;
    }

    /*
     * create method getLowestPopDensity(double[] populationDensity) that returns
     * double
     */
    public static double getLowestPopDensity(double[] populationDensity) {
        /*
         * Declare and initiate variable double lowest_density to the first value of
         * populationDensity array
         */
        double lowest_density = populationDensity[0];
        /*
         * Create a for loop to compare all the values of populationDensity variable to
         * the value of lowest_density variable and set the lowest value to
         * lowest_density FOR (int i equals 0; i until the length of populationDensity;
         * increment i by 1)
         */
        for (int i = 1; i < populationDensity.length; i++) {
            if (populationDensity[i] < lowest_density) {
                lowest_density = populationDensity[i];
            }
        }
        /*
         * Return lowest_density
         */
        return lowest_density;

    }

    /*
     * Create method getCityPosition(String city) that returns int
     */
    public static int getCityPosition(String city) {
        /*
         * Declare and initiate variable String[] city_names
         */
        String[] city_names = { "Albuquerque, NM", "Kansas City, MO", "Richmond, VA", "Sacramento, CA",
                "Baton Rouge, LA", "Rochester, NY", "Colorado Springs, CO", "Cincinnati, OH" };
        /*
         * Create a for loop to compare the city String to all the elements of
         * city_names FOR(int i equals 0; i until the length of city_names; increment i
         * by 1)
         */
        for (int i = 0; i < city_names.length; i++) {
            /*
             * IF any element of city_names matched with city variable return the position
             */
            if (city_names[i].equals(city)) {
                return i;
            }

        }
        /*
         * ELSE return -1
         */
        return -1;

    }

    /*
     * Create method getDistance(int position) that returns double
     */
    public static double getDistance(int position) {
        /*
         * Declare and initiate double[] distance_miles
         */
        double[] distance_miles = { 64, 63.3, 109, 87.9, 81.2, 73.9, 70.5, 107 };
        /*
         * Return double[] distance_miles multiplied by 1.60934
         */
        return distance_miles[position] * 1.60934;

    }

    /*
     * Create method processInput() which is void
     */
    public static void processInput() {
        /*
         * Call Scanner class
         */
        Scanner input = new Scanner(System.in);
        /*
         * Declare and initiate double[] population_density to calcPopDensity()
         */
        double[] population_density = calcPopDensity();
        /*
         * Declare and initiate double average_population_density to
         * calcPopAvgDensity(population_density)
         */
        double average_population_density = calcAvgPopDensity(population_density);
        /*
         * Declare and initiate double lowest_density to
         * getLowestPopDensity(population_density)
         */
        double lowest_density = getLowestPopDensity(population_density);
        /*
         * Declate and initate int n to 0
         */
        int n = 0;
        /*
         * Declare and initiate Boolean continuous to true
         */
        boolean continuous = true;
        /*
         * WHILE continuous is true the loop continues
         */
        while (continuous) {
            if (n == 0) {
                System.out.println("Enter the city: ");
            }
            /*
             * Ask for the user for city name via input.nextLine()
             */
            String city = input.nextLine().trim();
            /*
             * calculate the position
             */
            int position = getCityPosition(city);
            /*
             * IF position is equals to -1 ask the user to enter a valid city name
             */
            if (position == -1) {
                System.out.println("Please enter a valid city name: ");
                /*
                 * Increment n so that "Enter a city: " do not get printed after printing
                 * "Please enter a valid city name: "
                 */
                n++;
                /*
                 * reassign true to continuous to ask for a city name from the user again
                 */
                continuous = true;
            }
            /*
             * ELSE position is valid and print all the variables called above:
             * getDistance(position), population_density[position],
             * average_population_density, getLowestPopDensity(population_density)
             */
            else {
                System.out.printf("%s dist of: %.2fkm and pop density of: %.2f\n", city, getDistance(position),
                        population_density[position]);
                System.out.printf("Average pop density of: %.2f\nLowest pop density of: %.2f",
                        average_population_density, getLowestPopDensity(population_density));
                System.out.println("\nWould you like to enter another city? Enter quit to exit.");
                /*
                 * Ask the user if he/she would like to "quit" via input.nextLine() If "quit"
                 * reassign false to continuous to end the loop
                 */
                if ((input.nextLine().trim()).equals("quit")) {
                    continuous = false;
                }
                /*
                 * If anything other than "quit" reassign true to continuous to continue the
                 * loop and ask for another city name
                 */
                else {
                    continuous = true;
                    /*
                     * reassign 0 to n so that the loop once again ask for "Enter the city: "
                     */
                    n = 0;

                }
            }
        }
    }

}
