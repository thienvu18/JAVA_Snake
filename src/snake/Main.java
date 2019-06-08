package snake;

import snake.controllers.GameController;
import snake.models.Game;

public class Main {
    public static void main(String[] args) {
        GameController.getInstance(new Game());
    }
}