import java.awt.EventQueue;
import javax.swing.JFrame;

public class window extends JFrame {

    public window() {
        initWindow();
    }

    private void initWindow() {

        add(new mazegen());

        setTitle("Oxygen");
        setSize(800, 800);

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
