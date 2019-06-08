package snake.utils;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CustomPanel extends JPanel {
    BufferedImage background;

    public CustomPanel(BufferedImage background) {

        this.background = background;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }
}