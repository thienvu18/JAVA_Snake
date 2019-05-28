package snake.models;

import snake.models.abstractModels.Model;
import snake.utils.constraints.Constrains;
import snake.utils.enums.Direction;
import snake.utils.enums.GameState;
import snake.views.GameView;
import snake.views.View;

import java.awt.*;
import java.util.ArrayList;

public class Container implements Model, Runnable {
    private ArrayList<View> views;
    private GameState state;
    private GameBoard gameBoard;
    private Apple apple;
    private ArrayList<Boom> booms;
    private Snake snake;
    private DeadBehavior deadBehavior;
    private int score = 0;
    private int level;

    public int getLevel() {
        return level;
    }

    @Override
    public GameBoard getGameBoard() {
        return gameBoard;
    }

    @Override
    public ArrayList<Boom> getBooms() {
        return booms;
    }

    @Override
    public Snake getSnake() {
        return snake;
    }

    public void setLevel(int level) {
        this.level = level;
        if (level == 3) {
            for (int i = 0; i < 5; i++) {
                booms.add(new Boom());
            }

        }
    }

    public void setDeadBehavior(DeadBehavior deadBehavior) {
        this.deadBehavior = deadBehavior;
    }

    public int getScore() {
        return score;
    }

    public Container() {
        this.views = new ArrayList<>();
        gameBoard = new GameBoard(Constrains.BOARD_COL, Constrains.BOARD_ROW);
        apple = new Apple();
        snake = new Snake();
        booms = new ArrayList<>();
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
        Thread thread = new Thread(this);
        thread.start();


    }

    @Override
    public synchronized void stop() {
        if (state == GameState.PLAYING || state == GameState.PAUSING) {
            state = GameState.STOPPED;
            snake.stop();
        }
    }

    @Override
    public synchronized void pause() {
        if (state == GameState.PLAYING || state == GameState.INITIALIZED) {
            state = GameState.PAUSING;
            snake.stop();
            System.out.println(state);
        }
    }

    @Override
    public synchronized void resume() {
        if (state == GameState.PAUSING) {
            state = GameState.PLAYING;
            System.out.println(state);
            snake.start();
        }
    }

    @Override
    public void draw(Graphics2D g) {

        gameBoard.draw(g);
        apple.draw(g);
        snake.draw(g);
        for (Boom boom : booms) {
            boom.draw(g);
        }
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();
        System.out.println(state+ "run");
        while (state == GameState.PLAYING ||state == GameState.INITIALIZED ) {

            if (snake.isHitApple(apple)) {
                score++;
                snake.addTail(snake.next());
                apple = new Apple();
                this.notifyModelChange();
                
                System.out.println("Ăn");
                System.out.println("Diem: " + score);
            }
            if (deadBehavior.isDead(this)) {
                snake.stop();
                System.out.println("Game over");
                break;
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
