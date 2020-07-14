package data.dto.products;

public class TV extends Electrical {
    private int inch;
    private int type;

    public TV() {
    }

    public TV(int inch, int type) {
        this.inch = inch;
        this.type = type;
    }

    public int getInch() {
        return inch;
    }

    public void setInch(int inch) {
        this.inch = inch;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "\n+ #TV(" + getId() + ")" + "{" + "name: " + getName() + ", inch: " + getInch()
                + ", type: " + getType() + '}'
                + "\nwat: " + getWat() + ", voltage: " + getVoltage()
                + "\nthe price is: " + getPrice() + ", count: " + getCount();
    }
}
