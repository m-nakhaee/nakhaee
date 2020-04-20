package products;

public class FormalShoes extends Shoes {
    private FormalType type;

    public FormalShoes(FormalType type) {
        this.type = type;
    }

    public FormalType getType() {
        return type;
    }

    public void setType(FormalType type) {
        this.type = type;
    }

}
