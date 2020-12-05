package Labs.Lab11;

import java.util.ArrayList;

public class Bookshelf {
    /**
     *Private instances of Bookshelf
     */
    private int size;
    private ArrayList<Book> books;
    /**
     *Default constructor
     */
    public Bookshelf(){
    this.size=2;
    this.books=new ArrayList<>();
    }
    /**
     *parameter constructor
     */
    public Bookshelf(int size){
        this.size = size;
        this.books=new ArrayList<>();
    }
    /**
     *This method returns the value of the variable size
     */
    public int getSize() {
        return size;
    }
    /**
     *This method returns the value of the variable books
     */
    public ArrayList<Book> getBooks() {
        return books;
    }
    /**
     *This method add a new Book value to variable books
     */
    public void addBook(Book book){
        if(books.size()<size){
            books.add(book);
        }
    }
    /**
     *This method remove a new Book value to variable books
     */
    public Book removeBook(){
        if(books.size()!=0){
            return books.remove(0);
        }
        return null;
    }
    /**
     *This method remove all the values of variable books
     */
    public void emptyBookshelf(){
        books.removeAll(books);
    }
}


