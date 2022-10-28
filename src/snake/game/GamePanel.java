package snake.game;//  imports
import snake.gui.settings.SettingsFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.Timer;
import javax.swing.JPanel;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Random;

import snake.gui.Frame;

public class GamePanel extends JPanel implements ActionListener {

    //  fields and constants
    private final int WIDTH = 1200;
    private final int HEIGHT = 800;
    private final int GRID_SIZE = 50;
    private final int NUM_UNITS = (WIDTH * HEIGHT) / (GRID_SIZE * GRID_SIZE);
    private int DELAY = 75;
    private final int[] x = new int[NUM_UNITS];
    private final int[] y = new int[NUM_UNITS];
    private final Font gameFont;
    private final FontMetrics gameFontMetrics;
    private final GamePanel gamePanel = this;


    //  schools
    private BufferedImage biLogo;
    private BufferedImage oconnellLogo;
    private BufferedImage pviLogo;
    private BufferedImage gonzagaLogo;
    private BufferedImage jpgLogo;
    private BufferedImage demathaLogo;
    private BufferedImage carrollLogo;
    private BufferedImage maryRykenLogo;

    //  programming languages
    private BufferedImage java;
    private BufferedImage cpp;
    private BufferedImage c;
    private BufferedImage rust;
    private BufferedImage javascript;
    private BufferedImage html;
    private BufferedImage csharp;
    private BufferedImage python;

    //  operating systems
    private BufferedImage archLinux;
    private BufferedImage gentooLinux;
    private BufferedImage fedora;
    private BufferedImage lfs;
    private BufferedImage bsd;
    private BufferedImage ubuntu;
    private BufferedImage windows;
    private BufferedImage osx;

    private int bodyLength = 6;
    private int applesEaten;
    private int appleX;
    private int appleY;
    private boolean running = false;
    private char direction;
    private String endMessage;
    private Timer timer;
    private Random random;
    private int highScore;
    private Frame frame;
    private boolean loggedIn;

    //  constructor
    public GamePanel(Frame frame) {
        this.random = new Random();
        this.gameFont = new Font("JetBrainsMono Nerd Font", Font.BOLD, 35);
        this.gameFontMetrics = getFontMetrics(this.gameFont);
        this.direction = 'r';
        this.frame = frame;
        this.highScore = getHighScore();
        this.loggedIn = frame.getLoggedIn();
        setup();
        initLogos();
        startGame();
    }

    //  TODO: add other images, add point system for "apples" - apple texture

    //  setup method
    public void setup() {

        //  making the key listener
        KeyListener kl = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                //  do nothing
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();

                switch (code) {
                    case KeyEvent.VK_LEFT, KeyEvent.VK_A -> {
                        if (direction != 'r') {
                            direction = 'l';
                        }
                    }
                    case KeyEvent.VK_RIGHT, KeyEvent.VK_D -> {
                        if (direction != 'l') {
                            direction = 'r';
                        }
                    }
                    case KeyEvent.VK_UP, KeyEvent.VK_W -> {
                        if (direction != 'd') {
                            direction = 'u';
                        }
                    }
                    case KeyEvent.VK_DOWN, KeyEvent.VK_S -> {
                        if (direction != 'u') {
                            direction = 'd';
                        }
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //  do nothing
            }
        };

        //  setting the attributes of the panel
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        addKeyListener(kl);
        setFocusable(true);
        requestFocus();
    }

    //  start game method
    public void startGame() {
        createNewApple();
        this.running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    //  create new apple method
    public void createNewApple() {
        this.appleX = random.nextInt((WIDTH / GRID_SIZE)) * GRID_SIZE;
        this.appleY = random.nextInt((HEIGHT / GRID_SIZE)) * GRID_SIZE;
    }

    //  paint component
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Toolkit.getDefaultToolkit().sync();
        draw(g);
    }

