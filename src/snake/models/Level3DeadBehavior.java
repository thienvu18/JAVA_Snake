package snake.models;

import java.util.ArrayList;

public class Level3DeadBehavior implements DeadBehavior {


    public Level3DeadBehavior() {

    }


    @Override
    public boolean isDead(Model model) {
        if (model.getSnake().isHitWall(model.getGameBoard().getWidth(), model.getGameBoard().getHeight())) {
            return true;
        }

        else if (model.getSnake().isHitSelf()) {
            return true;
        }
        ArrayList<Boom> booms = model.getBooms();
        for (int i = 0; i < booms.size(); i++) {
            if (model.getSnake().isHitBoom(booms.get(i))) {
                System.out.println("Đụng Boom");
                return true;
            }
        }
        return false;
    }
}
