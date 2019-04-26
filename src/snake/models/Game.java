package snake.models;

import snake.views.View;

import java.util.ArrayList;

public class Game implements Model {
    private ArrayList<View> views;

    public Game() {
        this.views = new ArrayList<>();
    }

    @Override
    public void addView(View view) {
        views.add(view);
    }

    @Override
    public void removeView(View view) {
        views.remove(view);
    }

    @Override
    public void notifyModelChange() {
        for (View v : views) {
            v.render();
        }
    }
}