    //  draw method
    public void draw(Graphics g) {
        Toolkit.getDefaultToolkit().sync();
        //  only perform if running
        if (running) {

            //  filling an apple
            g.setColor(Color.RED);
            g.fillOval(this.appleX, this.appleY, GRID_SIZE, GRID_SIZE);

            int logo;
            //  drawing the snake
            for (int i = 0; i < this.bodyLength; i++) {
                if (i == 0) {
                    g.setColor(new Color(85, 107, 47));
                    //g.fillRect(this.x[i], this.y[i], GRID_SIZE, GRID_SIZE);
                    g.drawImage(this.biLogo, this.x[i], this.y[i], null);
                    g.setColor(Color.WHITE);
                    //g.drawRect(this.x[i], this.y[i], this.GRID_SIZE, this.GRID_SIZE);
                } else {
                    g.setColor(Color.GREEN);
                    //g.fillRect(this.x[i], this.y[i], GRID_SIZE, GRID_SIZE);

                    logo = i < 8 ? i : i - ((i / 7) * 7);

                    //g.drawImage(getSchoolLogo(logo), this.x[i], this.y[i], null);
                    g.drawImage(getOSLogo(logo), this.x[i], this.y[i], null);
                    g.setColor(Color.WHITE);
                    //g.drawRect(this.x[i], this.y[i], this.GRID_SIZE, this.GRID_SIZE);
                }
            }

            g.setColor(Color.BLUE);
            g.setFont(this.gameFont);
            FontMetrics gameFontMetrics = this.gameFontMetrics;
            g.drawString("Score: " + this.applesEaten, (WIDTH - gameFontMetrics.stringWidth("Score: " + this.applesEaten)) / 2, this.gameFont.getSize());
        } else {

            //  if the game is not running
            gameEnd(g);
        }
    }

    //  game end method
    public void gameEnd(Graphics g) {

        g.setColor(Color.BLUE);
        g.setFont(gameFont);
        FontMetrics gameFontMetrics = this.gameFontMetrics;
        g.drawString("Score: " + this.applesEaten, (WIDTH - gameFontMetrics.stringWidth("Score: " + applesEaten)) / 2, this.gameFont.getSize());
        g.drawString("Game Over: " + this.endMessage, (WIDTH - gameFontMetrics.stringWidth("Game Over: " + this.endMessage)) / 2, HEIGHT / 2);

        if (this.applesEaten > this.highScore) {
            writeHighScore();
            g.drawString("New High Score: " + this.applesEaten, (WIDTH - gameFontMetrics.stringWidth("New High Score: " + this.applesEaten)) / 2, (HEIGHT / 2) + 40);
        } else {
            g.drawString("High Score: " + readHighScore(), (WIDTH - gameFontMetrics.stringWidth("High Score: " + readHighScore())) / 2, (HEIGHT / 2) + 40);
        }
    }

    //  move method
    public void move() {
        Toolkit.getDefaultToolkit().sync();
        for (int i = this.bodyLength; i > 0; i--) {
            this.x[i] = this.x[i - 1];
            this.y[i] = this.y[i - 1];
        }

        //  moving based on direction
        switch (this.direction) {
            case 'r' -> this.x[0] = this.x[0] + GRID_SIZE;
            case 'l' -> this.x[0] = this.x[0] - GRID_SIZE;
            case 'u' -> this.y[0] = this.y[0] - GRID_SIZE;
            case 'd' -> this.y[0] = this.y[0] + GRID_SIZE;
            default -> System.out.println("That is not a valid direction");
        }
    }

    //  check for collisions method
    public void checkForCollision() {

        //  head colliding with body
        for(int i = this.bodyLength; i > 0; i--) {
            if (this.x[0] == this.x[i] && this.y[0] == this.y[i]) {
                running = false;  //  end the game
                this.endMessage = "Ran into self";
                break;
            }
        }

        //  head colliding with west border
        if (this.x[0] < 0) {
            running = false;  //  end the game
            this.endMessage = "Ran into wall";
        }

        //  head colliding with east border
        if (this.x[0] >= WIDTH) {
            running = false;  //  end the game
            this.endMessage = "Ran into wall";
        }

        //  head colliding with north border
        if (this.y[0] < 0) {
            running = false;  //  end the game
            this.endMessage = "Ran into wall";
        }

        //  head colliding with south border
        if (this.y[0] >= HEIGHT) {
            running = false;  //  end the game
            this.endMessage = "Ran into wall";
        }

        //  ending timer once the game is over
        if (!running) {
            timer.stop();
        }

    }

