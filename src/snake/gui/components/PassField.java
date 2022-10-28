package snake.gui.components;

//  imports
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;

public class PassField extends JPanel {

    //  fields and constants
    private final Font font = new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16);

    private final Color foreground;
    private final Color background;
    private JPasswordField passField;

    //  constructor
    public PassField(Color foreground, Color background) {
        this.foreground = foreground;
        this.background = background;
        JPasswordField anonymousField = createPassField();
        add(anonymousField);
    }

    //  create pass field
    private JPasswordField createPassField() {
        this.passField = new JPasswordField("", 16);
        passField.setForeground(this.foreground);
        passField.setBackground(this.background);
        passField.setFont(font);
        return passField;
    }

    //  get password
    public String getPassword() {
        return this.passField.getPassword().toString();
    }
}
