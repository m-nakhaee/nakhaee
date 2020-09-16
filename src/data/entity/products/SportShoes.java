package data.entity.products;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "sport_shoes")
public class SportShoes extends Shoes {
    @Column(name = "type")
    private int typeOfSportShoe;

    public SportShoes() {
    }

    public SportShoes(int type) {
        this.typeOfSportShoe = type;
    }

    public int getTypeOfSportShoe() {
        return typeOfSportShoe;
    }

    public void setTypeOfSportShoe(int type) {
        this.typeOfSportShoe = type;
    }

    @Override
    public String toString() {
        return "\n+ #Sport_shoes(" + getId() + ")" + "{" + "name: " + getName() + ", type: " + getTypeOfSportShoe() + '}'
                + "\nsize: " + getSize() + ", gender: " + getGender()
                + "\nthe price is: " + getPrice() + ", count: " + getCount();
    }
}
