package snake.controllers;

import java.awt.image.BufferedImage;

public interface Controller{
    void newGame();
    void pause();
    void resume();

    void turnSnakeLeft();
    void turnSnakeRight();
    void turnSnakeUp();
    void turnSnakeDown();

}
