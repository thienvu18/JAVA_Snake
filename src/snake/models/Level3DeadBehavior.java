package snake.models;

public class Level3DeadBehavior implements DeadBehavior {
    @Override
    public boolean isDead(int boardWidth, int boardHeight, Snake snake) {
        if (snake.isHitWall(boardWidth, boardHeight)) {
//            snake.stop();
            System.out.println("Cắn tường");
            return true;
        }

        if (snake.isHitSelf()) {
//            snake.stop();
            System.out.println("Cắn thân");
            return true;
        }

//        if(snake.isHitBoom())
        return false;


    }
}
