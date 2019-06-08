package snake.views;

import snake.controllers.Controller;
import snake.controllers.MenuAction;
import snake.models.Model;
import snake.utils.constraints.Constrains;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuView extends JPanel implements View {
    private Model game;
    private Controller controller;
    private JButton newgamebt, help, quitbt, levelbt, highscorebt;

    public MenuView(Model game, Controller controller) {
        this.game = game;
        this.controller = controller;
        this.setLayout(new BorderLayout());
//        game.addView(this);

        this.setFocusable(true);
        init();
    }

    private void init() {
        addButton();
        mouseEventButton();
    }

    @Override
    public void paintComponent(Graphics g) {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(Constrains.VIEW_GAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.drawImage(image, 0, 0, null);
    }

    private void addButton() {
        /** GridLayout */
        JPanel p = new JPanel(new GridLayout(6, 1, 5, 5));
        p.setPreferredSize(new Dimension(200, 250));
        p.setMaximumSize(new Dimension(200, 250));
        p.setMinimumSize(new Dimension(200, 250));
        /** create button */
        newgamebt = new JButton("New Game");
        levelbt = new JButton("Level");
        highscorebt = new JButton("High Score");
        help = new JButton("Help");
        quitbt = new JButton("Quit");
        /** set font and size for text in button */
        newgamebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        highscorebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        levelbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        help.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        quitbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        /** set no border for button */
        newgamebt.setBorder(null);
        highscorebt.setBorder(null);
        levelbt.setBorder(null);
        help.setBorder(null);
        quitbt.setBorder(null);
        /** hiden background button */
        newgamebt.setFocusPainted(false);
        highscorebt.setFocusPainted(false);
        levelbt.setFocusPainted(false);
        help.setFocusPainted(false);
        quitbt.setFocusPainted(false);
        newgamebt.setContentAreaFilled(false);
        highscorebt.setContentAreaFilled(false);
        levelbt.setContentAreaFilled(false);
        help.setContentAreaFilled(false);
        quitbt.setContentAreaFilled(false);
        /** no bug for hide background jbutton */
        p.setOpaque(false);
        /** add button ==> layout */
        p.add(newgamebt);
        p.add(highscorebt);
        p.add(levelbt);
        p.add(help);
        p.add(quitbt);
//        p.setLocation(180, 200);
        add(p, BorderLayout.NORTH);
    }

    public void mouseEventButton() {
        MenuAction menuAction = new MenuAction(this.controller);
        newgamebt.addMouseListener(menuAction);
        highscorebt.addMouseListener(menuAction);
        levelbt.addMouseListener(menuAction);
        help.addMouseListener(menuAction);
        quitbt.addMouseListener(menuAction);
    }


    @Override
    public void render() {

    }


}