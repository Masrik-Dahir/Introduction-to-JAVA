package Labs.Lab11;

public class Book {
    /**
     *Private instances of Book
     */
    private String title;
    private String author;
    /**
     *Default constructor
     */
    public Book(){
        this.title = "Test";
        this.author = null;
    }
    /**
     *parameter constructor
     */
    public Book(String title, String author){
        this.title=title;
        this.author=author;
    }
    /**
     *This method returns the value of the variable title
     */
    public String getTitle() {
        return title;
    }
    /**
     *This method returns the value of the variable Author
     */
    public String getAuthor() {
        return author;
    }


    /**
     *This method set the value of the variable title
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     *This method set the value of the variable Author
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     *This method return the value of the variable title and author
     */
    public String toString(){
        return ("\""+title+"\" by " + author);
    }
}
