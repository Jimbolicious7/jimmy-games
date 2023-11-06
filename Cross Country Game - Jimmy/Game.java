import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.lang.Thread;
import java.lang.Runnable;
import javax.swing.JFrame;

public class Game extends JFrame implements KeyListener, Runnable{

    final int FPS = 60;
    int ovalX = 200;
    private Canvas canvas = new Canvas();
    private RenderHandler renderer;
    
    public Game(){
        //Set close operation of the JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Set location and size of the JFrame
        setBounds(0, 0, 1000, 800);

        //Center the JFrame on the screen
        setLocationRelativeTo(null);

        //Add canvas to the JFrame
       add(canvas);

        //Add the KeyListener to the JFrame
        canvas.addKeyListener(this);

        //Make the JFrame visible
        setVisible(true);

        //Create object for Buffer Strategy
        canvas.createBufferStrategy(3);

        //Creates the RenderHandler
        renderer = new RenderHandler(getWidth(), getHeight());
    }

    public void update(){
        renderer.update();
    }

    public void render(){

        //Create the graphics component
        BufferStrategy bufferStrategy = canvas.getBufferStrategy();
        Graphics g = bufferStrategy.getDrawGraphics();
        Graphics2D g2 = (Graphics2D) g;

        //Tells the RenderHandler to render everything
        renderer.render(g);

        g.dispose();
        g2.dispose();
        bufferStrategy.show();
    }

    public void run(){

        long lastTime = System.nanoTime();  //Get time in nanoseconds
        double converter = 1000000000.0 / FPS;  //Conversion rate between nanoseconds and seconds for 74 frames per second
        double deltaSeconds = 0;

        while(true){

            long now = System.nanoTime();  //Gets time at each tick in nanoseconds

            deltaSeconds += (now - lastTime) / converter;  //Converts each detlaSecond into 1/FPS of a second

            //While loop ticks every 1 second
            while(deltaSeconds >= 1){
                renderer.update();
                deltaSeconds = 0;
            }

            //Runs the render method every time the program ticks
            render();

            lastTime = now;
        }

    }

    public void keyPressed(KeyEvent e){
       int i = e.getKeyCode();
       
       //The UP arrow changes the selected menu option to the one above
        if(i == 38){
            renderer.arrowUp();
       }

       //The DOWN arrow changes the selected menu option to the one below
       if(i == 40){
            renderer.arrowDown();
       }

        //Allows the Enter key to pause the game
        if(i == 10){
            renderer.pause();
        }

        //Pressing R restarts the round
        if(i == 82)
            renderer.levelReset();

        //Pressing E selects an option on the menu
        if(i == 69){
            renderer.select();
        }

        //Pressing Q quits the game
        if(i == 81){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    public void keyReleased(KeyEvent e){
        int i = e.getKeyCode();

        //The spacebar increases the player's progress
        if(i == 32){
            renderer.run();
        }
    }

    public void keyTyped(KeyEvent e){
 
    }

    public static void main(String[] args){
        Game game = new Game();
        Thread gameThread = new Thread(game);
        gameThread.start();
    }
}