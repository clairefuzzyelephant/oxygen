import java.awt.EventQueue;
import javax.swing.JFrame;

public class window extends JFrame {

    public window() {

        initUI();
    }

    private void initUI() {

        add(new Board());

        setTitle("Oxygen");
        setSize(400, 300);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
            window ex = new window();
            ex.setVisible(true);
        });
    }
}
