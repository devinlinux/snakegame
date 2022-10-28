package snake.gui.settings;//  imports
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;

public class SettingsPanel extends JPanel {

    //  fields and constants
    private final int WIDTH = 500;
    private final int HEIGHT = 250;

    //  constructor
    public SettingsPanel() {
        setup();
    }

    //  setup method
    public void setup() {

        //  setting the attributes of the panel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus();
    }
}
