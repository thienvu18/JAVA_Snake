package snake.controllers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuAction extends MouseAdapter {
    private Controller controller;

    public MenuAction( Controller controller) {
        this.controller = controller;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);

        JButton bt = (JButton) e.getSource();
        if (bt.getText() == "New Game") {
            bt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
        }
        if (bt.getText() == "High Score") {
            bt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
        }
        if (bt.getText() == "Level") {
            bt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
        }
        if (bt.getText() == "Help") {
            bt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
        }
        if (bt.getText() == "Quit") {
            bt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        JButton bt = (JButton) e.getSource();
        if (bt.getText() == "New Game") {
            controller.play();
        }
        if (bt.getText() == "High Score") {
            controller.highScore();
        }
        if (bt.getText() == "Level") {
            controller.chooseLevel();
        }
        if (bt.getText() == "Help") {
            controller.changeHelpView();
        }

        if (bt.getText() == "Quit") {
            controller.quit();
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);
        JButton bt = (JButton) e.getSource();
        if (bt.getText() == "New Game") {
            bt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        }
        if (bt.getText() == "High Score") {
            bt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        }
        if (bt.getText() == "Level") {
            bt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        }
        if (bt.getText() == "Help") {
            bt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        }
        if (bt.getText() == "Quit") {
            bt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        }
    }


}
