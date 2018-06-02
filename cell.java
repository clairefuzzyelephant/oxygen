import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.awt.Graphics;

public class cell{

  private int x;
  private int y; //Left top corner coordinates
  private boolean mark;
  private boolean tree;
  private String direction;

  public cell(){
    x = 40;
    y = 40;
    mark = false;
    tree = false;
    direction = "right";
  }

  public cell(int i, int j){
    x = i;
    y = j;
    mark = false;
    tree = false;
    direction = "right";
  }

  public cell(int i, int j, boolean isMarked, boolean isTree){
    x = i;
    y = j;
    mark = isMarked;
    tree = isTree;
    direction = "right";
  }

  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }

  public boolean getMarkStatus(){
    return mark;
  }

  public boolean getTreeStatus(){
    return tree;
  }


  public void visited(){
    mark = true;
  }

  public void addTree(){
    tree = true;
  }

  public String getDirection(){
    return direction;
  }

  public void setDirection(String dir){
    direction = dir;
  }

  public String toString(){

    return "cell at " + y + ", " + x;
  }


}
