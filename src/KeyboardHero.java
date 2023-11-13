import java.awt.*;
import java.util.*;

public class KeyboardHero {
	static final Color ERASE = Color.WHITE;
	//static final Color WHITEKEY_PRESSED = ;
	static final Color BLACKKEY_UNPRESSED = Color.BLACK;
	//static final Color BLACKKEY_PRESSED = ;
	
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
		}
		for (int i = 0; i < 21; i++) {
			if (!notBlackKeys.contains(i % 12)){
				StdDraw.filledRectangle(1.0/22.0/2.0 + 1.0/22.0/2.0 + (i * (1.0/22.0/1.0)), 0.5/7.0/2.0, 1.0/22.0/4.0, 0.5/7.0/2.0);
			}
		}
	}
	
	public void pressKey(String key) {
		
	}
	public void unPressKey(String key) {
		
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
