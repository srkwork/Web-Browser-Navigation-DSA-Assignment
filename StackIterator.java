import java.util.*;

public class StackIterator<T> implements Iterator<T>{

    // Creating an iterator used to traverse stack elements
    private Iterator<T> it;
    
    // Stack whose whose elements will be iterated
    public StackIterator(BrowserStack<T> stack){

        // Uses the stack's iterator to traverse elements
        it = stack.iterator();
    }


    // Returns true if there are more elements in the stack.
    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    // Returns the next element in the stack iteration.
    @Override
    public T next() {
        return it.next();
    }
    
}