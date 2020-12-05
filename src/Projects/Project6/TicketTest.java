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

import org.junit.Test;

import java.awt.datatransfer.FlavorEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TicketTest {

    public void Ticket_instanceVariableCountTest(){
        Ticket testFan = new Ticket();
        @SuppressWarnings("rawtypes")
        Class c = testFan.getClass();
        try {
            assertEquals(
                    "You must only have the instance variables specified. When looking at the number of instance variables we",
                    4, c.getDeclaredFields().length);
        }
        catch (Exception e) {
            fail("Something weird went wrong");
        }
    }

    @Test
    public void Ticket_instanceVariablesTest(){
        Ticket testTicket = new Ticket();
        instanceVariablePrivate("location",testTicket);
        instanceVariablePrivate("food",testTicket);
        instanceVariablePrivate("drink",testTicket);
        instanceVariablePrivate("discount",testTicket);

        instanceVariableStatic("location",testTicket);
        instanceVariableStatic("food",testTicket);
        instanceVariableStatic("drink",testTicket);
        instanceVariableStatic("discount",testTicket);

        instanceVariableCorrectType("location",Location.class,testTicket);
        instanceVariableCorrectType("food", Food_Option.class,testTicket);
        instanceVariableCorrectType("drink",Drink_Option.class,testTicket);
        instanceVariableCorrectType("discount",Discount.class,testTicket);
    }

    @Test
    public void Ticket_defaultConstructorTest() {
        Ticket testTicket = new Ticket();

        testVariable("location",testTicket,Location.FRONT,"When checking the value of location we");
        testVariable("food",testTicket,Food_Option.NONE,"When checking the value of flavor we");
        testVariable("drink",testTicket,Drink_Option.NONE,"When checking the value of drink we");
        testVariable("discount",testTicket,Discount.NONE,"When checking the value of discount we");

    }

    @Test
    public void Ticket_parameterizedConstructorTest() {
        Ticket testTicket = new Ticket(Location.BACK);

        testVariable("location",testTicket,Location.BACK,"When checking the value of location we");
        testVariable("food",testTicket,Food_Option.NONE,"When checking the value of flavor we");
        testVariable("drink",testTicket,Drink_Option.NONE,"When checking the value of drink we");
        testVariable("discount",testTicket,Discount.NONE,"When checking the value of discount we");

    }

    @Test
    public void Ticket_getLocationTest() {
        Ticket testTicket = createTicket(Location.FRONT,Food_Option.CANDY_BOX,Drink_Option.NONE,Discount.SENIOR);
        assertEquals("With an Ticket object who's location instance variable is FRONT, when calling getLocation we",Location.FRONT,testTicket.getLocation());
    }

    @Test
    public void Ticket_setLocationTest() {
        Ticket testTicket = createTicket(Location.FRONT,Food_Option.CANDY_BOX,Drink_Option.NONE,Discount.SENIOR);
        testTicket.setLocation(Location.BACK);
        testVariable("location",testTicket,Location.BACK,"After calling Ticket's setLocation method with an argument of BACK, for the value of location we");
    }

    @Test
    public void Ticket_getFoodTest() {
        Ticket testTicket = createTicket(Location.FRONT,Food_Option.CANDY_BOX,Drink_Option.NONE,Discount.SENIOR);
        assertEquals("With an Ticket object who's food instance variable is CANDY_BOX, when calling getFood we",Food_Option.CANDY_BOX,testTicket.getFood());
    }

    @Test
    public void Ticket_setFoodTest() {
        Ticket testTicket = createTicket(Location.FRONT,Food_Option.CANDY_BOX,Drink_Option.NONE,Discount.SENIOR);
        testTicket.setFood(Food_Option.PIZZA);
        testVariable("food",testTicket,Food_Option.PIZZA,"After calling Ticket's setFood_Option method with an argument of PIZZA, for the value of food we");
    }

    @Test
    public void Ticket_getDrinkTest() {
        Ticket testTicket = createTicket(Location.FRONT,Food_Option.CANDY_BOX,Drink_Option.NONE,Discount.SENIOR);
        assertEquals("With an Ticket object who's drink instance variable is NONE, when calling getDrink we",Drink_Option.NONE,testTicket.getDrink());
    }

    @Test
    public void Ticket_setDrinkTest() {
        Ticket testTicket = createTicket(Location.FRONT,Food_Option.CANDY_BOX,Drink_Option.NONE,Discount.SENIOR);
        testTicket.setDrink(Drink_Option.SODA);
        testVariable("drink",testTicket,Drink_Option.SODA,"After calling Ticket's setDrink method with an argument of SODA, for the value of drink we");
    }

    @Test
    public void Ticket_getDiscountTest() {
        Ticket testTicket = createTicket(Location.FRONT,Food_Option.CANDY_BOX,Drink_Option.NONE,Discount.SENIOR);
        assertEquals("With an Ticket object who's discount instance variable is SENIOR, when calling getDiscount we",Discount.SENIOR,testTicket.getDiscount());
    }

    @Test
    public void Ticket_setDiscountTest() {
        Ticket testTicket = createTicket(Location.FRONT,Food_Option.CANDY_BOX,Drink_Option.NONE,Discount.SENIOR);
        testTicket.setDiscount(Discount.NONE);
        testVariable("discount",testTicket,Discount.NONE,"After calling Ticket's setDiscount method with an argument of NONE, for the value of discount we");
    }

    @Test
    public void Ticket_toStringTest() {
        Ticket testTicket = createTicket(Location.FRONT, Food_Option.FRIES, Drink_Option.SODA, Discount.CHILD);
        assertEquals(  "\n\tFRONT\n" +
                "\tFRIES\n" +
                "\tSODA\n" +
                "\tCHILD\n", testTicket.toString());

        testTicket = createTicket(Location.FRONT,Food_Option.POPCORN, Drink_Option.NONE,Discount.SENIOR);
        assertEquals(  "\n\tFRONT\n" +
                "\tPOPCORN\n" +
                "\tNONE\n" +
                "\tSENIOR\n", testTicket.toString());
    }

    private Customer createCustomer(String aLastName, String aFirstName, String aPhone, String anEmail, ArrayList<Ticket> someTicket){
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

            Field sauce = c.getDeclaredField("phone");
            sauce.setAccessible(true);
            sauce.set(testCustomer, aPhone);

            Field topping = c.getDeclaredField("email");
            topping.setAccessible(true);
            topping.set(testCustomer, anEmail);

        } catch (Exception e) {
            fail(e.toString());
        }

        return testCustomer;
    }

    private Ticket createTicket(Location aLocation, Food_Option aFood_Option, Drink_Option aDrink_Option, Discount aDiscount){
        Ticket testTicket = new Ticket();
        @SuppressWarnings("rawtypes")
        Class c = testTicket.getClass();

        try {
            Field location = c.getDeclaredField("location");
            location.setAccessible(true);
            location.set(testTicket, aLocation);

            Field food_option = c.getDeclaredField("food");
            food_option.setAccessible(true);
            food_option.set(testTicket, aFood_Option);

            Field drink_option = c.getDeclaredField("drink");
            drink_option.setAccessible(true);
            drink_option.set(testTicket, aDrink_Option);

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

            assertEquals("You must make the speed instance variable of type"+ aClass.toString() +".", aClass, c.getDeclaredField(aField).getType());

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
            Field sizeFieldo1 = c.getDeclaredField("size");
            sizeFieldo1.setAccessible(true);
            Object sizeo1 = sizeFieldo1.get(o1);

            Field sizeFieldo2 = c.getDeclaredField("size");
            sizeFieldo2.setAccessible(true);
            Object sizeo2 = sizeFieldo2.get(o2);

            Field flavorFieldo1 = c.getDeclaredField("flavor");
            flavorFieldo1.setAccessible(true);
            Object flavoro1 = flavorFieldo1.get(o1);

            Field flavorFieldo2 = c.getDeclaredField("flavor");
            flavorFieldo2.setAccessible(true);
            Object flavoro2 = flavorFieldo2.get(o2);

            Field icingFieldo1 = c.getDeclaredField("icing");
            icingFieldo1.setAccessible(true);
            Object icingo1 = icingFieldo1.get(o1);

            Field icingFieldo2 = c.getDeclaredField("icing");
            icingFieldo2.setAccessible(true);
            Object icingo2 = icingFieldo2.get(o2);

            Field toppingFieldo1 = c.getDeclaredField("topping");
            toppingFieldo1.setAccessible(true);
            Object toppingo1 = toppingFieldo1.get(o1);

            Field toppingFieldo2 = c.getDeclaredField("topping");
            toppingFieldo2.setAccessible(true);
            Object toppingo2 = toppingFieldo2.get(o2);

            if(sizeo1.equals(sizeo2) && flavoro1.equals(flavoro2) && icingo1.equals(icingo2) && toppingo1.equals(toppingo2)){
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
