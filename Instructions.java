import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Instructions{

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel rulesPanel;

    public Instructions(){
        initInstructions();
    }

    public static void main(String[] args){
        Instructions instructions = new Instructions();
        instructions.showFrame();
    }

    private void initInstructions(){
        mainFrame = new JFrame("i live on ten");
        mainFrame.setSize(800,800);
        mainFrame.setLayout(new GridLayout(4, 1));

        headerLabel = new JLabel("",JLabel.CENTER);
        statusLabel = new JLabel("",JLabel.CENTER);
        statusLabel.setSize(350,100);

        mainFrame.addWindowListener(new WindowAdapter() {
           public void windowClosing(WindowEvent windowEvent){
              System.exit(0);
           }
        });
        rulesPanel = new JPanel();
        rulesPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(rulesPanel);
        mainFrame.setVisible(true);
    }

    private void showFrame(){
        headerLabel.setText("Rules");

        JLabel ruleOne = new JLabel("You have a specified amount of oxygen to get to the end of the maze");
        JLabel ruleTwo = new JLabel("Run out of oxygen, and you die");
        JLabel ruleThree = new JLabel("Running into live trees will grant you oxygen");
        JLabel ruleFour = new JLabel("Running into dead trees will kill you");
        JLabel ruleFive = new JLabel("Good luck!");

        rulesPanel.add(ruleOne);
        rulesPanel.add(ruleTwo);
        rulesPanel.add(ruleThree);
        rulesPanel.add(ruleFour);
        rulesPanel.add(ruleFive);

        JButton backButton = new JButton("Back");
        backButton.setActionCommand("go back");
        backButton.addActionListener(new ButtonClickListener());
        mainFrame.add(backButton);

        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
           String command = e.getActionCommand();

           if( command.equals("go back")) {
                statusLabel.setText("Going back to home screen");
                new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            System.out.println("back to home screen");
                            System.exit(0);
                        }
                    },
                    2000
                );
           }
        }
    }

}
