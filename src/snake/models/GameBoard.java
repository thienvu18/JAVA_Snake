package snake.models;

import snake.utils.constraints.Constrains;

import java.awt.*;

public class GameBoard implements Drawable {
    private int width;
    private int height;

    public GameBoard(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public void draw(Graphics2D g) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int x = i * Constrains.POINT_SIZE;
                int y = j * Constrains.POINT_SIZE;
                if ((i + j) % 2 == 0) {
                   g.setColor(Constrains.CELL_COLOR_1);
                } else {
                    g.setColor(Constrains.CELL_COLOR_2);
                }
                g.fillRect(x, y, Constrains.POINT_SIZE, Constrains.POINT_SIZE);
            }
        }
    }
}
