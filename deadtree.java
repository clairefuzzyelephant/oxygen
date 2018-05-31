import java.awt.*;
import javax.swing.JFrame;

public class deadtree extends JFrame{

  public deadtree(){
    initUI();
  }

  private void initUI(){
    add(new maze());

    pack();

    setTitle("DeadTree");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }

  public static void main(String[] args){

    EventQueue.invokeLater(() -> {
      deadtree x = new deadtree();
      x.setVisible(true);
    });
  }
}
