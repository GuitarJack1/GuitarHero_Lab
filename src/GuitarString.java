
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
	}
	public void tic() {
		// advance the simulation one time step
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
