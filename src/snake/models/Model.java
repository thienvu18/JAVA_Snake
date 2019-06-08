package snake.models;

import snake.models.Boom;
import snake.models.Drawable;
import snake.models.GameBoard;
import snake.models.Snake;
import snake.utils.enums.GameState;
import snake.views.View;

import java.util.ArrayList;

public interface Model extends Drawable {
    void addView(View view);

    void removeView(View view);

    void notifyModelChange();

    void start();

    void stop();

    void pause();

    void resume();

    int getScore();

    int getLevel();

    GameState getState();

    GameBoard getGameBoard();

    ArrayList<Boom> getBooms();

    Snake getSnake();
}
