import java.io.*;
import java.util.*;

/**
 * Apportionment Program
 *
 * This program reads U.S. state population and congressional representation
 * data from a file called "CensusApportionment.txt".
 * 
 * It then:
 * 1. Stores each state’s name, population, and number of representatives.
 * 2. Sorts the states alphabetically.
 * 3. Calculates the population per congressional district for each state.
 * 4. Prints the results for all states.
 * 5. Identifies the state where each representative serves the most people
 * (highest ratio) and the state where each representative serves the fewest
 * people
 * (lowest ratio).
 *
 * The purpose is to highlight the disparity in representation between states.
 */
public class Apportionment {
	public Apportionment() {
		// Lists to hold state names, populations, representatives, and population per
		// district
		ArrayList<String> states = new ArrayList<String>();
		ArrayList<Integer> population = new ArrayList<Integer>();
		ArrayList<Integer> representatives = new ArrayList<Integer>();
		ArrayList<Integer> popPerDistrict = new ArrayList<Integer>();

		// Input file containing census data
		File name = new File("CensusApportionment.txt");

		try {
			BufferedReader input = new BufferedReader(new FileReader(name));
			String text;
			int i = 0;

			// Read the file line by line
			while ((text = input.readLine()) != null) {
				if (i > 0) // Skip header row if present
				{
					// Handle states with two-word names (e.g., "New York", "West Virginia")
					if (text.split(" ")[0].equals("North") || text.split(" ")[0].equals("South")
							|| text.split(" ")[0].equals("Rhode") || text.split(" ")[0].equals("New")
							|| text.split(" ")[0].equals("West")) {
						states.add(text.split(" ")[0] + " " + text.split(" ")[1]);
						String temp = text.split(" ")[2];
						population.add(Integer.parseInt(temp.replace(",", "")));
						representatives.add(Integer.parseInt(text.split(" ")[3]));
					} else {
						// Handle states with single-word names
						states.add(text.split(" ")[0]);
						String temp = text.split(" ")[1];
						population.add(Integer.parseInt(temp.replace(",", "")));
						representatives.add(Integer.parseInt(text.split(" ")[2]));
					}
				}
				i++;
			}
		} catch (IOException io) {
			System.err.println("Error reading file => " + io);
		}

		// Sort states alphabetically along with their populations and representatives
		for (int x = 0; x < states.size(); x++) {
			for (int y = 0; y < states.size(); y++) {
				if (states.get(x).compareTo(states.get(y)) < 0) {
					// Swap state names
					String temp = states.get(x);
					states.set(x, states.get(y));
					states.set(y, temp);

					// Swap populations
					int tempPop = population.get(x);
					population.set(x, population.get(y));
					population.set(y, tempPop);

					// Swap representatives
					int tempRep = representatives.get(x);
					representatives.set(x, representatives.get(y));
					representatives.set(y, tempRep);
				}
			}
		}

		// Print population per congressional district
		System.out.println("Population per Congressional District by State:\n");
		for (int z = 0; z < population.size(); z++) {
			int pop = (int) (population.get(z) / representatives.get(z));
			popPerDistrict.add(pop);
			System.out.println(states.get(z) + " -> " + popPerDistrict.get(z) + "\n");
		}

		// Find states with minimum and maximum population per district
		int min = popPerDistrict.get(0);
		int max = popPerDistrict.get(0);
		int ind1 = 0;
		int ind2 = 0;

		for (int k = 1; k < popPerDistrict.size(); k++) {
			if (popPerDistrict.get(k) < min) {
				min = popPerDistrict.get(k);
				ind1 = k;
			}
			if (popPerDistrict.get(k) > max) {
				max = popPerDistrict.get(k);
				ind2 = k;
			}
		}

		// Print disparity message
		System.out.println("A congressman from " + states.get(ind2) +
				" represents " + max + " people, while a congressman from "
				+ states.get(ind1) + " only represents " + min + " people!");
	}

	// Main method to run the program
	public static void main(String[] args) {
		Apportionment app = new Apportionment();
	}
}
