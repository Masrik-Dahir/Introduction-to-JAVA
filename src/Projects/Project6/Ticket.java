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

public class Ticket {
    /**
     * Declaring Instance Variables: Location location, Discount discount,
     * Food_Option food, and Drink_Option drink
     */
    private Location location;
    private Discount discount;
    private Food_Option food;
    private Drink_Option drink;

    /**
     * Parameter Constructor
     */
    public Ticket(Location location) {
        this.location = location;
        this.discount = discount.NONE;
        this.food = food.NONE;
        this.drink = drink.NONE;
    }

    /**
     * Default Constructor
     */
    public Ticket() {
        this.location = location.FRONT;
        this.discount = discount.NONE;
        this.food = food.NONE;
        this.drink = drink.NONE;
    }

    /**
     * setter for location
     * @param location
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * setter for food variable
     * @param food
     */
    public void setFood(Food_Option food) {
        this.food = food;
    }

    /**
     * setter for drink variable
     * @param drink
     */
    public void setDrink(Drink_Option drink) {
        this.drink = drink;
    }

    /**
     * setter for discount variable
     * @param discount
     */
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**
     * getter for location variable
     * @return location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * getter for discount variable
     * @return discount
     */
    public Discount getDiscount() {
        return discount;
    }

    /**
     * getter for food variable
     * @return food
     */
    public Food_Option getFood() {
        return food;
    }

    /**
     * getter for drink variable
     * @return drink
     */
    public Drink_Option getDrink() {
        return drink;
    }

    /**
     * toString method for Ticket class
     * @return all the enums in different lines as a String
     */
    public String toString() {
        return "\n\t"+location + "\n\t" + food + "\n\t" + drink + "\n\t" + discount+ "\n";
    }




}

