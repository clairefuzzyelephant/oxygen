import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;

public class mazegen extends JFrame{

  private cell[][] allcells;
  private int counteri;
  private int counterj;

  public mazegen(){
    setSize(850,850);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    counteri = 0;
    counterj = 0;

    allcells = new cell[26][26];
    for (int i = 40; i < 800 && counteri < 26; i += 30){
      counterj = 0;
      for (int j = 40; j < 800 && counterj < 26; j += 30){
        allcells[counteri][counterj] = new cell(i,j);
        counterj++;
      }
      counteri++;
    }

    allcells[0][0].visited();

  }



  void lines(Graphics m){
    Graphics2D g2d = (Graphics2D)m;

    outer: for (int i = 30; i < 800; i += 30){
      for (int j = 30; j < 800; j += 30){
        g2d.fillRect(i, j+10, 10, 20);
        g2d.fillRect(i + 10, j, 20, 10);
        g2d.fillRect(i, j, 10, 10);

        //g2d.fillRect(40, 40, 20, 20);
        //create array of coordinates
      }
    }

    g2d.clearRect(30, 40, 10, 20);


    gen(new cell(), g2d);



    //g2d.fillRect(40, 40, 20, 20);

}

    //gen(current);


  void gen(cell now, Graphics thing){

      int x = now.getY();
      int y = now.getX();

      int yind = now.getY()/30-1;

      int xind = now.getX()/30-1;
      System.out.println("xind = " + xind);
      System.out.println("yind = " + yind);


      ArrayList<cell> unvisited = new ArrayList<cell>();
      if (yind > 0){
        if (allcells[xind][yind-1].getMarkStatus() == false){
          unvisited.add(allcells[xind][yind-1]);
          allcells[xind][yind-1].setDirection("left");
        }
      }
      if (xind > 0){
        if (allcells[xind-1][yind].getMarkStatus() == false){
          unvisited.add(allcells[xind-1][yind]);
          allcells[xind-1][yind].setDirection("top");
        }
      }
      if (yind < 26){
        if (allcells[xind][yind+1].getMarkStatus() == false){
          unvisited.add(allcells[xind][yind+1]);
          allcells[xind][yind+1].setDirection("right");
        }
      }
      if (xind < 26){
        if (allcells[xind+1][yind].getMarkStatus()== false){
          unvisited.add(allcells[xind+1][yind]);
          allcells[xind+1][yind].setDirection("bottom");
        }
      }

      System.out.println(unvisited);

      int q = (int)(Math.random() * unvisited.size());
      System.out.println(q);

      unvisited.get(q).visited(); //marked as visited
      //unvisited.get(q).clearWall(); //clears wall

      if (unvisited.get(q).getDirection() == "top"){
        thing.clearRect(x, y-10, 20, 10);
        System.out.println("top cleared");
      }
      else if (unvisited.get(q).getDirection() == "right"){
        thing.clearRect(x+20, y, 10, 20);
        System.out.println("right cleared");
      }
      else if (unvisited.get(q).getDirection() == "bottom"){
        thing.clearRect(x, y+20, 20, 10);
        System.out.println("bottom cleared");
      }
      else{
        thing.clearRect(x-10, y, 10, 20);
        System.out.println("left cleared");
      }
      System.out.println(unvisited.get(q));
      gen(unvisited.get(q), thing);


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
