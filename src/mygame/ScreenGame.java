
package mygame;

import java.awt.HeadlessException;
import javax.swing.JFrame;


public class ScreenGame extends JFrame {

    public ScreenGame(String string) throws HeadlessException {
        super(string);
    }
    
    public static void main(String[] args) {
        
        ScreenGame screen = new ScreenGame("hunter");
        
        screen.setResizable(false);
        screen.setFocusable(false);
        
        screen.setSize(800,600);
        
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Game game = new Game();
        
        game.requestFocus();
        game.addKeyListener(game);
        
        game.setFocusable(true);
        game.setFocusTraversalKeysEnabled(false);
        
        screen.add(game);
        screen.setVisible(true);
    }
    
}
