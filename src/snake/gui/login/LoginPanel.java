package snake.gui.login;

//  imports
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.w3c.dom.Text;
import snake.gui.components.Button;
import snake.gui.components.PassField;
import snake.gui.components.TextField;
import snake.gui.Frame;

public class LoginPanel extends JPanel {

    //  fields and constants
    private final int WIDTH = 500;
    private final int HEIGHT = 250;
    private LoginFrame frame;
    private Frame mainFrame;
    private Secure secure;
    private TextField username;
    private PassField password;

    //  constructor
    public LoginPanel(LoginFrame frame, Frame mainFrame) {
        setup();
        this.frame = frame;
        this.mainFrame = mainFrame;

    }

    //  setup method
    public void setup() {

        //  setting the attributes of the panel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        addComponents();
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus();
    }

    //  add components
    public void addComponents() {
        this.username = new TextField("Username", Color.GREEN, Color.BLACK);
        this.password = new PassField(Color.GREEN, Color.BLACK);

        add(username);
        add(password);
        add(new Button("Submit", Color.GREEN, Color.BLACK, new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Secure secure = new Secure(username.getText(), password.getPassword());
                boolean alreadyCreated = secure.start();
                if (alreadyCreated) {
                    boolean b = secure.verify();
                    mainFrame.setUser(username.getText());
                    mainFrame.setPwd(secure.encrypt(password.getPassword()));
                    System.out.println("LOGGED IN");
                    frame.dispose();
                } else {
                    int i = secure.writeToFile();
                    if (i == 1) {
                        frame.dispose();
                        mainFrame.setLoggedIn(true);
                        mainFrame.setUser(username.getText());
                    }
                }
            }
        }));
    }

    //  paint component
    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(Color.GREEN);
        g.drawRect(0, 0, getWidth(), getHeight());
    }
}
