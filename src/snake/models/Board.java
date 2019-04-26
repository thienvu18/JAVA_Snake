package snake.models;

import snake.utils.constraints.Constrains;

import java.awt.*;

public class Board implements Drawable {
    private int width;
    private int height;

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
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
