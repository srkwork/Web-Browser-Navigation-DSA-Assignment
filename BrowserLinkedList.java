import java.util.*;

public class BrowserLinkedList<T> implements Iterable<T> {
    private int size;
    private Node head;
    private Node tail;

    public BrowserLinkedList(){
        size = 0;
        head = null;
        tail = null;
    }


    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }
    
    private class Node{
        
        T data;
        Node prev;
        Node next;

        Node(T data, Node prev, Node next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    // Adding node to the stack (at head)
    public void addFirst(T data){
        Node newNode = new Node(data, null, head);
        if(head != null){
            // Adding node to the stack (newNode = head)
            head.prev = newNode;
        }
        else{
            // List is empty therefore tail becomes newNode
            tail = newNode;
        }
        head = newNode;
        size++; 
    }

    // Removing node from the stack (from head)
    public T removeFirst(){
        if(isEmpty()) throw new NoSuchElementException("List is empty");

        T removedHead = head.data;
        head = head.next;

        if(head != null){
            // Removing node from the stack
            head.prev = null;
        }
        else{
            // List is empty therefore tail is empty;
            tail = null;
        }

        size--; 
        return removedHead;
    }

    // Peek at the head node
    public T peekFirst(){
        if(isEmpty()) throw new NoSuchElementException("List is empty");
        return head.data;
    }


    @Override
    public Iterator<T> iterator() {
        return new BrowserLinkedListIterator();
    }
    
    private class BrowserLinkedListIterator implements Iterator<T>{
        private Node current = head;
        
        @Override
        public boolean hasNext(){
            return current != null;
        }

        @Override
        public T next(){
            if(!hasNext()) throw new NoSuchElementException();
            
            T val = current.data;
            current = current.next;
            return val;
        }
        
    }
    
}
