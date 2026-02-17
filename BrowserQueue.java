import java.util.*;

public class BrowserQueue<T> implements Iterable<T> {

    private BrowserArrayList<T> list;

    public BrowserQueue(){
        list = new BrowserArrayList<T>();
    }

    // Adding to a queue
    public void enqueue(T data){
        list.add(data);
    }

    // Removing from a queue
    public T dequeue(){
        return list.remove();
    }

    public T peek(){
        return list.peek();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void clear(){
        list = new BrowserArrayList<T>();
    }

    public int size(){
        return list.getSize();
    }

    

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
    
}
