package snake.utils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class Utils {

    public static BufferedImage getScreenShot(Component component) {
// cắt ra panel lúc đó
        BufferedImage image = new BufferedImage(component.getWidth(), component.getHeight(), BufferedImage.TYPE_INT_RGB);
        // paints into image's Graphics
        component.paint(image.getGraphics());
//làm mờ
        Kernel kernel = new Kernel(3, 3, new float[] { 1f / 9f, 1f / 9f, 1f / 9f,
                1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f, 1f / 9f });
        BufferedImageOp op = new ConvolveOp(kernel);
        image = op.filter(image, null);

        return image;
    }


}
