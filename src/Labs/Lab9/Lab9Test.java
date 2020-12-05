/****************************************************************************
 * Copyright 2020, Masrik Dahir, All Right Reserved
 ****************************************************************************/
package Labs.Lab9;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

public class Lab9Test {

    @Test
    public void testIsEmpty() {
        int[][] actual = {};
        int[][] expected = {};
        Lab9.addTo10(actual);
        assertArrayEquals("Incorrect result", expected, actual);
    }

    @Test
    public void testOneRow() {
        int[][] actual = { { 1, 3, 0, 1 } };
        int[][] expected = { { 1, 3, 5, 1 } };
        Lab9.addTo10(actual);
        assertArrayEquals("Incorrect result", expected, actual);
    }

    @Test
    public void testSeveralRows1() {
        int[][] actual = { { 0, 1, 2, 3, 4 }, { 1, 1, 0, 1, 1 }, { 5, 3, 1, 2, 0 } };
        int[][] expected = { { 0, 1, 2, 3, 4 }, { 1, 1, 6, 1, 1 }, { 5, 3, 1, 2, -1 } };
        Lab9.addTo10(actual);
        assertArrayEquals("Incorrect result", expected, actual);
    }

    @Test
    public void testSeveralRows2() {
        int[][] actual = { { 0, 1 }, { 9, 0 }, { 0, 19 }, { -1, 0 } };
        int[][] expected = { { 9, 1 }, { 9, 1 }, { -9, 19 }, { -1, 11 } };
        Lab9.addTo10(actual);
        assertArrayEquals("Incorrect result", expected, actual);
    }

    @Test
    public void testSeveralRowsOfVariousLengths() {
        // Hint: Use the number of elements on each row independently!
        int[][] actual = { { 0, 1, 1, 1 }, { 2, 0 }, { 3, 3, 0, 3, 3 } };
        int[][] expected = { { 7, 1, 1, 1 }, { 2, 8 }, { 3, 3, -2, 3, 3 } };
        Lab9.addTo10(actual);
        assertArrayEquals("Incorrect result", expected, actual);
    }

    @Test
    public void testFindAverageGivenExample1() {
        double[][] actual = {{  5, 4.5,  6.8}, {  6,  0,  3.4}, { 7,  8.4,  2.3}};
        double[][] expected = { {  5, 4.5,  6.8}, {  6,  4.3,  3.4}, { 7,  8.4,  2.3} };
        Lab9.findAverage(actual);
        assertArrayEquals("Incorrect result", expected, actual);
    }

    @Test
    public void testFindAverageGivenExample2() {
        double[][] array = { { 6.7,  23.8,  0,  5.9,  12.8,  45.7},
                {  0,  36.8,  13.5,  6.7,  54.9,  67.4},
                {  37.4,  2.5,  39.8,  0,  2.4,  43.6},
                {  44,6,  76.5,  4.5, 2.4,  0, 54.6},
                {  5.4,  76.3,  6.5, 28.5,  54.7,  0},
                {  19.4,  0,  5.3,  65.4,  93.5,  3.5} };
        double[][] expected = { { 6.7,  23.8,  23.600000000000005,  5.9,  12.8,  45.7},
                {  29.883333333333336,  36.8,  13.5,  6.7,  54.9,  67.4},
                {  37.4,  2.5,  39.8,  20.95,  2.4,  43.6},
                {  44,6,  76.5,  4.5, 2.4,  26.857142857142858, 54.6},
                {  5.4,  76.3,  6.5, 28.5,  54.7,  31.17619047619048},
                {  19.4,  31.183333333333337,  5.3,  65.4,  93.5,  3.5} };
        Lab9.findAverage(array);
        assertArrayEquals("Incorrect result", expected, array);
    }

    @Test
    public void testFindAverageOneRow() {
        double[][] array = { { 6.7,  23.8,  0,  5.9,  12.8,  45.7} };
        double[][] expected = { { 6.7,  23.8,  15.816666666666668,  5.9,  12.8,  45.7} };
        Lab9.findAverage(array);
        assertArrayEquals("Incorrect result", expected, array);
    }

    @Test
    public void testFindAverageOneCol() {
        double[][] array = { { 6.7}, {2.8}, {4.5}, {0}, {5.7}, {15.9} };
        double[][] expected = { { 6.7}, {2.8}, {4.5}, {5.933333333333334}, {5.7}, {15.9} };
        Lab9.findAverage(array);
        assertArrayEquals("Incorrect result", expected, array);
    }
}
