import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberGuessingGame extends JFrame {
    private int gen_Num;
    private int max_Turns = 10;
    private int turns_Rem;
    private int times_Won;
    private boolean progress;

    private JTextField guessField;
    private JButton guessButton;
    private JLabel feedbackLabel;
    private JLabel turns_RemLabel;
    private JLabel times_WonLabel;

    public NumberGuessingGame() {
        setTitle("Guess the Number");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(5, 1));

        guessField = new JTextField();
        guessButton = new JButton("Guess Number!");
        feedbackLabel = new JLabel();
        turns_RemLabel = new JLabel("Attempts left: " + max_Turns);
        times_WonLabel = new JLabel("Rounds won: " + times_Won);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!progress) {
                    startNewRound();
                } else {
                    checkGuess();
                }
            }
        });

        add(new JLabel("Guess a number between 1 and 100:"));
        add(guessField);
        add(guessButton);
        add(feedbackLabel);
        add(turns_RemLabel);
        add(times_WonLabel);
    }

    private void startNewRound() {
        gen_Num = generateRandomNumber(1, 100);
        turns_Rem = max_Turns;
        progress = true;
        guessButton.setText("Guess");
        feedbackLabel.setText("");
        turns_RemLabel.setText("Attempts left: " + turns_Rem);
    }

    private void checkGuess() {
        try {
            int guess = Integer.parseInt(guessField.getText());
            turns_Rem--;

            if (guess == gen_Num) {
                progress = false;
                guessButton.setText("Play Again");
                feedbackLabel.setText("Congratulations! You guessed the number right!");
                times_Won++;
                times_WonLabel.setText("Rounds won: " + times_Won);
            } else if (turns_Rem == 0) {
                progress = false;
                guessButton.setText("Play Again");
                feedbackLabel.setText("Sorry, you've run out of attempts. The number was " + gen_Num + ".");
            } else if (guess < gen_Num) {
                feedbackLabel.setText("Too low! Try again.");
                turns_RemLabel.setText("Attempts left: " + turns_Rem);
            } else {
                feedbackLabel.setText("Too high! Try again.");
                turns_RemLabel.setText("Attempts left: " + turns_Rem);
            }
        } catch (NumberFormatException ex) {
            feedbackLabel.setText("Invalid input. Please enter a valid number.");
        }
    }

    private int generateRandomNumber(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                NumberGuessingGame game = new NumberGuessingGame();
                game.setVisible(true);
            }
        });
    }
}