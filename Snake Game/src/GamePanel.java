import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 800;
    static final int UNIT_SIZE = 20;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 75;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyParts = 8;
    int applesEaten;
    int appleX;
    int appleY;

    int pearX;
    int pearY;

    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;


    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.black);
       // this.setVisible(true);
        this.setFocusable(true);
        this.addKeyListener(new MyKeyAdapter());
        startGame();
    }

    public void startGame(){

        newApple();
        newPear();
        running = true;
        timer = new Timer(DELAY,this);
        timer.start();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
       // for (int i= 0;i<SCREEN_HEIGHT/UNIT_SIZE;i=i+1){
       //     g.drawLine(i*UNIT_SIZE,0,i*UNIT_SIZE,SCREEN_HEIGHT);
       //     g.drawLine(0,i*UNIT_SIZE,SCREEN_HEIGHT,i*UNIT_SIZE);
      //  }
        if(running) {
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
            g.setColor(Color.YELLOW);
            g.fillOval(pearX, pearY,UNIT_SIZE,UNIT_SIZE);

            for (int i = 0; i <= bodyParts; i = i + 1) {
                if (i == 0) {
                    g.setColor(Color.blue);
                    g.fillOval(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            g.setColor(Color.blue);
            g.setFont(new Font("Ink Free",Font.BOLD,30));
            FontMetrics metrics = getFontMetrics(g.getFont());
            g.drawString("Score: "+applesEaten,(SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2,
                    g.getFont().getSize());


        }
        else {
            gameOver(g);
        }

    }

    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

    }

    public void newPear(){
        pearX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        pearY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;

    }

    public void move(){
        for(int i = bodyParts;i>0;i=i-1){
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction){
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }

    }

    public void checkApple(){
        if (x[0]==appleX&&y[0]==appleY){
            bodyParts = bodyParts + 1;
            applesEaten = applesEaten + 1;
            newApple();
        }

    }

    public void checkPear(){
        if (x[0]==pearX&&y[0]==pearY){
            bodyParts = bodyParts - 1;
            newPear();
        }

    }

    public void checkCollisions(){
        // checks if head collides with body
        for (int i = bodyParts; i >0;i = i-1){
            if(x[0]==x[i]&&y[0]==y[i]){
                running  = false;
            }
        }
        // check if hit wall
        if (x[0]<0){
            running = false;
        } else if (x[0]>SCREEN_WIDTH) {
            running = false;
        } else if (y[0]>SCREEN_HEIGHT) {
            running = false;
        } else if (y[0]<0) {
            running = false;
        }

        if(!running){
            timer.stop();
        }

    }

    public void gameOver(Graphics g){
// Game over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free",Font.BOLD,75));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Game Over",(SCREEN_WIDTH - metrics.stringWidth("Game Over"))/2,
                (SCREEN_HEIGHT/2));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Final Score: "+applesEaten,(SCREEN_WIDTH - metrics1.stringWidth("Final Score: "+applesEaten))/2,
                g.getFont().getSize());

    }

    public void actionPerformed(ActionEvent e){
        if(running){
            move();
            checkApple();
            checkPear();
            checkCollisions();

        }

        repaint();

    }
    public class MyKeyAdapter extends KeyAdapter{

        public void keyPressed(KeyEvent e){
            switch (e.getKeyCode()){
                case KeyEvent.VK_LEFT:
                    if (direction!= 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction!= 'L'){
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction!= 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction!= 'U'){
                        direction = 'D';
                    }
                    break;
            }
        }
    }
}
