

import java.util.*;
/**
 * Class that represent List addition and subtraction
 * 
 */
public class SP1_b {
    
    /**
     * Helper Method which iterate List and return next element
     *
     * @param itr : The iterator of the list
     * @return next element of the list or null if the next element is null
     */
    public static Integer next(Iterator<Integer> itr) {
        return itr.hasNext() ? itr.next() : null;
    }
    
    /**
     * Method that represent addition of two list
     * @param list1 : This is a Linked list variable  
     * @param list2 : This is a Linked list variable
     * @param result : Output list that show addition of two list
     * @param b 
     */
    public static void add(List<Integer> list1, List<Integer> list2, 
                           List<Integer> result, int b) {
        Iterator itr1 = list1.iterator();
        Iterator itr2 = list2.iterator();
        Integer x1 = next(itr1);
        Integer x2 = next(itr2);
        int carry = 0, sum = 0;
        
        while (x1 != null && x2 != null) {
            sum = x1 + x2 + carry;
            result.add(sum % b);
            carry = sum / b;
            x1 = next(itr1);
            x2 = next(itr2);
        }
        //Adding the remaining numbers to the list
        while (x1 != null) {
            sum = x1 + carry;
            result.add(sum % b);
            carry = sum / b;
            x1 = next(itr1);
        }
        while (x2 != null) {
            sum = x2 + carry;
            result.add(sum % b);
            carry = sum / b;
            x2 = next(itr2);
        }
        //Adding the carry bit in case of any to the list
        if (carry > 0) {
            result.add(carry);
        }
    }

    /**
     * Method that represent Subtraction of two list
     * Assume list1>= list2
     * @param list1 : This is a Linked list variable  
     * @param list2 : This is a Linked list variable
     * @param result : Output list that show subtraction of two list
     * @param b 
     */
    public static void Subtract(List<Integer> list1, List<Integer> list2, 
                                List<Integer> result, int b) {
        Iterator itr1 = list1.iterator();
        Iterator itr2 = list2.iterator();
        Integer x = next(itr1);
        Integer y = next(itr2);
        // Flag to check if borrow has to be done from the previous digit or not
        Boolean borrow = false;
        
        while (x != null && y != null) {
            if (borrow) {
                if (x == 0) {
                    x = x + b;
                    x = x - 1;
                } else {
                    x = x - 1;
                    
                    borrow = false;
                }
            }
            if (x < y) {
                borrow = true;
                x = x + b;
                result.add(x - y);
            } else {
                result.add(x - y);
            }
            x = next(itr1);
            y = next(itr2);
        }
        //Considering the remaining entries to the list
        while (x != null) {
            if (borrow) {
                if (x == 0) {
                    x = x + b;
                    x = x - 1;
                } else {
                    x = x - 1;
                    borrow = false;
                }
            }
            result.add(x);
            x = next(itr1);
        }
    }

    public static void main(String[] args) {
        System.out.println("List Addition and subtraction Implementation:");
        System.out.print("Enter Base value: ");
        Scanner sc1 = new Scanner(System.in);
        int b = sc1.nextInt();
        int count = 0, recent = 0;

        List<Integer> list1 = new LinkedList<>();
        List<Integer> list2 = new LinkedList<>();
        List<Integer> AddResult = new LinkedList<>();
        List<Integer> SubResult = new LinkedList<>();


        System.out.print("Enter no of elements for List1: ");

        int n = sc1.nextInt();
        System.out.print("Enter the elements : ");
        for (int i = 0; i < n; i++) {

            int value = sc1.nextInt();
            list1.add(value);
        }

        System.out.print("Enter no of elements for List2: ");

        n = sc1.nextInt();
        System.out.print("Enter the elements : ");
        for (int i = 0; i < n; i++) {
            int value = sc1.nextInt();
            list2.add(value);
        }
        add(list1, list2, AddResult, b);
        Subtract(list1, list2, SubResult, b);
        System.out.println("Addition");
        for (Integer x : AddResult) {
            System.out.print(x + " ");
        }
        System.out.println();
        
        // Finding the recent non zero number in Substracted result
        for (Integer x : SubResult) {
            if (x != 0) {
                recent = count;
            }
            count++;
        }
        
        // Printing Substraction result until the non zero number
        System.out.println("Subtraction");
        for (int i = 0; i <= recent; i++) {
            System.out.print(SubResult.get(i) + " ");
        }
        System.out.println();
    }

}
