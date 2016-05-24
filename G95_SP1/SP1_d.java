

import java.util.Scanner;
import java.util.Stack;

/**
 * Class represent Binary Search Tree Implementation
 * @param <T>
 */
public class SP1_d<T extends Comparable<? super T>> {
    /**
     * Class represent Binary Tree Node
     * 
     */
    public class BinaryTreeNode<T> {

        private T data;
        private BinaryTreeNode<T> left;
        private BinaryTreeNode<T> right;

        BinaryTreeNode(T data) {

            this.data = data;
            left = null;
            right = null;
        }

        BinaryTreeNode(T data, BinaryTreeNode left, BinaryTreeNode right) {

            this.data = data;
            this.left = left;
            this.right = right;
        }

        /**
         * @return the data
         */
        public T getData() {
            return data;
        }

        /**
         * @param data the data to set
         */
        public void setData(T data) {
            this.data = data;
        }

        /**
         * @return the left
         */
        public BinaryTreeNode<T> getLeft() {
            return left;
        }

        /**
         * @param left the left to set
         */
        public void setLeft(BinaryTreeNode<T> left) {
            this.left = left;
        }

        /**
         * @return the right
         */
        public BinaryTreeNode<T> getRight() {
            return right;
        }

        /**
         * @param right the right to set
         */
        public void setRight(BinaryTreeNode<T> right) {
            this.right = right;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

    }

    private BinaryTreeNode<T> root;
    private T data;

    SP1_d() {

        root = null;
    }

    /**
     * @return the root
     */
    public BinaryTreeNode<T> getRoot() {
        return root;
    }

    public BinaryTreeNode<T> addNode(T data) {

        if (getRoot() == null) {
            root = new BinaryTreeNode(data);
            return getRoot();
        } else {
            return addTo(getRoot(), data);
        }
    }

    private BinaryTreeNode<T> addTo(BinaryTreeNode<T> node, T data) {

        // If value is less then current node's value added as left child
        if (data.compareTo(node.getData()) <= 0) {
            if (node.getLeft() == null) {
                BinaryTreeNode<T> temp = new BinaryTreeNode(data);
                node.setLeft(temp);

                return node.getLeft();
            } else {
                return addTo(node.getLeft(), data);
            }
        } // if value is greater or equal to current node's value then it is added as right child
        else if (node.getRight() == null) {
            BinaryTreeNode<T> temp = new BinaryTreeNode(data);
            node.setRight(temp);
            return node.getRight();
        } else {
            return addTo(node.getRight(), data);
        }
    }

    public void printPreOrderUsingStack(BinaryTreeNode<T> root) {
        //Base Case
        if (root == null) {
            return;
        }

        Stack<BinaryTreeNode> stackNode = new Stack<>();
        BinaryTreeNode previous = new BinaryTreeNode(Integer.MAX_VALUE);
        
        //Pushing the first entry to the stack
        stackNode.push(root);

        while (!stackNode.isEmpty()) {
            BinaryTreeNode current = stackNode.peek();

            if (current == null) {
                stackNode.pop();
            } 
            //
            else if (current.isLeaf()) {
                System.out.print(current.getData() + " ");
                stackNode.pop();
            } 
            //If previous node is left node then visit right node
            else if (current.getLeft() == previous) {
                stackNode.push(current.getRight());
            } 
            // If previous node is right node then pop current from the stack
            else if (current.getRight() == previous) {
                stackNode.pop();
            } 
            // Print root node and push left node to the stack
            else {
                System.out.print(current.getData() + " ");
                stackNode.push(current.getLeft());
            }
            previous = current;

        }

    }
    /**
     * This 
     * @param args 
     */
    public static void main(String[] args) {
        System.out.println("Recursion using Stack Implementation:");
        Scanner sc1 = new Scanner(System.in);
        System.out.print("Enter no of nodes : ");
        int n = sc1.nextInt();

        SP1_d<Integer> bstObj = new SP1_d();
        System.out.print("Enter the elements : ");
        for (int i = 0; i < n; i++) {

            int value = sc1.nextInt();
            bstObj.addNode(value);
        }

        System.out.println("Pre Order Traversal ");

        bstObj.printPreOrderUsingStack(bstObj.getRoot());

    }

}
