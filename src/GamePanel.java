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
    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 800;
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
    }
    
    public void startGame(){
        
    }
    
    public void paintComponent(Graphics g){
        
    }
    
    public void draw(Graphics g){
        
    }
    
    public void move(){
        
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
