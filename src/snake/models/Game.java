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
    private GameBoard gameBoard;
    private Apple apple;
    private ArrayList<Boom> booms;
    private Snake snake;
    private DeadBehavior deadBehavior;
    private int score;
    private int level;
    private HighScore highScore = HighScore.getInstance();
    private boolean[][] occupiedCells;

    public Game() {
        state = GameState.INITIALIZING;
        init();

        Thread thread = new Thread(this);
        thread.start();
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public GameState getState() {
        return state;
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

    public void setDeadBehavior(DeadBehavior deadBehavior) {
        this.deadBehavior = deadBehavior;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String getHighScore() {
        StringBuilder res = new StringBuilder();

        for (String highScore : highScore.get()
        ) {
            res.append(highScore);
            res.append("\n");
        }

        return res.toString();
    }

    private void init() {
        this.views = new ArrayList<>();
        gameBoard = new GameBoard(Constrains.BOARD_COL, Constrains.BOARD_ROW);
        occupiedCells = new boolean[Constrains.BOARD_COL][Constrains.BOARD_ROW];
        generateApple();
        snake = new Snake();
        if (booms != null) {
            for (Boom b :
                    booms) {
                b.destroy();
            }
        }
        booms = new ArrayList<>();
        score = 0;


        state = GameState.INITIALIZED;


    }

    private synchronized void generateApple() {
        apple = new Apple();
        Point p = apple.getPoint();
        while (occupiedCells[p.x][p.y]) {
            apple = new Apple();
        }

    }

    private synchronized void updateOccupied() {
//        occupiedCells = new boolean[Constrains.BOARD_COL][Constrains.BOARD_ROW];

        for (int i = 0; i < Constrains.BOARD_ROW; i++) {
            for (int j = 0; j < Constrains.BOARD_COL; j++) {
                occupiedCells[i][j] = false;
            }
        }

        for (Point p : snake.getBody()) {
            if(p.x < 0 || p.x >= Constrains.BOARD_ROW || p.y <0 || p.y >= Constrains.BOARD_COL) continue;
            occupiedCells[p.x][p.y] = true;
        }

        for (Boom b : booms) {
            Point p = b.getPoint();
            occupiedCells[p.x][p.y] = true;
        }
        Point p = apple.getPoint();
        occupiedCells[p.x][p.y] = true;

    }

    private synchronized void gamePlay() {
        if (snake.isHitApple(apple)) {
            score++;
            snake.eat();
            apple.destroy();
            generateApple();
        }
        if (deadBehavior.isDead(this)) {
            snake.stepBack();
            stop();
        }
        this.notifyModelChange();
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
    public void newGame() {
        if (state == GameState.PAUSING || state == GameState.PLAYING) {
            stop();
        }
        if (state == GameState.STOPPED || state == GameState.INITIALIZED) {
            state = GameState.INITIALIZING;
            init();
        }
    }

    @Override
    public synchronized void start() {
        if (state == GameState.INITIALIZED) {
            if (level == 3) {
                for (int i = 0; i < 5; i++) {

                    Boom b = new Boom();
                    Point p = b.getPoint();
                    while (occupiedCells[p.x][p.y]) {
                        b = new Boom();
                    }
                    booms.add(b);
                }

            }
        }
        if (state == GameState.INITIALIZED || state == GameState.PAUSING) {
            snake.start();
            state = GameState.PLAYING;
        }
    }

    @Override
    public synchronized void stop() {
        if (state == GameState.PLAYING || state == GameState.PAUSING) {
            state = GameState.STOPPED;
            snake.stop();
            highScore.save(this.score);
        }
    }

    @Override
    public synchronized void pause() {
        if (state == GameState.PLAYING || state == GameState.INITIALIZED) {
            state = GameState.PAUSING;
            snake.stop();
        }
    }

    @Override
    public synchronized void resume() {
        if (state == GameState.PAUSING) {
            this.start();
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

        while (true) {

            if (state == GameState.INITIALIZING) {
                init();
            } else if (state == GameState.PLAYING) {
                gamePlay();
                updateOccupied();
            }

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
