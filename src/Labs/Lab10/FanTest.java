/****************************************************************************
 * Copyright 2020, Masrik Dahir, All Right Reserved
 ****************************************************************************/
package Labs.Lab10;

import static Labs.Lab10.Fan.*;

public class FanTest {
    public static void main(String[] args) {

        /**Declaring 2 objects*/
        Fan fan1 = new Fan(3, false, 25.0, "Yellow");
        Fan fan2 = new Fan(3, false, 25.0, "Yellow");

        /**Seting values for the object 1*/
        fan1.setSpeed(FAST);
        fan1.setRadius(10);
        fan1.setColor("yellow");
        fan1.setOn(true);

        /**Seting values for the object 2*/
        fan2.setSpeed(MEDIUM);
        fan2.setRadius(20);
        fan2.setColor("blue");
        fan2.setOn(false);

        /**Printing it out*/
        System.out.println(fan1.toString());
        System.out.println(fan2.toString());


    }

}

