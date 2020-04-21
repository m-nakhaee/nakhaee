package products;

public class Shoe extends Product {
    private int size;
    private Gender gender;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public String getDetails() {
        return "size: " + size + ", gender: " + gender;
    }
}
