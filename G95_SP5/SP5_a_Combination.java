/**
 * Class to compute the Combination of k objects out of n numbers 
 * G95_SP5
 * @author Ramakrishnan Sathyavageeswaran
 * @author Thiagarajan ramakrishnan
 * @author Jithendhiralal Ramlal
 */
public class SP5_a_Combination {

    public SP5_a_Combination(boolean[] A) {
        for (int i = 0; i < A.length; ++i) {
            A[i] = false;
        }
    }

    public void combination(boolean[] A, int n, int k) {

        if (k > n) {
            return;
        } else if (k == 0) {
            visit(A);
        } else {
            //case 1: n is not selected
            combination(A, n - 1, k);

            A[n] = true;

            //case 2: n is selected
            combination(A, n - 1, k - 1);

            //cleanup
            A[n] = false;
        }

    }

    int count = 0;

    private void visit(boolean[] A) {

        for (int i = 1; i < A.length; i++) {

            if (A[i]) {
                System.out.print(i);
            }
        }
        count++;
        System.out.println();

    }

    public static void main(String[] args) {
        int n = 3;
        int k = 2;
        boolean[] A = new boolean[n + 1];
        SP5_a_Combination obj = new SP5_a_Combination(A);
        Timer time = new Timer();
        time.start();
        obj.combination(A, n, k);
        System.out.println(time.end());
    }

}
