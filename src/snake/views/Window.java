package snake.views;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {
    private static Window instance = null;

    private Window(String title, int width, int height) {
        super(title);

        this.getContentPane().setPreferredSize(new Dimension(width, height));
        this.getContentPane().setMaximumSize(new Dimension(width, height));
        this.getContentPane().setMinimumSize(new Dimension(width, height));

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
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
        this.getContentPane().requestFocus();
        this.revalidate();
        this.setVisible(true);
    }

    public Component getCurrentPanel() {
        return this.getContentPane();
    }
}
