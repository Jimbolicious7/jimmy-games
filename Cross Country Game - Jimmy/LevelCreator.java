import java.util.LinkedList;

public class LevelCreator extends LinkedList<Level>{

    public LevelCreator(){
        //Initializes the title screen
        add(new TitleScreen());

        //Initializes the tutorial
        Level level = new Level();
        level.setBackground("Sprites/Background1.png");
        level.setPlayer("Sprites/MC.png", 20);
        level.addRunner("Sprites/Shawn.png", 0.3, 1);
        level.calibrateSpeed();
        add(level);

        //Initializes level 1
        level = new Level();
        level.setBackground("Sprites/Background1.png");
        level.setPlayer("Sprites/MC.png", 15);
        level.addRunner("Sprites/Sohan.png", 0.4, 1);
        level.calibrateSpeed();
        level.addRunner("Sprites/Shin1.png", 0.1, 2);
        level.addRunner("Sprites/Musa.png", 0.3, 3);
        level.addRunner("Sprites/Shin2.png", 0.1, 4);
        add(level);

        //Initializes level 2
        level = new Level();
        level.setBackground("Sprites/Background1.png");
        level.setPlayer("Sprites/MC.png", 15);
        level.addRunner("Sprites/Jake.png", 0.4, 1);
        level.calibrateSpeed();
        level.addRunner("Sprites/James.png", 0.5, 2);
        level.addRunner("Sprites/Aidan.png", 0.4, 3);
        add(level);

        //Initializes level 3
        level = new Level();
        level.setBackground("Sprites/Background1.png");
        level.setPlayer("Sprites/MC.png", 10);
        level.addRunner("Sprites/Shin2.png", 0.3, 1);
        level.calibrateSpeed();
        level.addRunner("Sprites/James.png", 0.5, 2);
        add(level);

        //Initializes level 4
        level = new Level();
        level.setBackground("Sprites/Background1.png");
        level.setPlayer("Sprites/MC.png", 10);
        level.addRunner("Sprites/Staff.png", 0.7, 1);
        level.calibrateSpeed();
        level.addRunner("Sprites/Hudson.png", 0.5, 2);
        level.addRunner("Sprites/Milan.png", 0.6, 3);
        add(level);

        //Initializes level 5
        level = new Level();
        level.setBackground("Sprites/Background1.png");
        level.setPlayer("Sprites/MC.png", 10);
        level.addRunner("Sprites/Robert.png", 0.7, 1);
        level.calibrateSpeed();
        level.addRunner("Sprites/Sean.png", 0.8, 2);
        level.addRunner("Sprites/Jimmy.png", 0.7, 3);
        add(level);

        //Initializes level 6
        level = new Level();
        level.setBackground("Sprites/Background1.png");
        level.setPlayer("Sprites/MC.png", 10);
        level.addRunner("Sprites/Ben.png", 0.9, 1);
        level.calibrateSpeed();
        level.addRunner("Sprites/MattB.png", 0.8, 2);
        level.addRunner("Sprites/AJ.png", 0.6, 3);
        add(level);

        //Initializes level 7
        level = new Level();
        level.setBackground("Sprites/Background1.png");
        level.setPlayer("Sprites/MC.png", 10);
        level.addRunner("Sprites/Renee.png", 1, 1);
        level.calibrateSpeed();
        level.addRunner("Sprites/Ollie.png", 1, 2);
        add(level);

        //Initializes level 8
        level = new Level();
        level.setBackground("Sprites/Background1.png");
        level.setPlayer("Sprites/MC.png", 10);
        level.addRunner("Sprites/Kenny.png", 1.1, 1);
        level.calibrateSpeed();
        level.addRunner("Sprites/Neil.png", 1, 2);
        add(level);

        //Initializes level 9
        level = new Level();
        level.setBackground("Sprites/Background1.png");
        level.setPlayer("Sprites/MC.png", 5);
        level.addRunner("Sprites/Shawn.png", .55, 1);
        level.calibrateSpeed();
        add(level);

        //Initializes level 10
        level = new Level();
        level.setBackground("Sprites/Background1.png");
        level.setPlayer("Sprites/MC.png", 5);
        level.addRunner("Sprites/Pena.png", .6, 1);
        level.calibrateSpeed();
        add(level);
       

        //Initializes Hidden 1
        level = new Level();
        level.setBackground("Sprites/Background1.png");
        level.setPlayer("Sprites/MC.png", 20);
        level.addRunner("Sprites/Schulman.png", 3, 1);
        level.calibrateSpeed();
        add(level);
    
        //Initializes Hidden 2
        level = new Level();
        level.setBackground("Sprites/Background1.png");
        level.setPlayer("Sprites/MC.png", 5);
        level.addRunner("Sprites/Shawn2.png", 0.7, 1);
        level.calibrateSpeed();
        level.addRunner("Sprites/Pena2.png", .7, 2);
        add(level);
    }

    //Returns the selected level
    public Level getLevel(int levelNumber){
        return get(levelNumber);
    }
}
