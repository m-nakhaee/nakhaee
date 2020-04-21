package products;

public class SportShoe extends Shoe {
    private SportType type;

    public SportShoe() {
    }

    public SportShoe(SportType type) {
        this.type = type;
    }

    public SportType getType() {
        return type;
    }

    public void setType(SportType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SportShoe " + getName() + " {" +
                "type=" + type +
                '}' +"\nthe price is: " + getPrice();
    }
}
