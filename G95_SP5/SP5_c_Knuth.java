/**
 * Class to compute Knuth's algorithm for generating permutations in
 * lexicographic order.
 * G95_SP5
 * @author Ramakrishnan Sathyavageeswaran
 * @author Thiagarajan ramakrishnan
 * @author Jithendhiralal Ramlal
 */
public class SP5_c_Knuth {

    public SP5_c_Knuth(int[] A) {

        for (int i = 0; i < A.length; ++i) {
            A[i] = i;
        }
    }

    public void permute(int[] A) {

        int n = A.length;
        int l = n - 1;
        int j = l - 1;

        visit(A, 1);

        while (true) {

            while (j > 0 && A[j] > A[j + 1]) {
                j--;
            }
            
            if (j == 0) {
                break;
            }
            
            while (A[j] > A[l]) {
                l--;
            }

            swap(A, j, l);

            int mid = (j + 1 + n) / 2;

            int t = 0;

            for (int i = j + 1; i < mid; i++) {
                int temp = A[i];
                A[i] = A[n - t - 1];
                A[n - t - 1] = temp;
                t++;
            }

            visit(A, 1);
            l = n - 1;
            j = l - 1;
        }
    }
    

    private void visit(int[] A, int n) {

        for (int i = n; i < A.length; i++) {
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
        int[] A = new int[n+1]; 
        SP5_c_Knuth obj = new SP5_c_Knuth(A);
        Timer timer = new Timer();
        obj.permute(A);
        System.out.println(timer.end());

    }

}
