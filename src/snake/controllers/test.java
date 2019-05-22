package snake.controllers;

public class test {
    private static test ourInstance = null;

    public static synchronized test getInstance() {
        if (ourInstance == null)  {

            ourInstance = new test();
        }
        return ourInstance;
    }

    private test() {
    }
}
