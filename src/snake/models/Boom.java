package snake.models;

import snake.models.Drawable;
import snake.models.Point;
import snake.utils.constraints.Constrains;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Boom implements Drawable, Runnable {
    private final String IMAGE_PATH = Constrains.BOOM;
    private BufferedImage bufferedImage = null;
    private Point point;
    private double currentScale = 1.0;

    public Boom() {
        try {
            bufferedImage = ImageIO.read(new File(IMAGE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        point = Point.getRandomPoint(20, 20);
        start();
    }

    private synchronized void animation() {
        int d = 1;

        if (currentScale >= 1.3) {
            d = -1;
        }

        if (currentScale <= 1) {
            d = 1;
        }

        currentScale += 0.1 * d;
    }

    public synchronized void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void draw(Graphics2D g) {
        Image image = bufferedImage.getScaledInstance((int) (Constrains.POINT_SIZE * currentScale), (int) (Constrains.POINT_SIZE * currentScale), Image.SCALE_SMOOTH);

        g.drawImage(image, point.getCenterXInPixel() - image.getWidth(null) / 2, point.getCenterYInPixel() - image.getHeight(null) / 2, null);
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (true) {

            this.animation();

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = 1000 / Constrains.ANIMATION_APPLE_FPS - timeDiff;
            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ignored) {
            }
            beforeTime = System.currentTimeMillis();
        }
    }
}