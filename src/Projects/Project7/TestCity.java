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

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static org.junit.Assert.*;

public class TestCity {
    private static final double EQ_DELTA = .01;

    @Test
    public void testInstanceVarsExistAndArePrivate() {
        City city = new City();
        String[] priv_vars = {"name", "area", "population", "distance"};
        for (String var : priv_vars) {
            assertInstanceVariablePrivate(var, city);
            assertInstanceVariableNotStatic(var, city);
        }
    }

    // Test Default Constructor


    @Test
    public void testDefaultConstructorIsCorrect() {
        City city = new City();
        assertInstanceVariableIsExpected("name", city, null, "When we use the default constructor" +
                " we expect the name to be `null`");
        assertInstanceVariableIsExpected("area", city, 0d, "When we use the default constructor " +
                "we" +
                " expect the area to be 0");
        assertInstanceVariableIsExpected("population", city, 0, "When we use the default " +
                "constructor we" +
                " expect the population to be 0");
        assertInstanceVariableIsExpected("distance", city, 0d, "When we use the default " +
                "constructor we" +
                " expect the distance to be 0");
    }

    // Test Parameterized Constructor
    @Test
    public void testParameterizedConstructorIsCorrect() {
        City city = new City("Goochland", 235.032, 54928, 4018.30);
        assertInstanceVariableIsExpected("name", city, "Goochland", "When we use the " +
                "parameterized constructor we ");
        assertInstanceVariableIsExpected("area", city, 235.032, "When we use the " +
                "parameterized constructor we ");
        assertInstanceVariableIsExpected("population", city, 54928, "When we use the " +
                "parameterized constructor we ");
        assertInstanceVariableIsExpected("distance", city, 4018.30, "When we use the " +
                "parameterized constructor we ");
    }

    //Test setName(String) sets
    @Test
    public void testSetNameSetsValue() {
        City city = createCity("Richmond", 20, 20000, 3673.25);
        city.setName("Richmond, VA");
        assertEquals("When we call setName() we ",
                "Richmond, VA", city.getName());
    }

    // Test setArea(double) sets
    @Test
    public void testSetAreaSetsValue() {
        City city = createCity("Richmond", 20, 20000, 3673.25);
        city.setArea(593.02);
        assertEquals("When we call setArea() we expect the area to be updated!",
                593.02, city.getArea(), EQ_DELTA);
    }

    // Test setPopulation(int)
    @Test
    public void testSetPopulationSetsValue() {
        City city = createCity("Richmond", 20, 20000, 3673.25);
        city.setPopulation(983);
        assertEquals("When we call setPopulation() we expect the population to be updated!",
                983, city.getPopulation(), EQ_DELTA);
    }

    // Test setDistance(double)
    @Test
    public void testSetDistanceSetsValue() {
        City city = createCity("Richmond", 20, 20000, 3673.25);
        city.setDistance(200.5);
        assertEquals("When we call setDistance() we expect the distance to be updated!",
                200.5, city.getDistance(), EQ_DELTA);
    }

    // Test getName(): String
    @Test
    public void testGetNameReturnsValue() {
        City city = createCity("Richmond", 20, 20000, 3673.25);
        assertEquals("When we called city.getName() we ", "Richmond", city.getName());
    }


    // Test getArea(): double
    @Test
    public void testGetAreaReturnsValue() {
        City city = createCity("Richmond", 20, 20000, 3673.25);
        assertEquals("When we called city.getArea() we ", 20, city.getArea(), EQ_DELTA);
    }

    // Test getPopulation(): double
    @Test
    public void testGetPopulationReturnsValue() {
        City city = createCity("Richmond", 20, 20000, 3673.25);
        assertEquals("When we called city.getPopulation() we ", 20000, city.getPopulation());
    }

    // Test getDistance(): Double
    @Test
    public void testGetDistanceReturnsValue() {
        City city = createCity("Richmond", 20, 20000, 3673.25);
        assertEquals("When we called city.getDistance we ", 3673.25, city.getDistance(), EQ_DELTA);
    }

    // Test toString(): String
    @Test
    public void testToStringReturnsExpectedValues() {
        City city = createCity("Richmond", 1234.0, 506932, 3673.25);
        String[] expected_tok = "Richmond 1234.0 506932 3673.25".split(" ");
        String actual = city.toString();
        for (String each : expected_tok) {
            assertTrue(String.format(
                    "When we called toString, we expected the returned value to contain %s but we" +
                            " got %s", each, actual
            ), actual.contains(each));
        }
    }

    // Test equals(): boolean
    @Test
    public void testTwoObjectsWithSameNameAreEqual() {
        City city1 = createCity("Richmond", 1, 2, 3);
        City city2 = createCity("Richmond", 309, 203, 3029.1);
        City city3 = createCity("Henrico", 1, 2, 3);

        assertEquals("When we compare two cities with the same name, we expect them to be equal",
                city1, city2);
        assertNotEquals("When we compare two cities with diffferent names, we expect to be " +
                "not equal", city1, city3);
    }

    private City createCity(String name, double area, int population, double distance) {
        City testCity = new City();
        @SuppressWarnings("rawtypes")
        Class c = testCity.getClass();

        try {
            Field size = c.getDeclaredField("name");
            size.setAccessible(true);
            size.set(testCity, name);

            Field flavor = c.getDeclaredField("area");
            flavor.setAccessible(true);
            flavor.set(testCity, area);

            Field age = c.getDeclaredField("population");
            age.setAccessible(true);
            age.set(testCity, population);

            Field email = c.getDeclaredField("distance");
            email.setAccessible(true);
            email.set(testCity, distance);

        } catch (Exception e) {
            fail(e.toString());
        }

        return testCity;
    }

    private void assertInstanceVariablePrivate(String aField, Object testObject) {
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertTrue(
                    String.format(
                            "You must make your instance variables private: `%s.%s` is not private",
                            c.getSimpleName(), aField
                    ),
                    Modifier.isPrivate(c.getDeclaredField(aField).getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void assertInstanceVariableNotStatic(String aField, Object testObject) {
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertFalse(String.format(
                    "Your instance variables must NOT be static: `%s.%s` is static!",
                    c.getSimpleName(), aField
                    ),
                    Modifier.isStatic(c.getDeclaredField(aField).getModifiers()));
        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void assertInstanceVariableIsExpected(String aField, Object testObject, Object expected,
                                                  String message) {
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            Field field = c.getDeclaredField(aField);
            field.setAccessible(true);
            Object fieldValue = field.get(testObject);

            if (expected == null) {
                assertNull(message, fieldValue);
            }
            //If class is a double we have a special Junit assert to run
            else if (expected.getClass().equals(Double.class)) {
                double doubleFieldValue = (double) fieldValue;
                double doubleExpected = (double) expected;
                assertEquals(message, doubleExpected, doubleFieldValue, .01);
            }
            //Array of some kind yay
            else if (expected.getClass().isArray()) {

            } else {
                assertEquals(message, expected, fieldValue);
            }

        } catch (Exception e) {
            fail(e.toString());
        }
    }

    private Object getPrivateInstanceVariable(String varName, Object o) throws NoSuchFieldException {
        Class c = o.getClass();
        Field field = c.getDeclaredField(varName);
        field.setAccessible(true);
        try {
            return field.get(o);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Something weird happened:\n" + e.getLocalizedMessage());
        }


    }
}
