package snake.gui.settings;//  imports
import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import snake.game.GamePanel;
public class SettingsFrame extends JFrame {

    //  constants and fields

    //  constructor
    public SettingsFrame() {
        setup();
    }

    //  setup method
    public void setup() {
        setTitle("Snake! Settings");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        add(new SettingsPanel());
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
