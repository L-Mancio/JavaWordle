import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MyActionListener implements ActionListener {
    protected JFrame frame;
    protected Wordle wordle;
    protected Dictionary dictionary;

    protected int row = 0;
    protected ArrayList<JTextField> enabledTextFields;

    public MyActionListener(JFrame frame, Wordle wordle, Dictionary dictionary) {
        this.frame = frame;
        this.wordle = wordle;
        this.dictionary = dictionary;
    }

    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        JTextField currTf = (JTextField) frame.getFocusOwner();
        System.out.println(cmd);
        if(currTf.isFocusable()){
            if(cmd.equals("Enter")){
                //ArrayList<JTextField> enabledTextFields = new ArrayList<>();
                String wordleGuess = getWordleGuess();
                System.out.println(wordleGuess);
                assert wordleGuess != null;
                int guessLen = wordleGuess.length();
                //enabledTextFields =

                if(guessLen == wordle.wordle.length()){
                    if(wordleGuess.equals(wordle.wordle)){
                        correctGuess(wordle.textFields.get(row), frame);
                        //disableRemFields(row+1);
                        restartGame(wordle, dictionary);
                        row = 0;
                        //restart game
                    }
                    else{
                        incorrectGuess(wordle.textFields.get(row), wordle);
                        if(row == wordle.rows - 1){
                            wrongGuess(frame);
                            //wrong guess() bottone rosso
                            restartGame(wordle, dictionary);
                            row = 0;
                            //create red restart button
                        }
                        else{
                            enableFields(wordle.textFields.get(row+1));
                            row+=1;
                        }

                    }
                    //row+=1;
                    currTf.transferFocus();

                }
                else{
                    incompleteGuess(enabledTextFields);
                    //create message panel
                }

            }
            else if(cmd.equals("<")){
                currTf.setText("");
                currTf.transferFocusBackward();
            }
            else{
                currTf.setText(cmd);
                currTf.transferFocus();
            }


        }


    }
    public String getWordleGuess(){
        StringBuilder sb = new StringBuilder();
        String wordleString = null;

        for(JTextField jtf: wordle.textFields.get(row)){
            sb.append(jtf.getText());
        }
        wordleString = sb.toString();

        return wordleString;
    }

    public void enableFields(ArrayList<JTextField> toEnable){
        for(JTextField jtf: toEnable){
            jtf.setEnabled(true);
            jtf.setBackground(Color.WHITE);
            jtf.setText("");
        }

    }
    public void disableRemFields(int row){
        for(int i=row; i<wordle.rows; i++){
            for(JTextField jtf: wordle.textFields.get(i)){
                jtf.setBackground(Color.GRAY);
                jtf.setEnabled(false);
                jtf.setText("");
            }
        }
    }

    public static void correctGuess(ArrayList<JTextField> jTextFields, JFrame frame){
        for(JTextField jtf: jTextFields){
            jtf.setBackground(Color.GREEN);
            jtf.setEnabled(false);
        }

        Message correctMessage = new Message("You Win");
        JPanel correctMessagePanel = correctMessage.generateMessage();
        frame.getContentPane().add(correctMessagePanel, BorderLayout.AFTER_LINE_ENDS);

    }
    public static void wrongGuess(JFrame frame){
        Message correctMessage = new Message("You Lose");
        JPanel correctMessagePanel = correctMessage.generateMessage();
        frame.getContentPane().add(correctMessagePanel, BorderLayout.AFTER_LINE_ENDS);

    }
    public static void incorrectGuess(ArrayList<JTextField> jTextFields, Wordle wordle){

        for(int i=0; i<wordle.wordle.length(); i++){
            JTextField curr = jTextFields.get(i);
            if(wordle.wordle.charAt(i) == curr.getText().charAt(0)){
                curr.setBackground(Color.green);

            }
            else if(wordle.wordle.charAt(i) != curr.getText().charAt(0) && wordle.wordleChars.contains(curr.getText().charAt(0))){
                curr.setBackground(Color.YELLOW);

            }
            else{
                curr.setBackground(Color.GRAY);
            }
            curr.setEnabled(false);
        }


    }

    //haven't figured this out yet
    public static void incompleteGuess(ArrayList<JTextField> jTextFields){
        for(JTextField jtf: jTextFields){
            if(jtf.getText().isEmpty()){
                jtf.setBackground(Color.YELLOW);
                jtf.setBackground(Color.WHITE);
            }
            jtf.setBackground(Color.YELLOW);
            jtf.setBackground(Color.WHITE);

        }
    }
    public void restartGame(Wordle wordle, Dictionary dictionary){
        //use dictionary to get new word
        //set change wordle string of wordle object with new random word
        //remove text and disable all jtextfields except first

        String newWord = dictionary.getRandomWord(wordle.wordle.length());
        System.out.println(newWord);
        wordle.changeWordle(newWord);
        enableFields(wordle.textFields.get(0));
        disableRemFields(1);
        wordle.textFields.get(0).get(0).requestFocus();

    }
}
