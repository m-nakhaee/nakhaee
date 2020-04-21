package products;

public class Book extends ReadableStuff {
    private String writer;

    public Book() {
    }

    public Book(String writer) {
        this.writer = writer;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    @Override
    public String toString() {
        this.getDetails();
        return "\n+ Book{" + "title: " + getName() +
                ", writer: " + writer +
                '}' + "\nthe price is: " + getPrice() + ", stock number: ";
    }


}
