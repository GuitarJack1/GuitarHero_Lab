import java.util.*;

public class GuitarHeroVisualizer {
	Queue<Double> wave = new LinkedList<Double>();
	
	public GuitarHeroVisualizer (int accuracy) {
		System.out.println(accuracy);
		for (int i = 0; i < accuracy; i++) {
			wave.offer(0.0);
		}
	}
	
	public void addSample(double sample) {
		wave.remove();
		wave.offer(sample);
	}
	
	public void drawWave() {
		StdDraw.setPenColor(StdDraw.WHITE);
		StdDraw.filledRectangle((1.0 + 0.22)/2.0, 1.0, ((1.0 - 0.22)/2.0), 0.051);		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.show(0);
		
		double prevX = 0.22;
		double prevY = 1.0;
		for (double sound : wave) {
			StdDraw.line(prevX, prevY, prevX + (1.0 - 0.22)/(wave.size()+2), Math.max(Math.min(sound/10 + 1.0, 1.05), 0.95));
			prevX += (1.0 - 0.22)/(wave.size()+2);
			prevY = Math.max(Math.min(sound/10 + 1.0, 1.05), 0.95);
		}
		StdDraw.line(prevX, prevY, prevX + (1.0 - 0.22)/wave.size(), 1.0);
		
		StdDraw.show();
	}
}
