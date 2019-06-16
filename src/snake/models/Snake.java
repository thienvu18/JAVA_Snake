package snake.models;

import snake.utils.constraints.Constrains;
import snake.utils.enums.Direction;

import java.awt.*;
import java.util.LinkedList;

public class Snake implements Drawable, Runnable {
    private LinkedList<Point> body;
    private Direction direction;
    private volatile boolean running = true;
    private MoveBehavior moveBehavior;

    public Snake() {
        this.body = new LinkedList<>();
        this.direction = Direction.EAST;
        moveBehavior = new Level1MoveBehavior();

        body.add(new Point(7, 10));
        body.add(new Point(8, 10));
        body.add(new Point(9, 10));
        body.add(new Point(10, 10));

    }

    public void setMoveBehavior(MoveBehavior moveBehavior) {
        this.moveBehavior = moveBehavior;
    }

    public LinkedList<Point> getBody() {
        return body;
    }

    private Point getHead() {
        return this.body.getLast();
    }

    private Point getTail() {
        return this.body.getFirst();
    }

    private void removeTail() {
        this.body.removeFirst();
    }

    private void removeHead() {
        this.body.removeLast();
    }

    private void addHead(Point p) {
        this.body.addLast(p);
    }

    private void addTail(Point p) {
        this.body.addFirst(p);
    }

    synchronized void turn(Direction newDirection) {
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
        this.addHead(this.moveBehavior.next(this.getHead(), direction));
    }

    public synchronized void stepBack() {
        switch (direction) {

            case NORTH:
                direction = Direction.SOUTH;
                break;
            case SOUTH:
                direction = Direction.NORTH;
                break;
            case EAST:
                direction = Direction.WEST;
                break;
            case WEST:
                direction = Direction.EAST;
                break;
        }
        this.removeHead();
        this.addTail(this.moveBehavior.next(this.getTail(), direction));
    }

    public synchronized void start() {
        Thread thread = new Thread(this);
        thread.start();
        running = true;
    }

    public synchronized void stop() {
        running = false;
    }

    public synchronized boolean isHitWall(int boardWidth, int boardHeight) {
        Point head = this.getHead();
        return head.x == -1 || head.x == boardWidth || head.y == -1 || head.y == boardHeight;
    }

    public synchronized boolean isHitSelf() {
        Point head = this.getHead();
        for (int i = 0; i < body.size() - 1; i++) {
            if (body.get(i).equals(head))
                return true;

        }
        return false;
    }

    public synchronized boolean isHitApple(Apple apple) {
        Point head = this.getHead();
        return head.equals(apple.getPoint());
    }

    public synchronized boolean isHitBoom(Boom boom) {
        Point head = this.getHead();
        return head.equals(boom.getPoint());
    }

    public synchronized void eat()  {
        this.addTail(this.moveBehavior.next(this.getHead(), direction));
    }

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(Constrains.POINT_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
//// vẽ snake
//        for (int i = 0; i < body.size() - 1; i++) {
//
//            g.drawLine(body.get(i).getCenterXInPixel(), body.get(i).getCenterYInPixel(),
//                    body.get(i + 1).getCenterXInPixel(), body.get(i + 1).getCenterYInPixel());
//
//        }
//        g.drawLine(body.getLast().getCenterXInPixel(), body.getLast().getCenterYInPixel(),
//                body.getLast().getCenterXInPixel(), body.getLast().getCenterYInPixel());

        g.setColor(new Color(68, 114, 230));
        for (int i = 0; i < body.size() - 1; i++) {
            g.drawRect(body.get(i).getCenterXInPixel(), body.get(i).getCenterYInPixel(),
                    5, 5);
        }
        g.setColor(Color.RED);
        g.drawRect(body.getLast().getCenterXInPixel(), body.getLast().getCenterYInPixel(),
                5, 5);
//        g.drawRect(body.getLast().getCenterXInPixel(), body.getLast().getCenterYInPixel(),
//                10, 10);
//        }
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (running) {

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
