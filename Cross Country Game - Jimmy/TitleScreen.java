import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class TitleScreen extends Level{
    
    //Creates an integer to keep track of the number of completed levels
    int levelsCompleted = 0;

    //Creates an integer that will keep track of the screen that will be displayed
    int screen = 0;

    //Creates an integer that will keep track of the selected option of the level select screen
    int option = 0;

    //Creates a LinkedList to store the text images for each level name
    ArrayList<BufferedImage> levels = new ArrayList<BufferedImage>();

    //Creates a BufferedImage to store the current level roster
    BufferedImage roster;

    //Creates the image for the title screen
    BufferedImage titleImage, levelSelectImage;

    public TitleScreen(){
        //Assigns a text image and roster image to each level name
        try{
        levels.add(ImageIO.read(new File("LevelSelect/Select0.png")));
        roster = ImageIO.read(new File("LevelSelect/Roster0.png"));
        //for(int i = 1; i < levels.size(); i++)
        //    levels.add(i, ImageIO.read(new File("LevelSelect/Neutral" + i + ".png")));
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        //Puts the title screen image into titleImage
        try{
            titleImage = ImageIO.read(new File("TitleImage.png"));
            levelSelectImage = ImageIO.read(new File("LevelSelectImage.png"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        //Creates the title screen message
        if(screen == 0){
          g2.drawImage(titleImage, 0, 0, null);
        } else {
            //Puts the phrase "Level Select" on the screen in a specified font
            g2.setColor(Color.GRAY);
            g2.setFont(new Font("Serif", 0, 42));
            g2.drawString("Level Select", 400, 150);
            g2.drawImage(levelSelectImage, 0, 0, null);

            //Puts the menu options and selected roster image on the screen in the form of the assigned text images
            g2.setFont(new Font("Serif", 0, 20));
            for(int i = 0; i < levels.size() && i < 11; i++){
                g2.drawImage(levels.get(i), 120, 150 + 50 * i, null);
            }
            if(levelsCompleted > 10){
                g2.drawImage(levels.get(11), 350, 150, null);
                g2.drawImage(levels.get(12), 350, 200, null);
            }
            g2.drawImage(roster, 400, 350, null);

            //Creates a message which says to use E to select and option
            g2.setColor(Color.GRAY);
            g2.drawString("Press E to select", 440, 600);
        }

    }

    public int select(){
        if(screen == 0)
            screen++;
        return screen;
    }

    public void arrowUp(){
        //Moves the odd-colored option up
        option--;
        if(option < 0)
            option = levels.size() - 1;
        try{
            if(option < levels.size() - 1)
                levels.set(option + 1, ImageIO.read(new File("LevelSelect/Neutral" + (option + 1) + ".png")));
            else
                levels.set(0, ImageIO.read(new File("LevelSelect/Neutral0.png")));
            levels.set(option, ImageIO.read(new File("LevelSelect/Select" + option + ".png")));
            roster = ImageIO.read(new File("LevelSelect/Roster" + option + ".png"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void arrowDown(){
        //Moves the odd-colored option down
        option++;
        if(option > levels.size() - 1)
            option = 0;
        try{
            if(option > 0)
              levels.set(option - 1, ImageIO.read(new File("LevelSelect/Neutral" + (option - 1) + ".png")));
            else
                levels.set(levels.size() - 1, ImageIO.read(new File("LevelSelect/Neutral12.png")));
            levels.set(option, ImageIO.read(new File("LevelSelect/Select" + option + ".png")));
            roster = ImageIO.read(new File("LevelSelect/Roster" + option + ".png"));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public int getScreen(){
        return screen;
    }

    public int getOption(){
        return option;
    }

    public void unlockLevel(int i){
        if(levels.size() == i && i < 13){
            try{
                levels.add(ImageIO.read(new File("LevelSelect/Neutral" + i + ".png")));
                if(i == 11)
                    levels.add(ImageIO.read(new File("LevelSelect/Neutral12.png")));
            }catch(Exception e){
                e.printStackTrace();
            }
            levelsCompleted++;
        }
    }

    public void run(){
        if(screen == 1)
            for(int i = 0; i < 13; i++)
                unlockLevel(i);
    }
}
