package snake.controllers;

import snake.views.GameView;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameAction extends MouseAdapter implements KeyListener {
    private Controller controller;
    private GameView gameView;


    public GameAction(Controller controller, GameView gameView) {
        this.controller = controller;
        this.gameView = gameView;
    }

    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        JButton cmd = (JButton) e.getSource();

        if (cmd.equals(gameView.getBtnPause())) {
            gameView.getBtnPause().setVisible(false);
            gameView.getBtnResume().setVisible(true);
            controller.pause();
        } else if (cmd.equals(gameView.getBtnResume())) {
            gameView.getBtnPause().setVisible(true);
            gameView.getBtnResume().setVisible(false);
            controller.resume();
        }
         else if (cmd.equals(gameView.getBtnMenuGame())) {
            controller.newGame();
        }

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                controller.turnSnakeLeft();
                break;
            case KeyEvent.VK_RIGHT:
                controller.turnSnakeRight();
                break;
            case KeyEvent.VK_UP:
                controller.turnSnakeUp();
                break;
            case KeyEvent.VK_DOWN:
                controller.turnSnakeDown();
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
