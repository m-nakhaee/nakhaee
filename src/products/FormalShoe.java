package products;

public class FormalShoe extends Shoe {
    private FormalType type;

    public FormalShoe() {
    }

    public FormalShoe(FormalType type) {
        this.type = type;
    }

    public FormalType getType() {
        return type;
    }

    public void setType(FormalType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\n#FormalShoe " + getName() + " {" +
                ", type: " + type +
                '}' + "\nthe price is: " + getPrice() + ", number: ";
    }
}
