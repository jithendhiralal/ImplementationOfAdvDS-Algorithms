/**
 * Class that represent Singly Linked List Implementation
 *
 */
public class SinglyLinkedList<T> {

    public class Node<T> {

        private T value;
        Node next;

        Node(T val) {

            this.value = val;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

    Node<T> head;
    private Node<T> tail;
    int length;

    public SinglyLinkedList() {
        length = 0;
        head = null;
        tail = null;
    }

    public Node<T> getHead() {
        return head;
    }

    public Node<T> getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }
    int length()
    {
    	int length=0;
    	Node<T> p=this.head;
    	while(p.next!=null)
    	{
    		length=length+1;
    		p=p.next;
    	}
    	return length;
    }
    //Add node at End of the Single Linked List
    public void addAtEnd(T value) {

        Node<T> temp = new Node<>(value);

        if (head == null) {
            head = temp;
        } else {
            tail.setNext(temp);

        }
        tail = temp;
        length++;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.getValue() + " ");
            temp = temp.getNext();
        }
        System.out.println();
    }

}
