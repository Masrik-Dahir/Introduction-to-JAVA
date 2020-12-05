package Labs.Lab12;

public class Card {
    private String name;


    public Card() {
        this.setName("");
    }
    public Card(String name) {
        this.setName(name);
    }
    public void setName() {
        this.name=name;
    }
    public String getName() {
        return name;
    }

    public String toString() {
        return "Card Holder: "+getName();
    }
    public void setName(String name) {
        this.name = name;
    }

}
