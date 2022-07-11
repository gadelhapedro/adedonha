package presentation;

import database.persistence.ParticipanteDAO;
import rule.Participante;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JPanel backgroundPanel;
    private JPanel helpPanel;
    private JPanel playPanel;
    private JButton playButton;
    private JButton helpButton;
    private JLabel infoLabel;
    private JPanel infoPanel;
    private JLabel titleLabel;
    private JPanel titlePanel;
    private JLabel subtitleLabel;
    private JPanel subtitlePanel;
    private JPanel usernamePanel;
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel idLabel;
    private JTextField idTextField;
    private JPanel idPanel;
    private JPanel difficultyPanel;
    private JLabel difficultyLabel;
    private JComboBox<String> difficultyComboBox;
    private boolean playButtonClicked;
    private int timerCounter;

    public MainWindow() {
        super("Adedonha");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(420, 320);
        setLayout(new BorderLayout());

        addComponents();
        addLayout();
        addEvents();

        pack();

        setVisible(true);
    }

    private void addComponents() {
        playButtonClicked = false;
        backgroundPanel = new JPanel();
        helpButton = new JButton("Como jogar");
        playButton = new JButton("Jogar");
        helpPanel = new JPanel();
        playPanel = new JPanel();
        infoLabel = new JLabel("tecprog@2022.1");
        infoPanel = new JPanel();
        titleLabel = new JLabel("Adedonha");
        titlePanel = new JPanel();
        subtitleLabel = new JLabel("ou Stop! ou adedanha, ou nome-lugar-objeto");
        subtitlePanel = new JPanel();
        usernamePanel = new JPanel();
        usernameLabel = new JLabel("Seu nome:");
        usernameTextField = new JTextField();
        idLabel = new JLabel("Seu ID:");
        idTextField = new JTextField();
        idPanel = new JPanel();
        difficultyPanel = new JPanel();
        difficultyLabel = new JLabel("Dificuldade:");
        difficultyComboBox = new JComboBox<>();
        difficultyComboBox.addItem("Facil");
        difficultyComboBox.addItem("Medio");
        difficultyComboBox.addItem("Dificil");
        difficultyComboBox.addItem("Impossível");


        titlePanel.add(titleLabel);
        subtitlePanel.add(subtitleLabel);
        infoPanel.add(infoLabel);
        helpPanel.add(helpButton);
        playPanel.add(playButton);
        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameTextField);
        idPanel.add(idLabel);
        idPanel.add(idTextField);
        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(difficultyComboBox);
        backgroundPanel.add(helpPanel);
        backgroundPanel.add(titlePanel);
        backgroundPanel.add(subtitlePanel);
        backgroundPanel.add(usernamePanel);
        backgroundPanel.add(idPanel);
        backgroundPanel.add(playPanel);
        backgroundPanel.add(infoPanel);
        backgroundPanel.add(difficultyPanel);



        idPanel.setVisible(false);
        usernamePanel.setVisible(false);
        difficultyPanel.setVisible(false);
        add(backgroundPanel);


    }
    private void addLayout() {
        setLayout(new FlowLayout());
        backgroundPanel.setLayout(new GridLayout(8, 1, 0, 5));
        usernamePanel.setLayout(new FlowLayout());
        helpPanel.setLayout(new FlowLayout(FlowLayout.TRAILING));
        titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        subtitlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        subtitleLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        infoPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 8));
        playPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        usernameTextField.setColumns(10);
        usernamePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        idPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        difficultyPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    private void addEvents() {
        helpButton.addActionListener(e -> helpClicked());
        playButton.addActionListener(e -> playClicked());
    }
    private void helpClicked() {
        JOptionPane.showMessageDialog(this, "Insira uma palavra com a letra sorteda na tela" +
                "que encaixe no tema.ex: carro, lugar, objeto, etc." +"Para começar a jogar clique em jogar e insira seu nome.");
    }
    private void getDifficulty() {
        if (difficultyComboBox.getSelectedIndex() == 0) {
            timerCounter = 1200;
        } else if (difficultyComboBox.getSelectedIndex() == 1) {
            timerCounter = 600;
        } else if (difficultyComboBox.getSelectedIndex() == 2) {
            timerCounter = 300;
        }
        else if (difficultyComboBox.getSelectedIndex() == 3) {
            timerCounter = 100;
        }
    }
    private void playClicked() {
        if (playButtonClicked && (usernameTextField.getText().isEmpty() || !checkId()) ) {
            JOptionPane.showMessageDialog(this, "Nome ou Id vazios ou incorretos!");
        }
        else if (usernameTextField.getText().isEmpty()) {
            usernamePanel.setVisible(true);
            idPanel.setVisible(true);
            difficultyPanel.setVisible(true);
            usernameTextField.requestFocus();
        }
        else{
            this.dispose();
            Participante participante = new Participante();
            participante.setNome(usernameTextField.getText());
            participante.setId(Integer.parseInt(idTextField.getText()));
            ParticipanteDAO.inserir(participante);
            getDifficulty();
            new GameWindow(usernameTextField.getText(), Integer.parseInt(idTextField.getText()), timerCounter);
        }
        playButtonClicked = true;
    }
    private boolean checkId(){
        if (idTextField.getText().isEmpty()) {
            return false;
        }
        if (idTextField.getText().length() > 10) {
            return false;
        }
        try{
            Integer.parseInt(idTextField.getText());
            return true;
        }
        catch (NumberFormatException e){
            return false;
        }
    }
}
