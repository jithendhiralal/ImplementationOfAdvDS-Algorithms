
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Class that represent Set Operations on Lists
 *
 */
public class SP1_a<E extends Comparable<? super E>> {

    /**
     * Method to find intersection of two lists.
     *
     * @param list1 : This can be LinkedList or ArrayList
     * @param list2 : This can be LinkedList or ArrayList
     * @param result : Output List which is an Intersection of List1 and List2
     */
    public void intersection(List<E> list1, List<E> list2, List<E> result) {

        Iterator<E> itr1 = list1.iterator();
        Iterator<E> itr2 = list2.iterator();
        E element1 = next(itr1);
        E element2 = next(itr2);

        while (element1 != null && element2 != null) {
            int comparison = element1.compareTo(element2);
            if (comparison < 0) {
                element1 = next(itr1);
            } else if (comparison > 0) {
                element2 = next(itr2);
            } else {
                result.add(element1);
                element1 = next(itr1);
                element2 = next(itr2);
            }
        }

    }

    /**
     * Method to find union of two lists.
     *
     * @param list1 : This can be LinkedList or ArrayList
     * @param list2 : This can be LinkedList or ArrayList
     * @param result : Output List which is an union of List1 and List2
     */
    public void union(List<E> list1, List<E> list2, List<E> result) {
        Iterator<E> itr1 = list1.iterator();
        Iterator<E> itr2 = list2.iterator();
        E element1 = next(itr1);
        E element2 = next(itr2);
        while (element1 != null && element2 != null) {
            int comparison = element1.compareTo(element2);
            if (comparison > 0) {
                result.add(element2);
                element2 = next(itr2);
            } else if (comparison < 0) {
                result.add(element1);
                element1 = next(itr1);

            } else {
                result.add(element2);
                element1 = next(itr1);
                element2 = next(itr2);
            }
        }
        while (element1 != null) {
            result.add(element1);
            element1 = next(itr1);
        }
        while (element2 != null) {
            result.add(element2);
            element2 = next(itr2);
        }
    }

    /**
     * Method to find the set difference of two list
     *
     * @param list1 : This can be LinkedList or ArrayList
     * @param list2 : This can be LinkedList or ArrayList
     * @param result : Output List which is an Set Difference of List1 and List2
     */
    public void setDifference(List<E> list1, List<E> list2, List<E> result) {
        Iterator<E> itr1 = list1.iterator();
        Iterator<E> itr2 = list2.iterator();
        E element1 = next(itr1);
        E element2 = next(itr2);
        while (element1 != null && element2 != null) {
            int comparison = element1.compareTo(element2);
            if (comparison > 0) {
                element2 = next(itr2);
            } else if (comparison < 0) {
                result.add(element1);
                element1 = next(itr1);
            } else {
                element1 = next(itr1);
                element2 = next(itr2);
            }
        }
        while (element1 != null) {
            result.add(element1);
            element1 = next(itr1);
        }
    }

    /**
     * Helper Method which iterate List and return next element
     *
     * @param itr : The iterator of the list
     * @return next element of the list or null if the next element is null
     */
    public E next(Iterator<E> itr) {
        return itr.hasNext() ? itr.next() : null;
    }

    public static <E> void print(List<E> result) {
        if(result.isEmpty()){
            System.out.println("No result found");
            return;
        }
        for (E item : result) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
    public static List<Integer> getUniquevalues(){
        Scanner sc1 = new Scanner(System.in);
        int n = sc1.nextInt();
        System.out.print("Enter the elements : ");
        Integer[] values  = new Integer[n];
        for (int i = 0; i < n; i++) {

            int value = sc1.nextInt();
            values[i] = value;
        }
        
        Arrays.sort(values);
        return Arrays.asList(values);
    }
    /**
     * Driver Method to 
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("Set Operations Implementation");
        
        List<Integer> unionResult = new LinkedList<>();
        List<Integer> intersectionResult = new LinkedList<>();
        List<Integer> setDiffResult = new LinkedList<>();

        System.out.print("Enter no of elements for List1: ");
        
        List<Integer> list1 = new LinkedList<>(new HashSet<Integer>(getUniquevalues()));
        
        System.out.print("Enter no of elements for List2: ");
        List<Integer> list2 = new LinkedList<>(new HashSet<Integer>(getUniquevalues()));
        
        SP1_a<Integer> obj = new SP1_a();

        System.out.println("Union");
        obj.union(list1, list2, unionResult);

        print(unionResult);

        System.out.println("Intersection");
        obj.intersection(list1, list2, intersectionResult);

        print(intersectionResult);

        System.out.println("Set Difference");
        obj.setDifference(list1, list2, setDiffResult);

        print(setDiffResult);

    }

}
