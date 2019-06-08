package snake.models;

public class Level2DeadBehavior implements DeadBehavior {


    @Override
    public boolean isDead(Model model) {
        if (model.getSnake().isHitWall(model.getGameBoard().getWidth(), model.getGameBoard().getHeight())) {
            System.out.println("Cắn tường");
            return true;
        }

        if (model.getSnake().isHitSelf()) {
            System.out.println("Cắn thân");
            return true;
        }
        return false;
    }
}
