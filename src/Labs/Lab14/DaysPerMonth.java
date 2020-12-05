package Labs.Lab14;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *   DaysPerMonth
 *   VCU - Computer Science Department
 *   A program that prompts the user for a month and year (both as integers)
 *   then displays the number of days in that month.
 **/

public class DaysPerMonth {

    public static void main(String[] args){
        File inputFile;
        File outputFile;
        if(args.length!=0) {
            inputFile = new File(args[0]);
            outputFile = new File(args[1]);

            //System.out.println(args[0]+" "+args[1]);
            processFile(inputFile, outputFile);
        }else{
            Scanner input = new Scanner(System.in);
            System.out.println("Enter a input File ");
            inputFile = new File(input.next());
            System.out.println("Please enter the outputFile name: ");
            outputFile = new File(input.next());
            processFile(inputFile, outputFile);
            System.out.println("none");
        }
    }

    public static void processFile(File inputFile, File outputFile){
        try{
            int month=2;
            int year=1;

            PrintWriter out = new PrintWriter(outputFile.getPath());
            Scanner in = new Scanner(inputFile);
            while(in.hasNextLine()) {
                boolean go = true;
                String line = in.nextLine();
                String[] tokens = line.split(",");
                try {
                    month = Integer.parseInt(tokens[0]);
                    year = Integer.parseInt(tokens[1]);
                    //out.println(month +" "+ year);
                    go = true;
                    if(month<1 || month>12) {
                        //System.out.println("Month must be between 1 and 12");
                        out.println("Month must be between 1 and 12");
                        go = false;
                    }
                    if (year < 0){
                        //System.out.println("Year cannot be negative");
                        out.println("Year cannot be negative");
                        go = false;
                    }
                }catch(NumberFormatException e){
                    //System.out.println("Not an integer");
                    out.println("Not an integer");
                    go = false;
                }



                if(go==true) {
                    out.printf("There are %s days in this month.\n", getDays(month, year));
                }
            }
            in.close();
            out.close();
        }catch (Exception e){
            System.out.println("Bad File Name: "+e);
        }

    }

    /**
     * method to determine the days for the given month and year
     **/
    private static int getDays(int mon, int yr) {
        int numDays = 0;

        switch(mon) {
            case 2: // February
                numDays = 28;
                if (yr % 4 == 0) {
                    numDays = 29;
                    if (yr % 100 == 0 && yr % 400 != 0) {
                        numDays = 28;
                    }
                }
                break;

            case 4:
                numDays=30;
                break;
            case 6:
                numDays=30;
                break;
            case 9:
                numDays=30;
                break;
            case 11:
                numDays = 30;
                break;

            default: numDays = 31;  // all the rest
        }
        return numDays;
    }

}
