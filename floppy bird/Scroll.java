import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Scroll
{
    
    private BufferedImage img = null;
    private int X = 0;
    
    public Scroll()
    {
        try {
            img = ImageIO.read(new File("C:\\floppyBird\\bar.png"));
        } catch (IOException ioe) {}
    }
    
    public void paint(Graphics2D g) {
        g.drawImage(img, X, 509, null);
        g.drawImage(img, X+900, 509, null);
    }
    
    public void move() {
        if (X<=-900) {X=0;} else {X--;}
    }
}
