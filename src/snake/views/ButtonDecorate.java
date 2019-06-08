package snake.views;

import javax.swing.*;
import java.awt.*;

public  abstract class ButtonDecorate extends JComponent {
    JComponent jComponent;
    public ButtonDecorate(JComponent jComponent) {
        this.jComponent=jComponent;
        setLayout(new BorderLayout() );
        add(jComponent);

    }
}