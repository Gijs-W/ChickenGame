package chickengame;

import chickengame.util.MouseCoordinate;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Ricardo
 */
public class Chicken implements Observer {
    
    private int speedX, speedY;
    
    private int posX, posY;
    
    //Image Bufferedreader
    
    public Chicken(int positionX, int positionY) {
        this.posX = positionX;
        this.posY = positionY;
    }
    
    public void Update() {
        posX += speedX;
        posY += speedY;
    }

    @Override
    public void update(Observable o, Object o1) {
        MouseCoordinate coordinate = (MouseCoordinate) o1;
        
         if (coordinate.getXpos() >= posX && coordinate.getXpos() <= posX+60
                  && coordinate.getYpos() > posY && coordinate.getYpos() <= posY+60)
          {
              //hit
              System.out.println("\nHIT BITCH!");
          }
    }
}
