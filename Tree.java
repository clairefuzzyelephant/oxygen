//import javax.swing.Timer;
import java.util.Timer;

public class Tree extends Sprite {

    private final int BOARD_WIDTH = 400;
    private final int BOARD_HEIGHT = 300;
    private final int TREE_SPEED = 1;
    protected int dx, dy;
    private boolean treeAlive = true;
    private boolean justDead = false;
    private final double direction = Math.random();
    private double nDirection = Math.random();

    public Tree(int x, int y) {
        super(x, y);
        initTree();
    }

    private void initTree() {
        loadImage("/Users/and1zhao/Downloads/fireball.jpg");
        getImageDimensions();
    }

    public int getdx(){
      return dx;
    }

    public int getdy(){
      return dy;
    }

    public boolean getDeathStatus(){
      return justDead;
    }

    public void justDied(){
      justDead = true;
    }

    public void diedForAWhile(){
      justDead = false;
    }

    public void move() {

        x += dx;
        y += dy;

        //directions for tree, will have to fix when integrating
        if (this.isAlive()){
            if(direction < 0.25)
                dx = -2;
            else if (direction > 0.25 && direction < 0.5)
                dx = 2;
            else if (direction > 0.5 && direction < 0.75)
                dy = -2;
            else if (direction > 0.75)
                dy = 2;
        }

        //below is my failed attempt to fix movement
        /*if (x <= 0){
            if (dx != 0){
                dx = 0;
                if(nDirection < 0.5){
                  dy = -1;
                } else {
                  dy = 1;
                }
                /*if (direction < 0.125){
                    dy = -1;
                }
                if (direction > 0.125){
                    dy = 1;
                }
            }
        }
        if (x >= BOARD_WIDTH - width){
            if (dx != 0){
                dx = 0;
                if(nDirection < 0.5){
                  dy = -1;
                } else {
                  dy = 1;
                }
                /*if (direction < 0.375){
                    dy = -1;
                }
                if (direction > 0.375){
                    dy = 1;
                }
            }
        }
        if (y <= 0){
            if (dy != 0){
                dy = 0;
                if(nDirection < 0.5){
                  dx = -1;
                } else {
                  dx = 1;
                }
                if (direction < 0.625){
                    dx = -1;
                }
                if (direction > 0.625){
                    dx = 1;
                }
            }
        }
        if (y >= BOARD_HEIGHT - 2 * height){
            if (dy != 0){
                dy = 0;
                if(nDirection < 0.5){
                  dx = -1;
                } else {
                  dx = 1;
                }
                if (direction < 0.875){
                    dx = -1;
                }
                if (direction > 0.875){
                    dx = 1;
                }
            }
        }*/

        else{
          if(!justDead)
            dx = 3;
        }

    }


    public boolean isAlive(){
        return treeAlive;
    }

    public void killTree(){
        treeAlive = false;
        dx = 0;
        dy = 0;
        /*new java.util.Timer().schedule(
          new java.util.TimerTask() {
              @Override
              public void run() {
                  dx = 1;
              }
          },
          5000
        );*/
    }

}
