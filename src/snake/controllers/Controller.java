package snake.controllers;

import snake.views.View;

public interface Controller {
    void newGame();

    void play();

    void pause();

    void resume();

    void highScore();

    void quit();

    void turnSnakeLeft();

    void turnSnakeRight();

    void turnSnakeUp();

    void turnSnakeDown();

    void setLevel(int level);

    void chooseLevel();

    void changeView(View v);

    void changeHelpView();
}
