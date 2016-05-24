
/**
 * Class to compute to get Permute(A, n, k): ordered sets of cardinality 
 * k from a set of size n.
 * G95_SP5
 * @author Ramakrishnan Sathyavageeswaran
 * @author Thiagarajan ramakrishnan
 * @author Jithendhiralal Ramlal
 */
public class SP5_b_NPK {

    int n;
    int k;

    public SP5_b_NPK(int n, int k) {
        this.n = n;
        this.k = k;
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

    public void visit(boolean[] A) {

        int[] per = new int[k];

        for (int i = 0,j=0; i < A.length; i++) {
            if (A[i]) {
                per[j++] = i;
                System.out.print(i);
            }
        }

        System.out.println();
        permutationUsingHeap(per,k);

    }

    public void permute(boolean A[], int n, int k) {

        if (k > n) {
            return;
        } else if (k == 0) {
            visit(A);
        } else {

            //case 1: n is not selected
            permute(A, n - 1, k);

            //case 2: n is selected
            A[n] = true;
            permute(A, n - 1, k - 1);
            
            //cleanup
            A[n] = false;
        }

    }

    public static void main(String[] args) {
        int n = 3;
        int k = 2;
        boolean[] A = new boolean[n + 1];
        SP5_b_NPK obj = new SP5_b_NPK(n, k);
        obj.permute(A, n, k);

    }

}
