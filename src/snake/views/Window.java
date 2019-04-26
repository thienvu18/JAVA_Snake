package snake.views;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static Window instance = null;

    private Window(String title, int width, int height) {
        super(title);

        this.setPreferredSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
    }

    public static Window getInstance(String title, int width, int height) {
        if (instance == null) {
            instance = new Window(title, width, height);
        }

        return instance;
    }

    public void changeView(View view) {
        this.setContentPane((JPanel)view);
        this.revalidate();
        this.pack();
        this.setVisible(true);
    }
}
