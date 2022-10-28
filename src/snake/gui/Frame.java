package snake.gui;

//  imports
import snake.game.GamePanel;
import snake.gui.start.StartPanel;

import javax.swing.JFrame;

public class Frame extends JFrame {

    //  fields
    private String stage;
    private boolean loggedIn;
    private StartPanel startPanel;
    private String user;
    private String pwd;

    //  constructor
    public Frame(String stage) {
        this.stage = stage;
        this.loggedIn = false;
        this.startPanel = new StartPanel(this);
        this.user = "highscore";
        setUser(this.user);
        this.pwd = "";
        setup();
    }

    //  setup method
    public void setup() {
        setTitle("Snake!");
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        addStage();
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    //  add stage
    public void addStage() {
        switch (stage) {
            case "start" -> {
                add(startPanel);
            }
            case "game" -> {
                GamePanel panel = new GamePanel(this);
                add(panel);
                panel.requestFocusInWindow();
            }
            case "end" -> {
                //  TODO: make end screen
            }
        }
    }

    //  setters
    public void setStage(String stage) {
        this.stage = stage;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setLoggedIn(boolean b) {
        this.loggedIn = b;
    }

    //  get logged in
    public boolean getLoggedIn() {
        return this.loggedIn;
    }

    public String getUser() {
        return this.user;
    }

    public String getPwd() {
        return this.pwd;
    }
}
