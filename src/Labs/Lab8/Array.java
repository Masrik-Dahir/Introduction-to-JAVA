/****************************************************************************
 * Copyright 2020, Masrik Dahir, All Right Reserved
 ****************************************************************************/
package Labs.Lab8;

public class Array {
    public static void main(String[] args) {
        String[] tokens = args[0].split(",");
        String[] customerName = new String[8];

        for (int i=0; i<tokens.length; i++)
            customerName[i] = tokens[i];
        for (String i : customerName){
            System.out.println(i);
        }
        System.out.println();
        customerName[6] = customerName[4];
        customerName[7] = customerName[5];
        customerName[4] = "Rick";
        customerName[5] = "Jessica";

        for (String i : customerName){
            System.out.println(i);
        }
        System.out.println();
        int middle = customerName.length / 2;

        String temp;
        int j = customerName.length -1;

        for (int i = 0 ; i < middle; i++) {
            temp = customerName[i];
            customerName[i] = customerName[j];
            customerName[j] = temp;
            j--;
        }
        for (String i : customerName) {
            System.out.println(i);
        }
        System.out.println();
        for (int i=0; i<customerName.length; i++)
            if (customerName[i].equals("Rick")&& customerName[i]!=null) {
                for(int k=i;k<customerName.length-1;k++){
                    customerName[k] = customerName[k + 1];
                    customerName[k + 1] = "null";
                }
            }
        for (String i : customerName) {
            System.out.println(i);
        }


    }
}
