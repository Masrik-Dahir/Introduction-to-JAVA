package Labs.Lab13;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lab13 {
    public static void main(String[] args){
        try {
            String inputFileName = args[0];
            File inputFile = new File(inputFileName);
            String outputFileName = args[1];
            PrintWriter out = new PrintWriter(outputFileName);
            File ouputFile = new File(outputFileName);
            processFile(inputFile, ouputFile);
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        } catch(Exception e){
            System.out.println("Other problem");
        }
        System.out.println();
    }

    public static void processFile(File inputFile,
                                   File outputFile) throws FileNotFoundException {
        Scanner in = new Scanner(inputFile);

        int highest_goal = 0;
        int lowest_goal = 100;
        String highest_name = "";
        String lowest_name = "";
        double sum_shot = 0;
        double avg_shot = 0;

        double numShots = 0;
        int count = 0;
        //in.nextLine();
        PrintWriter out = new PrintWriter(outputFile.getPath());
        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] tokens = line.split(",");
            int numGoals = 0;
            String name = tokens[0];
            try {
                numGoals = Integer.parseInt(tokens[1]);
                numShots = Double.parseDouble(tokens[2]);
                sum_shot+=numShots;
                Team team = new Team(name,numGoals,numShots);

            } catch (NumberFormatException e) {
                System.out.println("Bad data: " + tokens[0] + " " + tokens[1] + " " + tokens[2]);
            }
            Team team = new Team();
            if(numGoals>highest_goal){
                highest_goal = numGoals;
                highest_name = name;
            }
            if(numGoals<lowest_goal){
                lowest_goal = numGoals;
                lowest_name = name;
            }
            count++;

        }

        avg_shot = sum_shot/count;
        //CB - You should not create the Team object here.  You create it
        //In your while loop for each line you read.  You can simply printout
        //the lowest, highest, and average from the variables above
        //You need more output, see the specifications
        //out.println(team_highest.max()+"\n"+team_lowest.min()+"\n"+"Average shots per game: "+team_highest.getNumShots());
        out.printf("Maximum goals Scored: %s %d\n",highest_name,highest_goal);
        out.printf("Minimum goals Scored: %s %d\n",lowest_name,lowest_goal);
        out.printf("Average shots per game: %.3f",avg_shot);

        in.close();
        out.close();

    }
}
