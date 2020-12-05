/****************************************************************************
 * Copyright 2020, Masrik Dahir, All Right Reserved
 ****************************************************************************/
package Labs.Lab11;

public class Lab11 {

    public static void main(String[] x){
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book("Greek world","James");
        book1.setAuthor("Kairos");
        book1.setTitle("Artifacts of Asia");

        System.out.println(book1.toString());
        System.out.println(book2.toString());
        System.out.println(book3.toString());
        Bookshelf shelf = new Bookshelf();
        shelf.addBook(book1);
        shelf.addBook(book2);
        shelf.addBook(book3);

        for(Book a: shelf.getBooks()){
            System.out.println(a.toString());
        }
        shelf.emptyBookshelf();
        for(Book a: shelf.getBooks()){
            System.out.println(a.toString());
        }





    }
}
