import java.util.*;
public class M {

    // random choices from lists
    public static String choose(String[] s){
        return s[ (int) (Math.random()*(s.length))   ];
    }

    public static int choose(int[] s){
        return s[ (int) (Math.random()*(s.length))   ];
    }

    public static String choose(ArrayList<String> s){
        return s.get((int)(Math.random()*(s.size())));
    }

    // choose with multiplying probabilities
    public static String choose(String[] s, double multiplier) {
        ArrayList<String> probs = new ArrayList<String>();
        double currentProb = 1;
        for (int i = s.length - 1; i >= 0; i--) {
            for (int x = 0; x < currentProb; x++) {
                probs.add(s[i]);
            }
            currentProb *= multiplier;
        }
        return choose(probs);
    }

    // find the index of an item in an array by casting as ArrayList
    public static int find(String[] s, String item) {
        return Arrays.asList(s).indexOf(item);
    }

    // make numbered list of the input's items
    public static String getList(ArrayList<String> options) {
        String result = "1: " + options.get(0);
        for (int i = 1; i < options.size(); i++) {
            result += ", " + (i + 1) + ": " + options.get(i);
        }
        return result;
    }

    public static String getOptions(ArrayList<Planet> options) {
        String result = "1: " + options.get(0).getShortName() + " " + options.get(0).getExploredIcon();
        for (int i = 1; i < options.size(); i++) {
            result += ", " + (i + 1) + ": " + options.get(i).getShortName() + " " + options.get(i).getExploredIcon();
        }
        return result;
    }

    // checks if the input value is some form of a "yes" (for user y/n questions)
    public static boolean equalsY(String str) {
        return (str.equalsIgnoreCase("y") || str.equalsIgnoreCase("ye") || str.equalsIgnoreCase("yes"));
    }
}
