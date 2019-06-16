package snake.views;

import snake.controllers.Controller;
import snake.models.Model;
import snake.utils.CustomPanel;
import snake.utils.Utils;
import snake.utils.constraints.Constrains;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HelpView extends JPanel implements View {

    private Model game;
    private Controller controller;
    private View parent;
    private BufferedImage background;
    private JPanel panel;

    public HelpView(Component parent, Model game, Controller controller) {
        background = Utils.getScreenShot(parent);
        this.parent = (View) parent;
        this.game = game;
        this.controller = controller;

        this.setFocusable(true);
        init();

    }

    private void init() {
        levelHelp();
        initLayout();
        mouseEventButton();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(background, 0, 0, null);

    }


    public void levelHelp() {
        panel = new JPanel();
        panel.setPreferredSize(new Dimension(350, 450));
        panel.setMaximumSize(new Dimension(350, 450));
        panel.setMinimumSize(new Dimension(350, 450));

        panel.setLayout(new BorderLayout());

//--------------------------
        JPanel pnLabel = new JPanel();

        Border colorBorder = BorderFactory.createLineBorder(Color.GRAY);
        JLabel lbHelp = new JLabel("Help", JLabel.CENTER);
        lbHelp.setForeground(Color.BLUE);
        lbHelp.setFont(new Font("SVN-Block", Font.PLAIN, 20));

        JLabel t = new JLabel("<html> - Click New Game to start playing the game <br>"
                + "- Click High Score to see the player's high score <br>"
                + "- Click Level to select the difficulty when playing the game <br>"
                + "- Click Quit to exit the game screen <br> <br> </html>", JLabel.CENTER);
        pnLabel.setLayout(new BorderLayout());
        pnLabel.add(lbHelp, BorderLayout.NORTH);
        pnLabel.add(t, BorderLayout.CENTER);


        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(Constrains.VIEW_HELP));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JPanel pnText = new CustomPanel(image);

        panel.setBorder(colorBorder);
        panel.add(pnLabel, BorderLayout.NORTH);
        panel.add(pnText, BorderLayout.CENTER);
//-------------------------


        add(panel);

    }

    private void initLayout() {
        SpringLayout layout = new SpringLayout();

        layout.putConstraint(SpringLayout.NORTH, panel, (int) ((Constrains.HEIGHT - panel.getPreferredSize().getHeight()) / 2),
                SpringLayout.NORTH, this);
        layout.putConstraint(SpringLayout.WEST, panel, (int) ((Constrains.WIDTH - panel.getPreferredSize().getWidth()) / 2),
                SpringLayout.WEST, this);
//        this.setBackground(Color.gray);
        this.setLayout(layout);
    }


    public void mouseEventButton() {
        addMouseListener(addEvent());
    }

    public MouseListener addEvent() {
        return new MouseAdapter() {


            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!panel.contains(e.getPoint())) {
                    controller.changeView(parent);
                }
            }

        };
    }


    @Override
    public void render() {

    }


}

