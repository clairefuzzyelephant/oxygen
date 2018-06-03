import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;

public class mazegen extends JFrame{

  private cell[][] allcells;
  private int counteri;
  private int counterj;
  public int dim = 800; //pixel dimension of the maze
  public int box = (dim-50)/30-1; //25
  private ArrayList<cell> unvisited = new ArrayList<cell>();

  public mazegen(){
    setSize(dim,dim);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    counteri = 0;
    counterj = 0;

    allcells = new cell[box][box]; //25
    for (int i = 40; (i <= (dim-20)) && counteri < box; i += 30){
      counterj = 0;
      for (int j = 40; (j <= (dim-20)) && counterj < box; j += 30){
        allcells[counteri][counterj] = new cell(i,j);
        counterj++;
      }
      counteri++;
    }

    System.out.println(counteri + " " + counterj);
    //System.exit(0);

    allcells[0][0].visited();
  }

  void lines(Graphics m){
    Graphics2D g2d = (Graphics2D)m;

    outer:
    for (int i = 30; i < dim-50; i += 30){
      for (int j = 30; j < dim-50; j += 30){
        g2d.fillRect(i, j+10, 10, 20);
        g2d.fillRect(i + 10, j, 20, 10);
        g2d.fillRect(i, j, 10, 10);
      }
    }

    gen(new cell(), g2d);
    falsepath(allcells[0][0], g2d);
    falsepath(allcells[0][0], g2d);
    falsepath(allcells[0][0], g2d);
    falsepath(allcells[0][0], g2d);
    falsepath(allcells[0][0], g2d);
    falsepath(allcells[0][0], g2d);
    falsepath(allcells[0][0], g2d);

    g2d.fillRect(30, dim-50, dim-70, 10);
    g2d.fillRect(dim-50, 30, 10, dim-100);
    g2d.clearRect(30, 40, 10, 20);

  }

  boolean gen(cell now, Graphics thing){

      int x = now.getY();
      int y = now.getX();

      System.out.println(x + " " + y);

      int yind = x/30-1;
      int xind = y/30-1;
      System.out.println("xind = " + xind);
      System.out.println("yind = " + yind);

      if (xind == box-1 && yind == box-1){
        System.out.println("SUCCESSSSSSS");
        return true;
      }

      //creating the unvisited list
      unvisited.clear();
      ArrayList<cell> unvisited = new ArrayList<cell>();
      if ((yind > 1) && allcells[xind][yind-1].getMarkStatus() == false){
          unvisited.add(allcells[xind][yind-1]);
          //System.out.println(allcells[xind][yind-1]);
          allcells[xind][yind-1].setDirection("left");
      }
      if ((xind > 1) && allcells[xind-1][yind].getMarkStatus() == false){
          unvisited.add(allcells[xind-1][yind]);
          //System.out.println(allcells[xind-1][yind]);
          allcells[xind-1][yind].setDirection("top");
      }
      //System.out.println("hibhob");
      //System.out.println(xind + " " + yind);
      if ((yind < box-1) && (allcells[xind][yind+1].getMarkStatus() == false)){
          unvisited.add(allcells[xind][yind+1]);
          //System.out.println(allcells[xind][yind+1]);
          allcells[xind][yind+1].setDirection("right");
      }
      if ((xind < box-1) && (allcells[xind+1][yind].getMarkStatus() == false)){
          unvisited.add(allcells[xind+1][yind]);
          //System.out.println(allcells[xind+1][yind]);
          allcells[xind+1][yind].setDirection("bottom");
      }
      //System.out.println(unvisited);


      int counter = unvisited.size();

      //this part clears the walls
      while (counter > 0){
        int q = (int)(Math.random() * unvisited.size()); //randomly chooses unvisited cell
        //System.out.println(q);

        unvisited.get(q).visited(); //marked as visited
        counter--;

        if (unvisited.get(q).getDirection() == "top"){
          thing.clearRect(x, y-10, 20, 10);
          //System.out.println("top cleared");
        }
        else if (unvisited.get(q).getDirection() == "right"){
          thing.clearRect(x+20, y, 10, 20);
          //System.out.println("right cleared");
        }
        else if (unvisited.get(q).getDirection() == "bottom"){
          thing.clearRect(x, y+20, 20, 10);
          //System.out.println("bottom cleared");
        }
        else if (unvisited.get(q).getDirection() == "left"){
          thing.clearRect(x-10, y, 10, 20);
          //System.out.println("left cleared");
        }
        //System.out.println(unvisited.get(q) + "----------------------");

        if (gen(unvisited.get(q), thing) == true){
          double r = Math.random();
          if (r > 0)
            return true;
          else
            return false;
        }
        //recursion
      }
      return false;

  }

  boolean falsepath(cell now, Graphics thing){

      int x = now.getY();
      int y = now.getX();

      System.out.println(x + " " + y);

      int yind = x/30-1;
      int xind = y/30-1;
      System.out.println("xind = " + xind);
      System.out.println("yind = " + yind);

      int i = (int)(Math.random() * box);
      int j = (int)(Math.random() * box);

      if (xind == i && yind == j){
        System.out.println("SUCCESSSSSSS");
        return true;
      }

      //creating the unvisited list
      unvisited.clear();
      ArrayList<cell> unvisited = new ArrayList<cell>();
      if ((yind > 1)){
          unvisited.add(allcells[xind][yind-1]);
          System.out.println(allcells[xind][yind-1]);
          allcells[xind][yind-1].setDirection("left");
      }
      if ((xind > 1)){
          unvisited.add(allcells[xind-1][yind]);
          System.out.println(allcells[xind-1][yind]);
          allcells[xind-1][yind].setDirection("top");
      }
      System.out.println("hibhob");
      System.out.println(xind + " " + yind);
      if ((yind < box-1)){
          unvisited.add(allcells[xind][yind+1]);
          System.out.println(allcells[xind][yind+1]);
          allcells[xind][yind+1].setDirection("right");
      }
      if ((xind < box-1)){
          unvisited.add(allcells[xind+1][yind]);
          System.out.println(allcells[xind+1][yind]);
          allcells[xind+1][yind].setDirection("bottom");
      }
      System.out.println(unvisited);


      int counter = unvisited.size();

      //this part clears the walls
      while (counter > 0){
        int q = (int)(Math.random() * unvisited.size()); //randomly chooses unvisited cell
        System.out.println(q);

        unvisited.get(q).visited(); //marked as visited
        counter--;

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
        else if (unvisited.get(q).getDirection() == "left"){
          thing.clearRect(x-10, y, 10, 20);
          System.out.println("left cleared");
        }
        System.out.println(unvisited.get(q) + "----------------------");

        if (gen(unvisited.get(q), thing) == true){
          double r = Math.random();
          if (r > 0)
            return true;
          else
            return false;
        }
        //recursion
      }
      return false;

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
