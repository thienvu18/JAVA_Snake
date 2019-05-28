package snake.views;

import snake.controllers.Controller;
import snake.models.abstractModels.Model;
import snake.utils.constraints.Constrains;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameView extends JPanel implements View {
    private Model game;
    private Controller controller;
    private BoardView boardView;
    private JPanel pnNorth, pnMain;
    private JLabel labelScore;
    private JButton btnPause, btnResum;

    public GameView(Model game, Controller controller) {
        this.game = game;
        this.controller = controller;
        boardView = new BoardView(game, controller);
        setLayout(new BorderLayout());
        game.addView(this);
        frameView();
        this.setFocusable(true);
        this.addKeyListener(this);
    }

    public void frameView() {

        pnNorth = new JPanel();
        pnMain = new JPanel();
        pnMain.setBackground(Constrains.BACKGROUND);
        labelScore = new JLabel("0");
        labelScore.setForeground(Color.WHITE);
        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon(Constrains.RES_APPLE).getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
        JLabel labelApple = new JLabel();
        labelApple.setIcon(imageIcon);
        pnNorth.setBackground(Constrains.BACKGROUND_NORTH);

        JPanel panelCenterNorth = new JPanel();
        panelCenterNorth.setBackground(Color.GREEN);
        panelCenterNorth.setLocation(20, 0);
//        pnNorth.add(panelCenterNorth);
        ImageIcon pause = new ImageIcon(
                new ImageIcon(Constrains.PAUSE).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));
        ImageIcon resum = new ImageIcon(
                new ImageIcon(Constrains.RESUM).getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT));

        btnPause = new JButton(pause);
        btnPause.setBorder(null);
        btnPause.setFocusPainted(false);

        btnPause.setContentAreaFilled(false);
        btnPause.addMouseListener(addEvent());

        btnResum = new JButton(resum);
        btnResum.setBorder(null);
        btnResum.setFocusPainted(false);
        btnResum.setContentAreaFilled(false);
        btnResum.addMouseListener(addEvent());

        pnNorth.add(labelApple);
        pnNorth.add(labelScore);
        pnNorth.add(btnPause);
        pnNorth.add(btnResum);


        pnMain.add(boardView);
        initLayout();

        add(pnNorth, BorderLayout.NORTH);
        add(pnMain, BorderLayout.CENTER);
    }

    public MouseListener addEvent() {
        return new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                JButton cmd = (JButton) e.getSource();
                if (cmd.equals(btnPause)) {
                    controller.pause();
                } else if (cmd.equals(btnResum)) {
                    controller.resume();
                }

            }


        };
    }

    public void setScore() {
        labelScore.setText(game.getScore() + "");
    }

    @Override
    public void render() {
        setScore();
    }

    private void initLayout() {
        SpringLayout layout = new SpringLayout();

        int topMargin = (int) ((Constrains.HEIGHT - pnNorth.getPreferredSize().getHeight() - boardView.getPreferredSize().getHeight()) / 2);
        int leftMargin = (int) ((Constrains.WIDTH - boardView.getPreferredSize().getWidth()) / 2);

        layout.putConstraint(SpringLayout.NORTH, boardView, topMargin, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, boardView, leftMargin, SpringLayout.WEST, this);
        pnMain.setLayout(layout);

    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {
//        System.out.println("gameview");
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
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

}
