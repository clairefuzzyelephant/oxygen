import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.concurrent.TimeUnit;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Player player;
    private List<Tree> trees = new ArrayList<>();
    private boolean inGame;
    private final int P_X = 20;
    private final int P_Y = 20;
    private final int B_WIDTH = 400;
    private final int B_HEIGHT = 300;
    private final int DELAY = 10;
    private int maxTrees = 5;
    private int[][] pos = new int[maxTrees][2];

    public Board() {
        initBoard();
    }

    private void initBoard() {

        initTrees();

        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.black);
        setDoubleBuffered(true);
        inGame = true;

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

        player = new Player(P_X, P_Y);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initTrees() {
        setTrees();
        System.out.println(trees);
    }

    private void setTrees(){
      for (int[] p : pos){
          p[0] = (int)(Math.random() * (B_WIDTH - 50)) + 40;
          p[1] = (int)(Math.random() * (B_HEIGHT - 50)) + 40;
          trees.add(new Tree(p[0], p[1]));
      }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame)
          drawObjects(g);
        else
          drawGameOver(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void drawObjects(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);

        //List<Tree> ts = person.getTrees();

        if (trees.size() > 0){
            for (Tree tree : trees) {
                g2d.drawImage(tree.getImage(), tree.getX(), tree.getY(), this);
            }
        }

        g2d.setColor(Color.WHITE);
        g2d.drawString("Trees left: " + trees.size(), 5, 15);

    }

    private void drawGameOver(Graphics g) {

        String msg = "Game Over";
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics fm = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
                B_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        inGame();

        updatePlayer();
        updateTrees();

        checkCollisions();

        repaint();
    }

    private void inGame() {
        if (!inGame)
            timer.stop();
    }

    private void updatePlayer() {
        if (player.isVisible())
            player.move();
    }

    private void updateTrees() {

        //List<Tree> ts = trees;//person.getTrees();

        for (int i = 0; i < trees.size(); i++) {

            Tree t = trees.get(i);

            if (t.isVisible()) {
                t.move();
            } else {
                trees.remove(i);
            }
        }
    }

    /*private void step() {

        person.move();

        repaint(person.getX()-1, person.getY()-1,
                person.getWidth()+2, person.getHeight()+2);
    }*/

    public void checkCollisions() {

        Rectangle r2 = player.getBounds();

        //List<Tree> ts = trees;//person.getTrees();

        for (Tree t : trees) {

            Rectangle r1 = t.getBounds();

                if (r1.intersects(r2)) {

                    if(t.isAlive()){
                        t.killTree();
                        t.loadImage("/Users/and1zhao/Downloads/treeDead.png");
                        try{
                            Thread.sleep(5010);
                        }
                        catch(InterruptedException ex){
                            Thread.currentThread().interrupt();
                        }
                        System.out.println("tree is dead");
                        System.out.println(t.getdx() + " " + t.getdy());
                    } else {
                        System.out.println("u r ded");
                        inGame = false;
                    }

            }
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            player.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            player.keyPressed(e);
        }
    }
}
