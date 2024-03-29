package snake.views;

import snake.controllers.Controller;
import snake.controllers.GameAction;
import snake.models.Model;
import snake.utils.constraints.Constrains;
import snake.utils.enums.GameState;

import javax.swing.*;
import java.awt.*;

public class GameView extends JPanel implements View {
    private Model game;
    private Controller controller;
    private BoardView boardView;
    private JPanel pnNorth, pnMain;
    private JLabel labelScore;
    private JButton btnPause, btnResume, btnMenuGame;

    public GameView(Model game, Controller controller) {
        this.game = game;
        this.controller = controller;
        boardView = new BoardView(game, controller);
        setLayout(new BorderLayout());
        game.addView(this);
        frameView();
        addAction();
    }

    public JButton getBtnMenuGame() {
        return btnMenuGame;
    }

    public JButton getBtnResume() {
        return btnResume;
    }

    public JButton getBtnPause() {
        return btnPause;
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
                new ImageIcon(Constrains.PAUSE).getImage().getScaledInstance(60, 30, Image.SCALE_DEFAULT));

        ImageIcon resume = new ImageIcon(
                new ImageIcon(Constrains.RESUME).getImage().getScaledInstance(60, 30, Image.SCALE_DEFAULT));
        ImageIcon menuGame = new ImageIcon(
                new ImageIcon(Constrains.MENUGAME).getImage().getScaledInstance(60, 30, Image.SCALE_DEFAULT));
        btnPause = new JButton(pause);
        btnPause.setBorder(null);
        btnPause.setVisible(true);
        btnPause.setFocusPainted(false);
        btnPause.setContentAreaFilled(false);


        btnResume = new JButton(resume);
        btnResume.setBorder(null);
        btnResume.setVisible(false);
        btnResume.setFocusPainted(false);
        btnResume.setContentAreaFilled(false);

        btnMenuGame = new JButton(menuGame);
        btnMenuGame.setBorder(null);
        btnMenuGame.setFocusPainted(false);
        btnMenuGame.setContentAreaFilled(false);

        pnNorth.add(labelApple);
        pnNorth.add(labelScore);
        pnNorth.add(new ButtonBorder(btnPause));
        pnNorth.add(new ButtonBorder(btnResume));
        pnNorth.add(new ButtonBorder(btnMenuGame));



        pnMain.add(boardView);
        initLayout();

        add(pnNorth, BorderLayout.NORTH);
        add(pnMain, BorderLayout.CENTER);
    }

    public void addAction() {
        GameAction g = new GameAction(controller, this);
        btnPause.addMouseListener(g);
        btnResume.addMouseListener(g);
        btnMenuGame.addMouseListener(g);
        this.addKeyListener(g);
        this.setFocusable(true);
    }


    public void setScore() {
        labelScore.setText(game.getScore() + "");
    }

    @Override
    public void render() {
        setScore();

        if (game.getState() == GameState.STOPPED) {
            Object[] options = {"Exit"};
            JOptionPane.showOptionDialog(SwingUtilities.getWindowAncestor(this), "You died !!!!", "Game Over", JOptionPane.OK_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            controller.newGame();
        }
    }

    private void initLayout() {
        SpringLayout layout = new SpringLayout();

        int topMargin = (int) ((Constrains.HEIGHT - pnNorth.getPreferredSize().getHeight() - boardView.getPreferredSize().getHeight()) / 2);
        int leftMargin = (int) ((Constrains.WIDTH - boardView.getPreferredSize().getWidth()) / 2);

        layout.putConstraint(SpringLayout.NORTH, boardView, topMargin, SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, boardView, leftMargin, SpringLayout.WEST, this);
        pnMain.setLayout(layout);

    }


}
