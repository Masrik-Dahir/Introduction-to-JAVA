package Labs.Lab9;

import java.util.Arrays;

public class Lab9 {

    public static void main(String[] args) {
        int [][] addTo10Input = {
                {  6,  3,  2,  0,  4},
                { 34, 53,  0, 23,  1},
                {  0, 23, 54, 11,  7}
        };

        double [][] findAvgInput = {
                {  5, 4.5,  6.8},
                {  6,  0,  3.4},
                { 7,  8.4,  2.3}
        };

        double [][] findAvgInputLarge = {
                { 6.7,  23.8,  0,  5.9,  12.8,  45.7},
                {  0,  36.8,  13.5,  6.7,  54.9,  67.4},
                {  37.4,  2.5,  39.8,  0,  2.4,  43.6},
                {  44.6,  76.5,  4.5, 2.4,  0, 54.6},
                {  5.4,  76.3,  6.5, 28.5,  54.7,  0},
                {  19.4,  0,  5.3,  65.4,  93.5,  3.5}
        };

        addTo10 (addTo10Input);
        System.out.println();
        findAverage (findAvgInput);
        System.out.println();
        findAverage (findAvgInputLarge);
        }

    public static void addTo10(int[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                sum += array[i][j];

            }
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    array[i][j] = 10 - sum;
                    sum = 0;
                }

            }
        }

    }

    public static void findAverage (double [][] array) {
        int size = array[0].length;
        double col[] = new double[size];
        double row[] = new double[size];
        double highest[] = new double[size];

        double sum_row = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                sum_row += array[i][j];
                //CB - You cannot add up the column like this
                col[j] += array[i][j];
            }
            row[i]=sum_row/size;
            sum_row =0;
        }
        for (int i = 0; i < col.length; i++) {
            col[i] = col[i]/size;
        }

        //System.out.println(Arrays.toString(col));
        //System.out.println(Arrays.toString(row));


        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == 0) {
                    if(row[i]<=col[i]) {
                        array[i][j] = col[i];
                    }
                    else {
                        array[i][j] = row[i];
                    }
                }
            }
        }
    }


}