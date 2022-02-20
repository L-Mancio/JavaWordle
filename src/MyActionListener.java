import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.*;

public class MyActionListener implements ActionListener {
    protected JFrame frame;
    protected Wordle wordle;
    protected Dictionary dictionary;

    protected int row = 0;
    protected ArrayList<JTextField> enabledTextFields;

    protected JPanel messagePanel;

    public MyActionListener(JFrame frame, Wordle wordle, Dictionary dictionary) {
        this.frame = frame;
        this.wordle = wordle;
        this.dictionary = dictionary;
    }

    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        JTextField currTf = (JTextField) frame.getFocusOwner();
        //System.out.println(cmd);
        if(currTf.isFocusable()){
            if(cmd.equals("Enter")){
                String wordleGuess = GuiWordleActions.getWordleGuess(wordle.textFields.get(row));
                System.out.println("You guessed: " + wordleGuess);


                int guessLen = wordleGuess.length();

                if(guessLen == wordle.wordle.length()){
                    if(wordle.isCorrectWordle(wordleGuess)){
                        GuiWordleActions.displayResultPanel(wordle.textFields.get(row),"You Win", frame, messagePanel);
                        GuiWordleActions.restartGame(wordle, dictionary);
                        row = 0;
                    }
                    else{
                        GuiWordleActions.incorrectGuess(wordle.textFields.get(row), wordle);
                        if(row == wordle.rows - 1){
                            GuiWordleActions.displayResultPanel(wordle.textFields.get(row),"You Lose", frame, messagePanel);
                            GuiWordleActions.restartGame(wordle, dictionary);
                            row = 0;
                        }
                        else{
                            GuiWordleActions.enableFields(wordle.textFields.get(row+1));
                            row += 1;
                        }

                    }
                    currTf.transferFocus();
                }
                else{
                    GuiWordleActions.incompleteGuess(wordle.textFields.get(row));
                    GuiWordleActions.displayResultPanel(wordle.textFields.get(row),"Incomplete", frame, messagePanel);
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

}
