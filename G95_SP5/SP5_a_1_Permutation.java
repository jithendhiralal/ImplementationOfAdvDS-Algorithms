/**
 * Class to compute the Permutation of n numbers Take 2 algorithm discussed in 
 * class
 * G95_SP5
 * @author Ramakrishnan Sathyavageeswaran
 * @author Thiagarajan ramakrishnan
 * @author Jithendhiralal Ramlal
 */
public class SP5_a_1_Permutation {

    SP5_a_1_Permutation(int[] A) {
        for (int i = 0; i < A.length; i++) {
            A[i] = i;
        }

    }

    public void permutation(int[] A, int n) {

        if (n == 0) {
            display(A);
        } else {
            for (int i = 1; i <= n; i++) {
                swap(A, i, n);
                permutation(A, n - 1);
                swap(A, n, i);
            }
        }
    }

    private void display(int[] A) {

        for (int i = 1; i < A.length; i++) {
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
        int n = 4;
        int[] A = new int[n + 1];
        SP5_a_1_Permutation obj = new SP5_a_1_Permutation(A);
        Timer time = new Timer();
        time.start();
        obj.permutation(A, n);
        System.out.println(time.end());
    }

}
