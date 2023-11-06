import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import javax.imageio.ImageIO;

public class Level{
    
    int progress = 0;
    double danceTimer = 0;
    double progressInterval;
    double enemyProgress = 0;
    double enemyProgressInterval = 0;
    boolean paused = false, win = false, lose = false;
    private BufferedImage backgroundImage;
    private Menu menu = new Menu();
    private LinkedList<Character> characters = new LinkedList<Character>();

    public Level(){
        characters.add(new Character("Sprites/Shawn.png", 10));
    }

    public void update(){

        //Makes the enemy's meter increase by the amount set by enemyProgressInterval
        enemyProgress += enemyProgressInterval;

        //Updates the characters
        for(int i = 0; i < characters.size(); i++)
            characters.get(i).update();

        //When the enemy progress bar fills up, the game pauses and a loser message is produced
        if(enemyProgress >= 880 && lose == false){
            enemyProgress = 880;
            lose = true;
        }

        //When the player's progress bar fills up, the game pauses and a win message is produced
        if(progress >= 880 && win == false){
            progress = 880;
            win = true;
        }
    }

    public void render(Graphics g){
        
        Graphics2D g2 = (Graphics2D) g;

        //Draws the background
        g2.drawImage(backgroundImage, 0, 0, null);

        //Draws the characters
        for(int i = 0; i < characters.size(); i++)
            characters.get(i).render(g);

        //Progress bar
        g2.setStroke(new BasicStroke(4));
        g2.setColor(Color.WHITE);
        g2.drawRect(60, 720, 880, 10);
        g2.setColor(Color.GREEN);
        g2.fillRect(60, 720, progress, 10);

        //Enemy progress bar
        g2.setColor(Color.WHITE);
        g2.drawRect(60, 740, 880, 10);
        g2.setColor(Color.RED);
        g2.fillRect(60, 740, (int)enemyProgress, 10);

        //Pause screen
        if(paused){
            menu.render(g);
        }

        //Sets the font (only applies if victory/failure message cannot be found)
        g2.setFont(new Font("Serif", 0, 48));

        //If the player won, a victory message is displayed and the player dances
        if(win && !paused){
            //If the victory message cannot be found, the same message is written with drawString
            try{
                g2.drawImage(ImageIO.read(new File("ImageComponents/Victory.png")), 300, 50, null);
            } catch(IOException e) {
                e.printStackTrace();
                g2.drawString("Victory!", 360, 150);
                g2.drawString("Press R to restart", 320, 200);
            }
            if(danceTimer >= 150){
                characters.get(0).dance();
                danceTimer = 0;
            }
            danceTimer++;
        }

        //If the player loses, a failure message is displayed and the enemies dance
        if(lose && !paused){
            //If the failure message cannot be found, the same message is written with drawString
            try{
                g2.drawImage(ImageIO.read(new File("ImageComponents/Failure.png")), 300, 50, null);
            } catch(IOException e) {
                e.printStackTrace();
                g2.drawString("Failure!", 360, 150);
                g2.drawString("Press R to restart", 320, 200);
            }
            if(danceTimer >= 10){
                for(int e = 1; e < characters.size(); e++)
                    characters.get(e).dance();
                danceTimer = 0;
            }
            danceTimer++;
        }
    }

    //Returns true if the game is in motion (not paused, and not in a win or lose state)
    public boolean isRunning(){
        if(!paused && !win && !lose)
            return true;
        else
            return false;
    }

    //Sets the background image for the level
    public void setBackground(String imagePath){
        try{
            backgroundImage = ImageIO.read(new File(imagePath));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    //Returns true if the level has been won
    public boolean isWon(){
        return win;
    }

    //Aligns progressInterval and enemyProgressInterval with the speeds of the player and the main enemy
    public void calibrateSpeed(){
        progressInterval = characters.get(0).getSpeed();
        enemyProgressInterval = characters.get(1).getSpeed();
    }

    //Makes the player's progress increase
    public void run(){
        if(isRunning()){
            progress += progressInterval;
            characters.get(0).run();
        }
    }

    //Adds a runner to the track
    public void addRunner(String imagePath, double speed, int positionY){
        characters.add(new Runner(imagePath, speed, 200 + (positionY * 100)));
    }

    //Sets the image and the speed for the player character
    public void setPlayer(String imagePath, double speed){
        characters.remove(0);
        characters.add(0, new Character(imagePath, speed));
    }

    //Moves the menu selection highlight down
    public void arrowDown(){
        if(paused)
            menu.arrowDown();
    }

    //Moves the menu selection highlight up
    public void arrowUp(){
        if(paused)
            menu.arrowUp();
    }

    //Pauses the level
    public void pause(){
        if(paused)
            paused = false;
        else{
            menu.set();
            paused = true;
        }
    }

    //Resets the level
    public void reset(){
        progress = 0;
        enemyProgress = 0;
        paused = false;
        win = false;
        lose = false;
        for(int i = 0; i < characters.size(); i++)
            characters.get(i).reset();
    }

    //Selects a menu option
    public int select(){
        int o = menu.getOption();
        if(paused){
            if(o == 0)
                paused = false;
            if(o == 1){
                reset();
                paused = false;
            }
        }
        return o;
    }

}
