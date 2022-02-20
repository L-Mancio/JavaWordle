import javax.swing.*;
import java.util.ArrayList;

public class JFrameFunctions {
    protected JFrame frame;

    public JFrameFunctions(JFrame frame){
        this.frame = frame;
    }
    public String getCompleteWordle(){
        StringBuilder sb = new StringBuilder();

        JTextField currTf = (JTextField) frame.getFocusOwner();
        System.out.println(sb.toString());
        return sb.toString();
    }
}
