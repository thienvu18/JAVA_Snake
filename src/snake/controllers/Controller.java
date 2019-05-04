package snake.controllers;

import snake.views.View;

import java.awt.image.BufferedImage;

public interface Controller{
    void newGame();
    void pause();
    void resume();
    void quit();

    void turnSnakeLeft();
    void turnSnakeRight();
    void turnSnakeUp();
    void turnSnakeDown();

    void chooseLevel();
    void changeView(View view);
}
