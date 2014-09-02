package chickengame;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ricardo
 */
public class Game implements Runnable {

    final int WIDTH = 800;
    final int HEIGHT = 500;
    
    JFrame frame;
    Canvas canvas;
    BufferStrategy bufferStrategy;
    
    long desiredFPS = 60;
    long desiredDeltaLoop = (1000*1000*1000)/desiredFPS;
    
    boolean running = true;
    
    Chicken chicken1;
    
    public Game() {
        frame = new JFrame("Basic Game");

        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);

        canvas.addMouseListener(new MouseControl());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();

        canvas.requestFocus();
    }
    
    private class MouseControl extends MouseAdapter{
      public void mousePressed(MouseEvent e) {
          int mouseX = e.getX();
          int mouseY = e.getY();
          
          if (mouseX >= x && mouseX <= x+60
                  && mouseY > y && mouseY <= y+60)
          {
              //hit
              System.out.println("\nHIT BITCH!");
          }
      }
   }
    
    public void Init() {
        chicken1 = new Chicken(100, 100);
    }
    
    public void run() {

        long beginLoopTime;
        long endLoopTime;
        long currentUpdateTime = System.nanoTime();
        long lastUpdateTime;
        long deltaLoop;

        while (running) {
            beginLoopTime = System.nanoTime();

            render();

            lastUpdateTime = currentUpdateTime;
            currentUpdateTime = System.nanoTime();
            update((int) ((currentUpdateTime - lastUpdateTime) / (1000 * 1000)));

            endLoopTime = System.nanoTime();
            deltaLoop = endLoopTime - beginLoopTime;

            if (deltaLoop > desiredDeltaLoop) {
                //Do nothing. We are already late.
            } else {
                try {
                    Thread.sleep((desiredDeltaLoop - deltaLoop) / (1000 * 1000));
                } catch (InterruptedException e) {
                    //Do nothing
                }
            }
        }
    }
    
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        render(g);
        g.dispose();
        
        bufferStrategy.show();
    }
    
    //TESTING
    private double x = 0;
    private double y = 0;

    /**
     * Rewrite this method for your game
     */
    protected void update(int deltaTime) {
        x += deltaTime * 0.2;
        while (x > 500) {
            x -= 500;
        }
        
        y += deltaTime * 0.2;
        while (y > 500) {
            y -= 500;
        }
    }
    
    /**
     * Rewrite this method for your game
     */
    protected void render(Graphics2D g) {
        g.fillRect((int) x, (int)y, 60, 60);
    }

    public static void main(String[] args) {
        Game ex = new Game();
        new Thread(ex).start();
    }    
}
