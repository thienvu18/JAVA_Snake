package snake.views;

import snake.controllers.Controller;
import snake.models.Model;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuView extends JPanel implements View {
    private Model game;
    private Controller controller;

    public MenuView(Model game, Controller controller) {
        this.game = game;
        this.controller = controller;
        game.addView(this);

        this.setFocusable(true);
        this.addKeyListener(this);
    }

    @Override
    public void render() {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
