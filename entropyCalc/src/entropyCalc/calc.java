package entropyCalc;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class calc {

    public static void main(String[] args) throws IOException {

        if (args.length < 2) {
            System.out.println("Proper Usage is: calc \"/path/to/file\" numb_of_parts");
            System.exit(0);
        }

        double filter = 999;
        int split = Integer.parseInt(args[1]);

        if (args.length == 3) {
            filter = Integer.parseInt(args[2]);
        }

        int index = 0;
        int amount = 0;

        double minimalEntorpy = 10;
        double maximalEntropy = 0;
        double averageEntropy = 0;

        String path = args[0];

        String rawInput = readFile(path);
        amount = rawInput.length() / split;

        averageEntropy = Entropy.getShannonEntropy(rawInput);

        List<String> strings = cutString(index, rawInput, amount);

        System.out.println("Calculating Entoropy for " + path + ":");
        System.out.println();

        for (String str : strings) {
            double entorpy = Entropy.getShannonEntropy(str);

            if (minimalEntorpy >= entorpy) {
                minimalEntorpy = entorpy;
            }
            if (maximalEntropy <= entorpy) {
                maximalEntropy = entorpy;
            }
            
            int startIndex = ((++index-1) * amount + 1);

            if (filter == 999) {
                System.out.println("[0x" + Integer.toHexString(startIndex) + "] " + entorpy);
            } else if (entorpy <= filter){
                System.out.println("[0x" + Integer.toHexString(startIndex) + "] " + entorpy);
            }
        }

        System.out.println();
        System.out.println("[Minimum] " + minimalEntorpy);
        System.out.println("[Maximum] " + maximalEntropy);
        System.out.println("[Complete] " + averageEntropy);
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
