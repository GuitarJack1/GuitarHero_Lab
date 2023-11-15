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
		 static Map<String, GuitarString> keyboard;
		 static Map<String, Integer> keyToInt;
		 static Set<String> alreadyPressed;
		 static String white;
		 static String black;
		 static Map<String,Integer> KeyLocations;
		 static KeyboardHero keyHero;
		 static GuitarHeroVisualizer visualizer;
		 static boolean normal = false;
		 static final int WAVEVIS_ACCURACY = 75;
		 static final int WAVEVIS_FPS = 45;
		 static final int LETTERSFALLING_FPS = 60;
		 static final int CHANGEINSTRUMENT_KEYCODE = 16; //Shift
		 
		 static Set<FallingLetter> fLetters = new HashSet<FallingLetter>();
		
	    public static void main(String[] args) {
	    	white = "qwertyuiop[zxcvbnm,./ "; 
			black = "245789-=dfgjk;'";
			KeyLocations = new HashMap<String, Integer>();
			Set<Integer> notBlackKeys = new HashSet<Integer>();
			notBlackKeys.add(1);
			notBlackKeys.add(4);
			notBlackKeys.add(8);
			notBlackKeys.add(11);
			notBlackKeys.add(15);
			notBlackKeys.add(18);
			for (int i = 0; i < 22; i++) {
					KeyLocations.put(""+white.charAt(i), i);
			}
			int blackIndex = 0;
			for (int i = 0; i < 21; i++) {
				if (!notBlackKeys.contains(i)){
					KeyLocations.put(""+black.charAt(blackIndex), i);
					blackIndex++;
				}
			}
	    	//Hello
	        // Create a map of guitar strings
	    	keyboard = new HashMap<String, GuitarString>();
	    	keyToInt = new HashMap<String, Integer>();
	    	alreadyPressed = new HashSet<String>();
	    	
	    	populateKeyboard("q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ");
	    	populateKeyToInt();
	    	
	    	keyHero = new KeyboardHero(700, 700);
	    	visualizer = new GuitarHeroVisualizer(WAVEVIS_ACCURACY);
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
	                	if (!alreadyPressed.contains(""+key)) {
	                		fLetters.add(new FallingLetter(7, ""+key, KeyLocations, white.contains(""+key)));
	                		keyHero.pressKey(""+key);
	                	}
	                	
	                	if (!normal) {
		                	if (!alreadyPressed.contains(""+key)) {
		                		keyboard.get(""+key).pluck();
		                	}
	                	}else {
	                		keyboard.get(""+key).pluck();
	                	}
	                	
	                	alreadyPressed.add(""+key);
	                }
	                
	            }
	            
	            double sample = 0;
	            for (String key : keyboard.keySet()) {
	            	// advance the simulation of each guitar string by one step
	            	sample += keyboard.get(key).sample();
	            	if (!normal) {
		            	keyboard.get(key).ticHold(StdDraw.isKeyPressed(keyToInt.get(key)));
	            	}else {
	            		keyboard.get(key).tic();
	            	}
	            	
	            	if (!StdDraw.isKeyPressed(keyToInt.get(key)) && alreadyPressed.contains(""+key)) {
	            		keyHero.unPressKey(""+key);
	            		alreadyPressed.remove(key);
	            	}
	            }
	            
	            StdAudio.play(sample);
	            
	            Set<FallingLetter> remove = new HashSet<FallingLetter>();
		        if (System.currentTimeMillis() % (int)((1.0/LETTERSFALLING_FPS) * 1000) == 0) {
		            StdDraw.show(0);
		            StdDraw.setPenColor(StdDraw.BLUE);
		            StdDraw.filledRectangle(0.5, 0.505, .6, .43);
		            StdDraw.setPenColor(StdDraw.BLACK);
		            for (FallingLetter letter : fLetters) {
		            	//System.out.println("Printing Letter: " + letter.letter() + ", at position: " + letter.x() + "x, and " + letter.y() + "y");
		            	//System.out.print("hi");
		            	StdDraw.text(letter.x(), letter.y(), letter.letter());
		            }
		            StdDraw.show();
		        }
		        for (FallingLetter letter : fLetters) {
		            if (!letter.alive()) {remove.add(letter);continue;}
		            letter.update();
		        }
		        for (FallingLetter letter : remove) {
		            fLetters.remove(letter);
		        }
		        
	            visualizer.addSample(sample);
	            if (System.currentTimeMillis() % (int)((1.0/WAVEVIS_FPS) * 1000) == 0) {
	            	visualizer.drawWave();
	            }
	            
	            if (StdDraw.isKeyPressed(CHANGEINSTRUMENT_KEYCODE)) {
	            	keyHero.setClassical(!normal);
	            	normal = !normal;
	            	while (StdDraw.isKeyPressed(CHANGEINSTRUMENT_KEYCODE)) {};
	            }
	        }
	    }
	    
	    public static Set<String> getAlreadyPressed()
        {
        	return alreadyPressed;
        }
	    
	    public static void drawWave(double[] waveArr) {
	    	double prevX = 0.1;
	    	double prevY = waveArr[0]*0.1 + 0.9;
	    	
	    	for (int i = 1; i < waveArr.length; i++) {
	    		StdDraw.line(prevX, prevY, prevX + 0.005, waveArr[0]*0.1 + 0.9);
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
