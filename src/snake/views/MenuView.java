package snake.views;

import snake.controllers.Controller;
import snake.models.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuView extends JPanel implements View {
    private Model game;
    private Controller controller;
    private JButton newgamebt, optionbt, quitbt, resumebt, levelbt, highscorebt;

    public MenuView(Model game, Controller controller) {
        this.game = game;
        this.controller = controller;
        game.addView(this);
        this.setFocusable(true);
        this.addKeyListener(this);
        init();
    }

    private void init() {
        addButton();
        mouseEventButton();
    }

    private void addButton() {
        /** GridLayout */
        JPanel p = new JPanel(new GridLayout(6, 1, 5, 5));
        p.setSize(new Dimension(200, 250));
        /** create button */
        newgamebt = new JButton("New Game");
        resumebt = new JButton("Resume");
        levelbt = new JButton("Level");
        highscorebt = new JButton("High Score");
        optionbt = new JButton("Option");
        quitbt = new JButton("Quit");
        /** set font and size for text in button */
        newgamebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        resumebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        highscorebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        levelbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        optionbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        quitbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        /** set no border for button */
        newgamebt.setBorder(null);
        resumebt.setBorder(null);
        highscorebt.setBorder(null);
        levelbt.setBorder(null);
        optionbt.setBorder(null);
        quitbt.setBorder(null);
        /** hiden background button */
        newgamebt.setFocusPainted(false);
        resumebt.setFocusPainted(false);
        highscorebt.setFocusPainted(false);
        levelbt.setFocusPainted(false);
        optionbt.setFocusPainted(false);
        quitbt.setFocusPainted(false);
        newgamebt.setContentAreaFilled(false);
        resumebt.setContentAreaFilled(false);
        highscorebt.setContentAreaFilled(false);
        levelbt.setContentAreaFilled(false);
        optionbt.setContentAreaFilled(false);
        quitbt.setContentAreaFilled(false);
        /** no bug for hide background jbutton */
        p.setOpaque(false);
        /** add button ==> layout */
        p.add(newgamebt);
        p.add(resumebt);
        p.add(highscorebt);
        p.add(levelbt);
        p.add(optionbt);
        p.add(quitbt);
        p.setLocation(80, 200);
        add(p);
    }

    public void mouseEventButton() {
        newgamebt.addMouseListener(addEvent());
        resumebt.addMouseListener(addEvent());
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
                if (e.getSource() == resumebt) {
                    resumebt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
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
                if (e.getSource() == resumebt) {
                }
                if (e.getSource() == highscorebt) {
                }
                if (e.getSource() == levelbt) {

                }
                if (e.getSource() == optionbt) {
                }
                if (e.getSource() == quitbt) {
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == newgamebt) {
                    newgamebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
                }
                if (e.getSource() == resumebt) {
                    resumebt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
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

