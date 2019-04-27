package snake.models;

import snake.utils.constraints.Constrains;
import snake.utils.enums.Direction;

import java.awt.*;
import java.util.LinkedList;

public class Snake implements Drawable, Runnable {
    private LinkedList<Point> body;
    private Direction direction;

    public Snake() {
        this.body = new LinkedList<>();
        this.direction = Direction.EAST;

        body.add(new Point(7, 10));
        body.add(new Point(8, 10));
        body.add(new Point(9, 10));
        body.add(new Point(10, 10));

    }

    private Point getHead() {
        return this.body.getLast();
    }

    private void removeTail() {
        this.body.removeFirst();
    }

    private void addHead(Point p) {
        this.body.addLast(p);
    }

    private Point next() {
        Point p = new Point();

        switch (direction) {
            case NORTH:
                p.y = this.getHead().y - 1;
                p.x = this.getHead().x;
                break;
            case SOUTH:
                p.y = this.getHead().y + 1;
                p.x = this.getHead().x;
                break;
            case WEST:
                p.y = this.getHead().y;
                p.x = this.getHead().x - 1;
                break;
            case EAST:
                p.y = this.getHead().y;
                p.x = this.getHead().x + 1;
                break;
        }
        return p;
    }

    public synchronized void turn(Direction newDirection) {
        if (newDirection == direction) return;

        switch (newDirection) {
            case NORTH:
                if (direction != Direction.SOUTH) {
                    direction = newDirection;
                }
                break;
            case SOUTH:
                if (direction != Direction.NORTH) {
                    direction = newDirection;
                }
                break;
            case WEST:
                if (direction != Direction.EAST) {
                    direction = newDirection;
                }
                break;
            case EAST:
                if (direction != Direction.WEST) {
                    direction = newDirection;
                }
                break;
        }
    }

    private synchronized void move() {
        this.removeTail();
        this.addHead(this.next());
    }

    public synchronized void start() {
        Thread thread = new Thread(this);
        thread.start();
    }


    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(Constrains.POINT_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(new Color(68, 114, 230));

        for (int i = 0; i < body.size() - 1; i++) {
            g.drawLine(body.get(i).getCenterXInPixel(), body.get(i).getCenterYInPixel(), body.get(i + 1).getCenterXInPixel(), body.get(i + 1).getCenterYInPixel());
        }
        g.drawLine(body.getLast().getCenterXInPixel(), body.getLast().getCenterYInPixel(), body.getLast().getCenterXInPixel(), body.getLast().getCenterYInPixel());
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (true) {

            this.move();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = 1000 / Constrains.SNAKE_SPEED - timeDiff;
            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ignored) {
            }
            beforeTime = System.currentTimeMillis();
        }
    }
}
