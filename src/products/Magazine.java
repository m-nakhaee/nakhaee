package products;

public class Magazine extends ReadableStuff {
    private String concessionaire;
    private MagazineType type;

    public Magazine() {
    }

    public Magazine(String concessionaire, MagazineType type) {
        this.concessionaire = concessionaire;
        this.type = type;
    }

    public String getConcessionaire() {
        return concessionaire;
    }

    public void setConcessionaire(String concessionaire) {
        this.concessionaire = concessionaire;
    }

    public MagazineType getType() {
        return type;
    }

    public void setType(MagazineType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\n#Magazine{" + "title: " + getName() +
                ", concessionaire: " + concessionaire +
                ", type: " + type +
                '}' + "\nthe price is: " + getPrice() + ", stock number: ";
    }
}
