package chickengame;

/**
 *
 * @author Ricardo
 */
public class Chicken {
    
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
}
