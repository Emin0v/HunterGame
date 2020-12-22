package mygame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

class Fire {

    private int x;
    private int y;

    public Fire(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}

public class Game extends JPanel implements KeyListener, ActionListener {

    Timer timer = new Timer(5, this);

    private int time = 0;
    private int spent_fire = 0;

    BufferedImage image;

    private ArrayList<Fire> atesler = new ArrayList<>();
    private int atesDirX = 1;

    private int duckY = 0;
    private int duckAddY = 2;

    private int hunterY = 0;
    private int hunterAddY = 10;
    
    public boolean control(){
        for(Fire ates : atesler){
            
            if(new Rectangle(ates.getX(),ates.getY(),10,20).intersects(new Rectangle(770,duckY,20,20))){
              return true;
            }
        }
        return false;
    }

    public Game() {

        try {
            image = ImageIO.read(new FileInputStream("images.png"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        setBackground(Color.black);
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        time += 5;
        g.setColor(Color.red);
        g.fillOval(770, duckY, 20, 20);
        g.drawImage(image, 0, hunterY, image.getWidth() / 3, image.getHeight() / 2, this);

        for (Fire ates : atesler) {
            if (ates.getX() > 780) {
                atesler.remove(ates);
            }
        }
        g.setColor(Color.orange);
        for (Fire ates : atesler) {
            g.fillRect(ates.getX(), ates.getY(), 10, 20);
        }
        
         if(control()){
            timer.stop();
            String message="You Win...\n"+
                    "\nspent fire - "+spent_fire+
                    "\ntime - "+time /1000.0+" seconds" ;
            
            JOptionPane.showMessageDialog(this, message);
            System.exit(0);
        }
    }

    @Override
    public void repaint() {
        super.repaint(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int c = e.getKeyCode();

        if (c == KeyEvent.VK_UP) {
            if (hunterY < 0) {
                hunterY = 0;
            } else {
                hunterY -= hunterAddY;
            }

        } else if (c == KeyEvent.VK_DOWN) {
            if (hunterY >550) {
                hunterY = 550;
            } else {
                hunterY += hunterAddY;
            }
        }
        else if(c==KeyEvent.VK_CONTROL){
            atesler.add(new Fire(80, hunterY+25));
            
            spent_fire++;
        }
    }

        @Override
        public void keyReleased
        (KeyEvent e
        
        
        ) {

    }

    @Override
        public void actionPerformed(ActionEvent e ) {

        for (Fire ates : atesler) {
                ates.setX(ates.getX() + atesDirX);
            }

            duckY += duckAddY;

            if (duckY < 0) {
                duckAddY = -duckAddY;
            }
            if (duckY > 550) {
                duckAddY = -duckAddY;
            }
            repaint();
        }

    }
