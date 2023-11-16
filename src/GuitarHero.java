import java.awt.Color;
import java.io.*;
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
		 static PlaySongTiming songTiming;
		 static boolean normal = false;
		 static final int WAVEVIS_ACCURACY = 75; //75;
		 static final int WAVEVIS_FPS = 45; //45;
		 static final int LETTERSFALLING_FPS = 60; //60;
		 static final int CHANGEINSTRUMENT_KEYCODE = 16; //Shift
		 static final boolean HUMAN_PLAYING = true; //True = User plays, False = Computer plays
		 static final double PAUSE_TIME = 3.0; //Seconds
		 static Set<FallingLetter> toBePressed;
		 static Set<FallingLetter> totalGoaled;
		 
		 static double score = 0;
		 
		 static Set<FallingLetter> fLetters = new HashSet<FallingLetter>();
		
	    public static void main(String[] args) throws FileNotFoundException{
	    	white = "qwertyuiop[zxcvbnm,./ "; 
			black = "245789-=dfgjk;'";
			KeyLocations = new HashMap<String, Integer>();
			toBePressed = new HashSet<FallingLetter>();
			totalGoaled = new HashSet<FallingLetter>();
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
	    	songTiming = new PlaySongTiming(HUMAN_PLAYING);
	        play(keyboard);
	        System.exit(0);
	    }
	    
	    private static void play(Map<String, GuitarString> keyboard) {
	    	// the main input loop
	    	Stopwatch watch = new Stopwatch();
	        while (true) {
	        	for (FallingLetter fl : toBePressed) {
	        		if (totalGoaled.contains(fl)) {continue;}
        			if (fl.correct()) {
        				totalGoaled.add(fl);
        				StdDraw.show(0);
	            		StdDraw.setPenColor(StdDraw.BLACK);
	            		StdDraw.filledRectangle(1.05, 0.5/7.0/2.0, .05, .1);
	            		StdDraw.setPenColor(StdDraw.WHITE);
	            		StdDraw.text(1.025, 0.5/7.0/2.0, ""+(Math.round((score/totalGoaled.size())*100)) + "%");
	            		StdDraw.show();
        			}
                }
	        	// check if the user has typed a key, and, if so, process it
	            if (StdDraw.hasNextKeyTyped()) {
	 
	                // the user types this character
	                char key = StdDraw.nextKeyTyped();
	                
	                if (keyboard.keySet().contains(""+key)) {
	                	if (!alreadyPressed.contains(""+key)) {
	                		keyHero.pressKey(""+key);
	                		if (songTiming.getHumanPlay()) {
		                		Set<FallingLetter> removed = new HashSet<FallingLetter>();
		                		for (FallingLetter fl : toBePressed) {
		                			if (fl.letter().equals(""+key)) {
		                				if (fl.correct()) {
		                					StdDraw.setPenColor(StdDraw.GREEN);
		                					StdDraw.filledRectangle(1.05, 1.5, 0.025, 0.025);
		                					removed.add(fl);
		                					score++;
		                					System.out.println("Score now: " + score);
		                					StdDraw.show(0);
		        		            		StdDraw.setPenColor(StdDraw.BLACK);
		        		            		StdDraw.filledRectangle(1.05, 0.5/7.0/2.0, .05, .1);
		        		            		StdDraw.setPenColor(StdDraw.WHITE);
		        		            		StdDraw.text(1.025, 0.5/7.0/2.0, ""+(Math.round((score/totalGoaled.size())*100)) + "%");
		        		            		StdDraw.show();
		                				}
		                			}
		                		}
	                		
		                		for (FallingLetter fl : removed) {
		                			toBePressed.remove(fl);
		                		}
	                		}
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
	            
	            double elapsedTime = watch.elapsedTime();
	            Set<Double> remove1 = new HashSet<Double>();
	            for (double time : songTiming.getMap().keySet()) {
		            if (time - PAUSE_TIME <= elapsedTime) {
		            	for (String s : songTiming.getMap().get(time)) {
		            		FallingLetter fl = new FallingLetter(7, s, KeyLocations, white.contains(s));
		            		fLetters.add(fl);
		            		if (songTiming.getHumanPlay()) {
		            			toBePressed.add(fl);
		            		}
		            		if (!songTiming.getHumanPlay()) {
		            			keyboard.get(s).pluck();
		            		}
		            	}
		            	remove1.add(time);
		            }
	            }
	            for (double d : remove1) {
	            	songTiming.usedTime(d);
	            }
	            
	            if (songTiming.getMap().keySet().size() == 0 && !songTiming.getHumanPlay()) {
	            	return;
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
		            StdDraw.setPenColor(StdDraw.WHITE);
		           // StdDraw.filledRectangle(0.5, 0.505, .6, .43);
		            SetBackground(700,700);
		            StdDraw.show();
		            StdDraw.setPenColor(StdDraw.BLUE);
		        
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
		            if (songTiming.getHumanPlay()) {
		            	toBePressed.remove(letter);
		            }
		        }
		        
	            visualizer.addSample(sample);
	            if (System.currentTimeMillis() % (int)((1.0/WAVEVIS_FPS) * 1000) == 0) {
	            	//visualizer.addSample(sample);
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
	    
		public static void SetBackground(int width, int height) {
			//StdDraw.filledRectangle(0.5, 0.505, .6, .43);
			StdDraw.show(0);
			StdDraw.picture(.503, .5, "DarkCloudRainyBlahBG.png", 1, .85);
			StdDraw.setPenColor(Color.YELLOW);
			StdDraw.line(0.0, .15, 1.0, .15);
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.line(0.0, .15+.025, 1.0, .15+.025);
			StdDraw.line(0.0, .15-.025, 1.0, .15-.025);
			StdDraw.show();
			/*
			//StdDraw.setPenColor(Color.CYAN);
			int red = 32;
			int blue = 32;
			int green = 32;
			
			int endRed = 255;
			int endBlue = 255;
			int endGreen = 255;
		    
			StdDraw.setPenColor(new Color(red,green,blue));
			//StdDraw.filledRectangle(.5,.5,1,.8);
			
			int startPct = 20;
			int currentLine = (int) (height * startPct);
			
			//StdDraw.setPenColor(Color.BLACK);
			//StdDraw.line(0,0,1,1);
		
			
			for (int z=currentLine; z<height; z++) {
				
				
				
				int currentRed = (int) (red + (endRed - red) * ((z - currentLine)/(double) (height-currentLine)));
				int currentBlue = (int)  (blue + (endBlue - blue) * ((z - currentLine)/(double) (height-currentLine)));
				int currentGreen = (int) (green + (endGreen - green) * ((z - currentLine)/(double) (height-currentLine)));
				StdDraw.setPenColor(new Color(currentRed,currentBlue,currentGreen));
				double lineHeight = 1 * z/(double) height;
				StdDraw.line(0,lineHeight,1,lineHeight);
			}
			
			StdDraw.picture(.5, .75, "darkcloud3.png", .6, .25);
			*/
		}

	}
