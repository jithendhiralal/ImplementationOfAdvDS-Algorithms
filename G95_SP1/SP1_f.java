
import java.util.Scanner;

/**
 * Class that represent reversel of Linked List
 *
 * @param <T>
 */
public class SP1_f<T extends Comparable<? super T>> extends SinglyLinkedList<T> {

    /**
     * Reverse SinglyLinkedList using iterative method
     *
     */
    public void reverseUsingIterative() {

        if (head == null || head.getNext() == null) {
            return;
        }
        Node current, next, prev;
        current = head;
        prev = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }
        head = prev;

    }

    
    public void reverseUsingRecursion() {
        if (head == null || head.getNext() == null) {
            return;
        }
        head = reverseUsingRecursionUtil(head);
    }

    /**
     *
     * @param head
     * @return reverseNode
     */
    private Node reverseUsingRecursionUtil(Node current) {
        if (current.getNext() == null) {
            return current;
        }
        Node nextNode = current.getNext();
        current.setNext(null);
        Node reverseNode = reverseUsingRecursionUtil(nextNode);
        nextNode.setNext(current);
        return reverseNode;
    }

    public void printRec() {
        Node temp = head;
        printRecUtil(temp);
        System.out.println();
    }

    public void printRecUtil(Node temp) {

        //base condition
        if (temp == null) {
            return;
        }
        System.out.print(temp.getValue() + " ");
        printRecUtil(temp.getNext());
    }

    public static void main(String[] args) {
        System.out.println("Reverse a linked List : ");
        System.out.print("Enter number of nodes: ");
        Scanner sc1 = new Scanner(System.in);

        int n = sc1.nextInt();
        System.out.print("Enter the elements : ");
        SP1_f<Integer> slObj = new SP1_f<>();
        for (int i = 0; i < n; i++) {

            int value = sc1.nextInt();
            slObj.addAtEnd(value);
        }


        System.out.println("Reverse order using Recursion:");
        slObj.reverseUsingRecursion();
        slObj.printRec();
        System.out.println("Reverse order using Non-Recursion:");
        slObj.reverseUsingIterative(); // Again it will Reverse the list to original order
        slObj.printList();
    }

}
