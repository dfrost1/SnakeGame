
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author calvi
 */
public class GamePanel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25; //size of objects in game
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE; //how many units can fit into screen
    static  int DELAY = 200;
    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];
    int bodyOfSnake1 = 6;
    int bodyOfSnake2 = 6;
    int applesEaten;
    int appleX; //x coordinate of apple which will appear randomly
    int appleY; //y coordinate of apple which will appear randomly
    int speedX; //x coordinate apple for P2
    int speedY; //y coordinate apple for P2
    int pointX;
    int pointY;
    char direction = 'R'; //direction of snake at start
    char directionP2 = 'D'; //direction for P2
    boolean running = false;
    boolean checkGame = false;
    boolean power2 = true;
    boolean power3 = true;
    Timer timer;
    Random random;
    // Create JButton and JPanel
    JButton button = new JButton("Replay?");



    GamePanel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new Keys());
        startGame();
        if(running != false){
            this.add(button);
            button.setVisible(false);
        }

        
    }

    public void startGame() {
        newAppleP1(); //call new apple method to create new apple in game
        speedUp();
        extraPoint();
        
        
//        x2[0] = 300;
        running = true;
        checkGame =  true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {

        if (running) {

            //turn to grid with vertical lines and horizontal lines
            for (int i = 0; i < (SCREEN_HEIGHT / UNIT_SIZE); i++) {
                g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            }
         
            
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); //draw apple for P1
            
            //draw x2 speed powerup
            g.setColor(Color.blue);
            if(speedX != 0 && speedY != 0){
                power2 = true;
                g.fillRect(speedX, speedY, UNIT_SIZE  , UNIT_SIZE);
                g.setColor(Color.yellow);
                g.drawString("x2", (UNIT_SIZE + speedX - 18), (UNIT_SIZE + speedY -10));
            }else {
                power2 = false;
            }
            
            if(pointX != 0 && pointY != 0){
                power3 = true;
                g.setColor(Color.green);
                g.fillOval(pointX, pointY, UNIT_SIZE, UNIT_SIZE); //draw apple for P1
                g.setColor(Color.black);
                g.drawString("2P", (UNIT_SIZE + pointX - 18), (UNIT_SIZE + pointY -10));
            } else {
                power3 = false;
            }
            
            //draw snake
            for (int i = 0; i < bodyOfSnake1; i++) {
                if (i == 0) { //head of snake
                    g.setColor(Color.green);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            
        g.setColor(Color.red); //gameover colour
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics = getFontMetrics(g.getFont());
        g.drawString("Score:"+applesEaten, (SCREEN_WIDTH - metrics.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize()); //places text in centre of screen
        } else {
            gameOver(g);
        }
    }

    /**
     * Method to generate coordinates of new apple when called
     */
    public void newAppleP1() {
        appleX = random.nextInt((int) ((SCREEN_WIDTH/2) / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) ((SCREEN_WIDTH) / UNIT_SIZE)) * UNIT_SIZE;
        for (int i = 0; i < x.length; i++){
            if(x[i] == appleX && y[i] == appleY){
                appleX = random.nextInt((int) ((SCREEN_WIDTH/2) / UNIT_SIZE)) * UNIT_SIZE;
                appleY = random.nextInt((int) ((SCREEN_WIDTH) / UNIT_SIZE)) * UNIT_SIZE;
            }
        }
        
    }
    
    /**
     * Method to generate coordinates of new apple when called
     */
    public void speedUp() {
        speedX = random.nextInt((int) ((SCREEN_WIDTH/2) / UNIT_SIZE)) * UNIT_SIZE + SCREEN_WIDTH/2;
        speedY = random.nextInt((int) ((SCREEN_WIDTH) / UNIT_SIZE)) * UNIT_SIZE;
    }
    
    public void extraPoint(){
        pointX = random.nextInt((int) ((SCREEN_WIDTH/2) / UNIT_SIZE)) * UNIT_SIZE + SCREEN_WIDTH/2;
        pointY = random.nextInt((int) ((SCREEN_WIDTH) / UNIT_SIZE)) * UNIT_SIZE;        
        
        
        if(pointX == speedX && pointX == appleX){
            extraPoint();
        }
        if(pointY == speedY && pointX == appleY){
            extraPoint();
        }
    }
    

    /**
     * Method to move the snake
     */
    public void moveP1() {
        for (int i = bodyOfSnake1; i > 0; i--) {
            x[i] = x[i - 1]; //shift coordinates of array by 1
            y[i] = y[i - 1];
        }
        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE; //y coordinate of head of snake
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
    

    /**
     * Method to check points of player
     */
    public void checkPoints() {
        //if snake eats an apple
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyOfSnake1++;
            applesEaten++;
            newAppleP1();
            if(DELAY == 100){
                timer.stop();
                DELAY = 200;
                timer = new Timer(DELAY, this);
                timer.start();
            }
            //if snake eats apple and x2 powerup not in effect, randomly create x2 powerup
            int randomNumber1 = (int) (Math. random() * 2 + 1);
            int randomNumber2 = (int) (Math. random() * 2 + 1);
            if(randomNumber1 == randomNumber2 && DELAY == 200 && power2 != true){ 
                System.out.println(speedX);
                speedUp();
            } 
            
            //if eat apple and no 2P powerup present, create 2P powerup
            int randomNumber3 = (int) (Math. random() * 2+ 1);
            int randomNumber4 = (int) (Math. random() * 2 + 1);
            if(randomNumber3 == randomNumber4 && power3 != true){ 
                extraPoint();
            }
        }
        
        //if eat x2 powerup
        if ((x[0] == speedX) && (y[0] == speedY)) {
            timer.stop();
            DELAY = 100;
            timer = new Timer(DELAY, this);
            timer.start();
            speedX = 0;
            speedY = 0;
        }
        
        //if eat extra point powerup
        if ((x[0] == pointX) && (y[0] == pointY)) {
            applesEaten++;
            applesEaten++;
            bodyOfSnake1++;
            bodyOfSnake1++;
            pointX = 0;
            pointY = 0;
        }
        
        
    }

    /**
     * Method to check if the snake has collided
     */
    public void checkCollisions() {
        for (int i = bodyOfSnake1; i > 0; i--) {
            // shows head collided with body
            if ((x[0] == x[i]) && (y[0] == y[i])) {
                running = false; // triggers game over
            }
        }
        //checks if head touches border
        if (x[0] < 0) {
            running = false;
        }

        //checkes if head touches right border
        if (x[0] >= (SCREEN_WIDTH)) {
            running = false;
        }

        //checks if head touches top border
        if (y[0] < 0) {
            running = false;
        }

        //checks if head touches bottom border
        if (y[0] > SCREEN_HEIGHT) {
            running = false;
        }

        if (!running) {
            timer.stop();
        }
    }

    /**
     * Method to tell player game is over
     *
     * @param g
     */
    public void gameOver(Graphics g) {
        //display score
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 40));
        FontMetrics metrics1 = getFontMetrics(g.getFont());
        g.drawString("Score:"+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize()); //places text in centre of screen
        
        //Game Over text
        g.setColor(Color.red);
        g.setFont(new Font("Ink Free", Font.BOLD, 75));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Over", (SCREEN_WIDTH - metrics2.stringWidth("Game Over"))/2, SCREEN_HEIGHT/2); //places text in centre of screen
       
        checkGame = false;
        button.setVisible(true);
        Clicklistener click= new Clicklistener();
        button.addActionListener(click);     
    }
        
    private class Clicklistener implements ActionListener {
        public void actionPerformed(ActionEvent e){
            if (e.getSource() == button){                                  
            }
        }
    }
    
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            moveP1(); //move snake
//            moveP2();
            checkPoints();
            checkCollisions();
        }
        repaint();
    }

    /**
     * Inner class for keyboard
     */
    public class Keys extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
            }
        }
        
    }
}
