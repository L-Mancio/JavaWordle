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
        message.setBackground(Color.GREEN);
        messagePanel.add(message);

        return messagePanel;
    }
    public void hideMesagePanels(){

    }
}
