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
public class GamePanel extends JPanel implements ActionListener{
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25; //size of objects in game
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE; //how many units can fit into screen
    static final int DELAY = 100;
    final int x[] = new int [GAME_UNITS];
    final int y[] = new int [GAME_UNITS];
    int bodyOfSnake = 6;
    int applesEaten;
    int appleX; //x coordinate of apple which will appear randomly
    int appleY; //y coordinate of apple which will appear randomly
    char direction = 'R'; //direction of snake at start
    boolean running = false;
    Timer timer;
    Random random;
    
    
    GamePanel(){
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new Keys());
        startGame();
    }
    
    public void startGame(){
        newApple(); //call new apple method to create new apple in game
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }
    
    public void draw(Graphics g){
        //turn to grid with vertical lines and horizontal lines
        for(int i=0; i<(SCREEN_HEIGHT/UNIT_SIZE); i++){
            g.drawLine(i*UNIT_SIZE,0 , i*UNIT_SIZE, SCREEN_HEIGHT);
            g.drawLine(0,i*UNIT_SIZE ,SCREEN_WIDTH, i*UNIT_SIZE);
        }
        g.setColor(Color.red);
        g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE); //unit size will be how large apple is
        
        for(int i = 0; i < bodyOfSnake; i++){
            if(i == 0){ //head of snake
                g.setColor(Color.green);
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            } else {
                g.setColor(new Color(45,180,0));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }
        }
    }
    
    /**
     * Method to generate coordinates of new apple when called
     */
    public void newApple(){
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
    }
    
    /**
     * Method to move the snake
     */
    public void move(){
        for(int i=bodyOfSnake; i>0;i--){
            x[i] = x[i]-1; //shift coordinates of array by 1
            y[i] = y[i]-1;
        }
        switch(direction){
            case 'U':
                y[0] = y[0]-UNIT_SIZE; //y coordinate of head of snake
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
    public void checkPoints(){
        
    }
    
    /** 
     * Method to check if the snake has collided
     */
    public void checkCollisions(){
        
    }
    
    /**
     * Method to tell player game is over
     * @param g 
     */
    public void gameOver(Graphics g){
        
    }
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running){
            move(); //move snake
            checkPoints();
            checkCollisions();
        }
        repaint();
    }
    
    /**
     * Inner class for keyboard
     */
    public class Keys extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            
        }
    }
}
