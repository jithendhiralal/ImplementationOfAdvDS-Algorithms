package sp0h;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.Random;

/**
 * Class to which illustrate different method of finding frequent element in an
 * integer array
 *
 * @author G95
 */
public class findMostFrequentElement {

    /**
     * This method finds the most frequent array using Hashtable in O(n) where n
     * being the size of the array
     *
     * @param arr : Input array
     * @return
     */
    public static int mostFrequent(int[] arr) {
        int length = arr.length;
        int k = 0;
        int mostFrequent = 0;

        Hashtable<Integer, Integer> htable = new Hashtable<>();

        for (int i = 0; i < length; i++) {
            if (!htable.containsKey(arr[i])) {
                int count = 0;
                htable.put(arr[i], count);

            } else {
                htable.put(arr[i], htable.get(arr[i]) + 1);
                int count = htable.get(arr[i]);
                if (count > mostFrequent) {
                    mostFrequent = count;
                    k = arr[i];

                }
            }
        }

        return k;
    }

    /**
     * This method finds the most frequent array using Sorting technique and
     * comparing with adjacent elements in O(nlogn) where n being the size of
     * the array
     *
     * @param array : Input array
     * @return
     */
    public static int mostFrequentUsingSorting(int[] array) {

        //Approach:
        //Step 1: Sort element first  
        //Step 2: iterate through the array (size-1) times and check if adjacent
        //are equal and increment the count and compare with another counter
        //which hold the most frequent element and its count.
        Arrays.sort(array);
        int k = 0;
        int mostFrequent = 1;
        int count = 1;
        for (int i = 0; i < array.length - 1; i++) {

            if (array[i] == array[i + 1]) {
                ++count;
                if (count > mostFrequent) {
                    mostFrequent = count;
                    k = array[i];
                }
            } else {
                count = 0;
            }
        }

        return k;
    }

    public static void main(String[] args) {
        int n = 20;
        Random random = new Random(n);
        Timer timer = new Timer();

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt((n - 1) + 1) + 1;
        }
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] +" ");
        }
        System.out.println();
        System.out.println("Most Frequent element using HashTable : O(n) ");
        timer.start();
        System.out.println(mostFrequent(arr));
        timer.end();
        System.out.println(timer);
        System.out.println("Most Frequent element using Sorting : O(nlogn) ");
        timer.start();
        System.out.println(mostFrequentUsingSorting(arr));
        timer.end();
        System.out.println(timer);
    }
}
