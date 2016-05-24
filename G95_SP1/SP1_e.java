

import java.util.*;

public class SP1_e<T extends Comparable<? super T>> extends SinglyLinkedList<T> {

    public void multiUnzip(int k) {
        //If the given list is of size k or less than k just return
        if (getLength() <= k) {
            return;
        }
        //Creating an array of head and tail pointers of k size
        ArrayList<Node<T>> headList = new ArrayList<>(k);
        ArrayList<Node<T>> tailList = new ArrayList<>(k);

        Node<T> temp = head;
        int i, j;
        // Set head and tail pointers in positions 0 through k -1
        for (i = 0; i < k && temp != null; i++) {
            headList.add(i, temp);
            tailList.add(i, temp);
            temp = temp.getNext();
        }

        Node<T> nextNode = temp;

        int counter = 0;
        //Linking the tail(counter) node k+1th node and moving the tail pointers
        while (nextNode != null) {

            tailList.get(counter).setNext(nextNode);
            tailList.set(counter, nextNode);
            nextNode = nextNode.getNext();
            counter++;
            counter = counter % k;

        }
        //Linking the unconnected nodes to form a complete list
        for (j = 0; j < tailList.size() - 1; j++) {
            tailList.get(j).setNext(headList.get(j + 1));
        }
        tailList.get(j).setNext(null);

    }

    public static void main(String[] args) {

        System.out.println("MultiUnzip of linked List Implementation ");
        System.out.print("Enter the position of the list to unzip from: ");
        Scanner sc1 = new Scanner(System.in);
        int k = sc1.nextInt();
        System.out.print("Enter the size of the list: ");
        int n = sc1.nextInt();
        SP1_e<Integer> lst = new SP1_e<>();
        for (int i = 1; i <= n; i++) {
            lst.addAtEnd(i);
        }

        lst.printList();

        if (k < 2) {
            return;
        }
        lst.multiUnzip(k);
        lst.printList();
    }
}
