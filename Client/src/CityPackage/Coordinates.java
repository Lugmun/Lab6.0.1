package CityPackage;

import java.util.ArrayList;
import java.util.List;

public class Coordinates  implements Comparable<Coordinates> {
    private double x;
    private Integer y; //Поле не может быть null
    List<Object> list = new ArrayList<>(2);

    public Coordinates(double x, Integer y) {
        this.x = x; list.add(0, x);
        this.y = y; list.add(1, y);
    }

    public void setX(double x) {
        this.x = x; list.add(0, x);
    }

    public void setY(Integer y) {
        this.y = y; list.add(1, y);
    }

    public double getX() {
        return (double) list.get(0);
    }

    public Integer getY() {
        return (Integer) list.get(1);
    }

    public void setCoordinates(String xstr, String ystr) {
        list.add(0, Double.parseDouble(xstr)) ;
        list.add(1, Integer.valueOf(ystr));
    }

    public static Coordinates valueOf(Coordinates coo) {
        if (coo == null) {
            return new Coordinates(0,0);
        } else {
            return coo;
        }
    }

    @Override
    public int compareTo(Coordinates coo) {
        if ((this.getX() == (coo.getX()) && (this.getY().equals(coo.getY())))) {
            return 0;
        } else if (Double.valueOf(this.getY()) - Double.valueOf(coo.getY()) > 0) {
            return 1;
        } else {
            return -1;
        }
    }


    public String toString() {
        return x+";"+y;
    }
}

