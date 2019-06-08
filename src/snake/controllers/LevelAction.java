package snake.controllers;

import snake.views.LevelView;
import snake.views.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LevelAction extends MouseAdapter {
    private Controller controller;
    private View parent;
    private LevelView pnViewLevel;

    public LevelAction(Controller controller, View parent, LevelView pnViewLevel) {
        this.controller = controller;
        this.parent =  parent;
        this.pnViewLevel = pnViewLevel;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);

        Object o = e.getSource();

        if(o instanceof JButton) {
            JButton bt = (JButton) o;

            if (bt.getText() == "Easy") {
                bt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
            }
            if (bt.getText() == "Normal") {
                bt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
            }
            if (bt.getText() == "Hard") {
                bt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
            }
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        super.mouseExited(e);

        Object o = e.getSource();

        if(o instanceof JButton) {
            JButton bt = (JButton) o;

            if (bt.getText() == "Easy") {
                bt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
            } else if (bt.getText() == "Normal") {
                bt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
            } else if (bt.getText() == "Hard") {
                bt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        if (!pnViewLevel.getPanel().contains(e.getPoint())) {
            controller.changeView(parent);
        } else {
            Object o = e.getSource();

            if (o instanceof JButton) {
                JButton bt = (JButton) e.getSource();
                if (bt.getText().equals("Easy")) {

                    pnViewLevel.getEasybt().setForeground(Color.RED);
                    pnViewLevel.getNormalbt().setForeground(Color.BLACK);
                    pnViewLevel.getHardbt().setForeground(Color.BLACK);
                    controller.setLevel(1);
                } else if (bt.getText().equals("Normal")) {

                    pnViewLevel.getEasybt().setForeground(Color.BLACK);
                    pnViewLevel.getNormalbt().setForeground(Color.RED);
                    pnViewLevel.getHardbt().setForeground(Color.BLACK);

                    controller.setLevel(2);
                } else if (bt.getText().equals("Hard")) {
                    pnViewLevel.getEasybt().setForeground(Color.BLACK);
                    pnViewLevel.getNormalbt().setForeground(Color.BLACK);
                    pnViewLevel.getHardbt().setForeground(Color.RED);
                    controller.setLevel(3);
                }
            }
        }
    }
}
