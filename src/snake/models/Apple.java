package snake.models;

import snake.utils.constraints.Constrains;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Apple implements Drawable {
    private final String IMAGE_PATH = Constrains.RES_APPLE;

    private BufferedImage bufferedImage = null;
    private Point point;
    private double currentScale = 1.0;
    private int animationIgnore = 0;

    public Apple() {
        try {
            bufferedImage = ImageIO.read(new File(IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        point = Point.getRandomPoint(20, 20);
    }

    public synchronized void animation() {

        if (animationIgnore < Constrains.FPS / Constrains.ANIMATION_APPLE_FPS) {
            animationIgnore++;

            return;
        }
        animationIgnore = 0;

        int d = 1;

        if (currentScale >= 1.3) {
            d = -1;
        }

        if (currentScale <= 1) {
            d = 1;
        }

        currentScale += 0.1 * d;
    }

    @Override
    public void draw(Graphics2D g) {
        Image image = bufferedImage.getScaledInstance((int) (Constrains.POINT_SIZE * currentScale), (int)(Constrains.POINT_SIZE * currentScale), Image.SCALE_SMOOTH);
        g.drawImage(image, point.x * Constrains.POINT_SIZE, point.y*Constrains.POINT_SIZE, null);
    }
}
