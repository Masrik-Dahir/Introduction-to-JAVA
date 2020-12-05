/****************************************************************************
 * ScoopsAhoy
 ****************************************************************************
 * This program help shopkeepers to analyze the compatibility of mixing  different flavors of ice cream
 *_____________________________________________________
 * Masrik Dahir
 * 10/22/2020
 * CMSC 255-C90
 ****************************************************************************/
package Projects.Project5;
import java.util.Arrays;
import java.util.Scanner;

public class ScoopsAhoy {
    public static void main(String[] args) {
        /**
         * Declaring and initializing int variable choice
         */
        int choice;
        /**
         * calling Scanner class with input variable
         */
        Scanner input = new Scanner(System.in);
        /**
         * creating String[] variable flavors that would get the String array from getFLowers variable
         */
        String[] flavors = getFlavors(args[0]);
        /**
         * creating double[] variable scores that would get the double array from getScore variable
         */
        double[][] scores = getScores(args[1]);
        System.out.println(
                "Type 1 to search for flavors that are most compatible and 2 to search for flavors that are least compatible: ");
        /**
         * Asking the user to input 1 or 2
         */
        choice = input.nextInt();
        /**
         * declaring booklean above variable
         */
        boolean above;
        /**
         * initializing the value of above according to choice
         * IF(choice is equal to 1) true
         * IF(choice  is equal to 2) false
         */
        if (choice == 1) {
            above = true;
        } else {
            above = false;
        }
        System.out.println("Enter the flavor: ");
        /**
         * Asking the user for a flavor name
         */
        String flavor = input.next().trim();
        /**
         * printing out the matched flavor
         */
        System.out.println(Arrays.toString(searchCompatibility(scores, flavors, above, flavor)));

    }

    /**
     *
     * @param inputFlavorString
     * @return flavors
     */
    public static String[] getFlavors(String inputFlavorString) {
        /**
         * splitting the Strings by ","
         * assigning the values to flavors
         */
        String[] flavors = inputFlavorString.split(",");

        return flavors;
    }

    /**
     *
     * @param inputScoresString
     * @return scores
     */
    public static double[][] getScores(String inputScoresString) {
        /**
         * splitting the Strings by "<>" to get the columns
         */
        String[] column = inputScoresString.split("<>");
        /**
         * getting the column number
         */
        int num_column = column.length;
        /**
         * getting the row number
         */
        int num_row = column[0].split(",").length;
        /**
         * declaring and setting length of String[][] score
         */
        String[][] score = new String[num_column][num_row];
        /**
         * declaring and setting length of double[][] scores
         */
        double[][] scores = new double[num_column][num_row];
        /**
         * splitting the Strings by "," to get the rows using a nested for loop
         */
        for (int i = 0; i < score.length; i++) {
            for (int b = 0; b < score[i].length; b++) {
                score[i] = column[i].split(",");
            }
        }
        /**
         * Using another nested for loop to transform the String[] score to Double[] scores
         */
        for (int i = 0; i < score.length; i++) {
            for (int b = 0; b < score[i].length; b++) {
                scores[i][b] = Double.parseDouble(score[i][b]);
            }
        }
        return scores;
    }

    /**
     *
     * @param scores
     * @param flavors
     * @param above
     * @param flavor
     * @return result_no_null
     */
    public static String[] searchCompatibility(double[][] scores, String[] flavors, boolean above, String flavor) {
        /**
         * Declare and set length (to as same as flavors) the String[] result variable
         */
        String[] result = new String[flavors.length];
        /**
         * declare and initialize the boolean check variable
         */
        boolean check = false;
        /**
         * declare and initiate the int variable element
         */
        int elements = 0;
        /**
         * Using a for loop to explore through the flavors String[]
         */
        for (int i = 0; i < flavors.length; i++) {
            /**
             * using the STRING.equals() method to compare the user inputted flavor to the elements of the flavors array
             */
            if (flavors[i].equals(flavor)) {
                /**
                 * Using a nested for loop to find the each element of flavors array
                 */
                for (int j = 0; j < scores.length; j++) {
                    for (int b = 0; b < scores[j].length; b++) {
                        /**
                         * IF(scores < 1 and >= 0.75 and above is true and j is equals to the index) add the element to result array
                         * element++
                         */
                        if (j == i && above == true && scores[j][b] < 1 && scores[j][b] >= 0.75) {
                            result[elements] = flavors[b];
                            elements++;
                        }
                        /**
                         * IF(scores <= .5 and above is true and j is equals to the index) add the element to result array
                         * element++
                         */
                        if (j == i && above == false && scores[j][b] <= .5) {
                            result[elements] = flavors[b];
                            elements++;
                        }
                    }
                }
            }
        }
        /**
         * declare and setting length (to elements) of String[] result_no_null
         */
        String[] result_no_null = new String[elements];
        /**
         * using a for loop to copy the non null values from result to result_no_null
         */
        for (int a = 0; a<elements;a++) {
            result_no_null[a] = result [a];
        }
        /**
         * Using Arrays.sort to sort of the elements of result_no_null
         */
        Arrays.sort(result_no_null);
        return result_no_null;


    }

}
