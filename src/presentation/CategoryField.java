package presentation;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;

public class CategoryField extends JPanel{
    private JLabel categoryLabel;
    private JPanel categoryPanel;
    private JTextField categoryTextField;
    private JPanel categoryTFPanel;
    private String category;
    private String letter;

    public CategoryField(String category, String letter) {
        this.category = category;
        this.letter = letter;
        setLayout(new FlowLayout());
        categoryLabel = new JLabel(category);
        categoryTextField = new JTextField();
        categoryTextField.setColumns(14);
        categoryPanel = new JPanel();
        categoryTFPanel = new JPanel();
        categoryPanel.add(categoryLabel);
        categoryTFPanel.add(categoryTextField);
        add(categoryPanel);
        add(categoryTFPanel);
    }
    public String getAnswer() {
        if (categoryTextField.getText().split("")[0].toLowerCase().equals(letter.toLowerCase())) {
            return categoryTextField.getText().toLowerCase(Locale.ROOT);
        } else {
            return "";
        }
    }
    public void setLetter(String letter) {
        categoryTextField.setText("");
        this.letter = letter;
    }
}
