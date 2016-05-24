
/**
 * Class to compute the Permutation of n numbers using Heap algorithm 
 * Take 3 algorithm discussed in class
 * G95_SP5
 * @author Ramakrishnan Sathyavageeswaran
 * @author Thiagarajan ramakrishnan
 * @author Jithendhiralal Ramlal
 */
public class SP5_a_2_Permutation {

    SP5_a_2_Permutation(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] = i + 1;
        }
    }

    public void permutationUsingHeap(int[] A, int n) {
        if (n == 1) {
            display(A);
        } else {
            for (int i = 0; i < n; i++) {
                permutationUsingHeap(A, n - 1);
                if (n % 2 == 0) {
                    swap(A, i, n - 1);
                } else {
                    swap(A, 0, n - 1);
                }
            }
        }
    }

    private void display(int[] A) {

        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i]);
        }
        System.out.println();
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;

    }

    public static void main(String[] args) {
        int n = 3;
        int[] A = new int[n];
        SP5_a_2_Permutation obj = new SP5_a_2_Permutation(A);
        Timer time = new Timer();
        time.start();
        obj.permutationUsingHeap(A, n);
        System.out.println(time.end());
    }
}
