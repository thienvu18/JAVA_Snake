package snake.models;

import snake.utils.constraints.Constrains;
import snake.utils.enums.GameState;
import snake.views.View;

import java.awt.*;
import java.util.ArrayList;

public class Game implements Model, Runnable {
    private ArrayList<View> views;

    private GameState state;
    private Board board;
    private Apple apple;

    public Game() {
        this.views = new ArrayList<>();
        board = new Board(20, 20);
        apple = new Apple();

        state = GameState.INITIALIZED;
    }

    private synchronized void animation() {
        apple.animation();
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
        if (state == GameState.INITIALIZED) {
            Thread thread = new Thread(this);
            thread.start();
        }
        state = GameState.PLAYING;
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
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (state != GameState.STOPPED) {

            if (state == GameState.PLAYING) {
                animation();
                notifyModelChange();
            }

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = Constrains.DELAY - timeDiff;
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
