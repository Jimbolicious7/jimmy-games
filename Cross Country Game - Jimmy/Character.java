import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Character {
    
    double distance = 0;
    double speed;
    int positionY;
    int s = 50;
    int counterInterval = 1, spriteX = s * 2, spriteY = 0;
    int counterRun = 0;
    int counterDance = 0;
    BufferedImage image, sprite;

    public Character(String imagePath, double speed){
        //Sets the speed of the character
        this.speed = speed;

        //Sets the default Y position for the character
        positionY = 200;

        //Sets the image for the character
        try{
            image = ImageIO.read(new File(imagePath));
        } catch(IOException e) {
            e.printStackTrace();
        }

        sprite = image.getSubimage(spriteX, spriteY, s, 54);
    }

    public void render(Graphics g){
        //Draws the sprite
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(sprite, (int)(50 + distance), positionY, null);
    }

    public void update(){

    }

    public void reset(){
        distance = 0;
        spriteX = s * 2;
        sprite = image.getSubimage(spriteX, spriteY, s - 8, 54);
    }

    //Moves the character one increment of its speed
    //Also animates running
    public void run(){
        distance += speed/1.2;
        counterRun++;
        if(counterRun >= counterInterval){
            counterRun = 0;
            spriteX += s;
            if(spriteX > s * 3){
                spriteX = s * 2;
                sprite = image.getSubimage(spriteX - 3, spriteY, s, 54);
            } else {
                sprite = image.getSubimage(spriteX - 3, spriteY, s - 8, 54);
            }
        }
    }

    //Victory animation
    public void dance(){
        counterDance++;
        if(counterDance >= counterInterval){
            counterDance = 0;
            spriteX += s;
            if(spriteX > s)
                spriteX = 0;
            sprite = image.getSubimage(spriteX, spriteY, s, 54);
        }
    }

    public double getSpeed(){
        return speed;
    }
}
