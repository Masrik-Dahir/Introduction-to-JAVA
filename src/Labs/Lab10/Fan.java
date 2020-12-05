/****************************************************************************
 * Copyright 2020, Masrik Dahir, All Right Reserved
 ****************************************************************************/
package Labs.Lab10;

public class Fan {
    public final static int SLOW = 1;
    public final static int MEDIUM = 2;
    public final static int FAST = 3;
    private int speed;
    private boolean on;
    private double radius;
    private String color;

    /**
     * Default constructor of Fan Class
     */
    public Fan(){
        speed = SLOW;
        on = false;
        radius = 5;
        color = "blue";
    }

    /**
     * Constructor of the Fan class
     */
    public Fan(int speed, boolean on, double radius, String color) {
        this.speed = speed;
        this.on = on;
        this.radius = radius;
        this.color = color;
    }


    /*
     * Setter Methods
     */
    /**This method Sets speed */
    public void setSpeed(int newSpeed) {
        speed = newSpeed;
    }
    /**This method Sets color */
    public void setColor(String newColor) {
        color = newColor;
    }
    /**This method Sets radius */
    public void setRadius(double newRadius) {
        radius = newRadius;
    }

    /**This method Sets  on*/
    public void setOn(boolean b) {
        on = b;
    }


    /*
     * Getters Methos
     */
    /**This method Gets speed */
    public int getSpeed() {
        return speed;
    }
    /**This method Return radius */
    public double getRadius() {
        return radius;
    }

    /**This method Return color */
    public String getColor() {
        return color;
    }
    /**
     *This method returns the status of whether the fan on or off
     */
    public boolean isOn() {
        return on;
    }



    /**This method Returns a string description for the fan */
    public String toString() {
        if (on == true) {
            return "fan is " + getSpeed() + ", " + color +
                    ", and size " + radius;
        }
        else{
            //return "fan is " + getSpeed() + ", " + color +
            //        ", and size " + radius;
            return "fan is off";
        }
    }
}

