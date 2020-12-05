/****************************************************************************
 * Movie Tickets
 ****************************************************************************
 *  This program keep track of the customer and the movies tickets they purchased in an array
 *  The option to choose from while deciding the ticket types, food, drink and discount is
 *  listed on separate enums
 *_____________________________________________________
 * Masrik Dahir
 * 5 Vovember 2020
 * CMSC 255-C90
 * Copyright 2020, Masrik Dahir, All Right Reserved
 ****************************************************************************/
package Projects.Project6;

import Projects.Project6.Customer;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class CustomerTests {


    public void Customer_instanceCountTest(){
        Customer testCustomer = new Customer();
        @SuppressWarnings("rawtypes")
        Class c = testCustomer.getClass();
        try {
            assertEquals(
                    "You must only have the instance variables specified. When looking at the number of instance variables we",
                    5, c.getDeclaredFields().length);
        }
        catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    @Test
    public void Customer_instanceVariablesTest(){
        Customer testCustomer = new Customer();
        instanceVariablePrivate("lastName",testCustomer);
        instanceVariablePrivate("firstName",testCustomer);
        instanceVariablePrivate("age",testCustomer);
        instanceVariablePrivate("email",testCustomer);
        instanceVariablePrivate("tickets",testCustomer);

        instanceVariableStatic("lastName",testCustomer);
        instanceVariableStatic("firstName",testCustomer);
        instanceVariableStatic("age",testCustomer);
        instanceVariableStatic("email",testCustomer);
        instanceVariableStatic("tickets",testCustomer);

        instanceVariableCorrectType("lastName",String.class,testCustomer);
        instanceVariableCorrectType("firstName",String.class,testCustomer);
        instanceVariableCorrectType("age",int.class,testCustomer);
        instanceVariableCorrectType("email",String.class,testCustomer);
        instanceVariableCorrectType("tickets",ArrayList.class,testCustomer);
    }

    @Test
    public void Customer_defaultConstructorTest() {
        Customer testCustomer = new Customer();

        testVariable("lastName",testCustomer,null,"When checking the value of lastName we");
        testVariable("firstName",testCustomer,null,"When checking the value of firstName we");
        testVariable("age",testCustomer,0,"When checking the value of age we");
        testVariable("email",testCustomer,null,"When checking the value of email we");
        testVariable("tickets",testCustomer,new ArrayList<>(),"When checking the value of tickets we");

    }

    @Test
    public void Customer_parameterizedConstructorTest() {
        Customer testCustomer = new Customer("Budwell","Caroline",34,"ccbudwell@vcu.edu");

        testVariable("lastName",testCustomer,"Budwell","When checking the value of lastName we");
        testVariable("firstName",testCustomer,"Caroline","When checking the value of firstName we");
        testVariable("age",testCustomer,34,"When checking the value of age we");
        testVariable("email",testCustomer,"ccbudwell@vcu.edu","When checking the value of email we");
        testVariable("tickets",testCustomer,new ArrayList<>(),"When checking the value of tickets we");

    }

    @Test
    public void Customer_getLastNameTest() {
        ArrayList<Ticket> someTicket = new ArrayList<>();
        Customer testCustomer = createCustomer("Sparks","Zach",24,"zwhitten@vcu.edu",someTicket);
        assertEquals("With a Customer object who's lastName instance variable is Sparks, when calling getLastName we","Sparks",testCustomer.getLastName());
    }

    @Test
    public void Customer_setLastNameTest() {
        ArrayList<Ticket> someTicket = new ArrayList<>();
        Customer testCustomer = createCustomer("Whitten","Zach",34,"zwhitten@vcu.edu",someTicket);

        testCustomer.setLastName("Sparks");
        testVariable("lastName",testCustomer,"Sparks","After calling Customer's setLastName method with an argument of Sparks, for the value of lastName we");
    }

    @Test
    public void Customer_getFirstNameTest() {
        ArrayList<Ticket> someTicket = new ArrayList<>();
        Customer testCustomer = createCustomer("Whitten","Zach",34,"zwhitten@vcu.edu",someTicket);
        assertEquals("With a Customer object who's firstName instance variable is Zach, when calling getFirstName we","Zach",testCustomer.getFirstName());
    }

    @Test
    public void Customer_setFirstNameTest() {
        ArrayList<Ticket> someTicket = new ArrayList<>();
        Customer testCustomer = createCustomer("Sparks","Zach",34,"zwhitten@vcu.edu",someTicket);

        testCustomer.setFirstName("Clare");
        testVariable("firstName",testCustomer,"Clare","After calling Customer's setFirstName method with an argument of Clare, for the value of firstName we");
    }


    @Test
    public void Customer_getAgeTest() {
        ArrayList<Ticket> someTicket = new ArrayList<>();
        Customer testCustomer = createCustomer("Whitten","Zach",39,"zwhitten@vcu.edu",someTicket);
        assertEquals("With a Customer object who's age instance variable is 39, when calling getAge we",39,testCustomer.getAge());
    }

    @Test
    public void Customer_setAgeTest() {
        ArrayList<Ticket> someTicket = new ArrayList<>();
        Customer testCustomer = createCustomer("Whitten","Zach",22,"zwhitten@vcu.edu",someTicket);

        testCustomer.setAge(22);
        testVariable("age",testCustomer,22,"After calling Customer's setAge method with an argument of 22, for the value of age we");
    }

    @Test
    public void Customer_getEmailTest() {
        ArrayList<Ticket> someTicket = new ArrayList<>();
        Customer testCustomer = createCustomer("Whitten","Zach",39,"zwhitten@vcu.edu",someTicket);
        assertEquals("With a Customer object who's email instance variable is zwhitten@vcu.edu, when calling getEmail we","zwhitten@vcu.edu",testCustomer.getEmail());
    }

    @Test
    public void Customer_setEmailTest() {
        ArrayList<Ticket> someTicket = new ArrayList<>();
        Customer testCustomer = createCustomer("Whitten","Zach",34,"zwhitten@vcu.edu",someTicket);

        testCustomer.setEmail("zachary.whitten.11@cnu.edu");
        testVariable("email",testCustomer,"zachary.whitten.11@cnu.edu","After calling Customer's setEmail method with an argument of zachary.whitten.11@cnu.edu, for the value of email we");
    }

    @Test
    public void Customer_getNumTicketsTest() {
        ArrayList<Ticket> someTicket = new ArrayList<>();
        someTicket.add(createTicket(Location.BALCONY, Food_Option.FRIES, Drink_Option.LEMONADE, Discount.NONE));
        someTicket.add(createTicket(Location.BACK, Food_Option.NONE, Drink_Option.SODA, Discount.CHILD));
        Customer testCustomer = createCustomer("Whitten","Zach",34,"zwhitten@vcu.edu",someTicket);
        assertEquals("With a Customer object who's tickets instance variable is has two elements, when calling getNumTicket we",2,testCustomer.getNumTickets());
    }

    @Test
    public void Customer_getTicketsTest() {
        ArrayList<Ticket> someTicket = new ArrayList<>();
        someTicket.add(createTicket(Location.BALCONY, Food_Option.FRIES, Drink_Option.LEMONADE, Discount.NONE));
        someTicket.add(createTicket(Location.BACK, Food_Option.NONE, Drink_Option.SODA, Discount.CHILD));

        ArrayList<Ticket> expectedTicket = new ArrayList<>();
        expectedTicket.add(createTicket(Location.BALCONY, Food_Option.FRIES, Drink_Option.LEMONADE, Discount.NONE));
        expectedTicket.add(createTicket(Location.BACK, Food_Option.NONE, Drink_Option.SODA, Discount.CHILD));

        Customer testCustomer = createCustomer("Whitten","Zach",34,"zwhitten@vcu.edu",someTicket);
        testTicketArray("With a Customer object who's tickets instance variable is has two elements, when calling getTicket we",expectedTicket,testCustomer.getTickets());
    }

    @Test
    public void Customer_orderTicketOneTicketTest() {
        ArrayList<Ticket> someTicket = new ArrayList<>();

        ArrayList<Ticket> expectedTicket = new ArrayList<>();
        expectedTicket.add(createTicket(Location.BALCONY, Food_Option.FRIES, Drink_Option.LEMONADE, Discount.NONE));
        Customer testCustomer = createCustomer("Whitten","Zach",34,"zwhitten@vcu.edu",someTicket);

        testCustomer.purchaseTicket(createTicket(Location.BALCONY, Food_Option.FRIES, Drink_Option.LEMONADE, Discount.NONE));

        testVariable("tickets",testCustomer,expectedTicket,"After a Customer object orders a single Ticket, when checking the tickets instance variable we");

    }

    @Test
    public void Customer_orderTicketFourTicketTest() {
        ArrayList<Ticket> someTicket = new ArrayList<>();

        ArrayList<Ticket> expectedTicket = new ArrayList<>();


        expectedTicket.add(createTicket(Location.FRONT, Food_Option.PIZZA, Drink_Option.BOTTLED_WATER, Discount.FREQUENT_MOVIE_GOER));
        expectedTicket.add(createTicket(Location.BALCONY, Food_Option.CANDY_BOX, Drink_Option.SODA, Discount.SENIOR));
        expectedTicket.add(createTicket(Location.BALCONY, Food_Option.FRIES, Drink_Option.LEMONADE, Discount.NONE));


        Customer testCustomer = createCustomer("Whitten","Zach",34,"zwhitten@vcu.edu",someTicket);


        someTicket.add(createTicket(Location.FRONT, Food_Option.PIZZA, Drink_Option.BOTTLED_WATER, Discount.FREQUENT_MOVIE_GOER));
        someTicket.add(createTicket(Location.BALCONY, Food_Option.CANDY_BOX, Drink_Option.SODA, Discount.SENIOR));
        someTicket.add(createTicket(Location.BALCONY, Food_Option.FRIES, Drink_Option.LEMONADE, Discount.NONE));

        testVariable("tickets",testCustomer,expectedTicket,"After a Customer object orders a single Ticket, when checking the tickets instance variable we");

    }

    @Test
    public void Customer_toStringTest() {
        ArrayList<Ticket> someTicket = new ArrayList<>();
        someTicket.add(createTicket(Location.FRONT,Food_Option.POPCORN,Drink_Option.ICEE, Discount.CHILD));
        Customer testCustomer = createCustomer("Whitten","Zach",22,"zwhitten@vcu.edu",someTicket);

        String expected1 ="Zach Whitten\n" +
                "22\n" +
                "zwhitten@vcu.edu\n" +
                "Ticket Order:\n\n" +
                "\tFRONT\n" +
                "\tPOPCORN\n" +
                "\tICEE\n" +
                "\tCHILD";
        assertTrue(testCustomer.toString().contains(expected1));


        someTicket = new ArrayList<>();
        someTicket.add(createTicket(Location.FRONT, Food_Option.PIZZA, Drink_Option.BOTTLED_WATER, Discount.FREQUENT_MOVIE_GOER));
        someTicket.add(createTicket(Location.BALCONY, Food_Option.CANDY_BOX, Drink_Option.SODA, Discount.SENIOR));
        testCustomer = createCustomer("Budwell","Caroline",28,"ccbudwell@vcu.edu",someTicket);

        String expectedCaroline = "Caroline Budwell\n" +
                "28\n" +
                "ccbudwell@vcu.edu\n" +
                "Ticket Order:\n" +
                "\n" +
                "\tFRONT\n" +
                "\tPIZZA\n" +
                "\tBOTTLED_WATER\n" +
                "\tFREQUENT_MOVIE_GOER\n" +
                "\n" +
                "\tBALCONY\n" +
                "\tCANDY_BOX\n" +
                "\tSODA\n" +
                "\tSENIOR";
        assertTrue(testCustomer.toString().contains(expectedCaroline));
    }


    private Customer createCustomer(String aLastName, String aFirstName, int aAge, String anEmail, ArrayList<Ticket> someTicket){
        Customer testCustomer = new Customer();
        @SuppressWarnings("rawtypes")
        Class c = testCustomer.getClass();

        try {
            Field size = c.getDeclaredField("lastName");
            size.setAccessible(true);
            size.set(testCustomer, aLastName);

            Field flavor = c.getDeclaredField("firstName");
            flavor.setAccessible(true);
            flavor.set(testCustomer, aFirstName);

            Field age = c.getDeclaredField("age");
            age.setAccessible(true);
            age.set(testCustomer, aAge);

            Field email = c.getDeclaredField("email");
            email.setAccessible(true);
            email.set(testCustomer, anEmail);

            Field tickets = c.getDeclaredField("tickets");
            tickets.setAccessible(true);
            tickets.set(testCustomer, someTicket);

        } catch (Exception e) {
            fail(e.toString());
        }

        return testCustomer;
    }

    private Ticket createTicket(Location aLocation, Food_Option aFoodOption, Drink_Option aDrinkOption, Discount aDiscount){
        Ticket testTicket = new Ticket();
        @SuppressWarnings("rawtypes")
        Class c = testTicket.getClass();

        try {
            Field location = c.getDeclaredField("location");
            location.setAccessible(true);
            location.set(testTicket, aLocation);

            Field food = c.getDeclaredField("food");
            food.setAccessible(true);
            food.set(testTicket, aFoodOption);

            Field drink = c.getDeclaredField("drink");
            drink.setAccessible(true);
            drink.set(testTicket, aDrinkOption);

            Field discount = c.getDeclaredField("discount");
            discount.setAccessible(true);
            discount.set(testTicket, aDiscount);


        } catch (Exception e) {
            fail(e.toString());
        }

        return testTicket;
    }

    private void instanceVariablePrivate(String aField, Object testObject) {
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertTrue("You must make your instance variables private.", Modifier.isPrivate(c.getDeclaredField(aField).getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void instanceVariableStatic(String aField, Object testObject) {
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertEquals("Your instance variables must NOT be static.", false,
                    Modifier.isStatic(c.getDeclaredField(aField).getModifiers()));

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void instanceVariableCorrectType(String aField, Class<?> aClass,  Object testObject) {
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            c.getDeclaredField(aField);

            assertEquals("You must make the " + aField + " instance variable of type"+ aClass.toString() +".", aClass, c.getDeclaredField(aField).getType());

        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    private void testVariable(String aField, Object testObject, Object expected, String message){
        @SuppressWarnings("rawtypes")
        Class c = testObject.getClass();
        try {
            Field field = c.getDeclaredField(aField);
            field.setAccessible(true);
            Object fieldValue = field.get(testObject);

            if(expected == null){
                assertNull(message,fieldValue);
            }
            //If class is a double we have a special Junit assert to run
            else if(expected.getClass().equals(Double.class)){
                double doubleFieldValue = (double) fieldValue;
                double doubleExpected = (double) expected;
                assertEquals(message, doubleExpected, doubleFieldValue, .01);
            }
            //Array of some kind yay
            else if(expected.getClass().isArray()){

            }
            else if(expected.getClass().equals(ArrayList.class)){
                //CUSTOM FOR PROJECT6TESTS!!!
                testTicketArray(message,(ArrayList) expected, (ArrayList) fieldValue);
            }
            else{
                assertEquals(message, expected, fieldValue);
            }

        }
        catch (Exception e) {
            fail(e.toString());
        }
    }

    private void testTicketArray(String message, ArrayList expected, ArrayList actual){
        assertEquals(message + " looked at the size and ",expected.size(),actual.size());

        for(int i = 0; i < expected.size(); i++) {
            if (!TicketIsEqual(expected.get(i), actual.get(i))) {
                assertEquals(message, expected, actual);
            }
        }
    }

    private boolean TicketIsEqual(Object o1, Object o2){
        @SuppressWarnings("rawtypes")
        Class c = o1.getClass();
        try {
            Field locationFieldo1 = c.getDeclaredField("location");
            locationFieldo1.setAccessible(true);
            Object locationo1 = locationFieldo1.get(o1);

            Field locationFieldo2 = c.getDeclaredField("location");
            locationFieldo2.setAccessible(true);
            Object locationo2 = locationFieldo2.get(o2);

            Field foodFieldo1 = c.getDeclaredField("food");
            foodFieldo1.setAccessible(true);
            Object foodo1 = foodFieldo1.get(o1);

            Field foodFieldo2 = c.getDeclaredField("food");
            foodFieldo2.setAccessible(true);
            Object foodo2 = foodFieldo2.get(o2);

            Field drinkFieldo1 = c.getDeclaredField("drink");
            drinkFieldo1.setAccessible(true);
            Object drinko1 = drinkFieldo1.get(o1);

            Field drinkFieldo2 = c.getDeclaredField("drink");
            drinkFieldo2.setAccessible(true);
            Object drinko2 = drinkFieldo2.get(o2);

            Field discountFieldo1 = c.getDeclaredField("discount");
            discountFieldo1.setAccessible(true);
            Object discounto1 = discountFieldo1.get(o1);

            Field discountFieldo2 = c.getDeclaredField("discount");
            discountFieldo2.setAccessible(true);
            Object discounto2 = discountFieldo2.get(o2);

            if(locationo1.equals(locationo2) && foodo1.equals(foodo2) && drinko1.equals(drinko2) && discounto1.equals(discounto2)){
                return true;
            }
            else{
                return false;
            }


        } catch (NoSuchFieldException e) {
            fail("Could not find the " + e.getLocalizedMessage() + " instance variable");
        } catch (Exception e) {
            fail("Something weird went wrong");
        }

        return false;
    }

}
