import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TitleScreen{

    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;

    public TitleScreen(){
        initTitleScreen();
    }

    public static void main(String[] args){
        TitleScreen titleScreen = new TitleScreen();
        titleScreen.showFrame();
    }

    private void initTitleScreen(){
        mainFrame = new JFrame("are you on ten yet");
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
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

        mainFrame.add(headerLabel);
        mainFrame.add(controlPanel);
        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showFrame(){
        headerLabel.setText("Oxygen");

        JButton playButton = new JButton("New Game");
        JButton helpButton = new JButton("Instructions");
        JButton quitButton = new JButton("Quit");

        playButton.setActionCommand("Play");
        helpButton.setActionCommand("Help");
        quitButton.setActionCommand("Quit");

        playButton.addActionListener(new ButtonClickListener());
        helpButton.addActionListener(new ButtonClickListener());
        quitButton.addActionListener(new ButtonClickListener());

        controlPanel.add(playButton);
        controlPanel.add(helpButton);
        controlPanel.add(quitButton);

        mainFrame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener{
        public void actionPerformed(ActionEvent e) {
           String command = e.getActionCommand();

           if( command.equals("Play")) {
              statusLabel.setText("Loading New Game");
              new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        mainFrame.setVisible(false);
                    }
                },
                2000
              );
           } else if( command.equals("Help") ) {
              statusLabel.setText("Sending you to Instructions");
              new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        mainFrame.setVisible(false);
                    }
                },
                2000
              );
           } else {
              statusLabel.setText("Exiting game...");
              new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        System.exit(0);
                    }
                },
                2000
              );
           }
        }
    }

}
