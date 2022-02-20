import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Dictionary {
    protected String dictionaryPath;
    protected String nounlistPath;
    protected Map dictionary;
    protected ArrayList<String> words;
    protected HashSet<String> nounset = new HashSet<>();;

    public Dictionary(String dictionaryPath, String nounlistPath) throws IOException {
        this.dictionaryPath = dictionaryPath;
        this.nounlistPath = nounlistPath;
        initialize();
    }
    public void initialize() throws IOException {
        dictionary = getDictionaryTable();
        words = (ArrayList<String>) dictionary.keySet().stream().collect(Collectors.toList());
        nounset.addAll(getNounList());

    }
    public Map getDictionaryTable() throws IOException {
        Gson gson = new Gson();

        // create a reader
        Reader reader = Files.newBufferedReader(Paths.get(dictionaryPath));

        return gson.fromJson(reader, Map.class);
    }
    public ArrayList<String> getNounList() throws IOException {
        BufferedReader bufReader = new BufferedReader(new FileReader(nounlistPath));
        ArrayList<String> nounlist = new ArrayList<>();
        String line = bufReader.readLine();
        while (line != null) {
            nounlist.add(line); line = bufReader.readLine();
        }
        bufReader.close();

        return nounlist;
    }

    public String getRandomWord(int wordleLength){
        ArrayList<String> validWords = new ArrayList<>();

        for(String word : words){
            if(word.length() == wordleLength){
                validWords.add(word);
            }
        }

        Random generator = new Random();
        int randInt = generator.nextInt(validWords.size());//(int) Math.floor(Math.random()*(validWords.size()));
        String validWord = validWords.get(randInt);
        /*
        this could be interesting to implement, check whether the word is a noun or not otherwise pick another one, maybe use oxford api

        while (!nounset.contains(validWord)){
            randInt = generator.nextInt(validWords.size());
            validWord = validWords.get(randInt);
        }
        */
        return validWord.toUpperCase();

    }
    public static void main(String[] args) throws IOException {

        Dictionary d = new Dictionary("S:\\Github Projects\\Wordle\\dictionary.json", "S:\\Github Projects\\Wordle\\nounlist.txt");
        System.out.println(d.getRandomWord(5));
    }
}
