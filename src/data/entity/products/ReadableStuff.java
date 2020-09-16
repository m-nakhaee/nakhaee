package data.entity.products;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity(name = "readable_stuffs")
@Inheritance(strategy = InheritanceType.JOINED)
public class ReadableStuff extends Product {
    @Column(name = "publisher_name")
    private String publisherName;
    @Column(name = "publisher_number")
    private int publisherNumber;

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public int getPublisherNumber() {
        return publisherNumber;
    }

    public void setPublisherNumber(int publisherNumber) {
        this.publisherNumber = publisherNumber;
    }

}
