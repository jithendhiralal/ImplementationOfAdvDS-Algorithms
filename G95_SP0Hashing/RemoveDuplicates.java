import java.util.HashSet;
import java.util.Random;

/**
 * @author G95
 */
public class RemoveDuplicates {

    /**
     * This method will find unique elements in an Array and move them to front
     * and return the k unique elements
     *
     * @param array : Array of Objects with duplicates elements
     * @return
     */
    public static <T> int findDistinct(T[] array) {

        //Approach:
        //If element not in set add it to the set and push unique elements to 
        // front of the array
        int k = 0;
        HashSet<T> hset = new HashSet<>();

        for (int i = 0; i < array.length; i++) {
            if (!hset.contains(array[i])) {
                hset.add(array[i]);
                array[k++] = array[i];
            }
        }
        return k;
    }

    public static void main(String[] args) {
        int n = 20;
        Random random = new Random(n);
        Timer timer = new Timer();

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt((n - 1) + 1) + 1;
        }
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] +" ");
        }
         System.out.println();
        String[] arr1 = new String[]{"hi", "hi", "hey", "hey", "ji",
            "hello", "ram", "cat", "bat", "oi"};
        timer.start();
        System.out.println(findDistinct(arr));
        timer.end();
        System.out.println(timer);

    }
}
