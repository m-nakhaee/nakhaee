package dto.products;

public class Radio extends Electrical {
    private int antennaPower;
    private int type;

    public Radio() {
    }

    public Radio(int antennaPower, int type) {
        this.antennaPower = antennaPower;
        this.type = type;
    }

    public int getAntennaPower() {
        return antennaPower;
    }

    public void setAntennaPower(int antennaPower) {
        this.antennaPower = antennaPower;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\n+ #Radio(" + getId() + ")" + "{" + "name: " + getName() + ", antenna power: " + getAntennaPower()
                + ", type: " + getType() + '}'
                + "\nwat: " + getWat() + ", voltage: " + getVoltage()
                + "\nthe price is: " + getPrice() + ", count: " + getCount();
    }
}
