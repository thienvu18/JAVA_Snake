package snake.models;

import snake.utils.constraints.Constrains;
import snake.utils.enums.Direction;
import snake.utils.enums.GameState;
import snake.views.View;

import java.awt.*;
import java.util.ArrayList;

public class Game implements Model, Runnable {
    private ArrayList<View> views;

    private GameState state;
    private Board board;
    private Apple apple;
    private Snake snake;

    public Game() {
        this.views = new ArrayList<>();
        board = new Board(Constrains.BOARD_COL, Constrains.BOARD_ROW);
        apple = new Apple();
        snake = new Snake();

        state = GameState.INITIALIZED;
    }

    public synchronized void turnSnakeLeft() {
        Direction newDirection = Direction.WEST;
        snake.turn(newDirection);
    }

    public synchronized void turnSnakeRight() {
        Direction newDirection = Direction.EAST;
        snake.turn(newDirection);
    }


    public synchronized void turnSnakeUp() {
        Direction newDirection = Direction.NORTH;
        snake.turn(newDirection);
    }

    public synchronized void turnSnakeDown() {
        Direction newDirection = Direction.SOUTH;
        snake.turn(newDirection);
    }


    @Override
    public void addView(View view) {
        views.add(view);
    }

    @Override
    public void removeView(View view) {
        views.remove(view);
    }

    @Override
    public void notifyModelChange() {
        for (View v : views) {
            v.render();
        }
    }

    @Override
    public synchronized void start() {
        snake.start();
        apple.start();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public synchronized void stop() {
        if (state == GameState.PLAYING || state == GameState.PAUSING) {
            state = GameState.STOPPED;
        }
    }

    @Override
    public synchronized void pause() {
        if (state == GameState.PLAYING) {
            state = GameState.PAUSING;
        }
    }

    @Override
    public synchronized void resume() {
        if (state == GameState.PAUSING) {
            state = GameState.PLAYING;
        }
    }

    @Override
    public void draw(Graphics2D g) {
        board.draw(g);
        apple.draw(g);
        snake.draw(g);
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (true) {

            if (snake.isHitApple(apple)) {
                System.out.println("Ăn");

                apple = new Apple();


            }

            if (snake.isHitWall(board.getWidth(), board.getHeight())) {
                snake.stop();
                System.out.println("Cắn tường");
                break;
            }

            if (snake.isHitSelf()) {
                snake.stop();
                System.out.println("Cắn thân");
            }

            this.notifyModelChange();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = 1000 / Constrains.FPS - timeDiff;
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
