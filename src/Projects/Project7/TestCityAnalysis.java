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

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import static org.junit.Assert.*;

public class TestCityAnalysis {

    private final String newLine = System.lineSeparator();
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    private ByteArrayOutputStream outStream;

    private void writeLinesToFile(String[] lines, File f) {
        try {
            PrintWriter pw = new PrintWriter(f);
            for (String line : lines) {
                pw.write(String.format("%s%s", line, newLine));
            }
            pw.close();
        } catch (FileNotFoundException e) {
            fail("Something weird happened, please reach out to a TA.");
        }
    }

    private void assertContains(String message, String expected, String actual) {
        assertTrue(String.format("%s: \n Expected: %s\n Actual: %s", message, expected, actual),
                actual.contains(expected));
    }

    private String normalizeNewlines(String actual) {
        return actual.replace("\r\n", "\n").replace("\r", "\n");
    }

    @Before
    public void setUpStreams() {
        outStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outStream));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
    }

    // assert readFile(File) throws FileNotFoundException for no such file
    @Test(expected = FileNotFoundException.class)
    public void testReadFile_throwsFileNotFound_ifInvalidFile() throws FileNotFoundException {
        File f = new File("ThisIsInvalidFilePath");

        CityAnalysis.readFile(f);
        fail("We expected readFile() to throw FileNotFoundException if passed an invalid " +
                "file!");
    }


    // assert readFile(File) returns list of lines in file
    @Test
    public void testReadFile_returnsListOfLinesInFile() throws IOException {
        String[] lines = {"This is line 1", "This is line 2"};
        File f = tempFolder.newFile();
        writeLinesToFile(lines, f);

        List<String> actual = CityAnalysis.readFile(f);
        assertEquals("When we write 2 lines to the file, we expect the number of lines returned: ",
                2, actual.size()
        );
        assertEquals("When reading from the file we expect the first line to be ", lines[0],
                actual.get(0));
        assertEquals("When reading from the file we expect the second line to be ", lines[1],
                actual.get(1));
    }


    // assert parseData(ArrayList<String>) replaces non-int & negative city area with 0
    @Test
    public void testParseData_replacesNonNumericCityAreaWith0() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Albuquerque, NM\tone Hundred\t545852\t64");
        ArrayList<City> cities = CityAnalysis.parseData(lines);

        assertEquals("When we parse a city area of 'one hundred' we expect ",
                0, cities.get(0).getArea(),
                .001);

    }

    @Test
    public void testParseData_replacesNegativeCityAreaWith0() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Kansas City, MO\t-318.98\t459787\t63.3");
        ArrayList<City> cities = CityAnalysis.parseData(lines);
        assertEquals("When we parse a city area of '-318.98' we expect ",
                0, cities.get(0).getArea(),
                .001);
    }

    // assert parseData(ArrayList<String>) replaces non-int with 0
    @Test
    public void testParseData_replacesNonNumericPopWith0() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Richmond City, VA\t319.98\tOne bajillion\t63.5");
        ArrayList<City> cities = CityAnalysis.parseData(lines);
        assertEquals("When we parse a city population of 'One bajillion', we expect ",
                0, cities.get(0).getPopulation());
    }

    @Test
    public void testParseData_replacesNegativeCityPopWith0() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Richmond City, VA\t319.98\t-100\t63.5");
        ArrayList<City> cities = CityAnalysis.parseData(lines);
        assertEquals("When we parse a city population of -100, we expect ",
                0, cities.get(0).getPopulation());

    }

    // assert parseData(ArrayList<String>) replaces non-int & negative city distance with 0
    @Test
    public void testParseData_replacesNonNumericCityDistanceWith0() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Richmond City, VA\t319.98\t204317\tone hundred miles");
        ArrayList<City> cities = CityAnalysis.parseData(lines);
        assertEquals("When we parse a city distance of 'one hundred miles', we expect ",
                0, cities.get(0).getDistance(), 0.01);
    }

    @Test
    public void testParseData_replacesNegativeCityDistanceWith0() {
        ArrayList<String> lines = new ArrayList<>();
        lines.add("Richmond City, VA\t319.98\t204317\t-103");
        ArrayList<City> cities = CityAnalysis.parseData(lines);
        assertEquals("When we parse a city distance of -103, we expect ",
                0, cities.get(0).getDistance(), 0.01);
    }

    // assert calcPopulationAverage(ArrayList<City>) calculates average population for all cities
    @Test
    public void testCalcPopulationAverage_calculatesAvgPopulationForCities() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City());
        cities.add(new City());
        cities.add(new City());
        cities.get(0).setPopulation(105300);
        cities.get(1).setPopulation(13928);
        cities.get(2).setPopulation(682548);

        double actual = CityAnalysis.calcPopulationAverage(cities);
        assertEquals("When we calculate the averagePopulation we expect ", 267258.666, actual,
                .01);
    }

    // assert calcAreaAverage(ArrayList<City>) calculates average area for all cities
    @Test
    public void testCalcAreaAverage_calculatesAvgAreaForCities() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City());
        cities.add(new City());
        cities.add(new City());
        cities.get(0).setArea(103302.30);
        cities.get(1).setArea(2048672.50);
        cities.get(2).setArea(2094254.35);

        double actual = CityAnalysis.calcAreaAverage(cities);
        assertEquals("When we calculate the averageArea we expect ", 1415409.7166, actual,
                .01);
    }

    // assert calcPopulationAboveAverage(ArrayList<City>, double) returns list of cities w/
    // population above average
    @Test
    public void testCalcPopAboveAverage_returnsCitiesWithAboveAvgPopulation() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City("CityName_75", 0, 75, 0));
        cities.add(new City("CityName_100", 0, 100, 0));
        cities.add(new City("CityName_150", 0, 150, 0));
        cities.add(new City("CityName_175", 0, 175, 0));
        cities.add(new City("CityName_125", 0, 125, 0));
        ArrayList<String> actual = CityAnalysis.calcPopulationAboveAverage(cities, 125);
        assertEquals("When we call calcPopulationAboveAverage with 2 cities above average we " +
                        "expect only 2 cities returned but we got",
                2, actual.size());
        assertTrue(String.format("When we call calcPopulationAboveAverage we expect %s to be in " +
                        "the returned list %s", "CityName_150", actual.toString())
                , actual.contains("CityName_150")
        );
        assertTrue(String.format("When we call calcPopulationAboveAverage we expect %s to be in " +
                        "the returned list %s", "CityName_175", actual.toString())
                , actual.contains("CityName_175")
        );
        assertFalse(String.format("When we call calcPopulationAboveAverage we expect only cities " +
                        "that are above average, not cities that are exactly average. %s is not " +
                        "expected to be returned in the list %s", "CityName_125", actual.toString())
                , actual.contains("CityName_125")
        );

    }

    // assert findLargestDistance(ArrayList<City>) returns the name of the city with the largest
    // distance
    @Test
    public void testFindLargestDistance_returnsNameOfCity_withLargestDistance() {
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(new City("CityName_75", 0, 0, 75));
        cities.add(new City("CityName_100", 0, 0, 100));
        cities.add(new City("CityName_150", 0, 0, 150));
        cities.add(new City("CityName_175", 0, 0, 175));
        cities.add(new City("CityName_125", 0, 0, 125));

        String actual = CityAnalysis.findLargestDistance(cities);
        String expected = "CityName_175";
        assertEquals(String.format(
                "When we call findLargestDistance with %s we expect ", cities),
                expected, actual);
    }

    // assert findCity(ArrayList<City>, City) returns true if city is in list
    @Test
    public void testFindCity_returnsTrue_ifCityInList() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City("Richmond, VA", 0, 0, 0));
        cities.add(new City("Washington D.C.", 0, 0, 0));
        cities.add(new City("New York City, NY", 0, 0, 0));
        cities.add(new City("Mayberry, NC", 0, 0, 0));

        City mayberryV2 = new City("Mayberry, NC", 1, 2, 3);
        assertTrue(CityAnalysis.findCity(cities, mayberryV2));
    }

    // assert findCity(ArrayList<City, City) returns false if city is not in list
    @Test
    public void testFindCity_returnsFalse_ifCityNotInList() {
        ArrayList<City> cities = new ArrayList<>();
        cities.add(new City("Richmond, VA", 0, 0, 0));
        cities.add(new City("Washington D.C.", 0, 0, 0));
        cities.add(new City("New York City, NY", 0, 0, 0));
        cities.add(new City("Mayberry, NC", 0, 0, 0));

        City mayberryV2 = new City("Reston, VA", 0, 0, 0);
        assertFalse(CityAnalysis.findCity(cities, mayberryV2));
    }

    // assert writeOutData (String, ArrayList<String>, PrintWriter) correctly formatas and prints
    //TODO Should I check to make sure writeOutData is printing out two newlines?
    @Test
    public void testWriteOutData_withStringList_printsData() {
        OutputStream out = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(out);
        ArrayList<String> values = new ArrayList<>();
        values.add("Richmond, VA");
        values.add("Mayberry, NC");
        try {
            CityAnalysis.writeOutData("The cities above the average population are: ", values, pw);
        } catch (FileNotFoundException ex) {
            fail("Something weird happened. Please contact a TA.");
        } finally {
            pw.close();
        }
        String actual = out.toString();
        String expected = "The cities above the average population are: Richmond, VA Mayberry, " +
                "NC\n\n";
        Pattern r_expected = Pattern.compile("The cities above the average population are: " +
                "Richmond, VA Mayberry, NC *(\r\n|\r|\n){2}");
        assertTrue(String.format("When we call writeOutData with a list of strings we expect %s " +
                        "but get %s", expected, actual),
                r_expected.matcher(actual).matches());
    }


    // assert writeOutData (String, double, PrintWriter) correctly formats and prints
    // TODO Should it have two newlines at the end?
    @Test
    public void testWriteOutData_withDouble_printsData() {
        OutputStream out = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(out);

        try {
            CityAnalysis.writeOutData("The average area is: ", 129039.39, pw);
        } catch (FileNotFoundException ex) {
            fail("Something weird happened. Please contact a TA.");
        } finally {
            pw.close();
        }
        String actual = normalizeNewlines(out.toString());
        String expected = "The average area is: 129039.39\n\n";
        assertEquals("When we call writeOutData with a double we expect ", expected.trim(),
                actual.trim());
        assertEquals("Check your whitespace, your text looks correct but your spaces/newlines " +
                "don't match expected... ", expected, actual);
    }

    // assert writeOutData (String, String, PrintWriter) correctly formats and prints
    @Test
    public void testWriteOutData_withStringMessage_printsData() {
        OutputStream out = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(out);

        try {
            CityAnalysis.writeOutData("The largest distance is: ", "Las Cruces, NM", pw);
        } catch (FileNotFoundException ex) {
            fail("Something weird happened. Please contact a TA.");
        } finally {
            pw.close();
        }
        String actual = normalizeNewlines(out.toString());
        actual = normalizeNewlines(actual);
        String expected = "The largest distance is: Las Cruces, NM\n\n";
        assertEquals("When we call writeOutData with a string we expect ", expected.trim(),
                actual.trim());
        assertEquals("Check your whitespace, your text looks correct but your spaces/newlines " +
                "don't match expected... ", expected, actual);
    }


    // assert writeOutData (String, boolean, PrintWriter) correctly formats and prints
    @Test
    public void testWriteOutData_withBooleanMessage_printsData() {
        OutputStream out = new ByteArrayOutputStream();
        PrintWriter pw = new PrintWriter(out);

        try {
            CityAnalysis.writeOutData("Is Hawkins, IN in the data?: ", false, pw);
        } catch (FileNotFoundException ex) {
            fail("Something weird happened. Please contact a TA.");
        } finally {
            pw.close();
        }
        String actual = normalizeNewlines(out.toString());
        String expected = "Is Hawkins, IN in the data?: false\n\n";
        assertEquals("When we call writeOutData with a string we expect ", expected.trim(),
                actual.trim());
        assertEquals("Check your whitespace, your text looks correct but your spaces/newlines " +
                "don't match expected... ", expected, actual);
    }


}
