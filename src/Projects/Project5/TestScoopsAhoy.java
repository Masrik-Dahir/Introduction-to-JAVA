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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestScoopsAhoy {

    //region Variable setup and helper methods

    // I use this so I can run locally and verify output
    static final boolean DEBUG = false;
    static final double DELTA = .05;

    public ScoopsAhoy unit;

    // I like to add this no matter what so if the submission loops,
    // Gradescope doesn't get stuck.

//    @Rule
//    public Timeout globalTimeout = Timeout.seconds(10);


    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    // System independent newline
    public final String newline = System.getProperty("line.separator");


    @Before
    public void initUnit() {
        this.unit = new ScoopsAhoy();
    }

    @Before
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        errContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }
    //endregion


    /* *******************************
     *
     *  Tests that all methods work: 0pts
     *  Helper tests for students' debugging
     *
     * ******************************** */

    //region Test that methods exist

    @Test
    public void testGetFlavorsMethodExists() {
        doesMethodExist("getFlavors", String.class);
    }


    @Test
    public void testGetScoresMethodExists() {
        doesMethodExist("getScores", String.class);

    }


    @Test
    public void testsearchCompatibilityMethodExists() {
        doesMethodExist("searchCompatibility", double[][].class, String[].class, boolean.class, String.class);

    }

    //endregion

    /* *******************************
     *
     *  Tests that all methods work: 20pts
     *
     * ******************************** */
    //region Test Methods work



    @Test
    public void testGetFlavorsMethodWorks() {
        String FLAVORS = "Coffee,Vanilla,MintChip";
        assertTrue(Arrays.deepEquals(unit.getFlavors(FLAVORS), FLAVORS.split(",")));
    }


    @Test
    
    public void testGetScoresMethodWorks() {
        String compatibility_string =   "1.0,.9,.25<>" +
                                        ".9,1.0,.7<>" +
                                        ".25,.7,1.0";
        double[][] actual = unit.getScores(compatibility_string);
        double[][] expected = {{1.0,.9,.25},{.9,1.0,.7},{.25,.7,1.0}};
        assertTrue(
                String.format("When calling getScores with %s, we expected %s but got %s",
                        compatibility_string, Arrays.deepToString(expected), Arrays.deepToString(actual)),
                Arrays.deepEquals(actual, expected));
    }


    //endregion


    /* *******************************
     *
     *  Tests that all methods work: 30pts
     *
     * ******************************** */
    //region Test Output is correct

    @Test
    public void testSearchCompatibilityGeneratesEmptyArrayForInvalidFlavor(){
        String[] flavors = new String[]{"coffee","vanilla","mint_chip"};
        String flavor = "cookies_and_cream";
        double[][] expectedScores = {{1.0,.9,.25},{.9,1.0,.7},{.25,.7,1.0}};

        String[] actual = unit.searchCompatibility(expectedScores, flavors, true, flavor);
        String[] expected = new String[0]; // empty string array
        assertTrue(
                String.format("We expected %s when we searched for flavor %s, but we got %s",
                        Arrays.deepToString(expected),
                        flavor,
                        Arrays.deepToString(actual)),
                Arrays.equals(expected, actual));
    }

    @Test
    public void testSearchCompatibilityReturnsArrayThatFitsLikeAGlove(){
        String[] flavors = {"vanilla", "chocolate", "strawberry", "coffee"};
        double[][] scores = {
                {1, .75, .6, .9},   // Vanilla row
                {.75, 1, .5, .8},   // Chocolate row
                {.6, .5, 1, .3},    // Strawberry row
                {.9, .8, .3, 1}     // Coffee row
        };

        String flavor = "chocolate";
        List<String> actual = Arrays.asList(unit.searchCompatibility(scores, flavors, true, flavor));
        int expectedSize = 2;   // The result of searchCompatibility should return an array that is resized to only contain
        // the matching elements

        assertEquals(
                "We expect that the result should ONLY have the same number of elements as matches!",
                actual.size(), expectedSize
        );
    }


    @Test
    public void testSearchCompatibilityForGoodMatchesIncludesFlavorsWithScoresAbovePoint75(){
        String[] flavors = {"vanilla", "chocolate", "strawberry", "coffee"};
        double[][] scores = {
                {1, .75, .6, .9},
                {.75, 1, .5, .8},
                {.6, .5, 1, .3},
                {.9, .8, .3, 1}
        };
        String flavor = "vanilla";
        String[] actual = unit.searchCompatibility(scores, flavors, true, flavor);
        String[] expected = {"chocolate", "coffee"};
        assertTrue(Arrays.asList(actual).containsAll(Arrays.asList(expected)));
    }

    @Test
    public void testSearchCompatibilityForGoodMatchesDoesNotReturnFlavorsWithScoresOf1(){
        String[] flavors = {"vanilla", "chocolate", "strawberry", "coffee"};
        double[][] scores = {
                {1, .75, .6, .9},   // Vanilla row
                {.75, 1, .5, .8},   // Chocolate row
                {.6, .5, 1, .3},    // Strawberry row
                {.9, .8, .3, 1}     // Coffee row
        };
        String flavor = "vanilla";
        String[] actual = unit.searchCompatibility(scores, flavors, true, flavor);
        assertFalse(Arrays.asList(actual).contains("vanilla"));
    }


    @Test
    public void testSearchCompatibilityForBadMatchesReturnsFlavorsWithScoresBelowPoint5(){
        String[] flavors = {"vanilla", "chocolate", "strawberry", "coffee"};
        double[][] scores = {
                {1, .75, .6, .9},   // Vanilla row
                {.75, 1, .5, .8},   // Chocolate row
                {.6, .5, 1, .3},    // Strawberry row
                {.9, .8, .3, 1}     // Coffee row
        };
        String flavor = "strawberry";
        List<String> actual = Arrays.asList(unit.searchCompatibility(scores, flavors, false, flavor));
        List<String> expected = Arrays.asList("chocolate", "coffee");

        // We assert that the result SHOULD include the bad flavors: `expected`
        assertTrue(
                String.format("When searching for bad matches for '%s' we expected %s but got %s", flavor, expected, actual),
                actual.containsAll(expected) );

        // We assert that the result SHOULD NOT include the good flavors
        for (String f : new String[]{"vanilla", "strawberry"}){
            assertFalse(
                    String.format("When searching for bad matches for '%s' we did not expect %s to be included!", flavors, f),
                    actual.contains(f)
            );
        }
    }

    //endregion


    // Checks to see if a method with a given name exists. Method must accepts an arguments
    // specified in argTypes, specified by type of argument, not the name of the argument var
    public void doesMethodExist(String methodName, Class<?>... argTypes) {
        try {
            Method declaredMethods = unit.getClass().getMethod(methodName, argTypes);
        } catch (NoSuchMethodException ex) {
            fail(String.format(
                    "We could not find a method \"%s\". Check your spelling and the arguments it accepts.",
                    methodName
            ));
        }
    }
    //endregion

} // End of Tests class