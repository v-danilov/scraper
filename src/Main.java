import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        Data data = new Data();

        //Check number of arguments
        if (args.length < 2 || args.length > 6) {
            System.err.println("Check arguments: {url|path} {key words} [-v] [-w] [-c] [-e]");
            return;
        } else {

            //Get url from input data
            data.setUrls(getUrl(args[0]));

            //Geti key words from input data
            data.setKey_words(getWords(args[1]));

            if (!check_flags(args, data)) {
                System.err.println("Available flags: [-v] [-w] [-c] [-e]");
                return;
            } else {

            }
        }

        //Scrape
        scrape(data);

        //String str = "Если а < b то это определённо значит, что b > а";
        //str = sa.replaceAll("\\<[^>]*>","");
        //System.out.println(str);

    }

    private static List<String> getUrl(String input) {
        List<String> splitedUrls = new ArrayList<String>();

        //When input string is URL
        if (input.contains("http:\\") || input.contains("www.")) {
            splitedUrls = Arrays.asList(input.split(","));
        }

        //When input string is file
        else {
            try {
                //Read file
                File input_file = new File(input);
                if (input_file.exists()) {
                    BufferedReader br = new BufferedReader(new FileReader(input_file));
                    String str;
                    //Collecting urls
                    while ((str = br.readLine()) != null) {
                        splitedUrls.add(str);
                    }
                }
            } catch (Exception e) {
                System.err.println(e.getStackTrace() + e.getMessage());
            }
        }
        return splitedUrls;
    }

    //Parcing key words from input string
    private static List<String> getWords(String input) {
        return Arrays.asList(input.split(","));
    }

    private static boolean check_flags(String[] args, Data d) {

        String key = "";

        //Definite input flags and agregate it in Data class
        for (int i = 2; i < args.length; ++i) {
            key = args[i];
            if (key.equals("-v")) {
                d.setVerbose_flag(true);
            } else if (key.equals("-w")) {
                d.setWords_number_flag(true);
            } else if (key.equals("-c")) {
                d.setCharacter_flag(true);
            } else if (key.equals("-e")) {
                d.setExtract_flag(true);
            } else {
                return false;
            }
        }
        return true;

    }

    //Scrape
    private static void scrape(Data _data) {
        try {

            //Get flags
            boolean v = _data.isVerbose_flag();
            boolean c = _data.isCharacter_flag();
            boolean w = _data.isWords_number_flag();
            boolean e = _data.isExtract_flag();

            //Note the start time
            long startTime = System.currentTimeMillis();

            //Prepearing arrays
            List<String> res = _data.getUrls();
            List<String> kw = _data.getKey_words();
            List<Output> outputs = new ArrayList<Output>();

            int words = 0;
            int characters = 0;
            BufferedReader in = null;
            List<String> matches = new ArrayList<String>();

            //URLs loop
            for (String u : res) {

                //Creating URL
                URL url = new URL(u);

                //Key words loop
                for (String wd : kw) {

                    //Creating reader
                    in = new BufferedReader(new InputStreamReader(url.openStream()));
                    String str;

                    //Creating pattern to seach key word
                    Pattern p = Pattern.compile("\\b" + wd + ".?\\b", Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);

                    //Start reading data from url
                    while ((str = in.readLine()) != null) {

                        //Replace tag to "" (we can have some problems here)
                        //example: "e.g. if а < b then it definitely means that b > а";
                        str = str.replaceAll("\\<[^>]*>", "");

                        //If character flag is true counting total number of characters
                        if (c) {
                            characters += str.length() - 1;
                        }

                        //If words flag is true counting number of words
                        if (w) {

                                Matcher m = p.matcher(str);

                                //If extract flag is true - collecting strings containing key word
                                if (e) {
                                    matches.add(str);
                                }

                                //Counting number of words
                                while (m.find()) {
                                    words++;
                                }
                        }
                    }

                    //Add results stats to Output class
                    outputs.add(new Output(u, wd, words, characters, matches));

                    //Reset counters
                    characters = 0;
                    words = 0;
                }
            }
            in.close();

            //Printing
            for (Output out : outputs) {
                out.printResults(_data.isWords_number_flag(), _data.isCharacter_flag(), _data.isExtract_flag());
            }

            //Stop timers
            long stopTime = System.currentTimeMillis();
            long elapsedTime = (stopTime - startTime)/1000;

            //Printing time spent for scraping if verbose flag is true
            if (_data.isVerbose_flag()) {
                System.out.println("Time for execution: " + elapsedTime + "(s)");
            }
        } catch (Exception e) {
            System.err.println(e.getStackTrace() + "\n" + e.getMessage());
        }
    }
}
