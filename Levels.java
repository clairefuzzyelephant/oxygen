import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Levels{

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel levelPanel;

    public Levels(){
        initLevels();
    }

    public static void main(String[] args){
        Levels levels = new Levels();
        levels.showFrame();
    }

    private void initLevels(){
        mainFrame = new JFrame("do you have henn yet");
        mainFrame.setSize(400,400);
        mainFrame.setLayout(new GridLayout(3, 1));

        headerLabel = new JLabel("",JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        mainFrame.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent windowEvent){
              System.exit(0);
           }
        });
        levelPanel = new JPanel();
        levelPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(levelPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showFrame(){
        headerLabel.setText("Levels");

        JButton levelOne = new JButton("1");
        JButton levelTwo = new JButton("2");
        JButton levelThree = new JButton("3");

        levelOne.setActionCommand("one");
        levelTwo.setActionCommand("two");
        levelThree.setActionCommand("three");

        levelOne.addActionListener(new ButtonClickListener());
        levelTwo.addActionListener(new ButtonClickListener());
        levelThree.addActionListener(new ButtonClickListener());

        levelPanel.add(levelOne);
        levelPanel.add(levelTwo);
        levelPanel.add(levelThree);

        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
           String command = e.getActionCommand();

           if( command.equals("one")) {
                statusLabel.setText("Loading Level 1");
                new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("playing level one");
                            System.exit(0);
                        }
                    },
                    2000
                );
           } else if( command.equals("two") ) {
                statusLabel.setText("Loading Level 2");
                new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("playing level two");
                            System.exit(0);
                        }
                    },
                    2000
                );
           } else if (command.equals("three")){
                statusLabel.setText("Loading Level 3");
                new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("playing level three");
                            System.exit(0);
                        }
                    },
                    2000
                );
           }
        }
    }

}
