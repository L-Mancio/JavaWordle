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
    protected JPanel panel;
    protected Wordle wordle;
    protected int wordlen;
    protected int row = 0;
    protected ArrayList<JTextField> enabledTextFields = new ArrayList<>();

    public MyActionListener(JFrame frame, JPanel panel, Wordle wordle, int wordlen) {
        this.frame = frame;
        this.panel = panel;
        this.wordle = wordle;
        this.wordlen = wordlen;
    }

    public void actionPerformed(ActionEvent evt) {
        String cmd = evt.getActionCommand();
        JTextField currTf = (JTextField) frame.getFocusOwner();
        if(currTf.isFocusable()){
            if(cmd.equals("Enter")){
                //ArrayList<JTextField> enabledTextFields = new ArrayList<>();
                String wordleString = getWordleGuess();
                assert wordleString != null;
                int guessLen = wordleString.length();
                if(guessLen == wordlen){
                    if(wordleString.equals(wordle.wordle)){
                        correctGuess(enabledTextFields, frame);
                    }
                    else{
                         incorrectGuess(enabledTextFields, frame, wordle);
                    }
                    disableCurrFields();
                    //enableNextFields();
                    //disable current component enable next one return next row number so i can enable next components
                }
                else{
                    incompleteGuess(enabledTextFields);
                    System.out.println("Wordle incomplete " + wordleString.length());
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


        // append the cmd value to your out put...

    }
    public String getWordleGuess(){
        StringBuilder sb = new StringBuilder();
        String wordleString = null;
        for(Component c: panel.getComponents()){
            if(c.isEnabled()){
                JPanel row = (JPanel) c;
                for(Component jtxt: row.getComponents()){
                    JTextField jtf = (JTextField) jtxt;
                    enabledTextFields.add(jtf);
                    sb.append(jtf.getText());
                }

                wordleString = sb.toString();
            }

        }

        return wordleString;
    }
    public void enableNextFields(){
        for(JTextField jtf: wordle.textFields.get(row)){
            System.out.println(jtf.getText());
        }

    }
    public void disableCurrFields(){
        //System.out.println(Arrays.asList(wordle));

    }


    public static void correctGuess(ArrayList<JTextField> jTextFields, JFrame frame){
        for(JTextField jtf: jTextFields){
            jtf.setBackground(Color.GREEN);
            jtf.setEnabled(false);
        }
        Message correctMessage = new Message("You Win");
        JPanel correctMessagePanel = correctMessage.generateMessage();
        frame.getContentPane().add(correctMessagePanel, BorderLayout.AFTER_LINE_ENDS);
        //make all text fields green
        //disable all textfields
        //display winning Message
    }
    public static void incorrectGuess(ArrayList<JTextField> jTextFields, JFrame frame, Wordle wordle){

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
    public static void incompleteGuess(ArrayList<JTextField> jTextFields){
        for(JTextField jtf: jTextFields){
            if(jtf.getText().isEmpty()){
                jtf.setBackground(Color.YELLOW);
                jtf.setBackground(Color.WHITE);
            }

        }
    }
}
