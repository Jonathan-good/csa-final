import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
class PongPanel extends JPanel implements ActionListener, KeyListener {

    // Initialize variables: ball's popsition and velocity.
    // Peddles' position and size.
    private int ballX = 400, ballY = 300, ballVelocityX = 2, ballVelocityY = 2, ballSize = 20;
    private int paddle1Y = 250, paddle2Y = 250, paddleHeight = 100, paddleWidth = 15;
    private Timer timer;
    private int player1life = 3, player2life = 3;

    // Constructor and start timer for the game.
    // Every 10 milliseconds, the actionPerformed and repaint methods are called.

    public PongPanel() {
        timer = new Timer(10, this);
        timer.start();
        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);

        // Draw paddles
        g.setColor(Color.WHITE);
        g.fillRect(30, paddle1Y, paddleWidth, paddleHeight);
        g.fillRect(755, paddle2Y, paddleWidth, paddleHeight);

        // Draw ball
        g.fillOval(ballX, ballY, ballSize, ballSize);

        // Draw score
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.drawString("Jonathan: " + player1life, 100, 50);
        g.drawString("Austin: " + player2life, 500, 50);
    }


    // Move the ball and check for collisions.
    @Override
    public void actionPerformed(ActionEvent e) {
        ballX += ballVelocityX;
        ballY += ballVelocityY;

        if (ballY <= 0 || ballY >= 580) {
            ballVelocityY = -ballVelocityY;
        }

        if (ballX <= 45 && ballY > paddle1Y && ballY < paddle1Y + paddleHeight ||
                ballX >= 740 && ballY > paddle2Y && ballY < paddle2Y + paddleHeight) {
            ballVelocityX = -ballVelocityX;
        }

        if (ballX < 0) {
            player1life--;
            resetBall();
        } else if (ballX > 780) {
            player2life--;
            resetBall();
        }

        if (player1life == 0 || player2life == 0) {
            timer.stop();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            StartScreenPanel startPanel = new StartScreenPanel(frame);
            frame.add(startPanel);
            frame.revalidate();
            frame.repaint();
        }

        repaint();
    }

    // Reset the ball to the center of the screen.

    private void resetBall() {
        ballX = 400;
        ballY = 300;
        ballVelocityX = -ballVelocityX;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    // Move the paddles up and down.

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W && paddle1Y > 0) {
            paddle1Y -= 20;
//            System.out.println("W was pressed");
        }
        if (e.getKeyCode() == KeyEvent.VK_S && paddle1Y < 460) {
            paddle1Y += 20;
//            System.out.println("S was pressed");
        }
        if (e.getKeyCode() == KeyEvent.VK_UP && paddle2Y > 0) {
            paddle2Y -= 20;
//            System.out.println("Up was pressed");
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN && paddle2Y < 460) {
            paddle2Y += 20;
//            System.out.println("Down was pressed");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}