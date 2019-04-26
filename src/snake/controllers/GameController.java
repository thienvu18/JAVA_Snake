package snake.controllers;


import snake.models.Game;
import snake.models.Model;
import snake.views.MenuView;
import snake.views.View;
import snake.views.Window;

public class GameController implements Controller {
    private final int DELAY = 33;

    private Model game;
    private Window rootView;

    public GameController(Game game) {
        this.game = game;
        rootView = Window.getInstance("Snake game", 500, 500);
        View menuView = new MenuView(game, this);
        rootView.changeView(menuView);
        this.start();
    }

    public void start() {
        Thread thread = new Thread(this);
        thread.start();
    }

    private void gameLoop() {
        game.notifyModelChange();
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        beforeTime = System.currentTimeMillis();

        while (true) {
            gameLoop();
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY - timeDiff;
            if (sleep < 0) {
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException ignored) {
            }
            beforeTime = System.currentTimeMillis();
        }
    }

}
