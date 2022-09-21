import java.util.ArrayList;
import java.io.*;
import java.util.*;

/**
 * VowelPercent Program
 *
 * This program reads a list of U.S. state names from a file called
 * "states.txt".
 *
 * It then:
 * 1. Stores each state name in an ArrayList.
 * 2. Counts how many characters in each state name are vowels (a, e, i, o, u).
 * 3. Calculates the percentage of vowels in each state’s name.
 * 4. Prints the vowel percentage for each state.
 * 5. Identifies the state with the lowest vowel percentage.
 *
 * The goal is to show how vowel-heavy or vowel-light each state name is.
 */
public class VowelPercent {

  public VowelPercent() {
    // Stores state names
    ArrayList<String> states = new ArrayList<String>();
    // Stores percentage of vowels per state
    ArrayList<Double> percentage = new ArrayList<Double>();

    File name = new File("states.txt"); // Input file containing state names

    double count = 0; // Counts vowels per state
    double minNum = Double.MAX_VALUE; // Track the lowest vowel percentage
    int minIndex = -1; // Index of the state with the lowest vowel percentage
    int index = 0; // Index tracker while reading states

    try {
      BufferedReader input = new BufferedReader(new FileReader(name));
      String text;

      // Read each state name line by line
      while ((text = input.readLine()) != null) {
        states.add(text);
        index++;

        // Count vowels in the state name
        for (int i = 0; i < text.length(); i++) {
          String ch = text.substring(i, i + 1);
          if (ch.equalsIgnoreCase("a") || ch.equalsIgnoreCase("e") ||
              ch.equalsIgnoreCase("i") || ch.equalsIgnoreCase("o") ||
              ch.equalsIgnoreCase("u")) {
            count++;
          }
        }

        // Calculate percentage of vowels
        double percent = count / text.length();
        percentage.add(percent);

        // Update lowest vowel percentage if this state has fewer
        if (percent < minNum) {
          minNum = percent;
          minIndex = index - 1; // adjust since ArrayList is 0-based
        }

        // Reset vowel count for next state
        count = 0.0;
      }
    } catch (IOException io) {
      System.err.println("Error reading file => " + io);
    }

    // Print vowel percentages for all states
    for (int x = 0; x < percentage.size(); x++) {
      System.out.println(states.get(x) + " -> " + percentage.get(x));
    }

    // Print state with lowest vowel percentage
    if (minIndex >= 0) {
      System.out.println("\nThe state with the lowest percentage of vowels is "
          + states.get(minIndex) + " with "
          + percentage.get(minIndex));
    }
  }

  // Main method to run the program
  public static void main(String[] args) {
    VowelPercent app = new VowelPercent();
  }
}
