
public class Pair {

	/* Attribute declarations */
	private String word;
	private String type;

	// A constructor which initializes a new Pair object
	// with the specified word and type.
	public Pair(String word, String type) {
		this.word = word.toLowerCase();
		this.type = type;
	}

	// Returns the word stored in this Pair object.
	public String getWord() {
		return word;
	}

	// Returns the type stored in this Pair object.
	public String getType() {
		return type;
	}

	// Returns 0 if the key stored in this Pair object is equal to
	// k, returns -1 if the key stored in this Pair object is smaller than k, and it
	// returns 1 otherwise.
	public int compareTo(Pair k) {
		int compW = word.compareTo(k.getWord());
		int compT = type.compareTo(k.getType());
		if (compW == 0 && compT == 0) {
			return 0;
		}
		if (compW < 0 || compW == 0 && compT < 0) {
			return -1;
		}
		return 1;
	}
}