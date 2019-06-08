package snake.views;

import javax.swing.*;
import java.awt.*;

public class Line extends ButtonDecorate{
    public Line(JComponent jComponent) {
        super(jComponent);
        jComponent.setBorder(BorderFactory.createLineBorder(Color.BLUE));

    }
}
