import javax.swing.JFrame;

public class PongGame {

    // This is the main method that runs the game.
    public static void main(String[] args) {
        JFrame frame = new JFrame("Pong Game");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        StartScreenPanel startPanel = new StartScreenPanel(frame);
        frame.add(startPanel);

        frame.setVisible(true);
    }
}
