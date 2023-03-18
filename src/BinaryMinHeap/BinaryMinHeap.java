public class BinaryMinHeap {

  int[] data;
  int heapSize; // Number of actual elements stored (starts at 1)

  public BinaryMinHeap(){
    this(32);
  }

  public BinaryMinHeap(int capacity) {
    data = new int[capacity+1];
    data[0] = Integer.MIN_VALUE; // index 0 unused.  This is a placeholder
    heapSize = 0;
  }

  public void add(int value){
	  if (heapSize >= data.length-1)
		throw new RuntimeException("Out of Capacity");
	  else{
		 heapSize++;
		 data[heapSize] = value;
		 siftUp(heapSize); // <-- heapSize is the index of new thing to siftUp
  	  }
  }

  private void siftUp(int index){
	  int pi, tmp;  // pi is parentIndex
	  if (index != 1){
		  pi = parentIndex(index);
		  if ( data[pi] > data[index]){
			  tmp = data[pi];
			  data[pi] = data[index];
			  data[index] = tmp;
			  siftUp(pi);
	  	}
     }
  }

  public int poll(){

	  if (isEmpty())
      	throw new RuntimeException("Empty Heap");
	  int min = data[1];

	  data[1] = data[heapSize];
	  heapSize--;
	  if (heapSize > 1)
	  	siftDown(1);  // sift downward the new element at index 1
	  return min;
}

private void siftDown(int index){
  int lci = leftChildIndex(index);
  int rci = rightChildIndex(index);
  int minIndex, tmp;
  i
  //check if element at index needs to move down
  // case 1: lci && rci are out of bounds --> return wihout doing anything
  //case 2: rci out of bounds --> set minIndex to lci
  // case 3: both children in bounds compare them
//      --> set minIndex to the smaller element value (if tied set lci)

  // if the element at index out of order with that at minIndex 
  //          ---> SWAP index && minIndex elements
  //          ---> siftDown at minIndex recursively 
  

}



  public int peek() {
    if (isEmpty())
      throw new RuntimeException("Empty Heap");
    else
      return data[1];
  }

  public boolean isEmpty() {
    return (heapSize == 0);
  }

  private static int leftChildIndex(int nodeIndex) {
    return 2 * nodeIndex;
  }

  private static int rightChildIndex(int nodeIndex) {
    return 2 * nodeIndex + 1;
  }

  private static int parentIndex(int nodeIndex) {
        return nodeIndex / 2;
  }


  /************   Iterative Implementation *******/
  public static boolean isMinHeap2(int[]arr){

    for(int i = 0; i<arr.length/2; i++){
      if(arr[i]>arr[leftChildIndex(i)] || arr[i] > arr[rightChildIndex(i)] )
        return false;
    }
    return true;
  }

  /************   Recursive Implementation *******/
  public static boolean isMinHeap(int[] arr ){
    return isMinHeap(arr ,1);
  }

 public static boolean isMinHeap(int[] arr , int i){
	// if 'i' is a leaf node, return true as every leaf node is a heap
	if (rightChildIndex(i) > arr.length) {
		return true;
 	}

	/**********   if 'i' is an internal node  **************/

	// recursively check if the left child is a heap
	boolean left = (arr [i] <= arr [leftChildIndex(i)]) && isMinHeap(arr , leftChildIndex(i));

	// recursively check if the right child is a heap
	boolean right =  (rightChildIndex(i) == arr.length  ||   // right true if child out of bounds
					 (arr [i] <= arr [rightChildIndex(i)] && isMinHeap(arr ,rightChildIndex(i))));

	// return true if both left and right child are heaps
	return left && right;
  }

  public static void main(String[]args){
    int[] heapArr =    {-1,10,28,15,33,51,18,19,65,60,98,83,27,20,60,23};
	int[] notHeapArr = {-1,10,28,15,33,83,18,19,65,60,98,51,27,20,60,23};
	System.out.println(BinaryMinHeap.isMinHeap(heapArr));
    System.out.println(BinaryMinHeap.isMinHeap2(heapArr));
	System.out.println(BinaryMinHeap.isMinHeap(notHeapArr));
    System.out.println(BinaryMinHeap.isMinHeap(notHeapArr));

  }

}