import java.util.*;

public class Main{
    public static void main(String[] args) {
        // =========== Test BrowserStack =========
        System.out.println("======Testing BrowserStack=====");
        BrowserStack<String> stack = new BrowserStack<>();

        System.out.println("Stack is empty: " + stack.isEmpty());

        stack.push("A");
        stack.push("B");
        stack.push("C");

        System.out.println("Stack is empty: " + stack.isEmpty());
        System.out.println("stack.peek() should be C: " + stack.peek() + '\n');

        System.out.println("Iterating stack: ");
        for(String s : stack){
            System.out.print(s + " ");
        }
        System.out.println('\n');


        System.out.println("stack.pop() should be C: " + stack.pop());
        System.out.println("stack.pop() should be B: " + stack.pop());
        System.out.println("stack.peek() should be A: " + stack.peek());

        stack.clear(); // Clearing the whole stack
        System.out.println("After clear, stack.isEmpty() should be true: " + stack.isEmpty());

        try{
            stack.pop(); // Should throw EmptyStackException
            System.out.println("ERROR: pop() did not throw EmptyStackException");
        }catch(EmptyStackException e){
            System.out.println("OK: pop() threw EmptyStackException on empty stack");
        }

        // ========= Test BrowserQueue ======
        System.out.println("\n=== Testing BrowserQueue ===");
        BrowserQueue<Integer> queue = new BrowserQueue<>();

        System.out.println("Queue is empty: " + queue.isEmpty());


        for(int i = 1; i <= 10; i++){
            queue.enqueue(i);
        }

        System.out.println("Queue is empty: " + queue.isEmpty());
        System.out.println("queue.peek() should be 1: " + queue.peek());

        for(int x: queue){
            System.out.print(x + " ");
        }
        System.out.println();

        System.out.println("Dequeue 3 items (should be 1,2,3): " + queue.dequeue() + " " + queue.dequeue() + " " + queue.dequeue());
        System.out.println("queue.peek() should be at 4: " + queue.peek());
        for(int x : queue){
            System.out.print(x + " ");
        }
        System.out.println();

        queue.enqueue(16);
        queue.enqueue(17);
        queue.enqueue(18);

        // Iterating queue with new data
        for(int x: queue){
            System.out.print(x + " ");
        }
        System.out.println();
        
        queue.clear();

        System.out.println("After clear, queue.isEmpty() should be true: " + queue.isEmpty());
        System.out.println("queue.dequeue() on empty should be null: " + queue.dequeue());

        
    }
}