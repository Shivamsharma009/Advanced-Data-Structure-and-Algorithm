public class HeapDemo {
    static class Node {
        String name;
        Node next;

        Node(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        System.out.println("Start");

        // Objects allocated on the heap
        Node a = new Node("A");
        Node b = new Node("B");

        // References: both a and b point to heap objects
        a.next = b;

        // Reference sharing: c points to same object as a
        Node c = a;

        System.out.println("a.name = " + a.name);
        System.out.println("c.name = " + c.name);
        System.out.println("a.next.name = " + a.next.name);

        // Remove one reference (object still reachable via c)
        a = null;

        System.out.println("After a = null, c.name = " + c.name);

        // Remove last reference to first Node
        c = null;

        // Now the first Node object ("A") is eligible for GC
        // b is still reachable
        System.out.println("After c = null, b.name = " + b.name);

        // Remove last reference to b
        b = null;

        // Now all Node objects are eligible for GC
        System.out.println("End");
    }
}
