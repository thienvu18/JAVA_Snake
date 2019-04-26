package snake.models;

import snake.views.View;

public interface Model {
    void addView(View view);
    void removeView(View view);
    void notifyModelChange();
}
