import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;
import java.awt.geom.*;
import java.awt.event.*;

public class mazegen extends JPanel implements ActionListener{

  private Timer timer;
  private Player player;
  private List<Tree> trees = new ArrayList<>();
  private boolean inGame;
  private boolean linesDrawn = false;
  private final int P_X = 40;
  private final int P_Y = 40;
  private final int B_WIDTH = 800;
  private final int B_HEIGHT = 800;
  private final int DELAY = 10;
  private int maxTrees = 5;
  private int numTreesAlive = maxTrees;
  private int[][] pos = new int[maxTrees][2];
  private cell[][] allcells;
  private int counteri, counterj;
  public final int dim = 800; //pixel dimension of the maze
  public int box = (dim-50)/30-1; //25
  private ArrayList<cell> unvisited = new ArrayList<cell>();

  public mazegen(){
    initMaze();
  }

  private void initMaze(){

    addKeyListener(new TAdapter());
    setFocusable(true);
    setBackground(Color.WHITE);
    setDoubleBuffered(true);

    inGame = true;

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

    allcells[0][0].visited();

    initTrees();

    player = new Player(P_X, P_Y);
    timer = new Timer(DELAY, this);
    timer.start();
  }

  public void initTrees() {
      setTrees();
  }

  private void setTrees(){
    for (int[] p : pos){
        p[0] = (int)(Math.random() * dim);
        p[1] = (int)(Math.random() * dim);
        trees.add(new Tree(p[0], p[1]));
    }
  }

  void lines(Graphics m){

      Graphics2D g2d = (Graphics2D) m;

      //if(!linesDrawn){

          for (int i = 30; i < dim-50; i += 30){
            for (int j = 30; j < dim-50; j += 30){
              //left
              g2d.fillRect(i, j+10, 10, 20);
              //top
              g2d.fillRect(i + 10, j, 20, 10);
              //dots
              g2d.fillRect(i, j, 10, 10);
            }
          }

          gen(new cell(), g2d);

          //bottommost
          g2d.fillRect(30, dim-50, dim-70, 10);
          //rightmost
          g2d.fillRect(dim-50, 30, 10, dim-100);
          g2d.clearRect(30, 40, 10, 20);

          linesDrawn = true;
      //}

  }

  boolean legal(int x, int y) {
      if (x < 0 || x >= box || y < 0 || y >= box)
        return false;
      if (allcells[x][y].getMarkStatus())
        return false;
      return true;
  }

  void gen(cell c, Graphics g){

      int x = c.getX();
      int y = c.getY();

      int xind = x/30-1;
      int yind = y/30-1;

      //creating the unvisited list
      int[] dx = {0,0,1,-1};
      int[] dy = {-1,1,0,0};
      String[] dirs = {"top", "bottom", "right", "left"};
      ArrayList<cell> unvisited = new ArrayList<cell>();
      for (int i = 0; i < 4; i++) {
        int newx = xind+dx[i];
        int newy = yind+dy[i];
        if (legal(newx, newy)) {
          unvisited.add(allcells[newx][newy]);
          allcells[newx][newy].visited(); //mark as visited beforehand so it doesn't get picked again
          allcells[newx][newy].setDirection(dirs[i]);
        }
      }

      int counter = unvisited.size();

      //this part clears the walls
      while (counter > 0){
        int q = (int)(Math.random() * unvisited.size()); //randomly chooses unvisited cell
        cell neighborCell = unvisited.get(q);

        counter--;

        String dir = neighborCell.getDirection();
        if (dir == "top"){
          g.clearRect(x, y-10, 20, 10);
          c.clearTop();
        }
        else if (dir == "right"){
          g.clearRect(x+20, y, 10, 20);
          c.clearRight();
        }
        else if (dir == "bottom"){
          g.clearRect(x, y+20, 20, 10);
          c.clearBottom();
        }
        else if (dir == "left"){
          g.clearRect(x-10, y, 10, 20);
          c.clearLeft();
        }
        gen(neighborCell, g);
        unvisited.remove(q);
    }
  }

