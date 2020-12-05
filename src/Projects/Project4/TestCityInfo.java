
/****************************************************************************
 * CityInfo
 ****************************************************************************
 * CityInfo perform some analysis on certain cities. It calculates the population density for each
 * cities, calculate average density, city with lowest density, and the distance of the city to the closed city
 *_____________________________________________________
 * Masrik Dahir
 * 10 October 2020
 * CMSC 255-C90
 ****************************************************************************/
package Projects.Project4;

import java.io.FileDescriptor;
import java.net.InetAddress;
import java.security.Permission;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class TestCityInfo {

    //region Variable setup and helper methods
    // I use this so I can run locally and verify output
    static final boolean DEBUG = false;
    static final double DELTA = .05;

    public CityInfo unit;

    // I like to add this no matter what so if the submission loops,
    // Gradescope doesn't get stuck.
    @Rule
    public Timeout globalTimeout = Timeout.seconds(10);


    private ByteArrayOutputStream outContent;
    private ByteArrayOutputStream errContent;

    // System independent newline
    public final String newline = System.getProperty("line.separator");

    Pattern number_match_pattern = Pattern.compile("\\d+(\\.\\d*)?\\b");

    double [] distance_truth_miles = {64, 63.3, 109, 87.9, 81.2, 73.9, 70.5, 107};
    double [] distance_truth_km = new double[distance_truth_miles.length];

    String[] city_truth_table = {
            "Albuquerque, NM", "Kansas City, MO", "Richmond, VA",
            "Sacramento, CA", "Baton Rouge, LA", "Rochester, NY",
            "Colorado Springs, CO", "Cincinnati, OH"
    };

    // Makes it so can verify tests work for instructor solution.
    @Before
    public void initUnit() {
        this.unit = new CityInfo();
        // Converts the truth table into KM
        for (int i = 0; i < distance_truth_km.length; i++){
            distance_truth_km[i] = distance_truth_miles[i] * 1.60934;
        }
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

    public String getInputStringForInputsOf(String[] inputs) {
        StringBuilder sb = new StringBuilder();
        for (String i : inputs) {
            sb.append(i + newline);
        }
        return sb.toString();
    }

    public String[] getOutputForInputOf(String input, boolean getLowerCase) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        unit.main(new String[0]);
        String[] rawOutput = outContent.toString().split(newline);
        if (getLowerCase) {
            for (int i = 0; i < rawOutput.length; i++) {
                rawOutput[i] = rawOutput[i].toLowerCase();
            }
        }
        return rawOutput;
    }

    public String[] getOutputFromProcessInputForInputOf(String input, boolean getLowerCase) {
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        unit.processInput();
        String[] rawOutput = outContent.toString().split(newline);
        if (getLowerCase) {
            for (int i = 0; i < rawOutput.length; i++) {
                rawOutput[i] = rawOutput[i].toLowerCase();
            }
        }
        return rawOutput;
    }

    public String[] getOutputForInputOf(String input) {
        return getOutputForInputOf(input, false);
    }

    public double getCalculationResult(String input) throws NoSuchMatchException {
        Matcher matcher = number_match_pattern.matcher(input);
        if (!matcher.find()) {
            throw new NoSuchMatchException(String.format("\"%s\" does not contain a recognizable number!", input));
        }
        String g = matcher.group(0);
        double result = Double.parseDouble(g);
        return result;
    }

    public boolean isAlmostEqual(double a, double b, double delta) {
        return Math.abs(a - b) < delta;
    }

    public boolean isAlmostEqual(double a, double b) {
        return isAlmostEqual(a, b, DELTA);
    }

    //endregion


    @Test
    public void testQuitDoesNotUseSystemExit() {
        // Create a new Security Manager from the System-Rules-1.19.0 library from Maven.
        // The security manager will throw a CheckExitCalled exception in the case of any
        // call to System.exit()
        SecurityManager originalManager = System.getSecurityManager();
        System.setSecurityManager(new NoExitSecurityManager(originalManager));

        try {
            String input = getInputStringForInputsOf(new String[]{"Richmond, VA", "quit"});
            getOutputForInputOf(input);
        } catch (CheckExitCalled ex) {
            System.setSecurityManager(new NoExitSecurityManager(originalManager));
            fail("You are not allowed to use System.exit()!");
        } finally {
            //Reset the security manager to original manager (not necessary?)
        }
    }


    /* ***************************
     * Tests that prompts are correct pts: 1
     *************************** */
    @Test
    public void testPromptsAreCorrect() {
        String input = getInputStringForInputsOf(new String[]{"Fredericksburg, VA","Sacramento, CA","quit"});
        String[] output = getOutputForInputOf(input, true);
        assertTrue("Check your \"Enter the city\" prompt! Doesn't match what I expected",
                output[0].contains("enter") && output[0].contains("city")
        );
        assertTrue(
                "Expected \"Please enter a valid city name\" upon invalid city entry",
                output[1].contains("enter a valid city")
        );
        assertTrue(
                "Expected to be asked to continue or enter \"quit\" to exit at end",
                output[5].contains("enter another city")
        );
        assertTrue(
                "Expected to be asked to continue or enter \"quit\" to exit at end",
                output[5].contains("quit to exit")
        );

    }


    /* ************************************
     * Tests that loops work properly: 2pts
     *********************************** */
    //region Test looping

    @Test
    public void testLoopsOnNotQuit() {
        String input = getInputStringForInputsOf(new String[]{"Richmond, VA","continue","Sacramento, CA","quit"});
        String[] output = getOutputForInputOf(input, true);
        int repeatCount = 0;
        for (String line : output){
            Matcher m = number_match_pattern.matcher(line);
            if (line.contains("average") && m.find() ){
                repeatCount++;
            }
        }
        assertTrue(
                String.format("We expected to see two sets of calculations if the user chooses to loop but we saw %d",
                        repeatCount),
                repeatCount == 2
        );
    }

    @Test
    public void testQuitsOnQuitInput() {
        String input = getInputStringForInputsOf(new String[]{"Sacramento, CA","quit"});
        String[] output = getOutputForInputOf(input, true);
        int repeatCount = 0;
        for (String line : output){
            Matcher m = number_match_pattern.matcher(line);
            if (line.contains("average") && m.find() ){
                repeatCount++;
            }
        }
        assertEquals(
                String.format("We expected to see one sets of calculations if the user chooses to loop but we saw %d",
                        repeatCount), 1, repeatCount
        );
    }

    @Test
    public void testLoopsOnIncorrectCityName() {
        String input = getInputStringForInputsOf(new String[]{"Bakersfield, CA","Sacramento, CA","quit"});
        String[] output = getOutputForInputOf(input, true);
        assertTrue(output[1].contains("enter a valid city name"));
    }
    //endregion


    /* *******************************
     *
     *  Tests that all methods exist: 30pts
     *
     * ******************************** */
    //region Test Methods Exists


    @Test
    public void testProcessInputMethodExists() {
        doesMethodExist("processInput");
    }

    @Test
    public void testCalcPopDensityMethodExists() {
        doesMethodExist("calcPopDensity");

    }

    @Test
    public void testCalcAvgPopDensityMethodExists() {
        doesMethodExist("calcAvgPopDensity", double[].class);

    }

    @Test
    public void testGetLowestPopDensityMethodExists() {
        doesMethodExist("getLowestPopDensity", double[].class);
    }

    @Test
    public void testGetCityPositionMethodExists() {
        doesMethodExist("getCityPosition", String.class);
    }

    @Test
    public void testGetDistanceMethodExists() {
        doesMethodExist("getDistance", int.class);
    }


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


    /* ***************************************
     * Tests that all methods work properly: 10 pts
     *************************************** */
    //region Test Methods Work Properly
    @Test
    public void testProcessInputMethodCorrect() {
        String[] inputs = {"Kansas City, MO", "quit"};
        String inputString = getInputStringForInputsOf(inputs);
        String[] mainOutput = getOutputForInputOf(inputString, true);
        cleanUpStreams();
        setUpStreams();
        String[] processInputOutput = getOutputFromProcessInputForInputOf(inputString, true);
        assertArrayEquals(mainOutput, processInputOutput);
    }

    @Test
    public void testCalcPopDensityMethodCorrect() {
        double[] expected = {
                2890.093715253878, 1441.4289297134617, 3263.768579191306,
                4675.633958103638, 2588.7535250987025, 5664.917944578961,
                2131.5878378378375, 3732.3403720462543
        };
        double[] actual = unit.calcPopDensity();
        assertTrue("We expected the calculated popDensity to contain an entry for each state!",
                expected.length == actual.length);
        for (int i = 0; i < expected.length; i++) {
            assertTrue("Your calculation is not what we expected...", isAlmostEqual(expected[i], actual[i]));
        }


    }

    @Test
    public void testGetLowestPopDensityMethodCorrect() {
        double[] fakePopulationDensity = {102942.02, 1034.0000002193, 1039.0344829, 108673558.048, 049726484.01938};
        double response = unit.getLowestPopDensity(fakePopulationDensity);
        assertTrue(response == 1034.0000002193);
    }

    @Test
    public void testGetCityPositionMethodCorrect() {

        int random_index = new Random().nextInt(city_truth_table.length);
        int cityPos = unit.getCityPosition(city_truth_table[random_index]);
        assertTrue(
                String.format("You said that %s was in position %d. That is not correct for the array that Dr. Budwell " +
                        "provided. Make sure your array is identical and check your logic.", city_truth_table[random_index], cityPos),
                cityPos == random_index
        );
    }

    @Test
    public void testGetDistanceMethodCorrect() {
        int random_index = new Random().nextInt(distance_truth_km.length);

        double result = unit.getDistance(random_index);

        // Assert distance != correct distance in miles
        assertNotEquals(String.format("We expected the distance between %s and it's nearest neighbor to be in km, not miles!",
                city_truth_table[random_index]),
                distance_truth_miles[random_index], result, 0.01);

        // Assert distance == correct distance in km
        assertEquals(String.format("We expected the distance between %s and it's nearest neighbor to be %s km.",
                city_truth_table[random_index], distance_truth_km[random_index]), distance_truth_km[random_index], result, 0.01);
    }
    //endregion


    /* ****************************
     * Test output is rounded!
     **************************** */
    @Test
    public void testOutputIsRoundedCorrectly() {
        String input = getInputStringForInputsOf(new String[]{"Sacramento, CA","quit"});
        String[] output = getOutputForInputOf(input, true);
        try {
            // Normalizing the string...
            // Remove ',' from the string, in case they formatted the thousand's seperators ("1,000" vs "1000")...
            String avg_pop_density_line = (output[2]).replaceAll(",","");
            // Extracting the numeric component of the string
            double avg_pop_density = getCalculationResult(avg_pop_density_line);
            // Assert that the third string is actually the "average pop density" string
            assertTrue(avg_pop_density_line.contains("average") && avg_pop_density_line.contains("density"));

            assertTrue("You should be rounding to the nearest .01", isAlmostEqual(avg_pop_density, 3298.57, .01));
            try {
                String decimal = String.valueOf(avg_pop_density).split("\\.")[1]; // Get the decimal part of the regex match only
                assertTrue("You should be printing 2 (and only 2) decimal places", decimal.length() == 2);
            } catch (IndexOutOfBoundsException ex) { //  Thrown in there is no decimal place in the result
                fail("You should be printing the result to 2 decimal places, i.e., '10.00'");
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            fail("We expected there to be at least 3 lines of output!");
        }
    }
} // End of Tests class

class NoSuchMatchException extends RuntimeException {
    /**
     * A custom exception that is raised when a regex does not find an expected match.
     *
     * @param errMsg Error message
     */
    public NoSuchMatchException(String errMsg) {
        super(errMsg);
    }
}

class CheckExitCalled extends SecurityException {
    //    private static final long serialVersionUID = 159678654L;
    private final Integer status;

    public CheckExitCalled(int status) {
        super("Tried to exit with status " + status + "");
        this.status = status;
    }

    public Integer getStatus() {
        return this.status;
    }
}

class NoExitSecurityManager extends SecurityManager {
    private final SecurityManager originalSecurityManager;
    private Integer statusOfFirstExitCall = null;

    public NoExitSecurityManager(SecurityManager originalSecurityManager) {
        this.originalSecurityManager = originalSecurityManager;
    }

    public void checkExit(int status) {
        if (this.statusOfFirstExitCall == null) {
            this.statusOfFirstExitCall = status;
        }
        System.out.println("CheckExit Called!");
        throw new CheckExitCalled(status);
    }

    public boolean isCheckExitCalled() {
        return this.statusOfFirstExitCall != null;
    }

    public int getStatusOfFirstCheckExitCall() {
        if (this.isCheckExitCalled()) {
            return this.statusOfFirstExitCall;
        } else {
            throw new IllegalStateException("checkExit(int) has not been called.");
        }
    }

    public boolean getInCheck() {
        return this.originalSecurityManager != null && this.originalSecurityManager.getInCheck();
    }

    public Object getSecurityContext() {
        return this.originalSecurityManager == null ? super.getSecurityContext() : this.originalSecurityManager.getSecurityContext();
    }

    public void checkPermission(Permission perm) {
        if (perm.getName().startsWith("exitVM")){
            checkExit(Integer.parseInt(perm.getName().split("")[1]));
        }
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkPermission(perm);
        }

    }

    public void checkPermission(Permission perm, Object context) {
        if (perm.getName().startsWith("exitVM")){
            checkExit(Integer.parseInt(perm.getName().split("")[1]));
        }
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkPermission(perm, context);
        }

    }

    public void checkCreateClassLoader() {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkCreateClassLoader();
        }

    }

    public void checkAccess(Thread t) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkAccess(t);
        }

    }

    public void checkAccess(ThreadGroup g) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkAccess(g);
        }

    }

    public void checkExec(String cmd) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkExec(cmd);
        }

    }

    public void checkLink(String lib) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkLink(lib);
        }

    }

    public void checkRead(FileDescriptor fd) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkRead(fd);
        }

    }

    public void checkRead(String file) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkRead(file);
        }

    }

    public void checkRead(String file, Object context) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkRead(file, context);
        }

    }

    public void checkWrite(FileDescriptor fd) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkWrite(fd);
        }

    }

    public void checkWrite(String file) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkWrite(file);
        }

    }

    public void checkDelete(String file) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkDelete(file);
        }

    }

    public void checkConnect(String host, int port) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkConnect(host, port);
        }

    }

    public void checkConnect(String host, int port, Object context) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkConnect(host, port, context);
        }

    }

    public void checkListen(int port) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkListen(port);
        }

    }

    public void checkAccept(String host, int port) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkAccept(host, port);
        }

    }

    public void checkMulticast(InetAddress maddr) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkMulticast(maddr);
        }

    }

    public void checkMulticast(InetAddress maddr, byte ttl) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkMulticast(maddr, ttl);
        }

    }

    public void checkPropertiesAccess() {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkPropertiesAccess();
        }

    }

    public void checkPropertyAccess(String key) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkPropertyAccess(key);
        }

    }

    public boolean checkTopLevelWindow(Object window) {
        return this.originalSecurityManager == null ? super.checkTopLevelWindow(window) : this.originalSecurityManager.checkTopLevelWindow(window);
    }

    public void checkPrintJobAccess() {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkPrintJobAccess();
        }

    }

    public void checkSystemClipboardAccess() {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkSystemClipboardAccess();
        }

    }

    public void checkAwtEventQueueAccess() {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkAwtEventQueueAccess();
        }

    }

    public void checkPackageAccess(String pkg) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkPackageAccess(pkg);
        }

    }

    public void checkPackageDefinition(String pkg) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkPackageDefinition(pkg);
        }

    }

    public void checkSetFactory() {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkSetFactory();
        }

    }

    public void checkMemberAccess(Class<?> clazz, int which) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkMemberAccess(clazz, which);
        }

    }

    public void checkSecurityAccess(String target) {
        if (this.originalSecurityManager != null) {
            this.originalSecurityManager.checkSecurityAccess(target);
        }

    }

    public ThreadGroup getThreadGroup() {
        return this.originalSecurityManager == null ? super.getThreadGroup() : this.originalSecurityManager.getThreadGroup();
    }
}