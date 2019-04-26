package snake.views;

import snake.controllers.Controller;
import snake.models.Apple;
import snake.models.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameView extends JPanel implements View, FocusListener {
    private Model game;
    private Controller controller;

    public GameView(Model game, Controller controller) {
        this.game = game;
        this.controller = controller;

        game.addView(this);

        this.setFocusable(true);
        this.addFocusListener(this);
        this.addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        game.draw(g2d);

        g2d.dispose();
    }

    @Override
    public void render() {
        this.repaint();
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

    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
        controller.pause();
    }
}
