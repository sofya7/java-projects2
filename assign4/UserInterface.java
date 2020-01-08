
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * This class implements a simple text-based user interface that will allow
 * users to interact with the ordered dictionary through the use of the
 * following commands.
 */
public class UserInterface {

	public static void main(String[] args) {

		OrderedDictionary dict = new OrderedDictionary();
		File file = new File(args[0]); // read file from configurations
		BufferedReader readFile;

		try {
			try {
				readFile = new BufferedReader(new FileReader(file));
				String readLine = readFile.readLine(); // to read each line at a time
				while ((readLine != null)) {
					String data = readFile.readLine();
					String type;

					if (data.endsWith(".gif") || data.endsWith(".jpg"))
						type = "image";
					else if (data.endsWith(".wav") || data.endsWith(".mid"))
						type = "audio";
					else
						type = "text";
					Pair aKey = new Pair(readLine, type);
					dict.put(new Record(aKey, data));
					readLine = readFile.readLine();
				}
			} catch (FileNotFoundException e) {
				System.out.println("No file was entered!");
				System.exit(0);
			}
			StringReader keyboard = new StringReader();
			String line = keyboard.read("Enter next command: ");
			{
				while (!line.equals("finish")) { // do not terminate until user asks to finish
					StringTokenizer str = new StringTokenizer(line, " ");
					String command = str.nextToken();

					if (command.equals("get")) {
						String word = str.nextToken();
						Pair textPair = new Pair(word, "text");
						Pair audioPair = new Pair(word, "audio");
						Pair imagePair = new Pair(word, "image");

						if (dict.get(textPair) == null && dict.get(audioPair) == null && dict.get(imagePair) == null) {
							System.out.println("The word " + word + " is not in the ordered dictionary");
							Record predec = dict.predecessor(textPair);
							Record succes = dict.successor(textPair);
							if (predec == null) {
								System.out.println("All words in file are alphabetically after" + word);
							} else {
								System.out.println("Preceding word:" + predec.getKey().getWord());
							}
							if (succes == null) {
								System.out.println("All words in file are alphabetically before" + word);
							} else {
								System.out.println("Following word:" + succes.getKey().getWord());
							}
						} else {
							try {
								if (dict.get(textPair) != null)
									System.out.println(dict.get(textPair).getData());
								if (dict.get(audioPair) != null) {
									SoundPlayer sound = new SoundPlayer();
									sound.play(dict.get(audioPair).getData());
								}
								if (dict.get(imagePair) != null) {
									PictureViewer picture = new PictureViewer();
									picture.show(dict.get(imagePair).getData());
								}
							} catch (MultimediaException e) {
								System.out.println("Multi Media files cannot be processed!");
							}
						}
					} else if (command.equals("delete")) {
						String word = str.nextToken();
						String type = str.nextToken();
						Pair key = new Pair(word, type);
						try {
							dict.remove(key);
						} catch (DictionaryException e) {
							System.out
									.println("No record in the ordered dictionary has key (" + word + "," + type + ")");
						}
					} else if (command.equals("put")) {
						String word = str.nextToken();
						String type = str.nextToken();
						String data = str.nextToken();
						Pair key = new Pair(word, type);
						Record rec = new Record(key, data);
						try {
							dict.put(rec);
						} catch (DictionaryException e) {
							System.out.println("A record with the given key (" + word + "," + type
									+ ") is already in the ordered dictionary.");
						}
					} else if (command.equals("list")) {
						String prefix = str.nextToken();
						Record small = dict.smallest();
						try {
							int listed = 0;
							if (small.getKey().getWord().startsWith(prefix)) {
								System.out.println(small.getKey().getWord());
								listed = 1;
							}
							small = dict.successor(small.getKey());
							while (small != null) { // keep listing all the words with such prefix
								if (small.getKey().getWord().startsWith(prefix)) {
									System.out.println(small.getKey().getWord());
									listed = 1;
								}
								small = dict.successor(small.getKey());
							}
							if (listed == 0)
								System.out.println("No words in the ordered dictionary start with prefix" + prefix);
						} catch (NullPointerException e) {
							System.out.println("Empty dictionary!");
						}
					} else if (command.equals("smallest")) {
						System.out.println(dict.smallest().getKey().getWord() + "," + dict.smallest().getKey().getType()
								+ "," + dict.smallest().getData());
					} else if (command.equals("largest")) {
						System.out.println(dict.largest().getKey().getWord() + "," + dict.largest().getKey().getType()
								+ "," + dict.largest().getData());
					} else {
						System.out.println("Invalid command!");
					}
					line = keyboard.read("Enter next command: ");
				}
			}
		} catch (DictionaryException e) {
			System.out.println("There is a problem with the dictionary!");
		} catch (NoSuchElementException e) {
			System.out.println("Invalid input!");
		} catch (Exception e) {
			System.out.println("An exception was cought!");
		}
	}
}