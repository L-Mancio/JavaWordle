import java.util.Arrays;
import java.util.HashSet;

public class WordleBool {
    String modBool;
    HashSet<String> possibleValues = new HashSet<>(Arrays.asList("True", "False", "Partly"));

    public WordleBool(String modBool) throws Exception {
        if(possibleValues.contains(modBool)){
            this.modBool = modBool;
        }
        else{
            throw new Exception("Not a valid modified bool! [\"True\", \"False\", \"Partly\"]");
        }

    }

    public String getModBool() {
        return modBool;
    }

    @Override
    public String toString() {
        return modBool;
    }


}
