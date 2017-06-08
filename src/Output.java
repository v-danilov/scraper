import java.util.ArrayList;
import java.util.List;

public class Output {
    private  String resource;
    private String key_word;
    private  int words;
    private int chars;
    private List<String> match;

    public Output() {
        resource = "";
        key_word = "";
        words = 0;
        chars = 0;
        match = new ArrayList<String>();
    }

    public Output(String resource, String key_word, int words, int chars, List<String> match) {
        this.resource = resource;
        this.key_word = key_word;
        this.words = words;
        this.chars = chars;
        this.match = match;
    }

    public void addMatch(String s){
        match.add(s);
    }

    public String getKey_word() {
        return key_word;
    }

    public void setKey_word(String key_word) {
        this.key_word = key_word;
    }

    public int getWords() {
        return words;
    }

    public void setWords(int words) {
        this.words = words;
    }

    public int getChars() {
        return chars;
    }

    public void setChars(int chars) {
        this.chars = chars;
    }

    public List<String> getMatch() {
        return match;
    }

    public void setMatch(List<String> match) {
        this.match = match;
    }

    public void printResults(boolean wFlag, boolean cFlag, boolean eFlag){
        System.out.println("Web site: " + resource);
        System.out.println("Key word: " + key_word);
        if(wFlag){
            System.out.println("Number of words: " + words);
        }
        if(cFlag){
            System.out.println("Total number of characters: " + chars);
        }
        if(eFlag){
            System.out.println("Strings containing key words: ");
            for(String m : match)
            {
                System.out.println(m);
            }
        }
        System.out.println("----------------------------------");
    }
}
