package snake.views;

import snake.controllers.Controller;
import snake.models.Model;
import snake.utils.constraints.Constrains;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MenuView extends JPanel implements View {
    private Model game;
    private Controller controller;
    private JButton newgamebt, optionbt, quitbt, levelbt, highscorebt;

    public MenuView(Model game, Controller controller) {
        this.game = game;
        this.controller = controller;
        this.setLayout(new BorderLayout());
        game.addView(this);

        this.setFocusable(true);
        this.addKeyListener(this);
        init();
    }

    private void init() {
        addButton();
        mouseEventButton();
    }

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
        optionbt = new JButton("Option");
        quitbt = new JButton("Quit");
        /** set font and size for text in button */
        newgamebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        highscorebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        levelbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        optionbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        quitbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        /** set no border for button */
        newgamebt.setBorder(null);
        highscorebt.setBorder(null);
        levelbt.setBorder(null);
        optionbt.setBorder(null);
        quitbt.setBorder(null);
        /** hiden background button */
        newgamebt.setFocusPainted(false);
        highscorebt.setFocusPainted(false);
        levelbt.setFocusPainted(false);
        optionbt.setFocusPainted(false);
        quitbt.setFocusPainted(false);
        newgamebt.setContentAreaFilled(false);
        highscorebt.setContentAreaFilled(false);
        levelbt.setContentAreaFilled(false);
        optionbt.setContentAreaFilled(false);
        quitbt.setContentAreaFilled(false);
        /** no bug for hide background jbutton */
        p.setOpaque(false);
        /** add button ==> layout */
        p.add(newgamebt);
        p.add(highscorebt);
        p.add(levelbt);
        p.add(optionbt);
        p.add(quitbt);
//        p.setLocation(180, 200);
        add(p, BorderLayout.NORTH);
    }

    public void mouseEventButton() {
        newgamebt.addMouseListener(addEvent());
        highscorebt.addMouseListener(addEvent());
        levelbt.addMouseListener(addEvent());
        optionbt.addMouseListener(addEvent());
        quitbt.addMouseListener(addEvent());
    }

    public MouseListener addEvent() {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (e.getSource() == newgamebt) {
                    newgamebt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
                }
                if (e.getSource() == highscorebt) {
                    highscorebt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
                }
                if (e.getSource() == levelbt) {
                    levelbt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
                }
                if (e.getSource() == optionbt) {
                    optionbt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
                }
                if (e.getSource() == quitbt) {
                    quitbt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
                }

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);


                if (e.getSource() == newgamebt) {
                    controller.newGame();
                }
                if (e.getSource() == highscorebt) {
                }
                if (e.getSource() == levelbt) {
                        controller.chooseLevel();
                }
                if (e.getSource() == optionbt) {
                }
                if (e.getSource() == quitbt) {
                    controller.quit();
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == newgamebt) {
                    newgamebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
                }
                if (e.getSource() == highscorebt) {
                    highscorebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
                }
                if (e.getSource() == levelbt) {
                    levelbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
                }
                if (e.getSource() == optionbt) {
                    optionbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
                }
                if (e.getSource() == quitbt) {
                    quitbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
                }
                super.mouseExited(e);
            }
        };
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