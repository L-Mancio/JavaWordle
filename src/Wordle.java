import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Wordle {
    protected int rows;
    protected int columns;
    protected String wordle;
    protected JFrame frame;

    protected HashMap<String, String> dictionary;
    protected HashSet<Character> wordleChars;
    protected HashMap<Integer, ArrayList<JTextField>> textFields;
    protected HashMap<Integer, JPanel> textPanels;



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

                textField.addKeyListener(new KeyAdapter(){
                    @Override
                    public void keyTyped(KeyEvent e) {
                        if(Character.isLetter(e.getKeyChar())){
                            textField.setText(Character.toString(e.getKeyChar()).toUpperCase());
                            textField.transferFocus();
                            e.consume();
                        }
                    }
                    @Override
                    public void keyPressed(KeyEvent e) {
                         if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                         {
                             textField.setText("");
                             textField.transferFocusBackward();
                             e.consume();
                         }
                     }
                });

                tempTextList.add(textField);
                pRow.add(textField);

            }

            textPanels.put(row, pRow);
            textFields.put(row, tempTextList);
            wordle.add(pRow, c);
        }

        wordle.setLayout(new GridLayout(6,6));

        return wordle;
    }

    public void changeWordle(String newWord){

        HashSet<Character> wordleCharSet = new HashSet<>();
        wordle = newWord;
        for(char c : wordle.toCharArray()){
            wordleCharSet.add(c);
        }
        wordleChars = wordleCharSet;

    }





}
