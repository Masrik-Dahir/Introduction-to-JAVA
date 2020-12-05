/****************************************************************************
 * Copyright 2020, Masrik Dahir, All Right Reserved
 ****************************************************************************/
package Labs.Lab13;

public class Team {
    private String name;
    private int numGoals;
    private double numShots;

    public Team(){
        this.name="";
        this. numGoals = 0;
        this.numShots = 0;
    }

    public Team(String name, int numGoals, double numShots){
        this.name = name;
        this.numShots = numShots;
        this.numGoals = numGoals;
    }
    //getter

    public String getName() {
        return name;
    }

    public int getNumGoals() {
        return numGoals;
    }

    public double getNumShots() {
        return numShots;
    }
    //Setter

    public void setName(String name) {
        this.name = name;
    }

    public void setNumGoals(int numGoals) {
        this.numGoals = numGoals;
    }

    public void setNumShots(int numShots) {
        this.numShots = numShots;
    }
    //CB - YOu do not need these methods.
    //max

}
