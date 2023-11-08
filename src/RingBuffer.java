
public class RingBuffer {
	public RingBuffer(int capacity){
		// create an empty ring buffer, with given capacity
	}
	public int size() {
		// return number of items currently in the buffer
		
		return 0;
	}
	public boolean isEmpty() {
		// is the buffer empty (size equals zero)?
		
		return true;
	}
	public boolean isFull() {
		// is the buffer full  (size equals capacity)?
		
		return true;
	}
	public void enqueue(double x) {
		// add item x to the end (if not full)
	}
	public double dequeue() {
		// delete and return item from the front (if not empty)
		
		return 0.0;
	}
	public double peek(){
		// return item from the front of the buffer
		
		return 0.0;
	}
	public String toString() {
		//form [front, next, …, next, last]
		
		return null;
	}

}
