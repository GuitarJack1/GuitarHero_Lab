import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FallingLetter {
	Stopwatch time;
	
	final double STARTING_Y = 0.7;
	final double ENDING_Y = 0.1;
	final double GOAL_POS = 0.15;
	//double changeInY;
	
	double xPos;
	double yPos;
	String letter;
	double totalTime;
	
	boolean alive;
	public FallingLetter (double timeToHitBottomInSeconds, String key, Map<String, Integer> KeyLocations, boolean white) {
		
		time = new Stopwatch();
		if (white) {
			xPos = 1.0/22.0/2.0 + (KeyLocations.get(key) * (1.0/22.0/1.0));
		}else {
			xPos = 1.0/22.0/2.0 + 1.0/22.0/2.0 + (KeyLocations.get(key) * (1.0/22.0/1.0));
		}
		yPos = STARTING_Y;
		letter = key;
		totalTime = timeToHitBottomInSeconds;
		alive = true;
	}
	
	public void update() {
		yPos += getChangeInY();
		if (time.elapsedTime() >= totalTime) {
			alive = false;
		}
	}
	
	public double x() {
		return xPos;
	}
	public double y() {
		return yPos;
	}
	public String letter() {
		return letter;
	}
	
	public boolean alive() {
		return alive;
	}
	
	public double getChangeInY() {
		return (double)(ENDING_Y - yPos) / ((double)(totalTime - time.elapsedTime()) * (double)44100);
	}
}
