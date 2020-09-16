package data.entity.products;

import javax.persistence.Entity;

@Entity
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
        return "\n+ #Book(" + getId() + ")" + "{" + "title: " + getName() + ", writer: " + writer + '}'
                + "\npublisher: " + getPublisherName() + ", phone number: " + getPublisherNumber()
                + "\nthe price is: " + getPrice() + ", count: " + getCount();
    }


}
