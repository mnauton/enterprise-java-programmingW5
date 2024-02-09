package tokenizerPrime;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import mainYourPrime.*;

public class Tokenizer {
	
	public static void main(String[] args) throws IOException {

		// TODO use buffered file reader to input data from file, use the Scanner interface
		String path = "/home/margaux/java-workspace/EnterpriseJavaProgrammingW5/src/MovieDataset/metadata.csv";
		try {
			Scanner scan = new Scanner(new BufferedReader(new FileReader(path)));
			scan.useDelimiter("\n");
			scan.nextLine();
			while (scan.hasNext()) {
				System.out.println(argsParser(scan.next()));
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static Map<String, Object> argsParser(String str) {
		// TODO this method will capture argument using the String.split() method.
		// You will have to split the string using the "," delimiter, and use the following logic to extract these arguments:
		// 1 - Title: index 4 from the length of the String[]
		// 2 - Genre: use String.contains() method to look for Action, Drama and Comedy - default: null
		// 3 - Rating: index 2 from the length of the String[], you need to convert the rating to the scale of 5
		// return a map with the type of arugment as String, and the value as object
		
		Map<String, Object> mapArgs = new HashMap<>();
		int i = 0;
		String[] e = str.trim().split(",");

		for (String s : e) {
			if (i == e.length - 4) {
				mapArgs.put("Title", s);
			} else if (i == e.length - 2) {
				try {
					mapArgs.put("Rating", Double.valueOf(s) / 10 * 5);
				} catch (NumberFormatException ex) {
					mapArgs.put("Rating", 0d);
				}
			}
			if (!mapArgs.containsKey("Genre")) {
				mapArgs.put("Genre", getGenre(str));
			}
			i++;
		}
		return mapArgs;
	}
	
	// TODO create a utility function that will return the type of genre based on the
	// genre subtype keywords that you might find in the string
	public static Class<? extends Genre> getGenre(String s) {
		if (s.contains("Comedy")) {
			return Comedy.class;
		} else if (s.contains("Action")) {
			return Action.class;
		} else if (s.contains("Drama")) {
			return Drama.class;
		}
		return null;
	}
}

