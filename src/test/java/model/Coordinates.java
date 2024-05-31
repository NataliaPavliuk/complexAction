package model;

import java.util.Objects;

public class Coordinates {

    private String xCoordinate;
    private String yCoordinate;

    public Coordinates(String xCoordinate, String yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getIntXCoordinate() {
        return Integer.parseInt(xCoordinate);
    }

    public void setXCoordinate(String xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getIntYCoordinate() {
        return Integer.parseInt(yCoordinate);
    }

    public void setYCoordinate(String yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinates)) return false;
        Coordinates that = (Coordinates) o;
        return Objects.equals(xCoordinate, that.xCoordinate) && Objects.equals(yCoordinate, that.yCoordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate);
    }

    @Override
    public String toString() {
        return "xCoordinate='" + xCoordinate + '\'' + ", yCoordinate='" + yCoordinate;
    }
}