  public void maintain(cell c, Graphics g){

    int x = c.getX();
    int y = c.getY();
    Graphics g2d = (Graphics2D)g;

    for (int i = 30; i < dim-50; i += 30){
      for (int j = 30; j < dim-50; j += 30){
        g2d.fillRect(i, j+10, 10, 20);
        g2d.fillRect(i + 10, j, 20, 10);
        g2d.fillRect(i, j, 10, 10);
      }
    }

    /*if(!c.hasLeft())
      g2d.clearRect(x-10, y, 10, 20);
    if(!c.hasRight())
      g2d.clearRect(x+20, y, 10, 20);
    if(!c.hasTop())
      g2d.clearRect(x, y+20, 20, 10);
    if(!c.hasBottom())
      g2d.clearRect(x-10, y, 10, 20);*/


    g2d.fillRect(30, dim-50, dim-70, 10);
    g2d.fillRect(dim-50, 30, 10, dim-100);
    g2d.clearRect(30, 40, 10, 20);

  }

  public void paint(Graphics m){
      super.paint(m);
      lines(m);
      paintComponent(m);
  }

  @Override
  public void paintComponent(Graphics g) {

      if (inGame){
        if ((player.getX() > 730 && player.getX() < 750) && (player.getY() > 730 && player.getY() < 750)){
            drawWin(g);
        } else
          drawObjects(g);
      }
      else
        drawGameOver(g);

      Toolkit.getDefaultToolkit().sync();
  }

  private void drawObjects(Graphics g) {

      Graphics2D g2d = (Graphics2D) g;

      /*for(int i = 0; i < allcells.length; i++){
        for(int j = 0; j < allcells[i].length; j++){
          maintain(allcells[i][j], g2d);
        }
      }*/

      g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);

      for (Tree tree : trees){
          g2d.drawImage(tree.getImage(), tree.getX(), tree.getY(), this);
          if(tree.isAlive())
              numTreesAlive++;
      }

      g2d.setColor(Color.BLACK);
      g2d.drawString("Trees left: " + numTreesAlive, 5, 10);

      g2d.setColor(Color.BLACK);
      g2d.fillRect(770, 5, 20, 760);
      g2d.setColor(Color.WHITE);

      if(player.getOxygen() < 100)
          g2d.setColor(Color.RED);

      g2d.fillRect(773, 8 + (760 - (int)((player.getOxygen()/100)*76)),
      14, 754 - (760 - (int)((player.getOxygen()/100)*76)));

      numTreesAlive = 0;

  }

  private void drawGameOver(Graphics g) {

      String msg = "Game Over";
      Font small = new Font("Helvetica", Font.BOLD, 14);
      FontMetrics fm = getFontMetrics(small);

      g.setColor(Color.red);
      g.setFont(small);
      g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
              B_HEIGHT / 2);

      new java.util.Timer().schedule(
        new java.util.TimerTask() {
            @Override
            public void run() {
                System.exit(0);
            }
        },
        4000
      );
  }

  private void drawWin(Graphics g){

    String msg = "You win!";
    Font small = new Font("Helvetica", Font.BOLD, 14);
    FontMetrics fm = getFontMetrics(small);

    g.setColor(Color.red);
    g.setFont(small);
    g.drawString(msg, (B_WIDTH - fm.stringWidth(msg)) / 2,
            B_HEIGHT / 2);

    new java.util.Timer().schedule(
      new java.util.TimerTask() {
          @Override
          public void run() {
              System.exit(0);
          }
      },
      4000
    );

  }

  @Override
  public void actionPerformed(ActionEvent e) {

      inGame();

      updatePlayer();
      updateTrees();
      updateOxygenLevel();

      checkCollisions();

      repaint();
  }

  private void inGame() {
      if (!inGame)
          timer.stop();
  }

  private void updatePlayer() {
      player.move();
  }

  private void updateTrees() {

      for (int i = 0; i < trees.size(); i++) {

          Tree t = trees.get(i);

          t.move();
      }
  }

  private void updateOxygenLevel(){
      if (player.getOxygen() <= 0)
          inGame = false;
  }

  public void checkCollisions() {

      Rectangle r2 = player.getBounds();

      for (Tree t : trees) {

          Rectangle r1 = t.getBounds();

              if (r1.intersects(r2)) {

                  if(!t.getDeathStatus()){

                      if(t.isAlive()){
                          player.increaseOxygen();
                          t.killTree();
                          t.loadImage("/Users/and1zhao/Downloads/treeDead.png");
                          t.justDied();
                          new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    t.diedForAWhile();
                                }
                            },
                            5000
                          );
                      } else
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
