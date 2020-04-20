package products;

public class Radio extends Electrical {
    private int antennaPower;
    private RadioType type;

    public Radio(int antennaPower, RadioType type) {
        this.antennaPower = antennaPower;
        this.type = type;
    }

    public int getAntennaPower() {
        return antennaPower;
    }

    public void setAntennaPower(int antennaPower) {
        this.antennaPower = antennaPower;
    }

    public RadioType getType() {
        return type;
    }

    public void setType(RadioType type) {
        this.type = type;
    }
}
