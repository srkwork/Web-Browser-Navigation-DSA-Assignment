import java.util.*;

public class StackIterator<T> implements Iterator<T>{

    private Iterator<T> it;
    
    public StackIterator(BrowserStack<T> stack){
        it = stack.iterator();
    }

    @Override
    public boolean hasNext() {
        return it.hasNext();
    }

    @Override
    public T next() {
        return it.next();
    }
    
}