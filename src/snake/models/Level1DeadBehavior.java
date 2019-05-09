package snake.models;

public class Level1DeadBehavior implements DeadBehavior {
    @Override
    public boolean isDead(int boardWidth, int boardHeight, Snake snake) {
        if (snake.isHitSelf()) {
//            snake.stop();
            System.out.println("Cắn thân");
            return true;
        }
        return false;
    }
}
