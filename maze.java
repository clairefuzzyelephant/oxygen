import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;


public class maze extends JPanel{

  private Image livetree;

  public maze(){

    initMaze();

  }


  private void initMaze(){


      loadImage();

      int width = livetree.getWidth(this);
      int height = livetree.getHeight(this);
      setPreferredSize(new Dimension(width, height));
    }

    private void loadImage(){
      ImageIcon ii = new ImageIcon("greentree.png");
      livetree = ii.getImage();
    }

    @Override
    public void paintComponent(Graphics g){
      g.drawImage(livetree, 0, 0, null);
    }

}
