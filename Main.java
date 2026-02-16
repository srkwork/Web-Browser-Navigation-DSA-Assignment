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
    }
}