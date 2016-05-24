
import java.util.Comparator;

public class SP3_d_Selection<T> {
    
    /**
     * This Method finds the Kth largest element in an array using MaxHeap
     * @param <T>
     * @param arr
     * @param start
     * @param end
     * @param k
     * @return 
     */
    public static <T extends Comparable<? super T>> T selectUsingMaxHeap(T[] arr, int start, int end, int k) {
        Comparator cmp;
        cmp = new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return -1 * (o1.compareTo(o2));
            }
        };

        BinaryHeap<T> pq = new BinaryHeap<>(arr, cmp);

        for (int i = 1; i < k; i++) {
            pq.remove();
        }
        return pq.peek();
    }
    /**
     * This Method finds the Kth largest element using partition method
     * @param <T>
     * @param input
     * @param start
     * @param end
     * @param k
     * @return 
     */
    public static <T extends Comparable<? super T>> T selectUsingPartition(T[] input, int start, int end, int k) {
        int left = start;
        int right = end;
        int pivot = start;
        while (left <= right) {
            while (left <= right && (input[left].compareTo(input[pivot]) > 0)) {
                left++;
            }
            while (left <= right && (input[right].compareTo(input[pivot]) <= 0)) {
                right--;
            }

            if (left < right) {
                swap(input, left, right);
                left++;
                right--;
            }
        }
        swap(input, pivot, right);

        if (pivot == k) {
            return input[pivot];
        } else if (pivot < k) {
            return selectUsingPartition(input, pivot + 1, end, k);
        } else {
            return selectUsingPartition(input, start, pivot - 1, k);
        }
    }
    /**
     * This is helper method to swap elements
     * @param <T>
     * @param input
     * @param a
     * @param b 
     */
    public static <T extends Comparable<? super T>> void swap(T[] input, int a, int b) {
        T x = input[a];
        input[a] = input[b];
        input[b] = x;
    }
    /**
     * Driver method which calls select Kth largest element using MaxHeap 
     * and partition method
     * @param args 
     */
    public static void main(String[] args) {
        Integer[] arr = {2, 3, 11, 16, 27, 4, 15, 9, 8};
        int k = 3;
        Timer time = new Timer();
        time.start();
        System.out.println("The " + k + " largest element using  selectUsingMaxHeap is : "
                + selectUsingMaxHeap(arr, 0, arr.length - 1, k));
        System.out.println(time.end());
        time.start();
        System.out.println("The " + k + " largest element using selectUsingPartion is : "
                + selectUsingPartition(arr, 0, arr.length - 1, k));
        System.out.println(time.end());
    }

}
