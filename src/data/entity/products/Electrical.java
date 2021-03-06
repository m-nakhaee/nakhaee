package data.entity.products;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Electrical extends Product {
    private int wat;
    private int voltage;

    public int getWat() {
        return wat;
    }

    public void setWat(int wat) {
        this.wat = wat;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

}
