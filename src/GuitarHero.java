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
	            
	        }
	        
	    }
	    
	    private static void populateKeyboard(String input) {
	    	
	    }
	    
	    private static double frequency(int i) {
	    	return 0;
	    }
	}

}
