package snake.controllers;


import snake.models.*;
import snake.utils.constraints.Constrains;
import snake.views.*;

import javax.swing.*;

public class GameController implements Controller {

    private Container container;
    private Window rootView;
    private int level;

    public GameController(Container container) {
        this.container = container;
        rootView = Window.getInstance("Snake Game", Constrains.WIDTH, Constrains.HEIGHT);
        View menuView = new MenuView(container, this);
        rootView.changeView(menuView);

    }

    @Override
    public void newGame() {
        View gameView = new GameView(container, this);
        System.out.println("Level: "+level);
        switch (level) {
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
                container.setDeadBehavior(new Level2DeadBehavior());
                break;
        }
        rootView.changeView(gameView);
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
        this.level = level;
    }


    @Override
    public int chooseLevel() {
        View levelView = new LevelView(rootView.getCurrentPanel(), container, this);
        rootView.changeView(levelView);
        return level;

    }

    @Override
    public void changeView(View view) {
        rootView.changeView(view);
    }


}
