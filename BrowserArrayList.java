import java.util.*;

public class BrowserArrayList<T> implements Iterable<T>{

    private T[] items;
    private int size;
    private int front;
    private int rear;
    private int capacity;

    public BrowserArrayList(){
        size = 0;
        front = 0;
        capacity = 10;
        items = (T[]) new Object[capacity];
    }

    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size == 0;
    }

    public void clear(){
        size = 0;
        front = 0; 
        rear = 0;
    }

    // Adding item to the queue (Enqueue)
    public boolean add(T data){
        if(size == 0) return false;

        if(size == capacity){
            T[] newItems = (T[]) new Object[capacity * 2];
            for(int i = 0; i < size; i++){
                newItems[i] = items[(front + i) % capacity]; // Circular copying
            }
            items = newItems;
            capacity = newItems.length;
            front = 0; 
            rear = size;
        }
        items[rear] = data;
        rear = (rear+1) % capacity; // Circular increment for enqueing
        size++;
        return true;
    }

    // Removing item from the queue (Dequeue)
    public T remove(){
        if(size == 0) return null;

        T it = items[front];
        front = (front+1) % capacity; // Circular increment for dequeing
        size--;
        return it;
    }


    // Peek at the front value
    public T peek(){
        if(size == 0) return null;
        return items[front];
    }

    @Override
    public Iterator<T> iterator() {
        return new BrowserArrayListIterator();
    }

    private class BrowserArrayListIterator implements Iterator<T>{
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public T next() {
            if(!hasNext()) throw new NoSuchElementException();
            int index = (front + current) % capacity;
            current++;
            return items[index];

        }
        
    }
    
}
