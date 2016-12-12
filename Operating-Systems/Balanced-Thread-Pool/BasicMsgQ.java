import java.io.Serializable;
import java.util.*;

/**
 * <b>[TODO]</b> Basic queue implementation.
 * Implement missing functionality in this class. 
 * @author	Franklin D. Worrell
 * @version	9 November 2016
 */
public class BasicMsgQ implements MsgQ {
	// Underlying data structure is a Java PriorityQueue. 
	PriorityQueue<Serializable> queue = new PriorityQueue<Serializable>(); 

    /**
     * <b>TODO:</b> Implement this method as per the interface specification. 
	 * Appends the message at the end of the queue. Must block in order to call
	 * <code>notifyAll</code> to wake up any waiting threads. 
	 * @param	message		the data to enqueue
     */
    public void append(Serializable message) {
		// Insert the message into the underlying PriorityQueue. 
		this.queue.offer(message); 
		
		// Lock on underlying PriorityQueue must be acquired for notifyAll call. 
		synchronized(this.queue) {
			this.queue.notifyAll(); 
		} 
	} // end method append

    /**
     * <b>TODO:</b> Implement this method as per the interface specification.
	 * Returns the head message of the queue. Blocking method. 
	 * @return	the head of the queue
     */
    public Serializable pop() throws InterruptedException {
		// Acquire lock on underlying PriorityQueue. 
		synchronized(this.queue) {
			
			// If queue is empty, thread should wait for item to be added. 
			while (this.queue.size() == 0) {  
				this.queue.wait(); 
			}
			
			// Once item is present, return head of the queue. 
			return this.queue.poll(); 
		}
    } // end method pop

    /**
     * <b>TODO:</b> Implement this method as per the interface specification.
	 * Returns the head message of the queue. Nonblocking method. 
	 * @return	the head of the queue
     */
    public Serializable asyncPop() {
        return this.queue.poll();
    } // end method asyncPop
} // end class BasicMsgQ 
