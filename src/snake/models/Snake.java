package snake.models;

import snake.utils.constraints.Constrains;
import snake.utils.enums.Direction;

import java.awt.*;
import java.util.LinkedList;

public class Snake implements Drawable, Runnable {
    private LinkedList<Point> body;
    private Direction direction;
    private volatile boolean running = true;

    public Snake() {
        this.body = new LinkedList<>();
        this.direction = Direction.EAST;

        body.add(new Point(7, 10));
        body.add(new Point(8, 10));
        body.add(new Point(9, 10));
        body.add(new Point(10, 10));

    }

    public Point getHead() {
        return this.body.getLast();
    }

    private void removeTail() {
        this.body.removeFirst();
    }

    private void addHead(Point p) {
        this.body.addLast(p);
    }

    public void addTail(Point p) {
        this.body.addFirst(p);
    }

    public Point next() {
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
        System.out.println("index x: " + p.x);
        if (p.x == Constrains.BOARD_COL) {
            System.out.println("đụng");
            p.x = 0;
        }
        if (p.x == 0 - 1) {
            p.x = Constrains.BOARD_COL;
        }
        if (p.y == Constrains.BOARD_ROW) {
            p.y = 0;
        }
        if (p.y == 0 - 1) {
            p.y = Constrains.BOARD_ROW;
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
        running = true;
    }

    public synchronized void stop() {
        running = false;
        System.out.println(running);
    }

    public synchronized boolean isHitWall(int boardWidth, int boardHeight) {
        Point head = this.getHead();
//        System.out.println(head.x);
//        System.out.println(head.y);
        if (head.x == 0 || head.x == boardWidth || head.y == 0 || head.y == boardHeight)
            return true;
        return false;
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

    @Override
    public void draw(Graphics2D g) {
        g.setStroke(new BasicStroke(Constrains.POINT_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g.setColor(new Color(68, 114, 230));
//// vẽ snake
//        for (int i = 0; i < body.size() - 1; i++) {
//
//            g.drawLine(body.get(i).getCenterXInPixel(), body.get(i).getCenterYInPixel(),
//                    body.get(i + 1).getCenterXInPixel(), body.get(i + 1).getCenterYInPixel());
//
//        }
//        g.drawLine(body.getLast().getCenterXInPixel(), body.getLast().getCenterYInPixel(),
//                body.getLast().getCenterXInPixel(), body.getLast().getCenterYInPixel());

        for (int i = 0; i < body.size(); i++) {
            g.drawRect(body.get(i).getCenterXInPixel(), body.get(i).getCenterYInPixel(),
                    5, 5);
        }
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
