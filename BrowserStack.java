import java.util.*;

public class BrowserStack<T> implements Iterable<T>{

    private BrowserLinkedList<T> list;
    public BrowserStack(){
        list = new BrowserLinkedList<T>();

    }

    public void push(T item){
        list.addFirst(item);

    }

    public T pop(){
        if(isEmpty()) throw new EmptyStackException();
        return list.removeFirst();
    }

    public T peek(){
        if(isEmpty()) throw new EmptyStackException();
        return list.peekFirst();
    }

    public boolean isEmpty(){
        return list.isEmpty();
    }

    public void clear(){
        list = new BrowserLinkedList<T>();
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
    
}
