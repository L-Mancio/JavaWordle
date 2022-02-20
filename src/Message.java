import javax.swing.*;
import java.awt.*;

public class Message {
    protected String message;

    public Message(String message){
        this.message = message;
    }

    public JPanel generateMessage(){
        JPanel messagePanel = new JPanel();
        JButton message = new JButton(this.message);
        if(this.message.equals("You Win")){
            message.setBackground(Color.GREEN);
        }
        else if(this.message.equals("You Lose")){
            message.setBackground(Color.RED);
        }
        else{
            message.setBackground(Color.CYAN);
        }
        messagePanel.add(message);

        return messagePanel;
    }
    public void hideMesagePanels(){

    }
}
