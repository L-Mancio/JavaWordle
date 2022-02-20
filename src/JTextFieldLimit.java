import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class JTextFieldLimit extends PlainDocument {
    protected final int limit;
    protected String[] qwertyRows = {"Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L","Z", "X", "C", "V", "B", "N", "M"};
;
    protected final HashSet<String> validCharacters = Arrays.stream(qwertyRows).collect(Collectors.toCollection(HashSet::new));
    JTextFieldLimit(int limit) {
        super();
        this.limit = limit;
    }

    public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
        if (str == null) return;

        if ((getLength() + str.length()) <= limit) {
            if(validCharacters.contains(str.toUpperCase()))
                super.insertString(offset, str.toUpperCase(), attr);
        }
    }
}
