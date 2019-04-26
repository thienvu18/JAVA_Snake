package snake.views;

import snake.controllers.Controller;
import snake.models.Model;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class GameView extends JPanel implements  View {
    private Model game;
    private Controller controller;

    public GameView(Model game, Controller controller) {
        this.game = game;
        this.controller = controller;
    }

    @Override
    public void render() {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
