public class Record {

	/* Attribute declarations */
	private Pair key;
	private String data;

	public Record(Pair key, String data) {
		this.key = key;
		this.data = data;
	}

	// Returns the key stored in this Record object.
	public Pair getKey() {
		return key;
	}

	// Returns the data stored in this Record object.
	public String getData() {
		return data;
	}

}