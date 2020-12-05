/****************************************************************************
 * Copyright 2020, Masrik Dahir, All Right Reserved
 ****************************************************************************/
package Labs.Lab7;

import java.util.Scanner;

public class Lab7 {

  public static void main(String[] args) {
    // variable declarations for part 1
    String title;
    String firstName;
    String lastName;
    Scanner in = new Scanner(System.in);

    // prompt for input for part 1
    System.out.print("Enter a title:");
    title = in.next();
    System.out.print("Enter your first name:");
    firstName = in.next();
    System.out.print("Enter a your last name:");
    lastName = in.next();

    // call the method for part 1
    greeting(title, firstName, lastName);

    // variable declarations for part 2
    int number1;
    int number2;

    // user prompts for part 2
    System.out.print("Enter first number:");
    number1 = in.nextInt();
    System.out.print("Enter second number:");
    number2 = in.nextInt();

    // call the methods max and sumTo inside the println statement
    System.out.println("The largest number is " + max(number1, number2));
    System.out.println("The sum of the numbers is " + sumTo(number1, number2));
  }// end of main method

  public static void greeting(String a, String b, String c) {
    System.out.println("\nDear " + a + " " + b + " " + c + ",\n");
  }

  public static int sumTo(int a, int b) {
    int c = Integer.max(a,b);
    int d = Integer.min(a,b);
    for (int i=d+1;i<=c;i++){
      d+=i;
    }
    return d;
  }

  public static int max(int a, int b){
    return Integer.max(a,b);
  }
 /******************** greeting method goes here*********************/
  
 
  
  
  
  /***********************end of method*************************/
  
  /******************** max method goes here*********************/
  
  

  
  
  /***********************end of method*************************/
   
  /******************** sumTo method goes here*********************/
  
  

  
  
  /***********************end of method*************************/
}
