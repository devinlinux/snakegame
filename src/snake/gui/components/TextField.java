package snake.gui.components;

//  imports
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;

public class TextField extends JPanel {

    //  fields and constants
    private final Font font = new Font("JetBrainsMono Nerd Font", Font.PLAIN, 16);

    private final Color foreground;
    private final Color background;
    private final String text;
    private JTextArea textArea;

    //  constructor
    public TextField(String text, Color foreground, Color background) {
        this.foreground = foreground;
        this.background = background;
        this.text = text;
        this.textArea = createTextArea();
        add(textArea);
    }

    //  create text area
    private JTextArea createTextArea() {
        JTextArea textArea = new JTextArea(this.text, 1, 16);
        textArea.setForeground(this.foreground);
        textArea.setBackground(this.background);
        textArea.setFont(font);
        return textArea;
    }

    //  get text
    public String getText() {
        return this.textArea.getText();
    }
}
