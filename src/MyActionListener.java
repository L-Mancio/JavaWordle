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

    protected int row = 0;
    protected ArrayList<JTextField> enabledTextFields;

    public MyActionListener(JFrame frame, Wordle wordle) {
        this.frame = frame;
        this.wordle = wordle;

    }

    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        JTextField currTf = (JTextField) frame.getFocusOwner();
        if(currTf.isFocusable()){
            if(cmd.equals("Enter")){
                //ArrayList<JTextField> enabledTextFields = new ArrayList<>();
                String wordleGuess = getWordleGuess();

                assert wordleGuess != null;
                int guessLen = wordleGuess.length();
                enabledTextFields = wordle.textFields.get(row);

                if(guessLen == wordle.wordle.length()){
                    if(wordleGuess.equals(wordle.wordle)){
                        correctGuess(enabledTextFields, frame);
                        disableRemFields(row+1);
                        //restart game
                    }
                    else{
                        incorrectGuess(enabledTextFields, wordle);
                        if(row == wordle.rows - 1){
                            //create red restart button
                        }
                        enableNextFields();
                        currTf.transferFocus();
                    }

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

    public void enableNextFields(){
        for(JTextField jtf: wordle.textFields.get(row+1)){
            jtf.setEnabled(true);
        }

    }
    public void disableRemFields(int row){
        for(int i=row; i<wordle.rows; i++){
            for(JTextField jtf: wordle.textFields.get(i)){
                jtf.setBackground(Color.GRAY);
                jtf.setEnabled(false);
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
}
