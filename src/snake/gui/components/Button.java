package snake.gui.components;

//  imports
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class Button extends JPanel {

    //  fields
    private String text;
    private Color foreground;
    private Color background;
    private Font font;
    private Dimension dimension;

    //  normal constructor
    public Button(String text, Color foreground, Color background, Font font) {
        this.text = text;
        this.foreground = foreground;
        this.background = background;
        this.font = font;
        this.dimension = new Dimension(250, 50);
        JButton button = createButton();
        add(button);
        setBackground(this.background);
    }

    //  constructor with action listener
    public Button(String text, Color foreground, Color background, Font font, ActionListener listener) {
        this.text = text;
        this.foreground = foreground;
        this.background = background;
        this.font = font;
        JButton button = createButton();
        button.addActionListener(listener);
        add(button);
        setOpaque(true);
        setBackground(this.background);
    }

    //  normal constructor with dimension
    public Button(String text, Color foreground, Color background, Font font, Dimension dimension) {
        this.text = text;
        this.foreground = foreground;
        this.background = background;
        this.font = font;
        this.dimension = dimension;
        JButton button = createButton();
        add(button);
        setOpaque(true);
        setBackground(this.background);
    }

    //  constructor with action listener and dimension
    public Button(String text, Color foreground, Color background, Font font, ActionListener listener, Dimension dimension) {
        this.text = text;
        this.foreground = foreground;
        this.background = background;
        this.font = font;
        this.dimension = dimension;
        JButton button = createButton();
        button.addActionListener(listener);
        add(button);
        setOpaque(true);
        setBackground(this.background);
    }

    //  createButton method
    private JButton createButton() {
        JButton button = new JButton(this.text);
        button.setFont(this.font);
        button.setForeground(this.foreground);
        button.setBackground(this.background);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMinimumSize(this.dimension);
        button.setPreferredSize(this.dimension);
        button.setMaximumSize(this.dimension);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        return button;
    }
}
