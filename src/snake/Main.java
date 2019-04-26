package snake;

import snake.controllers.Controller;
import snake.controllers.GameController;
import snake.models.Game;
import snake.views.Window;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
//        GamePlay game = new GamePlay();
//        GameController gameController = new GameController(game);
//
//        gameController.start();

        new GameController(new Game());
    }
}