package chickengame;

import chickengame.controller.GameLoopController;
import chickengame.controller.MouseController;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;

import java.awt.image.BufferStrategy;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ricardo
 */
public class Game implements Runnable, Observer {

    final static int WIDTH = 800;
    final static int HEIGHT = 500;
    
    JFrame frame;
    Canvas canvas;
    BufferStrategy bufferStrategy;
    
    
    
    Chicken chicken1;
    
    MouseController mouseController;
    
    GameLoopController gameLoopController;
    
    public Game() {
        frame = new JFrame("Basic Game");

        JPanel panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(null);

        canvas = new Canvas();
        canvas.setBounds(0, 0, WIDTH, HEIGHT);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();

        canvas.requestFocus();
        mouseController = new MouseController();
        gameLoopController = new GameLoopController();
        
        canvas.addMouseListener(mouseController);
        
        init();
    }
    
   
    public void init() {
        chicken1 = new Chicken(100, 100);
        mouseController.addObserver(chicken1);
        gameLoopController.addObserver(this);
        
    }
    
    public void run() {

        gameLoopController.loop();
    }
    
    private void render() {
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.clearRect(0, 0, WIDTH, HEIGHT);
        render(g);
        g.dispose();
        
        bufferStrategy.show();
    }
    


  
    
    /**
     * Rewrite this method for your game
     */
    protected void render(Graphics2D g) {
        g.fillRect(chicken1.getPosX(), chicken1.getPosY(), chicken1.HEIGHT, chicken1.WIDTH);
    }

    public static void main(String[] args) {
        Game ex = new Game();
        new Thread(ex).start();
    }    

    @Override
    public void update(Observable o, Object o1) {
        chicken1.tick();
        render();
    }
}
