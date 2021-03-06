import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import java.util.List;

public class Player extends Sprite{

    private double oxygenLevel = 1000; //these are arbitrary and can be changed
    private double doxy = 0.25;
    private int dx, dy;
    private Image image;
    private boolean[] pressedChars = new boolean[5];


    public Player(int x, int y) {
        super(x,y);
        initPlayer();
    }

    private void initPlayer() {
        loadImage("/Users/and1zhao/Downloads/person.png");
        getImageDimensions();
    }

    public void move() {
        x += dx;
        y += dy;
        oxygenLevel -= doxy;
    }

    public double getOxygen(){
        return oxygenLevel;
    }

    public void increaseOxygen(){
        if(oxygenLevel + 100 < 1000)
            oxygenLevel += 10;
        else
            oxygenLevel = 1000;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if((this.x < 35 || this.x > 745 || this.y < 35 || this.y > 745)){
            x = 40;
            y = 40;
        }

        if (key == KeyEvent.VK_SPACE){
            if (pressedChars[1] == true){
                if(this.x >= 40){
                    dx = -2;
                }
            }
            if (pressedChars[2] == true){
                if(this.x <= 730){
                    dx = 2;
                }
            }
            if (pressedChars[3] == true){
                if(this.y >= 40){
                    dy = -2;
                }
            }
            if (pressedChars[4] == true){
                if(this.y <= 730){
                    dy = 2;
                }
            }

            pressedChars[0] = true;
        }

        if (key == KeyEvent.VK_LEFT){
            if(pressedChars[0] == true){
                if(this.x >= 40){
                    dx = -2;
                }
            }
            else{
                if(this.x >= 40){
                    dx = -1;
                }
            }
            pressedChars[1] = true;
        }

        if (key == KeyEvent.VK_RIGHT){
            if(pressedChars[0] == true){
                if(this.x <= 730){
                    dx = 2;
                }
            }
            else{
                if(this.x <= 730){
                    dx = 1;
                }
            }
            pressedChars[2] = true;
        }

        if (key == KeyEvent.VK_UP){
            if(pressedChars[0] == true){
                if(this.y >= 40){
                    dy = -2;
                }
            }
            else{
                if(this.y >= 40){
                    dy = -1;
                }
            }
            pressedChars[3] = true;
        }

        if (key == KeyEvent.VK_DOWN){
            if(pressedChars[0] == true){
                if(this.y <= 730){
                    dy = 2;
                }
            }
            else{
                if(this.y <= 730){
                    dy = 1;
                }
            }
            pressedChars[4] = true;
        }

    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        //pressedChars.remove(e.getKeyChar());

        if (key == KeyEvent.VK_SPACE) {

            if (pressedChars[1] == true){
                dx = -1;
                doxy = 0.25;
            }
            if (pressedChars[2] == true){
                dx = 1;
                doxy = 0.25;
            }
            if (pressedChars[3] == true){
                dy = -1;
                doxy = 0.25;
            }
            if (pressedChars[4] == true){
                dy = 1;
                doxy = 0.25;
            }

            pressedChars[0] = false;

        }

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            doxy = 0.1;
            pressedChars[1] = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            doxy = 0.1;
            pressedChars[2] = false;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
            doxy = 0.1;
            pressedChars[3] = false;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
            doxy = 0.1;
            pressedChars[4] = false;
        }
    }

}
