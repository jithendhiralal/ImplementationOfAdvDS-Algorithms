import java.text.DecimalFormat;
import java.util.*;

public class HeapSort<T> {
	/** Generic function for Heap Sort which uses Arraylist as collection
	 * 
	 * @param arr
	 */
	public void Sort(ArrayList<T> arr){
		PriorityQueue<ArrayList> queue = new PriorityQueue(arr);
		Iterator itr=queue.iterator();
		int i=0;
		while(itr.hasNext()){
			((ArrayList) arr).set(i++, (T) queue.remove());
		}
	}
	public static void main(String[] args) {
		int n = 20000000;// Runs for 20 million records
		ArrayList<Integer> arr = new ArrayList<>(n);
		for(int i=0;i<n;i++)
		{
			arr.add(n-i);
		}
		HeapSort<Integer> obj = new HeapSort();
		long startReadTime = System.nanoTime();    
		obj.Sort(arr);
        long totalLoadTime = System.nanoTime() - startReadTime;
        double totalLoadTimeInseconds = ((double)totalLoadTime / 1000000000);
        System.out.println("Time taken HeapSort " + new DecimalFormat("#.####").format(totalLoadTimeInseconds) + " Seconds");
	}

}
