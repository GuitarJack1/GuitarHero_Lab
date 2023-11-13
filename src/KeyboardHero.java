import java.util.*;

public class KeyboardHero {
	public KeyboardHero(int width, int height) {
		StdDraw.setCanvasSize(width, height);
		StdDraw.setPenColor(StdDraw.BLACK);
		Set<Integer> whiteKeys = new HashSet<Integer>();
		whiteKeys.add(0);
		whiteKeys.add(2);
		whiteKeys.add(3);
		whiteKeys.add(5);
		whiteKeys.add(7);
		whiteKeys.add(8);
		whiteKeys.add(10);
		for (int i = 0; i < 22; i++) {
				StdDraw.rectangle(1.0/22.0/2.0 + (i * (1.0/22.0/1.0)), 0.0, 1.0/22.0/2.0, 0.5/7.0);
		}
		for (int i = 0; i < 22; i++) {
			
		}
	}
}
