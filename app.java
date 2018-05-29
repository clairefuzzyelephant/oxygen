import javax.swing.*;
import java.awt.*;
import java.util.*;

public class app extends JFrame{

  public app(){
    initUI();
  }

  private void initUI(){

    add(new maze());

    setSize(500,500);

    setTitle("Oxygen");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }

  public static void main(String[] args){

    EventQueue.invokeLater(() -> {
      app x = new app();
      x.setVisible(true);
    });
  }
}
