import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

class BreakoutPanel extends JPanel implements ActionListener, KeyListener {
    private int ballX = 400, ballY = 300, ballVelocityX = 1, ballVelocityY = -1, ballSize = 20;
    private int paddle1X = 350, paddle1Y = 520, paddleHeight = 10, paddleWidth = 120;
    private Timer timer;
    private int scorePlayer1 = 0, life = 3;

    ArrayList<Bricks> bricks = new ArrayList<Bricks>();


    public BreakoutPanel() {
        timer = new Timer(5, this);
        timer.start();
        this.addKeyListener(this);
        this.setFocusable(true);

        // Draw bricks with different colors using 2d array, 0 is none, 1 is red, 2 is orange, 3 is yellow, 4 is green, 5 is blue


        int[][] bricks_arr = new int[][]{
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0,},
                {0, 1, 2, 2, 2, 2, 2, 2, 1, 0,},
                {0, 1, 2, 3, 3, 3, 3, 2, 1, 0,},
                {0, 1, 2, 3, 4, 4, 3, 2, 1, 0,},
                {0, 1, 2, 3, 4, 4, 3, 2, 1, 0,},
                {0, 1, 2, 3, 3, 3, 3, 2, 1, 0,},
                {0, 1, 2, 2, 2, 2, 2, 2, 1, 0,},
                {0, 1, 1, 1, 1, 1, 1, 1, 1, 0,},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0,},
                {1, 2, 2, 2, 2, 2, 2, 2, 2, 1,},
                {1, 2, 3, 3, 3, 3, 3, 3, 2, 1,},
                {1, 2, 3, 4, 4, 4, 4, 3, 2, 1,},
        };


        for(int i = 0; i < bricks_arr.length; i++){
            for(int j = 0; j < bricks_arr[i].length; j++){
                if(bricks_arr[i][j] == 0){
                    continue;
                }
                if(bricks_arr[i][j] == 1){
                    bricks.add(new Bricks(40 + j * 70, 70 + i * 30, 60, 20, Color.RED));
                }
                if(bricks_arr[i][j] == 2){
                    bricks.add(new Bricks(40 + j * 70, 70 + i * 30, 60, 20, Color.ORANGE));
                }
                if(bricks_arr[i][j] == 3){
                    bricks.add(new Bricks(40 + j * 70, 70 + i * 30, 60, 20, Color.YELLOW));
                }
                if(bricks_arr[i][j] == 4){
                    bricks.add(new Bricks(40 + j * 70, 70 + i * 30, 60, 20, Color.GREEN));
                }
                if(bricks_arr[i][j] == 5){
                    bricks.add(new Bricks(40 + j * 70, 70 + i * 30, 60, 20, Color.BLUE));
                }
            }
        }

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);

        // Draw paddles
        g.setColor(Color.WHITE);
        g.fillRect(paddle1X, paddle1Y, paddleWidth, paddleHeight);

        // Draw red line at the button


        // Draw ball
        g.fillOval(ballX, ballY, ballSize, ballSize);

        // Draw score

        g.setColor(Color.RED);
        g.fillRect(0, 565, 800, 10);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Player score: " + scorePlayer1, 100, 50);
        g.drawString("Life: " + life, 500, 50);





        for (Bricks brick : bricks) {
            if (brick.isVisible()) {
                g.setColor(brick.getColor());
                g.fillRect(brick.getX(), brick.getY(), brick.getWidth(), brick.getHeight());
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ballX += ballVelocityX;
        ballY += ballVelocityY;

        // Ball collision with top and bottom
        if (ballY <= 0) {
            ballVelocityY = -ballVelocityY;
        }

        // Ball collision with left and right
        if (ballX <= 0 || ballX >= 780) {
            ballVelocityX = -ballVelocityX;
        }

        // Ball collision with paddles
        if (ballY >= 500 && ballX > paddle1X && ballX < paddle1X + paddleWidth) {
            ballVelocityY = -ballVelocityY;
        }

        // Ball collision with bricks, include both from top and bottom, and left and right

        for (Bricks brick : bricks) {
            if (brick.isVisible()) {
                Rectangle ballRect = new Rectangle(ballX, ballY, ballSize, ballSize);
                Rectangle brickRect = brick.getRect();

                if (ballRect.intersects(brickRect)) {
                    brick.setVisibility(false);
                    scorePlayer1++;

                    // Calculate the center point of the ball
                    int ballCenterX = ballX + ballSize / 2;
                    int ballCenterY = ballY + ballSize / 2;

                    // Check top and bottom collision
                    if (ballCenterX > brick.getX() && ballCenterX < brick.getX() + brick.getWidth()) {
                        ballVelocityY = -ballVelocityY; // Reverses the Y-direction of the ball
                    }
                    // Check left and right collision
                    else if (ballCenterY > brick.getY() && ballCenterY < brick.getY() + brick.getHeight()) {
                        ballVelocityX = -ballVelocityX; // Reverses the X-direction of the ball
                    }
                }
            }
        }



        // losing a life
        if (ballY > 550) {
            life--;
            resetBall();
        }

        if (life <= 0) {
            // game over and switch to start screen
            timer.stop();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            frame.getContentPane().removeAll();
            StartScreenPanel startPanel = new StartScreenPanel(frame);
            frame.add(startPanel);
            frame.revalidate();
            frame.repaint();
            startPanel.requestFocusInWindow();
        }


        repaint();
    }

    private void resetBall() {
        ballX = 400;
        ballY = 300;
        ballVelocityX = -ballVelocityX;
        ballVelocityY = -ballVelocityY;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A && paddle1X > 0) {
            paddle1X -= 25;
        } else if (e.getKeyCode() == KeyEvent.VK_D && paddle1X < 680) {
            paddle1X += 25;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}