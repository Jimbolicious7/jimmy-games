import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Menu{

    //Creates a Color object for all 3 menu options
    Color[] colors = new Color[3];

    //Creates an int to keep track of which option is selected
    int option;

    public void set(){
        //Sets the starting colors of all menu options
        colors[0] = Color.YELLOW;
        colors[1] = Color.GRAY;
        colors[2] = colors[1];

        //Sets the currently selected option to the first option
        option = 0;
    }

    public void render(Graphics g){
        Graphics2D g2 = (Graphics2D) g;

        //Puts the word "Paused" on the screen in a specified font
        g2.setColor(Color.WHITE);
        g2.setFont(new Font("Serif", 0, 42));
        g2.drawString("Paused", 400, 150);

        //Puts the 3 menu options on the screen in a specified font
        g2.setFont(new Font("Serif", 0, 24));
        g2.setColor(colors[0]);
        g2.drawString("Resume", 180, 300);
        g2.setColor(colors[1]);
        g2.drawString("Restart", 180, 400);
        g2.setColor(colors[2]);
        g2.drawString("Quit", 180, 500);

        //Creates a message which says to use E to select and option
        g2.setColor(Color.GRAY);
        g2.drawString("Press E to select", 240, 600);
    }

    public void arrowUp(){
        //Moves the odd-colored option up
        Color temp = colors[0];
        for(int i = 0; i < colors.length - 1; i++)
            colors[i] = colors[i + 1];
        colors[colors.length - 1] = temp;

        option--;
        if(option < 0)
            option = colors.length - 1;
    }

    public void arrowDown(){
        //Moves the odd-colored option down
        Color temp = colors[colors.length - 1];
        for(int i = colors.length - 1; i > 0; i--)
            colors[i] = colors[i - 1];
        colors[0] = temp;

        option++;
        if(option > colors.length - 1)
            option = 0;
    }

    public int getOption(){
        return option;
    }
}