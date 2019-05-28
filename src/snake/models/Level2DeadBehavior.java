package snake.models;

public class Level2DeadBehavior implements DeadBehavior {


    @Override
    public boolean isDead(Model model) {
//        System.out.println(boardWidth +"/"+ boardHeight);
        System.out.println("level 22222222222222");
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
