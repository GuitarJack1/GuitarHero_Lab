import java.io.*;
import java.util.*;

public class PlaySongTiming {
	Map<Double, Set<String>> notesPlayed = new HashMap<Double, Set<String>>();
	Scanner in = new Scanner(new File("inputSong.txt"));
	public PlaySongTiming(boolean humanPlay) throws FileNotFoundException {
		String input;
		do {
			input = in.nextLine();
		}while(!input.equals("start:"));
		
		String[] input2;
		do {
			input2 = in.nextLine().split(" ");
		}while(!input2[0].equals("end"));
	}
}