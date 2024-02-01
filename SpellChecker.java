
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		if (word1.length() == 0) {
			return word2.length();
		}else if (word2.length() == 0) {
			return word1.length();
		}else if (word1.substring(0,1).equalsIgnoreCase(word2.substring(0,1))) {
			return levenshtein(tail(word1), tail(word2));
		}else{
			
			int minimum = 1000;
			int[] options = new int[3];
			options[0] = levenshtein(tail(word1), word2);
			options[1] = levenshtein(word1, tail(word2));
			options[2] = levenshtein(tail(word1), tail(word2));
			for (int i = 0; i < 3; i++) {
				if (options[i] < minimum) {
					minimum = options[i];
				}
			}

			return 1+minimum;
		}
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for (int i = 0; i < 3000; i++) {
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String newWord = word;
		int limit = threshold + 1;
		for (int i = 0; i < dictionary.length; i++) {
			if (levenshtein (word, dictionary[i]) < limit) {
				limit = levenshtein (word, dictionary[i]);
				newWord = dictionary[i];
			}
		}	
		return newWord;
	}

}
