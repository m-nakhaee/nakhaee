package data.dto.products;

public class ReadableStuff extends Product {
    private String publisherName;
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
