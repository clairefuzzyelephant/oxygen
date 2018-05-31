import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;

public class mazegen extends JFrame{

  public mazegen(){
    setSize(850,850);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }

  void lines(Graphics m){
    Graphics2D g2d = (Graphics2D)m;

    outer: for (int i = 30; i < 770; i += 30){
      for (int j = 30; j < 770; j += 30){
        g2d.fillRect(i, j, 20, 20);
        //create array of coordinates
      }
    }

    outer: for (int i = 30; i < 780; i += 30){
      for (int j = 50; j < 780; j += 30){
        double q  = (double)Math.random();
        //System.out.println(q);
        if (q < 0.4){
          g2d.fillRect(i, j, 20, 10);
        }
        else if (q < 0.5){
          g2d.fillRect(i + 20, j - 20, 20, 10);
          j += 30;
        }
        else if (q < 0.9){
          g2d.fillRect(i + 30, j, 20, 10);
          i += 30;
        }
        else{
          g2d.fillRect(i + 30, j + 30, 20, 10);
          i += 30;
          j += 30;
        }
      }
    }

    outer: for (int i = 50; i < 780; i += 30){
      for (int j = 30; j < 780; j += 30){
        double q  = (double)Math.random();
        //System.out.println(q);
        if (q < 0.25){
          g2d.fillRect(i, j, 10, 20);
        }
        else if (q < 0.5){
          g2d.fillRect(i, j+30, 10, 20);
          j += 30;
        }
        else if (q < 0.75){
          g2d.fillRect(i + 30, j, 10, 20);
          i += 30;
        }
        else{
          g2d.fillRect(i + 30, j + 30, 10, 20);
          i += 30;
          j += 30;
        }
      }
    }


    //g2d.drawLine(120, 200, 120, 200);
    //g2d.drawLine(120, 50, 120, 200);

    //g2d.draw(new Line2D.Double(59.2d, 99.8d, 419.1d, 99.8d));
    //g2d.draw(new Line2D.Float(21.50f, 132.50f, 459.50f, 132.50f));
  }

  public void paint(Graphics m){
    super.paint(m);
    lines(m);
  }

  public static void main(String[] args){

    SwingUtilities.invokeLater(new Runnable(){
      @Override
      public void run(){
        new mazegen().setVisible(true);
      }
    });
  }
}
