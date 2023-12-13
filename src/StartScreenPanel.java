import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreenPanel extends JPanel {

    // This is the constructor for the start screen panel.
    // It creates two buttons, one for Pong and one for Breakout.

    /**
     * StartScreenPanel constructor
     * @param frame object
     * @return void
     * Creates a start screen panel with two buttons
     */

    public StartScreenPanel(JFrame frame) {
        JButton pongButton = new JButton("Pong");

        // When the pong button is clicked, the panel is removed and a new pong panel is added.

        pongButton.addActionListener(new ActionListener() {

            /**
             * actionPerformed method
             * @param ActionEvent e
             * @return void
             * Removes the start screen panel and adds a pong panel
             */

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                PongPanel pongPanel = new PongPanel();
                frame.add(pongPanel);
                frame.revalidate();
                frame.repaint();
                pongPanel.requestFocusInWindow();
            }
        });

        // When the breakout button is clicked, the panel is removed and a new breakout panel is added.


        JButton breakoutButton = new JButton("Breakout");
        breakoutButton.addActionListener(new ActionListener() {

            /**
             * actionPerformed method
             * @param ActionEvent e
             * @return void
             * Removes the start screen panel and adds a breakout panel
             */

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().removeAll();
                BreakoutPanel breakoutPanel = new BreakoutPanel();
                frame.add(breakoutPanel);
                frame.revalidate();
                frame.repaint();
                breakoutPanel.requestFocusInWindow();
            }
        });

        // Add the buttons to the panel.
        this.add(pongButton);
        this.add(breakoutButton);
    }
}
