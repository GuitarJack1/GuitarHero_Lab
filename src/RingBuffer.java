import java.util.*;

public class RingBuffer {
	
	double[] buffer;
	int start;
	int end;
	int size;
	
	public RingBuffer(int capacity){
		// create an empty ring buffer, with given capacity
		size = 0;
		start = 0;
		end = 0;
		buffer = new double[capacity];
	}
	public int size() {
		// return number of items currently in the buffer
		
		return size;
	}
	public boolean isEmpty() {
		// is the buffer empty (size equals zero)?
		
		return size == 0;
	}
	public boolean isFull() {
		// is the buffer full  (size equals capacity)?
		
		return size == buffer.length;
	}
	public void enqueue(double x) {
		// add item x to the end (if not full)
		if (isFull()) {
			throw new NoSuchElementException();
		}
		int temp = end;
		end++;
		if (end == buffer.length) {
			end = 0;
		}
		size++;
		buffer[end] = x;
	}
	public double dequeue() {
		// delete and return item from the front (if not empty)
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		int temp = start;
		start++;
		if (start == buffer.length) {
			start = 0;
		}
		buffer[temp] = 0.0;
		size--;
		return buffer[temp];
	}
	public double peek(){
		// return item from the front of the buffer
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return buffer[start];
	}
	public String toString() {
		//form [front, next, …, next, last]
		
		String string = "[";
		for (int i = start; i != end; i++) {
			if (i > buffer.length) {
				i = 0;
			}
			string += buffer[i] + ", ";
		}
		return string + buffer[end] + "]";
	}

}
