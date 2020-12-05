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

import java.util.ArrayList;

public class Customer {
    private String firstName;
    private String lastName;
    private int age;
    private String email;
    private ArrayList<Ticket> tickets;

    /**
     * default constructor
     */
    public Customer() {
        this.lastName = null;
        this.firstName = null;
        this.age = 0;
        this.email = null;
        this.tickets = new ArrayList<Ticket>();
    }

    /**
     * parameter constructor
     * @param lastName
     * @param firstName
     * @param age
     * @param email
     */
    public Customer(String lastName, String firstName, int age, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.email = email;
        this.tickets = new ArrayList<Ticket>();
    }

    /**
     * getter for firstName variable
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * setter for firstName variable
     * @param first_name
     */
    public void setFirstName(String first_name) {
        this.firstName = first_name;
    }

    /**
     * getter for lastName variable
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * setter for lastName variable
     * @param last_name
     */
    public void setLastName(String last_name) {
        this.lastName = last_name;
    }

    /**
     * getter for age variable
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * setter for age variable
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * getter for email variable
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * setter for email variable
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * getter for tickets variable
     * @return tickets
     */
    public ArrayList<Ticket> getTickets(){
        return tickets;
    }

    /**
     * creating purchaseTicket method to add ticket object into the arraylist
     * @param ticket
     */
    public void purchaseTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    /**
     * creating getNumTickets method to count the size of the arraylist
     * @return number of tickets on the arraylist
     */
    public int getNumTickets() {
        return tickets.size();
    }

    /**
     * toString method for Customer class
     * @return the customer information and the ticket/s information
     */
    public String toString() {
        String result = "";
        for (int i =0; i<getNumTickets();i++){
            result+= getTickets().get(i);
        }
        return firstName + " " + lastName + "\n" + age + "\n" + email+ "\nTicket Order:\n"
                 + result;
    }

}

