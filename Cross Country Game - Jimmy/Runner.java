
public class Runner extends Character{

    public Runner(String imagePath, double speed, int positionY){
        super(imagePath, speed);
        this.positionY = positionY;
        counterInterval = 10;
        spriteY = 0;
        s = 50;
    }

    public void update(){
        run();
    }
}
