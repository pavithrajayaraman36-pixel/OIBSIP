import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Random;

public class GuessGame extends JFrame implements ActionListener {

    int randomNumber;
    int attempts = 0;
    int maxAttempts = 5;
    int score = 0;

    JTextField inputField;
    JLabel messageLabel, attemptsLabel, scoreLabel, titleLabel;
    JButton guessButton, restartButton;

    public GuessGame() {

        setTitle("Number Guessing Game 🎯");
        setSize(450, 350);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(30, 30, 60));

        // 🔷 TITLE
        titleLabel = new JLabel("🎯 Guess The Number Game", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(Color.WHITE);

        // 🔷 CENTER PANEL
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 1, 10, 10));
        centerPanel.setBackground(new Color(40, 40, 80));

        messageLabel = new JLabel("Guess a number (1-100)", JLabel.CENTER);
        messageLabel.setForeground(Color.YELLOW);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.BOLD, 16));
        inputField.setHorizontalAlignment(JTextField.CENTER);

        attemptsLabel = new JLabel("Attempts: 0/5", JLabel.CENTER);
        attemptsLabel.setForeground(Color.WHITE);

        scoreLabel = new JLabel("Score: 0", JLabel.CENTER);
        scoreLabel.setForeground(Color.CYAN);

        centerPanel.add(messageLabel);
        centerPanel.add(inputField);
        centerPanel.add(attemptsLabel);
        centerPanel.add(scoreLabel);

        // 🔷 BUTTON PANEL
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(30, 30, 60));

        guessButton = new JButton("Guess");
        restartButton = new JButton("Restart");

        guessButton.setBackground(Color.GREEN);
        guessButton.setForeground(Color.BLACK);
        guessButton.setFont(new Font("Arial", Font.BOLD, 14));

        restartButton.setBackground(Color.ORANGE);
        restartButton.setForeground(Color.BLACK);
        restartButton.setFont(new Font("Arial", Font.BOLD, 14));

        buttonPanel.add(guessButton);
        buttonPanel.add(restartButton);

        // ADD COMPONENTS
        add(titleLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        guessButton.addActionListener(this);
        restartButton.addActionListener(this);

        generateNumber();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    void generateNumber() {
        randomNumber = new Random().nextInt(100) + 1;
        attempts = 0;
        inputField.setText("");
        messageLabel.setText("New Game Started!");
        messageLabel.setForeground(Color.YELLOW);
        attemptsLabel.setText("Attempts: 0/5");
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == guessButton) {
            try {
                int guess = Integer.parseInt(inputField.getText());
                attempts++;

                if (guess == randomNumber) {
                    messageLabel.setText("🎉 Correct Guess!");
                    messageLabel.setForeground(Color.GREEN);
                    score += (maxAttempts - attempts + 1) * 10;
                    scoreLabel.setText("Score: " + score);
                    saveScore();
                    guessButton.setEnabled(false);
                }
                else if (guess < randomNumber) {
                    messageLabel.setText("Too Low!");
                    messageLabel.setForeground(Color.ORANGE);
                }
                else {
                    messageLabel.setText("Too High!");
                    messageLabel.setForeground(Color.RED);
                }

                attemptsLabel.setText("Attempts: " + attempts + "/5");

                if (attempts >= maxAttempts && guess != randomNumber) {
                    messageLabel.setText("❌ Game Over! Number: " + randomNumber);
                    messageLabel.setForeground(Color.RED);
                    saveScore();
                    guessButton.setEnabled(false);
                }

            } catch (Exception ex) {
                messageLabel.setText("Enter a valid number!");
                messageLabel.setForeground(Color.RED);
            }
        }

        if (e.getSource() == restartButton) {
            generateNumber();
            guessButton.setEnabled(true);
        }
    }

    void saveScore() {

        String name = JOptionPane.showInputDialog(this, "Enter your name:");

        try {
            Connection con = DBConnection.getConnection();

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO scores(username, score) VALUES(?, ?)"
            );

            ps.setString(1, name);
            ps.setInt(2, score);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(this, "✅ Score Saved!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new GuessGame();
    }
}