import java.io.*;
import java.util.*;

public class PlaySongTiming {
	Map<Double, Set<String>> notesPlayed = new HashMap<Double, Set<String>>();
	Scanner in;
	boolean humanPlay;
	
	static final double PAUSE_TIME = 3.0; //Seconds
	
	public PlaySongTiming(boolean humanPlay) throws FileNotFoundException {
		Map<String, String> keys = new HashMap<String, String>();
		keys.put("A2" , "q");
		keys.put("A#2", "2");
		keys.put("B2" , "w");
		keys.put("C2" , "e");
		keys.put("C#2", "4");
		keys.put("D2" , "r");
		keys.put("D#2", "5");
		keys.put("E2" , "t");
		keys.put("F2" , "y");
		keys.put("F#2", "7");
		keys.put("G2" , "u");
		keys.put("G#2", "8");
		keys.put("A3" , "i");
		keys.put("A#3", "9");
		keys.put("B3" , "o");
		keys.put("C3" , "p");
		keys.put("C#3", "-");
		keys.put("D3" , "[");
		keys.put("D#3", "=");
		keys.put("E3" , "z");
		keys.put("F3" , "x");
		keys.put("F#3", "d");
		keys.put("G3" , "c");
		keys.put("G#3", "f");
		keys.put("A4" , "v");
		keys.put("A#4", "g");
		keys.put("B4" , "b");
		keys.put("C4" , "n");
		keys.put("C#4", "j");
		keys.put("D4" , "m");
		keys.put("D#4", "k");
		keys.put("E4" , ",");
		keys.put("F4" , ".");
		keys.put("F#4", ";");
		keys.put("G4" , "/");
		keys.put("G#4", "'");
		keys.put("A5" , "S");
		
		this.humanPlay = humanPlay;
		in = new Scanner(new File("inputSong.txt"));
		
		String input;
		do {
			input = in.nextLine();
		}while(!input.equals("start:"));
		
		String[] input2;
		do {
			input2 = in.nextLine().split(" ");
			if (input2[0].equals("end")) {
				break;
			}
			Set<String> inputs = new HashSet<String>();
			for (int i = 0; i < input2.length-1; i++) {
				inputs.add(keys.get(input2[i]));
			}
			notesPlayed.put(Double.parseDouble(input2[input2.length-1])+2.0, inputs);
		}while(!input2[0].equals("end"));
	}
	
	public boolean getHumanPlay() {
		return humanPlay;
	}
	
	public Map<Double, Set<String>> getMap(){
		return notesPlayed;
	}
	
	public void usedTime(double key) {
		notesPlayed.remove(key);
	}
}