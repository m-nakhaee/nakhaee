package products;

public class TV extends Electrical {
    private int inch;
    private TVType type;

    public TV(int inch, TVType type) {
        this.inch = inch;
        this.type = type;
    }

    public int getInch() {
        return inch;
    }

    public void setInch(int inch) {
        this.inch = inch;
    }

    public TVType getType() {
        return type;
    }

    public void setType(TVType type) {
        this.type = type;
    }
}
