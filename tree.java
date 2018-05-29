import java.awt.*;
import javax.swing.JFrame;

public class tree extends JFrame{

  public tree(){
    initUI();
  }

  private void initUI(){
    add(new maze());

    pack();

    setTitle("LiveTree");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
  }

  public static void main(String[] args){

    EventQueue.invokeLater(() -> {
      tree x = new tree();
      x.setVisible(true);
    });
  }
}
