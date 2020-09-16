package data.entity.products;

import javax.persistence.Entity;

@Entity
public class Magazine extends ReadableStuff {
    private String concessionaire;
    private int type;

    public Magazine() {
    }

    public Magazine(String concessionaire, int type) {
        this.concessionaire = concessionaire;
        this.type = type;
    }

    public String getConcessionaire() {
        return concessionaire;
    }

    public void setConcessionaire(String concessionaire) {
        this.concessionaire = concessionaire;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\n#Magazine(" + getId() + ")\" + \"{" + "title: " + getName() + ", concessionaire: " + concessionaire +", type: " + type +'}'
                + "\npublisher: " + getPublisherName() + ", phone number: " + getPublisherNumber()
                + "\nthe price is: " + getPrice() + ", count: " + getCount();
    }
}
