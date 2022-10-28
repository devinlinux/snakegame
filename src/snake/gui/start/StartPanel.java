package snake.gui.start;

//  imports
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import snake.gui.Frame;
import snake.gui.components.Button;
import snake.gui.login.LoginFrame;
import snake.gui.settings.SettingsFrame;

public class StartPanel extends JPanel {

    //  fields and constants
    private final int WIDTH = 1200;
    private final int HEIGHT = 800;
    private Frame frame;
    private LoginFrame loginFrame;

    //  constructor
    public StartPanel(Frame frame) {
        this.frame = frame;
        setup();
    }

    //  setup method
    public void setup() {

        //  setting the attributes of the panel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setLayout(new GridBagLayout());
        addComponents();
        setBackground(Color.BLACK);
        setFocusable(true);
        requestFocus();
    }

    //  add components
    public void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(new Button("Start Game", Color.GREEN, Color.BLACK, new Font("JetBrainsMono Nerd Font", Font.BOLD, 35), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setStage("game");
                frame.getContentPane().removeAll();
                frame.addStage();
                frame.repaint();
                frame.setVisible(true);
            }
        }), gbc);
        add(new Button("Login", Color.GREEN, Color.BLACK, new Font("JetBrainsMono Nerd Font", Font.BOLD, 20), e -> loginFrame = new LoginFrame(frame)), gbc);
        add(new Button("Settings", Color.GREEN, Color.BLACK, new Font("JetBrainsMono Nerd Font", Font.BOLD, 20), new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SettingsFrame frame = new SettingsFrame();
            }
        }));

    }
}
