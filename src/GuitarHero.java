import java.util.*;

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

	public class GuitarHeroLite {
		 static Map<String, GuitarString> keyboard;
		
	    public static void main(String[] args) {
	    	//Hello
	        // Create a map of guitar strings
	    	keyboard = new HashMap<String, GuitarString>();
	    	populateKeyboard("q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ");

	        final double TEXT_POS_X = .2;
	        final double TEXT_POS_Y = .5;
	        
	        StdDraw.text(TEXT_POS_X, TEXT_POS_Y, "Type a key to play a note!");
	        
	        play(keyboard);
	        
	    }
	    
	    private static void play(Map<String, GuitarString> keyboard) {        // the main input loop
	        while (true) {
	        	// check if the user has typed a key, and, if so, process it
	            if (StdDraw.hasNextKeyTyped()) {
	 
	                // the user types this character
	                char key = StdDraw.nextKeyTyped();
	                if (keyboard.keySet().contains(""+key)) {
	                	// pluck the corresponding string
	                	keyboard.get(""+key).pluck();
	                }
	                
	            }

	            // compute the superposition of the samples
	            double sample = 0;
	            for (String key : keyboard.keySet()) {
	            	// advance the simulation of each guitar string by one step
	            	sample += keyboard.get(key).sample();
	            	keyboard.get(key).tic();
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
	    
	    private static double frequency(int i) {
	    	return (440* Math.pow(1.05956,i-25));
	    }
	}

}
