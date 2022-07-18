
import javax.swing.JFrame;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author calvi
 */
public class GameFrame extends JFrame {

    GameFrame() {
        GamePanel panel = new GamePanel(); //create instance of GamePanel
        this.add(panel); //adds panel to GameFrame
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();

    }
}
