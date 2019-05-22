package snake.models;

import snake.models.abstractModels.Model;

import java.util.ArrayList;

public class Level3DeadBehavior implements DeadBehavior {


    public Level3DeadBehavior() {

    }


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
