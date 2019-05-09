package snake.controllers;


import snake.models.Game;
import snake.models.Model;
import snake.utils.constraints.Constrains;
import snake.views.*;

import javax.swing.*;

public class GameController implements Controller {

    private Model game;
    private Window rootView;
    private int level;

    public GameController(Game game) {
        this.game = game;
        rootView = Window.getInstance("Snake game", Constrains.WIDTH, Constrains.HEIGHT);
        View menuView = new MenuView(game, this);
        rootView.changeView(menuView);

    }

    @Override
    public void newGame() {
        View gameView = new GameView(game, this);
        rootView.changeView(gameView);
        game.start();
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

    @Override
    public void quit() {

        if (JOptionPane.showConfirmDialog(rootView, "Are you sure to quit this game?", "Close game!",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }

    @Override
    public synchronized void turnSnakeLeft() {
        ((Game) game).turnSnakeLeft();
    }

    @Override
    public synchronized void turnSnakeRight() {
        ((Game) game).turnSnakeRight();
    }

    @Override
    public synchronized void turnSnakeUp() {
        ((Game) game).turnSnakeUp();
    }

    @Override
    public synchronized void turnSnakeDown() {
        ((Game) game).turnSnakeDown();
    }

    @Override
    public void setLevel(int level) {
        this.level = level;
    }


    @Override
    public void chooseLevel() {
        View levelView = new LevelView(rootView.getCurrentPanel(), game, this);
        rootView.changeView(levelView);

    }

    @Override
    public void changeView(View view) {
        rootView.changeView(view);
    }


}
