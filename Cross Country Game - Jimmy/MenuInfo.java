import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.File;
import javax.imageio.ImageIO;

public class MenuInfo {
    
    //Represents the X and Y coordinates to be the origin for the info drawing
    int originX, originY;

    //Represents the X and Y coordinates for the level title
    int titleX, titleY;

    public MenuInfo(int originX, int originY){
        this.originX = originX;
        this.originY = originY;
        titleX = originX + 50;
        titleY = originY + 10;
    }

    public void hover(int option, Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        //Sets the color of the font
        g2.setColor(Color.RED);

        //Depending on the option selected in the menu, a different thing will be drawn
        if(option == 0){
            g2.drawString("Practice with Shawn", titleX, titleY);
        }

        if(option == 1){
            g2.drawString("Practice with Sohan, Musa, and the Shins", titleX, titleY);
        }

        if(option == 2){
            g2.drawString("Practice with Jake, Aidan, and James", titleX, titleY);
        }

        if(option == 3){
            g2.drawString("Hills workout", titleX, titleY);
        }

        if(option == 4){
            g2.drawString("Race: Staff, Hudson, and Milan", titleX, titleY);
        }

        if(option == 5){
            g2.drawString("Race: Robert, Sean, and Jimmy", titleX, titleY);
        }

        if(option == 6){
            g2.drawString("Race: Matt Bischoff, Ben Cummings, and AJ Andiorio", titleX, titleY);
        }
        
        if(option == 7){
            g2.drawString("Race: Renee and Ollie", titleX, titleY);
        }

        if(option == 8){
            g2.drawString("Race: Kenny (and Neil)", titleX, titleY);
        }

        if(option == 9){
            g2.drawString("Race: Shawn", titleX, titleY);
        }

        if(option == 10){
            g2.drawString("Race: Ryan", titleX, titleY);
        }
    }
}
