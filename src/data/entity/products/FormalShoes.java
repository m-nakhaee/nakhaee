package data.entity.products;

import javax.persistence.Entity;

@Entity(name = "formal_shoes")
public class FormalShoes extends Shoes {
    private int type;

    public FormalShoes() {
    }

    public FormalShoes(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\n+ #Formal_shoes(" + getId() + ")" + "{" + "name: " + getName() + ", type: " + getType() + '}'
                + "\nsize: " + getSize() + ", gender: " + getGender()
                + "\nthe price is: " + getPrice() + ", count: " + getCount();
    }
}
