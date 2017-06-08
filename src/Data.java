import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<String> urls;
    private List<String> key_words;

    private boolean verbose_flag;
    private boolean words_number_flag;
    private boolean character_flag;
    private boolean extract_flag;

    public Data(){
        urls = new ArrayList<String>();
        key_words = new ArrayList<String>();
        verbose_flag = false;
        words_number_flag = false;
        character_flag = false;
        extract_flag = false;
    }

    public void addUrl(String url_string){
        urls.add(url_string);
    }

    public  void addKeyWord(String word_string){
        key_words.add(word_string);
    }

    public List<String> getUrls() {
        return urls;
    }

    public void setUrls(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getKey_words() {
        return key_words;
    }

    public void setKey_words(List<String> key_words) {
        this.key_words = key_words;
    }

    public boolean isVerbose_flag() {
        return verbose_flag;
    }

    public void setVerbose_flag(boolean verbose_flag) {
        this.verbose_flag = verbose_flag;
    }

    public boolean isWords_number_flag() {
        return words_number_flag;
    }

    public void setWords_number_flag(boolean words_number_flag) {
        this.words_number_flag = words_number_flag;
    }

    public boolean isCharacter_flag() {
        return character_flag;
    }

    public void setCharacter_flag(boolean character_flag) {
        this.character_flag = character_flag;
    }

    public boolean isExtract_flag() {
        return extract_flag;
    }

    public void setExtract_flag(boolean extract_flag) {
        this.extract_flag = extract_flag;
    }
}
