import sun.awt.ConstrainableGraphics;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

class Gui{

    public static void main(String args[]) throws Exception {
        //create frame
        JFrame frame = new JFrame("Wordle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,800);

        //Creating the MenuBar and adding components
        JMenuBar mb = new JMenuBar();
        JMenu m1 = new JMenu("File");
        JMenu m2 = new JMenu("?");
        mb.add(m1);
        mb.add(m2);


        Dictionary d = new Dictionary("PATH TO DICTIONARY", "PATH TO NOUNLIST");
        int wordleLen = 5; //could make this so that it can be changed and also changes gui accordingly
        Wordle w = new Wordle(6, 5, d.getRandomWord(5), frame);
        Keyboard k = new Keyboard(frame);

        System.out.println("Word Chosen: " + w.wordle);

        //create wordle boxes
        JPanel wordlePanel = w.generateWordle();

        //create panel for keyboard buttons
        JPanel keyboard = k.generateKeyboard();

        // add listener to buttons to write on textfields on button press
        MyActionListener listener = new MyActionListener(frame, wordlePanel, w, wordleLen);
        for(JButton jb: k.keyboardButtons) {
            jb.addActionListener(listener);
        }



        frame.getContentPane().add(wordlePanel, BorderLayout.CENTER);
        frame.getContentPane().add(keyboard, BorderLayout.SOUTH);
        frame.getContentPane().add(mb, BorderLayout.NORTH);
        frame.setVisible(true);
    }
}