import java.util.PriorityQueue;
/* This code is from Geeks-for-Geeks 
https://www.geeksforgeeks.org/java-program-for-heap-sort/

NOTE: Because this is working with a pre-existing array and not a creating
a new data Structure the index 0 is assumed to be part of the array.  Therefore,
the math changes slightly such that:

Left Child -->  2*n + 1
Right Child --> 2*n + 2

HeapSort uses a Max Heap rather than a Min Heap

*/

// Java program for implementation of Heap Sort
public class HeapSort {
  public static void sort(int arr[]) {

    // Build heap (rearrange array) Doing the first half is sufficient
    // to ensure array in Heap order.
    int n = arr.length;
    for (int i = n / 2 - 1; i >= 0; i--)
      heapify(arr, n, i);

    // One by one extract an element from heap
    for (int i = n - 1; i >= 0; i--) {
      // Root will be the Max, move it to end
      int temp = arr[0];
      arr[0] = arr[i];
      arr[i] = temp;
      // Call max heapify on the reduced heap
      // this will exclude sorted portion at the end
      heapify(arr, i, 0);
    }
  }

  // To heapify a subtree rooted with node i which is
  // an index in arr[]. n is size of overall heap
  private static void heapify(int arr[], int n, int i) {
    int indexOfMax = i; // Initialize indexOfMax as root
    int l = 2 * i + 1; // left = 2*i + 1
    int r = 2 * i + 2; // right = 2*i + 2

    // If left child is larger than indexOfMax so far
    if (l < n && arr[l] > arr[indexOfMax])
      indexOfMax = l;

    // If right child is larger than indexOfMax so far
    if (r < n && arr[r] > arr[indexOfMax])
      indexOfMax = r;

    // If indexOfMax is not root
    if (indexOfMax != i) {
      int swap = arr[i];
      arr[i] = arr[indexOfMax];
      arr[indexOfMax] = swap;

      heapify(arr, n, indexOfMax);
    }
  }

  /* A utility function to print array of size n */
  public static void printArray(int arr[]) {
    int n = arr.length;
    for (int i = 0; i < n; ++i)
      System.out.print(arr[i] + " ");
    System.out.println();
  }

  // Insertion sort - just put this here for comparison
  public static void insertionSort(int arr[]) {
    int n = arr.length;
    for (int i = 1; i < n; ++i) {
      int key = arr[i];
      int j = i - 1;

      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j = j - 1;
      }
      arr[j + 1] = key;
    }
  }

  // Driver program
  public static void main(String args[]) {
    int n = 400000; // <-- Number of items in test Array
    int[] arr = new int[n];
    for (int i = 0; i < n; i++)
      arr[i] = (int) (Math.random() * 2 * n);

    if (n <= 100) {
      System.out.println("Unsorted array is");
      printArray(arr);
    }
    long startTime = System.currentTimeMillis();
    HeapSort.sort(arr);
    long heapSortTime = System.currentTimeMillis() - startTime;
    if (n <= 100) {
      System.out.println("Sorted array is");
      printArray(arr);
    }

    System.out.println("Heap Sort complete in " + heapSortTime + " ms.");

    // Reset everything for insertion sort comparison
    for (int i = 0; i < n; i++)
      arr[i] = (int) (Math.random() * 2 * n);

    if (n < 100) {
      System.out.println("Unsorted array is");
      printArray(arr);
    }
    startTime = System.currentTimeMillis();
    HeapSort.insertionSort(arr);
    long insertionSortTime = System.currentTimeMillis() - startTime;
    if (n < 100) {
      System.out.println("Sorted array is");
      printArray(arr);
    }

    System.out.println("Insertion Sort complete in " + insertionSortTime + " ms.");

    if (heapSortTime > 0)
      System.out.println("With n = " + n + " Heap sort runs " + (insertionSortTime / heapSortTime) + " times as fast");

    startTime = System.currentTimeMillis();
    int[] arrNew = new int[n];
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int x : arr) {
      pq.add(x);
    }
    for (int i = 0; i < n; i++) {
      arrNew[i] = pq.poll();
    }
    long pqTime = System.currentTimeMillis() - startTime;
    System.out.println(pqTime + " ms.");
  }

}