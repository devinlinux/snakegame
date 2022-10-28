package snake.gui.login;

//  imports
import javax.swing.JFrame;

import snake.gui.Frame;

public class LoginFrame extends JFrame {

    //  fields
    private Frame frame;
    private LoginPanel panel;

    //  constructor
    public LoginFrame(Frame frame) {
        this.frame = frame;
        this.panel = new LoginPanel(this, frame);
        setup();
    }

    //  setup
    public void setup() {
        setTitle("Snake! Login");
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        add(panel);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
