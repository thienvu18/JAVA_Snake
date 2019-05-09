package snake.models;

import snake.utils.constraints.Constrains;

import java.util.Random;

public class Point {
    public int x;
    public int y;

    public Point() {
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getXInPixel() {
        return this.x * Constrains.POINT_SIZE;
    }

    public int getYInPixel() {
        return this.y * Constrains.POINT_SIZE;
    }

    public int getCenterXInPixel() {
        return this.x * Constrains.POINT_SIZE + Constrains.POINT_SIZE / 2;
    }

    public int getCenterYInPixel() {
        return this.y * Constrains.POINT_SIZE + Constrains.POINT_SIZE / 2;
    }

    public static Point getRandomPoint(int xMax, int yMax) {
        Random rd = new Random();
        int x = rd.nextInt(xMax);
        int y = rd.nextInt(yMax);

        return new Point(x, y);
    }

    @Override
    public boolean equals(Object obj) {
        // If the object is compared with itself then return true
        if (obj == this) {
            return true;
        }

        /* Check if o is an instance of Complex or not
          "null instanceof [type]" also returns false */
        if (!(obj instanceof Point)) {
            return false;
        }

        // typecast o to Complex so that we can compare data members
        Point c = (Point) obj;

        // Compare the data members and return accordingly
        return Integer.compare(x, c.x) == 0
                && Double.compare(y, c.y) == 0;

    }

    @Override
    public int hashCode() {
        return Integer.parseInt("" + x + y);
    }
}
