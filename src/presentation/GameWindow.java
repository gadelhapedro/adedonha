package presentation;

import database.persistence.CategoriasDAO;
import database.persistence.LetrasDAO;
import rule.Categorias;
import rule.Participante;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class GameWindow extends JFrame {
    private JLabel letterLabel;
    private JPanel letterPanel;
    // pessoa, carro, lugar, fruta, objeto, animal
    private CategoryField pessoaFild;
    private CategoryField carroFild;
    private CategoryField cidadeEstadoPaisFild;
    private CategoryField frutaFild;
    private CategoryField objetoFild;
    private CategoryField animalFild;
    private JPanel categoryPanel;
    private ArrayList<String> letters;
    private String currentLetter;
    private JButton submitButton;
    private JPanel submitPanel;
    private Participante participante;

    private java.util.Timer timer;
    private TimerTask task;
    private JProgressBar progressBar;
    private JPanel progressPanel;
    private int timerCounter;
    private int timerMax;
    private int errors;
    private String[] errorsMessages;

    public GameWindow(String username, int id, int timerMax) {
        super("Pense rápido, " + username);
        this.timerMax = timerMax;
        this.timerCounter = timerMax;
        participante = new Participante(id);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(570, 285);

        addComponents();
        addLayout();
        addEvents();

        init();

        setVisible(true);
    }
    private void init() {
        letters = LetrasDAO.getLetras();
        loadLetter(letters.get(0));

    }
    private void addComponents() {
        letterLabel = new JLabel();
        letterPanel = new JPanel();
        pessoaFild = new CategoryField("Pessoa", "P");
        carroFild = new CategoryField("Carro", "C");
        cidadeEstadoPaisFild = new CategoryField("Lugar", "E");
        frutaFild = new CategoryField("Fruta", "F");
        objetoFild = new CategoryField("Objeto", "O");
        animalFild = new CategoryField("Animal", "A");
        categoryPanel = new JPanel();
        submitButton = new JButton("Enviar");
        submitPanel = new JPanel();
        progressBar = new JProgressBar();
        progressBar.setMaximum(timerMax);
        progressPanel = new JPanel();


        errors = 0;
        errorsMessages = new String[]{"Depressa!!!", "O tempo está passando!!", "Você consegue!!", "Pense mais rápido",
                "Você está perdendo tempo lendo isso", "Tem mais algumas pela frente", "Não é tão difícil", "Todos estão esperando",
                "Estamos quase lá", "Você tentou.", "Na próxima vai ser mais fácil", "Tempo é o bem mais precioso",
                "Quase no fim","Golpe de misericordia"};

        addTask();

        letterPanel.add(letterLabel);
        JButton playButton = new JButton("Jogar");

        categoryPanel.add(pessoaFild);
        categoryPanel.add(carroFild);
        categoryPanel.add(cidadeEstadoPaisFild);
        categoryPanel.add(frutaFild);
        categoryPanel.add(objetoFild);
        categoryPanel.add(animalFild);
        progressPanel.add(progressBar);
        submitPanel.add(submitButton);

        add(letterPanel);
        add(categoryPanel);
        add(progressPanel);
        add(submitPanel);

    }
    private void addLayout() {
//        setLayout(new GridLayout(2, 1));
        setLayout(new FlowLayout());
        categoryPanel.setLayout(new GridLayout(3, 2, 0, 10));
        progressBar.setValue(100);

    }

    private void addTask() {
        task = new TimerTask() {
            @Override
            public void run() {
                if (timerCounter > 0) {
                    progressBar.setValue(timerCounter);
                    timerCounter--;
                } else {
                    timeUp();
                }
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(task, 0, 100);
    }
    private void addEvents() {
        submitButton.addActionListener(e -> submitClicked());
    }
    private void submitClicked() {
        resetToNextLetter();
    }
    private void timeUp() {
        resetToNextLetter();
        letterLabel.setText(letterLabel.getText() + ", " + errorsMessages[errors]);
        errors ++;
    }
    private void resetToNextLetter(){
        CategoriasDAO.insert(getCategorias());

        if (letters.size() > letters.indexOf(currentLetter) + 1 ) {
            loadLetter(letters.get(letters.indexOf(currentLetter) + 1));
            progressBar.setValue(timerMax);
            timerCounter = timerMax;
        } else {
            JOptionPane.showMessageDialog(this, "Fim do jogo");
            System.exit(0);
        }

    }
    private Categorias getCategorias() {
        Categorias categoria = new Categorias();
        categoria.setPessoa(pessoaFild.getAnswer());
        categoria.setCarro(carroFild.getAnswer());
        categoria.setCidadeEstadoPais(cidadeEstadoPaisFild.getAnswer());
        categoria.setFruta(frutaFild.getAnswer());
        categoria.setObjeto(objetoFild.getAnswer());
        categoria.setAnimal(animalFild.getAnswer());
        categoria.setLetraSorteada(letters.indexOf(currentLetter) + 1);
        categoria.setParticipante(participante);
        return categoria;
    }
    private void loadLetter(String letter) {
        letterLabel.setText("A Letra é " + letter);
        currentLetter = letter;
        pessoaFild.setLetter(letter);
        carroFild.setLetter(letter);
        cidadeEstadoPaisFild.setLetter(letter);
        frutaFild.setLetter(letter);
        objetoFild.setLetter(letter);
        animalFild.setLetter(letter);
    }

}
