
import java.io.*;
import java.util.*;
import java.math.*;
import java.text.DecimalFormat;
public class MergeSort<T extends Comparable<? super T>> {
	/**This is merge function which uses temp array to merge the two sorted arrays
	 * @param arr
	 * @param temp
	 * @param low
	 * @param mid
	 * @param high
	 */
	public void Merge(T[] arr, T[] temp, int low, int mid, int high){
		int i= low, j= mid+1,k;
		int n = high-low+1;
		//T[] b = (T[])new Comparable[n];
		for(k=0; k<n; k++){
			if(j > high){
				temp[k] = arr[i];
				i++;
			}
			else if(i > mid){
				temp[k] = arr[j];
			}
			else if(arr[i].compareTo(arr[j]) > 0){
				temp[k] = arr[j];
				j++;
			}
			else {
				temp[k] = arr[i];
				i++;
			}
		}
		for(k=0;k<n;k++){
			arr[low] = temp[k];
			low++;
		}
	}
	/**This is a Merge Sort function which uses divide and conquer method to sort the array
	 * @param arr
	 * @param temp
	 * @param low
	 * @param high
	 */
	public void Sort(T[] arr,T[] temp, int low, int high){
		if(low < high){
			int mid = (low+high)/2;
			Sort(arr, temp, low, mid);
			Sort(arr, temp, mid+1, high);
			Merge(arr, temp, low, mid, high);
		}
	}
	public static void main(String[] args) {
		int n = 20000000;// running for 20 million records
		Integer[] arr = new Integer[n];
		Integer[] temp = new Integer[n];
		for(int i=0;i<n;i++){
			arr[i] =n - i;
		}
		MergeSort<Integer> obj = new MergeSort<>();
		long startReadTime = System.nanoTime();    
		obj.Sort(arr, temp, 0, n-1);
        long totalLoadTime = System.nanoTime() - startReadTime;
        double totalLoadTimeInseconds = ((double)totalLoadTime / 1000000000);
        System.out.println("Time taken MergeSort " + new DecimalFormat("#.####").format(totalLoadTimeInseconds) + " Seconds");
	}

}
