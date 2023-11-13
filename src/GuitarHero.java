import java.util.*;
import java.lang.NullPointerException;

public class GuitarHero {
	/*****************************************************************************
	 *  Compilation:  javac GuitarHeroLite.java
	 *  Execution:    java  GuitarHeroLite
	 *  Dependencies: StdAudio.java StdDraw.java GuitarString.java
	 *
	 *  Plays two guitar strings (concert A and concert C) when the user
	 *  types the lowercase letters 'a' and 'c', respectively in the 
	 *  standard drawing window.
	 *
	 ****************************************************************************/
		 static Map<String, GuitarString> keyboard;
		 static Map<String, Integer> keyToInt;
		 static Set<String> alreadyPressed;
		 static boolean NORMAL = false;
		
	    public static void main(String[] args) {
	    	//Hello
	        // Create a map of guitar strings
	    	keyboard = new HashMap<String, GuitarString>();
	    	keyToInt = new HashMap<String, Integer>();
	    	alreadyPressed = new HashSet<String>();
	    	
	    	populateKeyboard("q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ");
	    	populateKeyToInt();
	        
	    	KeyboardHero kh = new KeyboardHero(1000, 1000);
	        play(keyboard);
	        
	    }
	    
	    private static void play(Map<String, GuitarString> keyboard) {
	    	// the main input loop
	        while (true) {
	        	// check if the user has typed a key, and, if so, process it
	            if (StdDraw.hasNextKeyTyped()) {
	 
	                // the user types this character
	                char key = StdDraw.nextKeyTyped();
	                if (keyboard.keySet().contains(""+key)) {
	                	// pluck the corresponding string
	                	if (!NORMAL) {
		                	if (!alreadyPressed.contains(""+key)) {
		                		keyboard.get(""+key).pluck();
		                		alreadyPressed.add(""+key);
		                	}
	                	}else {
	                		keyboard.get(""+key).pluck();
	                	}
	                }
	                
	            }

	            // compute the superposition of the samples
	            double sample = 0;
	            for (String key : keyboard.keySet()) {
	            	// advance the simulation of each guitar string by one step
	            	sample += keyboard.get(key).sample();
	            	if (!NORMAL) {
		            	keyboard.get(key).ticHold(StdDraw.isKeyPressed(keyToInt.get(key)));
		            	if (!StdDraw.isKeyPressed(keyToInt.get(key))) {
		            		alreadyPressed.remove(key);
		            	}
	            	}else {
	            		keyboard.get(key).tic();
	            	}
	            }
	            
	            // send the result to standard audio
	            StdAudio.play(sample);
	        }
	    }
	    
	    private static void populateKeyboard(String input) {
	    	for(int i = 1;i<=input.length();i++)
	    	{
	    		keyboard.put(input.substring(i-1,i), new GuitarString(frequency(i)));
	    	}
	    }
	    
	    private static void populateKeyToInt() {
	    	keyToInt.put("q", 81);
	    	keyToInt.put("2", 50);
	    	keyToInt.put("w", 87);
	    	keyToInt.put("e", 69);
	    	keyToInt.put("4", 52);
	    	keyToInt.put("r", 82);
	    	keyToInt.put("5", 53);
	    	keyToInt.put("t", 84);
	    	keyToInt.put("y", 89);
	    	keyToInt.put("7", 55);
	    	keyToInt.put("u", 85);
	    	keyToInt.put("8", 56);
	    	keyToInt.put("i", 73);
	    	keyToInt.put("9", 57);
	    	keyToInt.put("o", 79);
	    	keyToInt.put("p", 80);
	    	keyToInt.put("-", 45);
	    	keyToInt.put("[", 91);
	    	keyToInt.put("=", 61);
	    	keyToInt.put("z", 90);
	    	keyToInt.put("x", 88);
	    	keyToInt.put("d", 68);
	    	keyToInt.put("c", 67);
	    	keyToInt.put("f", 70);
	    	keyToInt.put("v", 86);
	    	keyToInt.put("g", 71);
	    	keyToInt.put("b", 66);
	    	keyToInt.put("n", 78);
	    	keyToInt.put("j", 74);
	    	keyToInt.put("m", 77);
	    	keyToInt.put("k", 75);
	    	keyToInt.put(",", 44);
	    	keyToInt.put(".", 46);
	    	keyToInt.put(";", 59);
	    	keyToInt.put("/", 47);
	    	keyToInt.put("'", 222);
	    	keyToInt.put(" ", 32);

	    }
	    
	    private static double frequency(int i) {
	    	return (440* Math.pow(1.05956,i-25));
	    }
	}