    //  check apple method
    public void checkApple() {
        if ((this.x[0] == this.appleX) && (this.y[0] == this.appleY)) {
            this.bodyLength += 1;
            this.applesEaten += 1;
            createNewApple();
        }
    }

    //  get random logo
    public BufferedImage getSchoolLogo(int logo) {
        switch (logo) {
            case 1 -> {
                return this.maryRykenLogo;
            }
            case 2 -> {
                return this.carrollLogo;
            }
            case 3 -> {
                return this.demathaLogo;
            }
            case 4 -> {
                return this.gonzagaLogo;
            }
            case 5 -> {
                return this.jpgLogo;
            }
            case 6 -> {
                return this.oconnellLogo;
            }
            case 7 -> {
                return this.pviLogo;
            }
        }
        return null;
    }

    public BufferedImage getOSLogo(int logo) {
        switch (logo) {
            case 1 -> {
                return this.archLinux;
            }
            case 2 -> {
                return this.gentooLinux;
            }
            case 3 -> {
                return this.bsd;
            }
            case 4 -> {
                return this.fedora;
            }
            case 5 -> {
                return this.ubuntu;
            }
            case 6 -> {
                return this.lfs;
            }
            case 7 -> {
                return this.windows;
            }
            case 8 -> {
                return this.osx;
            }
        }

        return null;
    }

    //  init logos method
    public void initLogos() {
        try {

            //  school logos
            this.biLogo = ImageIO.read(new File("assets/images/schools/bilogo.png"));
            this.maryRykenLogo = ImageIO.read(new File("assets/images/schools/maryryken.png"));
            this.carrollLogo = ImageIO.read(new File("assets/images/schools/carroll.png"));
            this.demathaLogo = ImageIO.read(new File("assets/images/schools/dematha.png"));
            this.gonzagaLogo = ImageIO.read(new File("assets/images/schools/gonzaga.png"));
            this.jpgLogo = ImageIO.read(new File("assets/images/schools/jpg.png"));
            this.oconnellLogo = ImageIO.read(new File("assets/images/schools/oconnell.png"));
            this.pviLogo = ImageIO.read(new File("assets/images/schools/pvi.png"));

            //  programming lanugage logos

            //  operating system logos
            this.archLinux = ImageIO.read(new File("assets/images/oss/arch.png"));
            this.gentooLinux = ImageIO.read(new File("assets/images/oss/gentoo.png"));
            this.fedora = ImageIO.read(new File("assets/images/oss/fedora.png"));
            this.bsd = ImageIO.read(new File("assets/images/oss/bsd.png"));
            this.lfs = ImageIO.read(new File("assets/images/oss/lfs.png"));
            this.ubuntu = ImageIO.read(new File("assets/images/oss/ubuntu.png"));
            this.windows = ImageIO.read(new File("assets/images/oss/windows.png"));
            this.osx = ImageIO.read(new File("assets/images/oss/mac.png"));
        } catch (IOException e) {
            //  do nothing
        }
    }

    //  write high score
    public void writeHighScore() {
        try {
            PrintWriter writer = new PrintWriter("data/users/" + frame.getUser() + ".txt", "UTF-8");
            writer.println(frame.getPwd());
            writer.println(this.applesEaten);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            //  do nothing
        }
    }

    //  read high score
    public String readHighScore() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/users/" + frame.getUser() + ".txt"));
            reader.readLine();
            String highScore = reader.readLine();
            reader.close();
            return highScore;
        } catch (IOException e) {
            //  do nothing
        }
        return null;
    }

    public int getHighScore() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/users/" + frame.getUser() + ".txt"));
            reader.readLine();
            String highScore = reader.readLine();
            reader.close();
            return Integer.parseInt(highScore);
        } catch (IOException e) {
            //  do nothing
        }
        return 0;
    }

    //  start timer
    public void startTimer() {
        timer = new Timer(DELAY, this);
        running = true;
        startGame();
    }

    //  get timer
    public Timer getTimer() {
        return this.timer;
    }

    //  actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {

        //  perform the actions only if the game is running
        if (running) {
            move();
            checkApple();
            checkForCollision();
        }
        repaint();
    }
}
