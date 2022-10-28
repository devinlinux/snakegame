import snake.gui.Frame;

//  imports
import snake.gui.login.Secure;

public class Game {

    //  main method
    public static void main(String... args) {
        Secure s = new Secure("admin", "admin");

        new Frame("start");
    }
}
