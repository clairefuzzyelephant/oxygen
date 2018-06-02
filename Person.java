import java.awt.*;
import java.awt.event.*;
import javax.swing.ImageIcon;

public class Person {

    private int dx, dy;
    private int x = 20, y = 30;
    private int w, h;
    private Image image;
    private boolean[] pressedChars = new boolean[5];

    public Person() {

        loadImage();
    }

    private void loadImage() {

        ImageIcon ii = new ImageIcon("/Users/and1zhao/Downloads/person.png");
        image = ii.getImage();

        w = image.getWidth(null);
        h = image.getHeight(null);
    }

    public void move() {
        x += dx;
        y += dy;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return w;
    }

    public int getHeight() {
        return h;
    }

    public Image getImage() {
        return image;
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        //pressedChars.add(e.getKeyChar());

        if (key == KeyEvent.VK_SPACE){
            if (pressedChars[1] == true)
                dx = -2;
            if (pressedChars[2] == true)
                dx = 2;
            if (pressedChars[3] == true)
                dy = -2;
            if (pressedChars[4] == true)
                dy = 2;

            pressedChars[0] = true;
        }

        if (key == KeyEvent.VK_LEFT){
            if(pressedChars[0] == true)
                dx = -2;
            else
                dx = -1;
            pressedChars[1] = true;
        }

        if (key == KeyEvent.VK_RIGHT){
            if(pressedChars[0] == true)
                dx = 2;
            else
                dx = 1;
            pressedChars[2] = true;
        }

        if (key == KeyEvent.VK_UP){
            if(pressedChars[0] == true)
                dy = -2;
            else
            dy = -1;
            pressedChars[3] = true;
        }

        if (key == KeyEvent.VK_DOWN){
            if(pressedChars[0] == true)
                dy = 2;
            else
            dy = 1;
            pressedChars[4] = true;
        }

    }

    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();
        //pressedChars.remove(e.getKeyChar());

        if (key == KeyEvent.VK_SPACE) {

            if (pressedChars[1] == true)
                dx = -1;
            if (pressedChars[2] == true)
                dx = 1;
            if (pressedChars[3] == true)
                dy = -1;
            if (pressedChars[4] == true)
                dy = 1;

            pressedChars[0] = false;

        }

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
            pressedChars[1] = false;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
            pressedChars[2] = false;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
            pressedChars[3] = false;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
            pressedChars[4] = false;
        }
    }
}
