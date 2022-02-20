import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Wordle {
    protected int rows;
    protected int columns;
    protected HashMap<String, String> dictionary;
    protected String wordle;
    protected HashSet<Character> wordleChars;
    protected HashMap<Integer, ArrayList<JTextField>> textFields;
    protected HashMap<Integer, JPanel> textPanels;
    protected JFrame frame;


    public Wordle(int rows, int columns, String wordle, JFrame frame){
        this.rows = rows;
        this.columns = columns;
        this.wordle = wordle;
        this.frame = frame;

        wordleChars = new HashSet<>();
        for(char c : wordle.toCharArray()){
            wordleChars.add(c);
        }
    }

    public boolean isCorrectWordle(String userGuess){
        return userGuess.equals(wordle);
    }


    public ArrayList<WordleBool> getDifferences(String userGuess) throws Exception {

        //boolean[] letterPosition = new boolean[this.wordle.length()];
        ArrayList<WordleBool> letterPosition = new ArrayList<>();
        char[] wordleArr = this.wordle.toCharArray();
        char[] userGuessArr = userGuess.toCharArray();


        for(int i = 0; i < this.wordle.length(); i++){
            char correctChar = wordleArr[i];
            char  userGuessChar = userGuessArr[i];

            if(correctChar == userGuessChar){
                letterPosition.add(new WordleBool("True"));
            }
            else if(wordleChars.contains(userGuessArr[i])){
                letterPosition.add(new WordleBool("Partly"));
            }
            else letterPosition.add(new WordleBool("False"));
        }

        return letterPosition;
    }



    public JPanel generateWordle(){
        textFields = new HashMap<>();
        textPanels = new HashMap<>();

        JPanel wordle = new JPanel();
        JPanel pRow;
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1d;
        for (int row = 0; row < rows; row++) {
            ArrayList<JTextField> tempTextList = new ArrayList<>();
            pRow = new JPanel();
            c.gridy = row;

            for (int col = 0; col < columns; col++){
                JTextField textField = new JTextField();
                if(row>0){
                    textField.setEnabled(false);
                    pRow.setEnabled(false);
                }
                if(col == columns - 1) textField.setFocusCycleRoot(true);//set not cyclable
                textField.setBackground(Color.WHITE);
                textField.setPreferredSize(new Dimension(50, 60));
                textField.setDocument(new JTextFieldLimit(1));
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setFont(new Font("Monospaced", Font.BOLD, 30));

                textField.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        if(e.getKeyCode() == KeyEvent.VK_ENTER){
                            //stuff for enter
                            //JFrameFunctions jf = new JFrameFunctions(frame);
                            //jf.getCompleteWordle();
                        }
                        else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                        {
                            textField.setText("");
                            textField.transferFocusBackward();
                        }
                        else{
                            textField.transferFocus();
                        }
                        e.consume();
                    }
                });

                tempTextList.add(textField);
                pRow.add(textField);

            }

            textPanels.put(row, pRow);
            textFields.put(row, tempTextList);
            tempTextList.clear();
            wordle.add(pRow, c);
        }

        wordle.setLayout(new GridLayout(6,6));

        return wordle;
    }
    public void monitorWordle(){

    }





}
