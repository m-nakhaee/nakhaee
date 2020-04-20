package products;

public class Book extends ReadableStuffs {
    private String writer;

    public Book(String writer) {
        this.writer = writer;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

}
