package snake.views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonBorder extends ButtonDecorate {
    private boolean mouse_over;
    private JComponent thiscomp;

    public ButtonBorder(JComponent jComponent) {
        super(jComponent);
        mouse_over = false;
        thiscomp = this;
        jComponent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                mouse_over = true;
                thiscomp.repaint();
            }

            public void mouseExited(MouseEvent e) {
                mouse_over = false;
                thiscomp.repaint();
            }
        });
    }

    public void paint(Graphics g) {
        super.paint(g);
        if (mouse_over) {
            Dimension size = super.getSize();
            g.setColor(Color.YELLOW);
            g.drawRect(0, 0, size.width - 1, size.height - 1);
            g.drawLine(size.width - 2, 0, size.width - 2, size.height - 1);
            g.drawLine(0, size.height - 2, size.width - 2, size.height - 2);
        }
    }
}
