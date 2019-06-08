package snake.controllers;


import javax.swing.JOptionPane;

import snake.models.Game;
import snake.models.HighScore;
import snake.models.Level1DeadBehavior;
import snake.models.Level2DeadBehavior;
import snake.models.Level3DeadBehavior;
import snake.utils.constraints.Constrains;
import snake.views.GameView;
import snake.views.HelpView;
import snake.views.LevelView;
import snake.views.MenuView;
import snake.views.View;
import snake.views.Window;

public class GameController implements Controller {

    private Game game;
    private Window rootView;
    public GameController(Game game) {
        this.game = game;
        rootView = Window.getInstance("Snake Game", Constrains.WIDTH, Constrains.HEIGHT);
        View menuView = new MenuView(game, this);
        rootView.changeView(menuView);
    }

    @Override
    public void newGame() {
        game.newGame();
        View menuView = new MenuView(game, this);
        rootView.changeView(menuView);
    }

    @Override
    public void play() {
        View gameView = new GameView(game, this);
        rootView.changeView(gameView);
        switch (game.getLevel()) {
            case 1:
                game.setDeadBehavior(new Level1DeadBehavior());
                break;
            case 2:
                game.setDeadBehavior(new Level2DeadBehavior());
                break;
            case 3:
                game.setDeadBehavior(new Level3DeadBehavior());

                break;
            default:
                game.setDeadBehavior(new Level1DeadBehavior());
                break;
        }
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
    public void highScore() {

		if (JOptionPane.showConfirmDialog(rootView, "High Score:\n" + game.getHighScore(), "High Score",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION) == JOptionPane.DEFAULT_OPTION) {
        }
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
        game.turnSnakeLeft();
    }

    @Override
    public synchronized void turnSnakeRight() {
        game.turnSnakeRight();
    }

    @Override
    public synchronized void turnSnakeUp() {
        game.turnSnakeUp();
    }

    @Override
    public synchronized void turnSnakeDown() {
        game.turnSnakeDown();
    }

    @Override
    public void setLevel(int level) {
        this.game.setLevel(level);
    }


    @Override
    public void chooseLevel() {
        View levelView = new LevelView(rootView.getCurrentPanel(), game, this);
        rootView.changeView(levelView);
    }

    @Override
    public void changeView(View v) {
        rootView.changeView(v);
    }

    @Override
    public void changeHelpView() {
        View helpView = new HelpView(rootView.getCurrentPanel(), game, this);
        rootView.changeView(helpView);
    }

}
