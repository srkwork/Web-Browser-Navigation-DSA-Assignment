import java.util.*;

// Class used for maintaining browser history
// It internally uses BrowserArrayList (circular array)
public class BrowserQueue<T> implements Iterable<T> {

    // Circular array-based queue implementation
    private BrowserArrayList<T> list;

    
    public BrowserQueue(){
        list = new BrowserArrayList<T>();
    }

    // Method to add to the queue
    public void enqueue(T data){
        list.add(data);
    }

    // Method to remove from a queue
    public T dequeue(){
        return list.remove();
    }

    // Method to return the front element of the queue
    public T peek(){
        return list.peek();
    }

    // Method to return true if the queue is empty
    public boolean isEmpty(){
        return list.isEmpty();
    }

    // Method to clear all elements from the queue
    public void clear(){
        list = new BrowserArrayList<T>();
    }

    // Method to return the number of elements currently in the queue
    public int size(){
        return list.getSize();
    }

    
    /*
    Method to return an iterator to traverse the queue
    in FIFO order (front -> rear)
    */
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
    
}
