package snake.models;

import snake.utils.constraints.Constrains;
import snake.utils.enums.Direction;

public class SnakePoint {
    private Point point;
    private Direction direction;
    private int speed;
    private int dx = 0, dy = 0;

    public SnakePoint() {
        this.point = new Point();
    }

    public SnakePoint(int x, int y, Direction direction, int speed) {
        this.point = new Point(x, y);
        this.direction = direction;
        this.speed = speed;
    }

    public SnakePoint(Point point, Direction direction, int speed) {
        this.point = point;
        this.direction = direction;
        this.speed = speed;
    }

    public void setSpeed(int speed) {
        if (speed > 0) {
            this.speed = speed;
        }
    }

    public void move() {
        if (Math.abs(dx) < Constrains.POINT_SIZE && Math.abs(dy) < Constrains.POINT_SIZE) {
            switch (direction) {
                case NORTH:
                    dy -= speed;
                    break;
                case SOUTH:
                    dy += speed;
                    break;
                case WEST:
                    dx -= speed;
                    break;
                case EAST:
                    dx += speed;
                    break;
            }
            return;
        }

        if (dx >= Constrains.POINT_SIZE) {
            dx = 0;
            point.x++;
        }

        if (dx < 0) {
            dx = 0;
            point.x--;
        }

        if (dy >= Constrains.POINT_SIZE) {
            dy = 0;
            point.y++;
        }
        if (dy < 0) {
            dy = 0;
            point.y--;
        }
    }

    public int getX() {
        return this.point.x;
    }

    public int getY() {
        return this.point.y;
    }

    public int getCenterXInPixel() {
        return point.getCenterXInPixel() + dx;
    }

    public int getCenterYInPixel() {
        return point.getCenterYInPixel() + dy;
    }

    public void setDirection(Direction newDirection) {
        if (newDirection == direction) return;

        switch (newDirection) {
            case NORTH:
                if (direction == Direction.EAST) {
                    dx = Constrains.POINT_SIZE + 1;
                } else dx = 0;
                break;
            case SOUTH:
                if (direction == Direction.EAST) {
                    dx = Constrains.POINT_SIZE + 1;
                } else dx = 0;
                break;
            case WEST:
                if (direction == Direction.SOUTH) {
                    dy = Constrains.POINT_SIZE + 1;
                } else dy = 0;
                break;
            case EAST:
                if (direction == Direction.SOUTH) {
                    dy = Constrains.POINT_SIZE + 1;
                } else dy = 0;
                break;
        }

        direction = newDirection;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Point)) {
            return false;
        }

        SnakePoint c = (SnakePoint) obj;

        return point.equals(c.point);
    }

    @Override
    public int hashCode() {
        return point.hashCode();
    }
}
