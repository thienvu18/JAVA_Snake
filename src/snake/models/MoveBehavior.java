package snake.models;

import snake.utils.enums.Direction;

public interface MoveBehavior {
    Point next(Point anchor, Direction direction);
}
