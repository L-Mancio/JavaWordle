import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

public class Keyboard {

    protected final String[][] qwertyRows = {
            {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "Backspace"},
            {"A", "S", "D", "F", "G", "H", "J", "K", "L", "Enter"},
            {"Z", "X", "C", "V", "B", "N", "M"}
    };
    protected ArrayList<JButton> keyboardButtons;

    protected JFrame frame;

    public Keyboard(JFrame frame){
        this.frame = frame;
    }


    public JPanel generateKeyboard() throws IOException {
        keyboardButtons = new ArrayList<>();
        JPanel keyboard = new JPanel();
        JPanel pRow;
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1d;



        for (int row = 0; row < qwertyRows.length; ++row) {
            pRow = new JPanel();

            c.gridy = row;

            for (int col = 0; col < qwertyRows[row].length; ++col){
                JButton tempButton;
                if(qwertyRows[row][col].equals("Backspace")){
                    tempButton = new JButton("<");
                    //tempButton.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0), "backspace");
                    //tempButton.getActionMap().put("backspace", (Action) KeyStroke.getKeyStroke(KeyEvent.VK_BACK_SPACE, 0));
                }
                else if(qwertyRows[row][col].equals("Enter")){
                    tempButton = new JButton("Enter");
                    frame.getRootPane().setDefaultButton(tempButton);
                }
                else {
                    tempButton = new JButton(qwertyRows[row][col]);
                    tempButton.setPreferredSize(new Dimension(50, 60));
                }

                tempButton.setBackground(Color.GRAY);
                tempButton.setFocusable(false);
                pRow.add(tempButton);
                keyboardButtons.add(tempButton);


            }


            keyboard.add(pRow, c);

        }

        keyboard.setLayout(new GridLayout(4,3));

        return keyboard;
    }



}
