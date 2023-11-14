import java.awt.*;
import java.util.*;

public class KeyboardHero {
	static final Color ERASE = Color.WHITE;
	
	//static final Color WHITEKEY_PRESSED = ;
	static final Color BLACKKEY_UNPRESSED = Color.BLACK;
	String white;
	String black;
	//static final Color BLACKKEY_PRESSED = ;
	//Map<String,Integer> allKeys;
	Map<String,Integer> KeyLocations;
	public KeyboardHero(int width, int height) {
		StdDraw.setCanvasSize(width, height);
		StdDraw.setPenColor(Color.WHITE);
		
		StdDraw.filledRectangle(0.0, 1.02, 0.2, 0.06);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.rectangle(0.0, 1.02, 0.2, 0.06);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 12));
		StdDraw.text(0.07, 1.02, "Instrument: Electronic");
		StdDraw.text(0.07, 0.99, "Press (Shift) to change");
		StdDraw.setFont(new Font("SansSerif", Font.PLAIN, 16));
		String letterset = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
		white = "qwertyuiop[zxcvbnm,./ "; 
		black = "245789-=dfgjk;'";
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
				StdDraw.rectangle(1.0/22.0/2.0 + (i * (1.0/22.0/1.0)), 0.0, 1.0/22.0/2.0, 0.5/7.0);
				
				KeyLocations.put(""+white.charAt(i), i);
		}
		int blackIndex = 0;
		for (int i = 0; i < 21; i++) {
			if (!notBlackKeys.contains(i)){
				StdDraw.filledRectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (i * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
				KeyLocations.put(""+black.charAt(blackIndex), i);
				blackIndex++;
			}
		}
	}
	public void whiteToGreen(String key)
	{
		//StdDraw.setPenColor(Color.GREEN);
		//StdDraw.rectangle(1.0/22.0/2.0 + (KeyLocations.get(key) * (1.0/22.0/1.0)), 0.0, 1.0/22.0/2.0, 0.5/7.0);
		
		
	}
	public void blackToGreen(String key)
	{
		StdDraw.setPenColor(Color.GREEN);
		StdDraw.filledRectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(key) * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
		StdDraw.setPenColor(Color.BLACK);
	}
	public void greenToBlack(String key)
	{
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.filledRectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(key) * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
	}
	public boolean isBlack(String letter)
	{
		//if(allKeys.get(letter))
		return false;
	}
	
	public void pressKey(String key) {
		if(white.contains(key))
		{
			//whiteToGreen(key);
		}
		else
		{
			blackToGreen(key);
		}
	}
	public void unPressKey(String key) {
		if(white.contains(key))
		{
			
		}
		else
		{
			greenToBlack(key);
		}
	}
	
	public void setClassical(boolean setNormal) {
		StdDraw.setPenColor(Color.WHITE);
		StdDraw.filledRectangle(0.0, 1.02, 0.2, 0.06);
		StdDraw.setPenColor(Color.BLACK);
		StdDraw.rectangle(0.0, 1.02, 0.2, 0.06);
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
