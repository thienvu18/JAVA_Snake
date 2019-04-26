package snake.models;

import snake.views.View;

public interface Model extends Drawable {
    void addView(View view);
    void removeView(View view);
    void notifyModelChange();

    void start();
    void stop();
    void pause();
    void resume();
}
