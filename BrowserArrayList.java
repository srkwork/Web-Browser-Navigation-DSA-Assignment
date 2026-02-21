import java.util.*;


// BrowserArrayList implements a circular array-based queue.
public class BrowserArrayList<T> implements Iterable<T>{

    private T[] items;      // Underlying array to store elements
    private int size;       // Current number of elements in the queue   
    private int front;      // Index of the front element (for dequeue/peek)
    private int rear;       // Index where the next element will be inserted
    private int capacity;   // Current capacity of the array

    public BrowserArrayList(){
        size = 0;
        front = 0;
        capacity = 10;
        items = (T[]) new Object[capacity];
    }

    // Method to return the number of elements currently in the queue
    public int getSize(){
        return size;
    }

    // Method to return true if the queue is empty
    public boolean isEmpty(){
        return size == 0;
    }

    // Method to clear the queue by resetting size and indices
    public void clear(){
        size = 0;
        front = 0; 
        rear = 0;
    }

    // Method to add item to the queue (Enqueue)
    public boolean add(T data){
        if(data == null) return false;

        // If the array is full, then double the capacity
        if(size == capacity){
            T[] newItems = (T[]) new Object[capacity * 2];

            // Copying elements in correct order using circular indexing
            for(int i = 0; i < size; i++){
                newItems[i] = items[(front + i) % capacity];
            }
            items = newItems;
            capacity = newItems.length;

            // Reset indices after resizing
            front = 0; 
            rear = size;
        }
        items[rear] = data; // Insert new element at rear
        rear = (rear+1) % capacity; // Circular increment of rear pointer (enqueuing)
        size++;
        return true;
    }

    // Method to remove item from the queue (Dequeue)
    public T remove(){
        if(size == 0) return null;

        T it = items[front];
        front = (front+1) % capacity; // Circular increment of front pointer (dequeuing)
        size--;
        return it;
    }


    // Method to peek at the front value
    public T peek(){
        if(size == 0) return null;
        return items[front];
    }

    /*
    Method to return an iterator that traverses elements
    from front to rear in FIFO order. 
    */
    @Override
    public Iterator<T> iterator() {
        return new BrowserArrayListIterator();
    }

    /*
    Iterator implementation for BrowserArrayList.
    Traverses elements in FIFO order using circular indexing.
    */
    private class BrowserArrayListIterator implements Iterator<T>{
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public T next() {
            if(!hasNext()) throw new NoSuchElementException();

            // Calculate correct index accounting for circular layout
            int index = (front + current) % capacity;
            current++;
            return items[index];

        }
        
    }
    
}
