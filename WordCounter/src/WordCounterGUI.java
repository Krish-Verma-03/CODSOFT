import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordCounterGUI extends JFrame {
    private JTextArea addText;
    private JButton countBtn;
    private JLabel result;
    private JCheckBox wordCheckBox;

    private Set<String> stopWords;

    public WordCounterGUI() {
        setTitle("Word Counter");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        addText = new JTextArea();
        countBtn = new JButton("Count Words");
        result = new JLabel("Word count: 0");
        wordCheckBox = new JCheckBox("Ignore Stop Words");

        stopWords = new HashSet<>(Arrays.asList("the", "and", "is", "of", "a", "an", "in"));

        countBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                countWords();
            }
        });

        setLayout(new BorderLayout());
        add(new JScrollPane(addText), BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(countBtn);
        bottomPanel.add(wordCheckBox);
        bottomPanel.add(result);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void countWords() {
        String text = addText.getText();
        if (text.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter some text to count words.");
            return;
        }

        String[] words = text.split("\\s+|\\p{Punct}+"); 
        int totalCount = words.length;

        if (wordCheckBox.isSelected()) {
            totalCount = 0;
            for (String word : words) {
                if (!stopWords.contains(word.toLowerCase())) {
                    totalCount++;
                }
            }
        }

        result.setText("Word count: " + totalCount);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                WordCounterGUI wordCounterGUI = new WordCounterGUI();
                wordCounterGUI.setVisible(true);
            }
        });
    }
}