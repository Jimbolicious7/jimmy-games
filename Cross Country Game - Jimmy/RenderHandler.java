import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

public class RenderHandler {

    private int currentLevelNumber = 0, levelsCompleted = 0;  //Created two integers to keep track of the current level and the number of completed levels
    private BufferedImage view;
    private LinkedList<Level> levels = new LinkedList<Level>(); //levels deals with updating the current level
    private Level currentLevel;

    public RenderHandler(int width, int height){
        //Create a BufferedImage that will be the background
        view = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //LevelCreates designs the levels and gives them to RenderHandler
        LevelCreator levelCreator = new LevelCreator();
        for(int i = 0; i < levelCreator.size(); i++){
            levels.add(levelCreator.getLevel(i));
        }

        //Sets the current level to the title screen
        currentLevel = levels.get(0);
    }

    public void render(Graphics g){
        
        //Draws the background
        g.drawImage(view, 0, 0, view.getWidth(), view.getHeight(), null);

        //Renders the current level
            currentLevel.render(g);
    }

    public void update(){
        if(currentLevel.isRunning())
            currentLevel.update();
        if(levelsCompleted < currentLevelNumber)
            if(currentLevel.isWon())
                ((TitleScreen)levels.get(0)).unlockLevel(currentLevelNumber);
    }

    public void run(){
        currentLevel.run();
    }

    public void arrowUp(){
        currentLevel.arrowUp();
    }

    public void arrowDown(){
        currentLevel.arrowDown();
    }

    public void pause(){
        currentLevel.pause();
    }

    public void levelReset(){
        currentLevel.reset();
    }

    public void select(){
        if(currentLevel == levels.get(0)){
            if(((TitleScreen)currentLevel).getScreen() == 1){
                currentLevelNumber = ((TitleScreen)currentLevel).getOption() + 1;
                currentLevel = levels.get(((TitleScreen)currentLevel).getOption() + 1);
                currentLevel.reset();
            } else {
                currentLevel.select();
            }
        } else {
            if(currentLevel.isWon()){
                currentLevel = levels.get(0);
            }
            else if(currentLevel.select() == 2){
                levelReset();
                currentLevel = levels.get(0);
                currentLevelNumber = 0;
            } else
                currentLevel.select();
        }
    }
}
