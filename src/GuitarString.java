
public class GuitarString {
	int samplingRate = 44100;
	RingBuffer rb;
	int tics = 0;
	int size;
	
	public GuitarString(double frequency){
		// use a sampling rate of 44,100
		rb = new RingBuffer((int)Math.ceil((samplingRate / frequency)));
		for (int i = 0; i < (int)Math.ceil((samplingRate / frequency)); i++) {
			rb.enqueue(0.0);
		}
		size = (int)Math.ceil((samplingRate / frequency));
	}
	public GuitarString(double[] init){
		// size and values are given by the array
		rb = new RingBuffer(init.length);
		for (double i : init) {
			rb.enqueue(i);
		}
		size = init.length;
	}
	public void pluck() {
		// set the buffer to white noise
		rb = new RingBuffer(size);
		for(int i = 0;i<size;i++)
		{
			rb.enqueue((Math.random()*1.0101010101)-.5);
		}
		
		
	}
	public void tic() {
		// advance the simulation one time step
		double firstElem = rb.dequeue();
		rb.enqueue((firstElem + rb.peek())/2 * 0.994);
	}
	public void ticHold(boolean hold) {
		// advance the simulation one time step
		double firstElem = rb.dequeue();
		if (!hold) {
			rb.enqueue((firstElem + rb.peek())/2 * 0.994);
		}else {
			rb.enqueue(firstElem);
		}
		tics++;
	}
	public double sample() {
		// return the current sample
		
		return rb.peek();
	}
	public int time() {
		// return number of tics
		
		return tics;
	}

}
