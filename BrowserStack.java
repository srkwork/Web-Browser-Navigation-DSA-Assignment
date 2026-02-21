import java.util.*;


// BrowserStack class implements a LIFO stack
public class BrowserStack<T> implements Iterable<T>{

    // Underlying linked list used to store stack elements
    private BrowserLinkedList<T> list;


    public BrowserStack(){
        list = new BrowserLinkedList<T>();

    }

    // Method to add an element onto the top of the stack
    public void push(T item){
        list.addFirst(item);

    }

    // Method to remove and return the top element of the stack
    public T pop(){
        if(isEmpty()) throw new EmptyStackException();
        return list.removeFirst();
    }

    // Method to return the top element of the stack
    public T peek(){
        if(isEmpty()) throw new EmptyStackException();
        return list.peekFirst();
    }

    // Method to return true if the stack contains no elements.
    public boolean isEmpty(){
        return list.isEmpty();
    }

    // Method to clear all elements from the stack.
    public void clear(){
        list = new BrowserLinkedList<T>();
    }


    // Method to return an iterator that traverses the stack
    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }
    
}
