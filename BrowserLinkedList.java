import java.util.*;


// BrowserLinkedList class is a doubly linked list implementation
public class BrowserLinkedList<T> implements Iterable<T> {
    
    // Number of nodes currently in the list
    private int size;

    // Head (front) and tail (end) pointers for the list
    private Node head;
    private Node tail;

    public BrowserLinkedList(){
        size = 0;
        head = null;
        tail = null;
    }

    // Method to return the number of elements in the list
    public int getSize(){
        return size;
    }

    // Method that returns true if the list contains no elements
    public boolean isEmpty(){
        return size == 0;
    }
    
    /*
    Node represents a single element in the doubly linked list.
    Each node stores:
        - data
        - reference to the previous node
        - reference to the next node  
    */
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

    // Method to add node to the stack (at head)
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

    // Method to remove node from the stack (from head)
    public T removeFirst(){
        if(isEmpty()) throw new NoSuchElementException("List is empty");

        
        T removedHead = head.data; //  Save the value from being removed
        head = head.next; // Move head forward

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

    // Method to peek at the head node
    public T peekFirst(){
        if(isEmpty()) throw new NoSuchElementException("List is empty");
        return head.data;
    }

    /*
    Returns an iterator that traverses the list from head -> tail. 
    */
    @Override
    public Iterator<T> iterator() {
        return new BrowserLinkedListIterator();
    }
    
    /*
    Iterator implementation for BrowserLinkedList
    Iterates through the nodes starting head and moving via next references. 
    */
    private class BrowserLinkedListIterator implements Iterator<T>{
        private Node current = head;
        
        @Override
        public boolean hasNext(){
            return current != null;
        }

        @Override
        public T next(){
            if(!hasNext()) throw new NoSuchElementException();
            
            // Return current value and advance pointer
            T val = current.data;
            current = current.next;
            return val;
        }
        
    }
    
}
