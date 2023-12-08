import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartScreenPanel extends JPanel {
    public StartScreenPanel(JFrame frame) {
        JButton pongButton = new JButton("Pong");
        pongButton.addActionListener(new ActionListener() {
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


        JButton breakoutButton = new JButton("Breakout");
        breakoutButton.addActionListener(new ActionListener() {
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

        this.add(pongButton);
        this.add(breakoutButton);
    }
}
