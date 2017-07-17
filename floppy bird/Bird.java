import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.Rectangle;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Bird
{
    private BufferedImage img = null;
    private double timefalling = 1;
    private int X = 200;
    private int Y = 300;
    
    public Bird()
    {
        try {
            img = ImageIO.read(new File("C:\\floppyBird\\bird.png"));
        } catch (IOException ioe) {}
    }
    
    public int getX() {
        return X;
    }
    
    public int getY() {
        return Y;
    }
    
    public void fall() {
        if (Y < 480) {
            try {
                img = ImageIO.read(new File("C:\\floppyBird\\birdfalling.png"));
            } catch (IOException ioe) {}
            Y+=(1*timefalling);
            timefalling+=0.01;
        }
    }
    
    public void die() {
        timefalling=3;
    }
    
    public void fly() {
        if (Y>0) {
            try {
                img = ImageIO.read(new File("C:\\floppyBird\\birdflying.png"));
            } catch (IOException ioe) {}
            timefalling = 1;
            Y--;
        }
    }
    
    public Rectangle getRect() {
        return new Rectangle(X, Y, 35, 28);
    }
        
    public void paint(Graphics2D g) {
        g.drawImage(img, X, Y, null);
    }
}
