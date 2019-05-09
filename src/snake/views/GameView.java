package snake.views;

import snake.controllers.Controller;
import snake.models.Model;
import snake.utils.constraints.Constrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;

public class GameView extends JPanel implements View, FocusListener {
    private Model game;
    private Controller controller;
    private BoardView boardView;
    private JPanel pnNorth, pnMain;
    private JLabel labelScore;
    public GameView(Model game, Controller controller) {
        this.game = game;
        this.controller = controller;
        boardView = new BoardView(game, controller);

        setLayout(new BorderLayout());
        frameView();
        this.setFocusable(true);
        this.addFocusListener(this);
        this.addKeyListener(this);
    }

    public void frameView() {

        pnNorth = new JPanel();
        pnMain = new JPanel();
        pnMain.setBackground(Constrains.BACKGROUND);
        labelScore = new JLabel("0");
        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon(Constrains.RES_APPLE).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        JLabel labelApple = new JLabel();
        labelApple.setIcon(imageIcon);
        pnNorth.setBackground(Constrains.BACKGROUND_NORTH);
        pnNorth.add(labelApple);
        pnNorth.add(labelScore);




        pnMain.add(boardView);
        initLayout();

        add(pnNorth, BorderLayout.NORTH);
        add(pnMain, BorderLayout.CENTER);
    }

    private void initLayout() {
        SpringLayout layout = new SpringLayout();

        int topMargin = (int)((Constrains.HEIGHT - pnNorth.getPreferredSize().getHeight() - boardView.getPreferredSize().getHeight()) / 2);
        int leftMargin = (int)((Constrains.WIDTH  - boardView.getPreferredSize().getWidth()) / 2);

        layout.putConstraint(SpringLayout.NORTH, boardView, topMargin, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, boardView, leftMargin, SpringLayout.WEST, this);
        pnMain.setLayout(layout);
    }

    @Override
    public void render() {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
//        System.out.println("gameview");
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode())
        {
            case KeyEvent.VK_LEFT:
                controller.turnSnakeLeft();
//                System.out.println("52");
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

    @Override
    public void focusGained(FocusEvent focusEvent) {

    }

    @Override
    public void focusLost(FocusEvent focusEvent) {

    }
}
