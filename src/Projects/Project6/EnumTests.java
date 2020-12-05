package Projects.Project6;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class EnumTests {

    @Test
    public void LocationTest() {
        try {
            Location.valueOf("FRONT");
            Location.valueOf("MIDDLE");
            Location.valueOf("BACK");
            Location.valueOf("BALCONY");
        }
        catch(IllegalArgumentException e) {
            fail(e.getLocalizedMessage());
        }
        assertEquals("When looking at the number of values in the Size enum, we",4,Location.values().length);
    }

    @Test
    public void Food_OptionTest() {
        try {
            Food_Option.valueOf("NONE");
            Food_Option.valueOf("POPCORN");
            Food_Option.valueOf("CANDY_BOX");
            Food_Option.valueOf("PIZZA");
            Food_Option.valueOf("FRIES");
        }
        catch(IllegalArgumentException e) {
            fail(e.getLocalizedMessage());
        }
        assertEquals("When looking at the number of values in the Food_Option enum, we",5,Food_Option.values().length);
    }

    @Test
    public void Drink_OptionTest() {
        try {
            Drink_Option.valueOf("NONE");
            Drink_Option.valueOf("ICEE");
            Drink_Option.valueOf("SODA");
            Drink_Option.valueOf("LEMONADE");
            Drink_Option.valueOf("BOTTLED_WATER");
        }
        catch(IllegalArgumentException e) {
            fail(e.getLocalizedMessage());
        }
        assertEquals("When looking at the number of values in the Drink_Option enum, we",5,Drink_Option.values().length);
    }

    @Test
    public void DiscountTest() {
        try {
            Discount.valueOf("NONE");
            Discount.valueOf("MILITARY");
            Discount.valueOf("SENIOR");
            Discount.valueOf("CHILD");
            Discount.valueOf("FREQUENT_MOVIE_GOER");
        }
        catch(IllegalArgumentException e) {
            fail(e.getLocalizedMessage());
        }
        assertEquals("When looking at the number of values in the Discount enum, we",5,Discount.values().length);
    }

}