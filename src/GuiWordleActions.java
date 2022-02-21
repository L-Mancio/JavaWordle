import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

public class GuiWordleActions {
    public static String getWordleGuess(ArrayList<JTextField> jTFields){
        StringBuilder sb = new StringBuilder();
        String wordleString = null;

        for(JTextField jtf: jTFields){
            sb.append(jtf.getText());
        }
        wordleString = sb.toString();

        return wordleString;
    }

    public static void enableFields(ArrayList<JTextField> toEnable){
        for(JTextField jtf: toEnable){
            jtf.setEnabled(true);
            jtf.setBackground(Color.WHITE);
            jtf.setText("");
        }

    }
    public static void disableRemFields(HashMap<Integer,ArrayList<JTextField>> fullWordlePanel, int presentRows, int currRow){
        for(int i=currRow; i<presentRows; i++){
            for(JTextField jtf: fullWordlePanel.get(i)){
                jtf.setBackground(Color.GRAY);
                jtf.setEnabled(false);
                jtf.setText("");
            }
        }
    }

    public static void displayResultPanel(ArrayList<JTextField> jTextFields, String messageResult, JFrame frame, JPanel resultPanel){

        for(JTextField jtf: jTextFields){
            if(messageResult.equals("You Win")){
                jtf.setBackground(Color.GREEN);
                jtf.setEnabled(false);
            }
            else if(messageResult.equals("You Lose")){
                jtf.setBackground(Color.GRAY);
                jtf.setEnabled(false);
            }

        }

        Message message = new Message(messageResult);
        resultPanel = message.generateMessage();


        JPanel finalResultPanel = resultPanel;
        Timer timer = new Timer(2500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(finalResultPanel);
                //SwingUtilities.windowForComponent(resultPanel).dispose();
                SwingUtilities.updateComponentTreeUI(frame);
            }
        });

        frame.getContentPane().add(resultPanel, BorderLayout.LINE_START);
        SwingUtilities.updateComponentTreeUI(frame);
        timer.start();



    }
    public static void wrongGuess(ArrayList<JTextField> jTextFields, JFrame frame, JPanel correct, JPanel wrong, JPanel incomplete){
        for(JTextField jtf: jTextFields){
            jtf.setBackground(Color.GREEN);
            jtf.setEnabled(false);
        }

        Message correctMessage = new Message("You Lose");
        correct = correctMessage.generateMessage();
        frame.getContentPane().remove(wrong);
        frame.getContentPane().remove(incomplete);
        frame.getContentPane().add(correct, BorderLayout.AFTER_LINE_ENDS);

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
    public static void restartGame(Wordle wordle, Dictionary dictionary){
        //use dictionary to get new word
        //set change wordle string of wordle object with new random word
        //remove text and disable all jtextfields except first

        String newWord = dictionary.getRandomWord(wordle.wordle.length());
        System.out.println(newWord);
        wordle.changeWordle(newWord);
        enableFields(wordle.textFields.get(0));
        disableRemFields(wordle.textFields, wordle.rows, 1);
        wordle.textFields.get(0).get(0).requestFocus();

    }
    public static void guiGreen(Wordle wordle){
        for(int i=0; i<wordle.rows; i++){
            for(JTextField jtf: wordle.textFields.get(i)){
                jtf.setBackground(Color.GREEN);
            }
        }
    }
}
