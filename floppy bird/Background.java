import java.awt.Graphics2D;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Background
{
    
    private BufferedImage img = null;
    
    public Background()
    {
        try {
            img = ImageIO.read(new File("C:\\floppyBird\\bg.png"));
        } catch (IOException ioe) {}
    }
    
    public void paint(Graphics2D g) {
        g.drawImage(img, 0, 0, null);
    }
}
