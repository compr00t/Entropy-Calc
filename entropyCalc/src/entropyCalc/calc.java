package entropyCalc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class calc {

    public static void main(String[] args) throws IOException {
        
        if(args.length == 0)
        {
            System.out.println("Proper Usage is: calc \"\\path\\to\\file\"");
            System.exit(0);
        }
        
        int index = 0;
        int amount = 0;
        String path = args[0];
        
        String rawInput = readFile(path);
        amount = rawInput.length()/100;
        
        List<String> strings = cutString(index, rawInput, amount);
        
        System.out.println("Calculating Entoropy for " + path + ":");
        System.out.println("");
        
        for (String str : strings) {
            System.out.println("[" + ++index + "] " + Entropy.getShannonEntropy(str));
        }
    }

    private static List<String> cutString(int index, String rawInput, int amount) {
        List<String> strings = new ArrayList<String>();
        
        while (index < rawInput.length()) {
            strings.add(rawInput.substring(index, Math.min(index + amount, rawInput.length())));
            index += amount;
        }
        return strings;
    }

    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }

}
