package products;

public class SportShoes extends Shoes {
    private SportType type;

    public SportShoes(SportType type) {
        this.type = type;
    }

    public SportType getType() {
        return type;
    }

    public void setType(SportType type) {
        this.type = type;
    }
}
