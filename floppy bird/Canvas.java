import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.ConcurrentModificationException;

public class Canvas extends JPanel {
    
    private Background background;
    private Scroll scroll;
    private Bird bird;
    private ArrayList<Tube> tubes;
    private Iterator<Tube> iter;
    
    private KeyListener listener;
    
    private Graphics2D g2d;
    
    private int score = 0;
    
    private Random random;
    
    private boolean flying = false; 
    
    private int counter = 0;
    private int GAP = 500;
    
    private boolean started = false;
    private boolean dead = false;
    private static final int GRAVITY = 30;
        
    public Canvas(int n) {}
    
    public Canvas() { 
        random = new Random();
        background = new Background();
        scroll = new Scroll();
        bird = new Bird();
        tubes = new ArrayList<>();
        
        listener = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        if (!started) {started=true;}
                        if (!dead) {
                            flying = true;
                        } else {
                            if (bird.getY()>=480) {
                                started=false;
                            }
                        }
                    break;
                }
                repaint();
            }
            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_SPACE:
                        flying = false;
                    break;
                }
            }
        };
        addKeyListener(listener);
        setFocusable(true);
        requestFocusInWindow();
   	    Thread t1 = new Thread(new Runnable() {
    	public void run()
    	{
			while (true) {
				try {
				    while (flying) {
				        bird.fly();
				        Thread.sleep(1);
				    }
    		    } catch (InterruptedException ie) {} catch (ConcurrentModificationException ce) {}
    	   }
    	}});  
    	t1.start();
    }
    
    public void act() {
        if (!dead) {
            for (Tube t: tubes) {
                t.move();
            }
            scroll.move();
            if (counter < GAP ) {
                counter++;
            } else {
                tubes.add(new Tube());
                iter = tubes.iterator();
                while (iter.hasNext()) {
                    Tube t = iter.next();
                
                    if (t.getX()<-200) {
                        iter.remove();
                    }
                }
                counter=0;
            }
            GAP = random.nextInt(500 + 1 + 200) + 200;
        }
    }
    
    public void collisions() {
        for (Tube t: tubes) {
            if (t.getUpRect().intersects(bird.getRect()) || t.getDownRect().intersects(bird.getRect())) {
                dead=true;
                bird.die();
            }
            if (t.getX()==80) {
                score++;
            }
        }
    }
    
    public void fall() {
        bird.fall();
    }
    
    public boolean launched() {
        return started;
    }
                   
    @Override
    public void paint(Graphics e) {
        super.paint(e);
        g2d = (Graphics2D) e;
        background.paint(g2d);
        iter = tubes.iterator();
        while (iter.hasNext()) {
            iter.next().paint(g2d);
        }
        scroll.paint(g2d);
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("TimesRoman", Font.PLAIN, 30)); 
        g2d.drawString(""+score, 425, 100);
        bird.paint(g2d);
    }
        
    public static void main(String[] args) {
        while (true) {
            JFrame frame = new JFrame("Floppy Bird");
            frame.setSize(900, 588);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Canvas juego = new Canvas();
            frame.add(juego);
            frame.setVisible(true);
            
            while (!juego.launched()) {
            }
            
            while (juego.launched()) {
                try {
                    Thread.sleep(5);
                } catch (InterruptedException ie) {}
                juego.act();
                juego.fall();
                juego.collisions();
                juego.repaint();
            }
            frame.dispose();
        }
    }
}