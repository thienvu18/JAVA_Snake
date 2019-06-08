package snake.models;

import snake.utils.constraints.Constrains;
import snake.utils.enums.Direction;

public class Level1MoveBehavior implements MoveBehavior {
    @Override
    public Point next(Point anchor, Direction direction) {
        Point p = new Point();

        switch (direction) {
            case NORTH:
                p.y = anchor.y - 1;
                p.x = anchor.x;
                break;
            case SOUTH:
                p.y = anchor.y + 1;
                p.x = anchor.x;
                break;
            case WEST:
                p.y = anchor.y;
                p.x = anchor.x - 1;
                break;
            case EAST:
                p.y = anchor.y;
                p.x = anchor.x + 1;
                break;

        }
        if (p.x == Constrains.BOARD_COL) {
            p.x = 0;
        }
        if (p.x == 0 - 1) {
            p.x = Constrains.BOARD_COL - 1;
        }
        if (p.y == Constrains.BOARD_ROW ) {
            p.y = 0;
        }
        if (p.y == 0 - 1) {
            p.y = Constrains.BOARD_ROW - 1;
        }

        return p;
    }
}
