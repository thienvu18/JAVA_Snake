package snake.views;

import snake.controllers.Controller;
import snake.controllers.LevelAction;
import snake.models.Model;
import snake.utils.CustomPanel;
import snake.utils.Utils;
import snake.utils.constraints.Constrains;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LevelView extends JPanel implements View {
    private Model game;
    private Controller controller;
    private View parent;
    private BufferedImage background;
    private JButton easybt, normalbt, hardbt;
    private JPanel panel;

    public LevelView(Component parent, Model game, Controller controller) {
        background = Utils.getScreenShot(parent);
        this.parent = (View) parent;
        this.game = game;
        this.controller = controller;

//        this.setFocusable(true);
//        this.addKeyListener(this);
        init();

    }

    public JPanel getPanel() {
        return panel;
    }

    public JButton getHardbt() {
        return hardbt;
    }

    public JButton getNormalbt() {
        return normalbt;
    }

    public JButton getEasybt() {
        return easybt;
    }

    private void init() {
        levelView();
        initLayout();
        mouseEventButton();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(background, 0, 0, null);
    }

    public void levelView() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(Constrains.VIEW_LEVEL));

        } catch (IOException e) {
            e.printStackTrace();
        }
        panel = new CustomPanel(image);
        panel.setLayout(new GridLayout(3, 1, 5, 5));
        panel.setPreferredSize(new Dimension(200, 250));
        panel.setMaximumSize(new Dimension(200, 250));
        panel.setMinimumSize(new Dimension(200, 250));
        easybt = new JButton("Easy");
        normalbt = new JButton("Normal");
        hardbt = new JButton("Hard");
        easybt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        normalbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        hardbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
        easybt.setBorder(null);
        normalbt.setBorder(null);
        hardbt.setBorder(null);
        easybt.setFocusPainted(false);
        normalbt.setFocusPainted(false);
        hardbt.setFocusPainted(false);
        easybt.setContentAreaFilled(false);
        normalbt.setContentAreaFilled(false);
        hardbt.setContentAreaFilled(false);
        if (game.getLevel() == 3) {
            hardbt.setForeground(Color.RED);
        } else if (game.getLevel() == 2) {
            normalbt.setForeground(Color.RED);
        } else {
            easybt.setForeground(Color.RED);
        }
        panel.add(easybt);
        panel.add(normalbt);
        panel.add(hardbt);
//        panel.setLocation(20, 0);
//        System.out.println(this.getAlignmentX() +  " bb "+ this.getAlignmentY());
//        this.setAlignmentX(CENTER_ALIGNMENT);
//        System.out.println(this.getAlignmentX() +  " bb "+ this.getAlignmentY());

        add(panel);

    }

    private void initLayout() {
        SpringLayout layout = new SpringLayout();

        layout.putConstraint(SpringLayout.NORTH, panel, (int) ((Constrains.HEIGHT - panel.getPreferredSize().getHeight()) / 2),
                SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, panel, (int) ((Constrains.WIDTH - panel.getPreferredSize().getWidth()) / 2),
                SpringLayout.WEST, this);
        this.setLayout(layout);
    }


    public void mouseEventButton() {
        LevelAction levelAction = new LevelAction(controller, parent, this);
        easybt.addMouseListener(levelAction);
        normalbt.addMouseListener(levelAction);
        hardbt.addMouseListener(levelAction);
        addMouseListener(levelAction);
    }


    @Override
    public void render() {

    }


}
