package snake.views;

import snake.controllers.Controller;
import snake.models.Model;
import snake.utils.Utils;
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

        this.setFocusable(true);
        this.addKeyListener(this);
        init();

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
//        panel.setSize(new Dimension(160, 210));
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
//        if(controller.getLevel() == 1){
//            easybt.setForeground(Color.RED);
//        }else if(controller.getLevel() == 2){
//            normalbt.setForeground(Color.RED);
//        }else{
//            hardbt.setForeground(Color.RED);
//        }
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

        layout.putConstraint(SpringLayout.NORTH, panel, (int) ((Constrains.HEIGHT - panel.getPreferredSize().getHeight()) / 2), SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, panel, (int) ((Constrains.WIDTH - panel.getPreferredSize().getWidth()) / 2), SpringLayout.WEST, this);
        this.setLayout(layout);
    }


    public void mouseEventButton() {
        easybt.addMouseListener(addEvent());
        normalbt.addMouseListener(addEvent());
        hardbt.addMouseListener(addEvent());
        addMouseListener(addEvent());
    }

    public MouseListener addEvent() {
        return new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                if (e.getSource() == easybt) {
                    easybt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
                }
                if (e.getSource() == normalbt) {
                    normalbt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
                }
                if (e.getSource() == hardbt) {
                    hardbt.setFont(new Font("SVN-Block", Font.PLAIN, 20));
                }

            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource() == easybt) {
                    easybt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
                }
                if (e.getSource() == normalbt) {
                    normalbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
                }
                if (e.getSource() == hardbt) {
                    hardbt.setFont(new Font("SVN-Block", Font.PLAIN, 16));
                }
                super.mouseExited(e);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
//                if (e.getSource() == easybt) {
//                    controller.setLevel(1);
//                    dispose();
//                }
//                if (e.getSource() == normalbt) {
//                    controller.setLevel(2);
//                    dispose();
//                }
//                if (e.getSource() == hardbt) {
//                    controller.setLevel(3);
//                    dispose();
//                }

                if(!panel.contains(e.getPoint())) {
                        controller.changeView(parent);
                }
                System.out.println(e.getPoint());
                System.out.println(panel.contains(e.getPoint()));
                super.mouseClicked(e);
            }

        };
    }


    @Override
    public void render() {

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
}

class CustomPanel extends JPanel {
    BufferedImage background;

    public CustomPanel(BufferedImage background) {
        this.background = background;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(background, 0, 0,getWidth(),getHeight(), null);
    }
}
