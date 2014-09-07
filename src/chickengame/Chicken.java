package chickengame;

import chickengame.util.EventListenerList;
import chickengame.util.MouseCoordinate;
import event.HitListener;
import java.util.Observable;
import java.util.Observer;


/**
 *
 * @author Ricardo
 */
public class Chicken implements Observer {
    
    public final int HEIGHT = 60;
    
    public final int WIDTH = 60;
       
    private int speedX, speedY;
    
    private int posX, posY;
    
    private EventListenerList eventListeners = new EventListenerList();
    
    
    //Image Bufferedreader
    
    public Chicken(int positionX, int positionY) {
        this.posX = positionX;
        this.posY = positionY;
        
        speedX = 1;
        speedY = 2;
        
    }
    
    @Override
    public void update(Observable o, Object o1) {
        MouseCoordinate coordinate = (MouseCoordinate) o1;
        
         if (coordinate.getXpos() >= posX && coordinate.getXpos() <= posX+WIDTH
                  && coordinate.getYpos() >= posY && coordinate.getYpos() <= posY+HEIGHT)
          {
              //hit
              System.out.println("\nHIT! X="+posX+" Y="+posY );
              
              
            increaseSpeed();
          }
         else {
             decreaseSpeed();
         }
       
    }
    
    public void tick() {
        
        posX += speedX;
        posY += speedY;

        
        if (posX + WIDTH > Game.WIDTH) {
          speedX *= -1;
        }
        if (posY + HEIGHT > Game.HEIGHT) {
          speedY *= -1;
        }
         if (posX <0) {
          speedX *= -1;
        }
        if (posY < 0) {
          speedY *= -1;
        }
               
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }
    
    private void increaseSpeed() {
        speedX *= 2;
        speedY *= 2;
    }
    
    
    private void decreaseSpeed() {
        speedX /= 2;
        speedY /= 2;
    }
    
    public void addHitListener(HitListener l) {
        eventListeners.add(HitListener.class, l);
    }
    
}
