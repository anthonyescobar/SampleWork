import java.io.*;
import java.util.*;
/**
This program takes a file from standard input and produces an alphabetized inventory of its words, 
with their frequencies, on standard output. It uses a HashMap to record individual words and their 
frequencies. 

(1) When forming words, only alphanumeric characters (letters and digits) count- 
ignore all commas, semi-colons and colons, hyphens, apostrophes, etc. 

(2) By default, all letters should be converted to upper-case; however, 
if case SENSITIVITY is desired, then the program should be run with the -s option. 

(3) Hyphens should be ignored. For example, all of the following will be counted as CATS: 
CATS, cats, cAtS, cat-s, C-at--s-, -c-AT----S (unless, of course, the -s option is in effect). 

(4) Except or hyphens ad apostrophes, blanks and all non-alphanumeric characters are presumed to be 
delimiters. For example, MARYS and MARY'S are equivalent, whereas MARY:SHE and MARY,SHE represent 
the pair of words MARY and SHE. 

(5) Frequencies should outputted along with the strings. 
If CLEAN output (i.e., without frequencies) is desired, then the program should be run with the -c option.
*/
public class WordFrequencies {
    
    public WordFrequencies() {
    }

    public static void main(String args[]) {
        boolean s = false; // Case Sensitivity
        boolean c = false; // Clean Output
        HashMap<String, Integer> hashmap = new HashMap<String, Integer>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        if (args.length == 1 && args[0].charAt(0) == '-') {
            for (int i = 0; i < args[0].length(); i++) {
                if (args[0].charAt(i) == 's') {
                    s = true;
                } else if (args[0].charAt(i) == 'c') {
                    c = true;
                }
            }
        }
        try {
            String line = reader.readLine();    
            while (line != null) {
                line = line.replaceAll("[^a-zA-Z0-9\\s-]", " "); 
                    // regular expression to search and delete non alphanumeric characters except spaces
                    // ^ (not these characters:) a-zA-Z0-9 (or) \\s (spaces)
                line = line.replaceAll("  ", " "); // fixes extra spaces
                String[] container = line.split(" ");
                if (s) {
                    for (int i = 0; i < container.length; i++) {
                        if (hashmap.containsKey(container[i])) {
                            int value = hashmap.get(container[i]);
                            hashmap.put(container[i], value + 1);
                        } else {
                            hashmap.put(container[i], 1);
                        }
                    }
                } else {
                    for (int i = 0; i < container.length; i++) {
                         container[i] = container[i].toUpperCase();
                        System.out.println("... " + container[i]);
                        if (hashmap.containsKey(container[i])) {
                            int value = hashmap.get(container[i]);
                            hashmap.put(container[i], value + 1);
                        } else {
                            hashmap.put(container[i], 1);
                        }
                    }
                }
                line = reader.readLine();
            }
            TreeMap<String, Integer> alphabetizedMap = new TreeMap<String, Integer>(hashmap);
            for (Map.Entry<String, Integer> in : alphabetizedMap.entrySet()) {
                String key = in.getKey();
                Integer value = in.getValue();
                if (c) {
                    System.out.println(key);
                } else {
                    System.out.println(key + " " + value);
                }
            }
        } catch(java.io.IOException ioe) {
            System.out.println("IOException");
        }
    }
}