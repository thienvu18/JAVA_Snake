package snake.models;

import java.util.Random;

public class Point {
    public int x;
    public int y;

    public Point() {}

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Point getRandomPoint(int xMax, int yMax) {
        Random rd = new Random();
        int x = rd.nextInt(xMax);
        int y = rd.nextInt(yMax);

        return new Point(x, y);
    }
}
