public class Tree extends Sprite {

    private final int BOARD_WIDTH = 400;
    private final int TREE_SPEED = 1;
    private boolean treeAlive = true;
    private final double direction = Math.random();

    public Tree(int x, int y) {
        super(x, y);
        initTree();
    }

    private void initTree() {
        loadImage("/Users/and1zhao/Downloads/treeAlive.png");
        getImageDimensions();
    }

    public void move() {

        if(direction < 0.25 && direction > 0)
          x += TREE_SPEED;
        else if (direction > 0.25 && direction < 0.5)
          x -= TREE_SPEED;
        else if (direction > 0.5 && direction < 0.75)
          y += TREE_SPEED;
        else if (direction > 0.75 && direction < 1)
          y -= TREE_SPEED;

        if (x > BOARD_WIDTH)
            visible = false;
    }

    public boolean isAlive(){
        return treeAlive;
    }

    public void killTree(){
        treeAlive = false;
    }

}
