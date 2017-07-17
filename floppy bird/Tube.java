import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Random;

public class Tube
{
    private Random random;
    private BufferedImage img = null;
    private BufferedImage imginv = null;
    private int X = 900;
    private int Y = 0;
    private int GAP = 50;
    
    public Tube()
    {
        random = new Random();
        Y = random.nextInt(401) - 400;
        GAP = random.nextInt(250 - 200 + 1) + 200;
        try {
            img = ImageIO.read(new File("C:\\floppyBird\\tube.png"));
            imginv = ImageIO.read(new File("C:\\floppyBird\\tubeinverted.png"));
        } catch (IOException ioe) {}
    }
    
    public int getX() {
        return X;
    }
    
    public Rectangle getUpRect() {
        return new Rectangle(X+177, Y, 65, 420);
    }
    
    public Rectangle getDownRect() {
        return new Rectangle(X+177, Y+420+GAP, 65, 420);
    }
        
    public void paint(Graphics2D g) {
        g.drawImage(imginv, X, Y, null);
        g.drawImage(img, X, Y+420+GAP, null);
    }
    
    public void move() {
        X--;
    }
}
