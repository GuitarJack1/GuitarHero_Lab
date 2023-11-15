import java.awt.*;
import java.util.*;

public class KeyboardHero {
	static final Color ERASE = Color.WHITE;
	
	//static final Color WHITEKEY_PRESSED = ;
	static final Color BLACKKEY_UNPRESSED = Color.BLACK;
	String white;
	String black;
	Set<Double> blackRelativeToWhite;
	double[] posForRelativity = {.5,2.5,3.5,5.5,6.5,7.5,9.5,10.5,12.5,13.5,14.5,16.5,17.5,19.5,20.5};;
	//static final Color BLACKKEY_PRESSED = ;
	//Map<String,Integer> allKeys;
	Map<String,Integer> KeyLocations;
	public KeyboardHero(int width, int height) {
		StdDraw.setCanvasSize(width, height);
		SetBackground(width,height);
		
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.filledRectangle(0.0, 1.02, 0.218, 0.071);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.rectangle(0.0, 1.02, 0.218, 0.071);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
		StdDraw.text(0.07, 1.02, "Instrument: Electronic");
		StdDraw.text(0.07, 0.99, "Press (Shift) to change");
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
		String letterset = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		white = "qwertyuiop[zxcvbnm,./ "; 
		black = "245789-=dfgjk;'";
		blackRelativeToWhite = (Set.of(.5,2.5,3.5,5.5,6.5,7.5,9.5,10.5,12.5,13.5,14.5,16.5,17.5,19.5,20.5));
		KeyLocations = new HashMap<String, Integer>();
		//allKeys = new HashMap<String,Integer>();
		//for(int keys = 0;keys<37;keys++)
		//{
		//	allKeys.put(letterset.substring(keys,keys+1),keys);
		//}
		StdDraw.setPenColor(BLACKKEY_UNPRESSED);
		Set<Integer> notBlackKeys = new HashSet<Integer>();
		notBlackKeys.add(1);
		notBlackKeys.add(4);
		notBlackKeys.add(8);
		notBlackKeys.add(11);
		notBlackKeys.add(15);
		notBlackKeys.add(18);

		for (int i = 0; i < 22; i++) {
			     StdDraw.setPenColor(Color.WHITE);
				StdDraw.filledRectangle(1.0/22.0/2.0 + (i * (1.0/22.0/1.0)), 0.0, 1.0/22.0/2.0, 0.5/7.0);
				
				KeyLocations.put(""+white.charAt(i), i);
		}
		int blackIndex = 0;
		StdDraw.setPenColor(BLACKKEY_UNPRESSED);
		for (int i = 0; i < 21; i++) {
			if (!notBlackKeys.contains(i)){
				StdDraw.filledRectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (i * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
				KeyLocations.put(""+black.charAt(blackIndex), i);
				blackIndex++;
			}
		}
	}
	

	public void SetBackground(int width, int height) {
		width = 800;
		height =800;
		
		//StdDraw.setPenColor(Color.CYAN);
		int red = 32;
		int blue = 32;
		int green = 32;
		
		int endRed = 255;
		int endBlue = 255;
		int endGreen = 255;
	    
		StdDraw.setPenColor(new Color(red,green,blue));
		StdDraw.filledRectangle(.5,.5,1,1);
		
		double startPct = .072;
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

	}
	
	public void whiteToGreen(String key)
	{
		StdDraw.setPenColor(Color.GREEN);
		StdDraw.filledRectangle(1.0/22.0/2.0 + (KeyLocations.get(key) * (1.0/22.0/1.0)), 0.0, 1.0/22.0/2.0, 0.5/7.0);
		Set<Integer> closeBlackKeys = getBlacksNearWhite(key); 
		//for(int i = 0;i < closeBlackKeys.size();i++)
		StdDraw.setPenColor(Color.BLACK);
		for(int Bkey : closeBlackKeys)
		{
			if( GuitarHero.getAlreadyPressed().contains(black.substring(Bkey,Bkey+1)))
			{
				StdDraw.setPenColor(Color.GREEN);
				StdDraw.filledRectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(black.substring(Bkey,Bkey+1)) * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
				StdDraw.rectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(black.substring(Bkey,Bkey+1)) * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
				StdDraw.setPenColor(Color.BLACK);
			}
			else
			{		
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.filledRectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(black.substring(Bkey,Bkey+1)) * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
			StdDraw.rectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(black.substring(Bkey,Bkey+1)) * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
			}
		}		
	}
	
	public void greenToWhite(String key)
	{
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.filledRectangle(1.0/22.0/2.0 + (KeyLocations.get(key) * (1.0/22.0/1.0)), 0.0, 1.0/22.0/2.0, 0.5/7.0);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.rectangle(1.0/22.0/2.0 + (KeyLocations.get(key) * (1.0/22.0/1.0)), 0.0, 1.0/22.0/2.0, 0.5/7.0);
	
		Set<Integer> closeBlackKeys = getBlacksNearWhite(key); 
		for(int Bkey : closeBlackKeys)
		{
			if( GuitarHero.getAlreadyPressed().contains(black.substring(Bkey,Bkey+1)))
			{
				StdDraw.setPenColor(Color.GREEN);
				StdDraw.filledRectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(black.substring(Bkey,Bkey+1)) * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
				StdDraw.rectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(black.substring(Bkey,Bkey+1)) * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
				StdDraw.setPenColor(Color.BLACK);
			}
			else
			{		
			StdDraw.setPenColor(Color.BLACK);
			StdDraw.filledRectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(black.substring(Bkey,Bkey+1)) * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
			StdDraw.rectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(black.substring(Bkey,Bkey+1)) * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
			
			}
		}
		
	}
	public void blackToGreen(String key)
	{
		StdDraw.setPenColor(Color.GREEN);
		StdDraw.filledRectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(key) * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
		StdDraw.rectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(key) * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
		StdDraw.setPenColor(Color.BLACK);
	}
	public void greenToBlack(String key)
	{
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.filledRectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(key) * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
		StdDraw.rectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(key) * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
	}
	public boolean isBlack(String letter)
	{
		//if(allKeys.get(letter))
		return false;
	}
	public Set<Integer> getBlacksNearWhite(String whiteKey)
	{
		Set<Integer> set = new HashSet<Integer>();
		if(blackRelativeToWhite.contains(KeyLocations.get(whiteKey) - .50))
		{
			set.add(Arrays.binarySearch(posForRelativity, KeyLocations.get(whiteKey)-.50));
		}
		if(blackRelativeToWhite.contains(KeyLocations.get(whiteKey) + .50))
		{
			set.add(Arrays.binarySearch(posForRelativity, KeyLocations.get(whiteKey)+.50));
		}
		return set;
	}
	
	public void pressKey(String key) {
		if(white.contains(key))
		{
			whiteToGreen(key);
		}
		else
		{
			blackToGreen(key);
		}
	}
	public void unPressKey(String key) {
		if(white.contains(key))
		{
			greenToWhite(key);
		}
		else
		{
			greenToBlack(key);
		}
	}
	
	public void setClassical(boolean setNormal) {
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.filledRectangle(0.0, 1.0218, 0.2, 0.071);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.rectangle(0.0, 1.0218, 0.2, 0.071);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
		if (setNormal) {
			StdDraw.text(0.07, 1.02, "Instrument: Classical");
			StdDraw.text(0.07, 0.99, "Press (Shift) to change");
		}else {
			StdDraw.text(0.07, 1.02, "Instrument: Electronic");
			StdDraw.text(0.07, 0.99, "Press (Shift) to change");
		}
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
	}
}
