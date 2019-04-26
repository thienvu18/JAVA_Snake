package snake.views;

import snake.controllers.Controller;
import snake.models.Model;

public class GameView {
    private Model game;
    private Controller controller;

    public GameView(Model game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }
}
