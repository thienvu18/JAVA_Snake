package snake.models;

import snake.models.abstractModels.Model;

public class Level1DeadBehavior implements DeadBehavior {
    @Override
    public boolean isDead(Model model) {
        if (model.getSnake().isHitSelf()) {
//            snake.stop();
            System.out.println("Cắn thân");
            return true;
        }
        return false;
    }
}
