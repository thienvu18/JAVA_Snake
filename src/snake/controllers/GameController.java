package snake.controllers;


import snake.models.*;
import snake.utils.constraints.Constrains;
import snake.views.*;

import javax.swing.*;

public class GameController implements Controller {

    private Container container;
    private Window rootView;


    public GameController(Container container) {
        this.container = container;
        rootView = Window.getInstance("Snake Game", Constrains.WIDTH, Constrains.HEIGHT);
        View menuView = new MenuView(container, this);
        rootView.changeView(menuView);

    }

    @Override
    public void newGame() {
        View gameView = new GameView(container, this);
        rootView.changeView(gameView);
        switch (container.getLevel()) {
            case 1:
                container.setDeadBehavior(new Level1DeadBehavior());
                break;
            case 2:
                container.setDeadBehavior(new Level2DeadBehavior());
                break;
            case 3:
                container.setDeadBehavior(new Level3DeadBehavior());

                break;
            default:
                container.setDeadBehavior(new Level1DeadBehavior());
                break;
        }
        container.start();
    }

    @Override
    public void pause() {
        container.pause();
        System.out.println("Paused");
    }

    @Override
    public void resume() {
        container.resume();
        System.out.println("Resumed");
    }

    @Override
    public void highScore() {
        if (JOptionPane.showConfirmDialog(rootView, "High Score: 1000", "High Score",
                JOptionPane.DEFAULT_OPTION, JOptionPane.DEFAULT_OPTION) == JOptionPane.DEFAULT_OPTION) {
        }
    }

    @Override
    public void quit() {

        if (JOptionPane.showConfirmDialog(rootView, "Are you sure to quit this container?", "Close container!",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }

    }

    @Override
    public synchronized void turnSnakeLeft() {
        ((Container) container).turnSnakeLeft();
    }

    @Override
    public synchronized void turnSnakeRight() {
        ((Container) container).turnSnakeRight();
    }

    @Override
    public synchronized void turnSnakeUp() {
        ((Container) container).turnSnakeUp();
    }

    @Override
    public synchronized void turnSnakeDown() {
        ((Container) container).turnSnakeDown();
    }

    @Override
    public void setLevel(int level) {
        this.container.setLevel(level);
    }


    @Override
    public void chooseLevel() {
        View levelView = new LevelView(rootView.getCurrentPanel(), container, this);
        rootView.changeView(levelView);
    }

    @Override
    public void changeView(View v) {
        rootView.changeView(v);
    }

    @Override
    public void changeHelpView() {
        View helpView = new HelpView(rootView.getCurrentPanel(), container, this);
        rootView.changeView(helpView);
    }

}
