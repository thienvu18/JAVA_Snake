package snake.controllers;


import snake.models.Game;
import snake.models.Model;
import snake.views.GameView;
import snake.views.MenuView;
import snake.views.View;
import snake.views.Window;

public class GameController implements Controller {

    private Model game;
    private Window rootView;

    public GameController(Game game) {
        this.game = game;
        rootView = Window.getInstance("Snake game", 600, 600);
        View menuView = new MenuView(game, this);
        rootView.changeView(menuView);
        game.start();
    }

    @Override
    public void newGame() {
        View gameView = new GameView(game, this);
        rootView.changeView(gameView);
    }

    @Override
    public void pause() {
        game.pause();
        System.out.println("Paused");
    }

    @Override
    public void resume() {
        game.resume();
        System.out.println("Resumed");
    }
}
