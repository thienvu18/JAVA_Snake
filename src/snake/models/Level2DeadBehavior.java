package snake.models;

public class Level2DeadBehavior implements DeadBehavior {


    @Override
    public boolean isDead(Model model) {
        if (model.getSnake().isHitWall(model.getGameBoard().getWidth(), model.getGameBoard().getHeight())) {
            return true;
        }

        if (model.getSnake().isHitSelf()) {
            return true;
        }
        return false;
    }
}
