
public class GuitarString {
	int samplingRate = 44100;
	RingBuffer rb;
	
	public GuitarString(double frequency){
		// use a sampling rate of 44,100
		rb = new RingBuffer((int)Math.ceil((samplingRate / frequency)));
		for (int i = 0; i < (int)Math.ceil((samplingRate / frequency)); i++) {
			rb.enqueue(0.0);
		}
	}
	public GuitarString(double[] init){
		// size and values are given by the array
		rb = new RingBuffer(init.length);
		for (double i : init) {
			rb.enqueue(i);
		}
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
	}
	public double sample() {
		// return the current sample
		
		return 0.0;
	}
	public int time() {
		// return number of tics
		
		return 0;
	}

}
