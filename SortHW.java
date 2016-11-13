public class Sort {
  public static <AnyType extends Comparable<? super AnyType>>
    void heapSort(AnyType [] a) {
    // build heap 
    for (int i = a.length / 2 - 1; i >=0; i--) {
      percDown(a, i, a.length);
    }
    
    // after this step, we have a max heap
    // now we need to move max to the end array
    // and keep doing that until we get sorted array
    for (int i = a.length - 1; i > 0; i--) {
      printArray(a);
      // swap max to the last element
      swapRef(a, 0, i);
      //printArray(a);
      // percolate the first element
      percDown(a, 0, i);
    }
  }
  
  /**
   * percolate down 
   * @param a array of elements Comparable 
   * @param i the index of element we want to percolate 
   * @param n the size of array
   */
  public static <AnyType extends Comparable<? super AnyType>> 
  void percDown (AnyType [] a, int i, int n) {
    AnyType tmp;
    int child;
    tmp = a[i];
    
    while (leftChild(i) < n) {
      child = leftChild(i);
      // compare left child to right child
      if (child + 1 < n - 1 && a[child].compareTo(a[child + 1]) < 0) 
        child++;
      
      // compare the bigger child to their parent elements
      if (tmp.compareTo(a[child]) < 0) {
        a[i] = a[child];
        i = child;
      }
      else 
        break;
    }
    
    // percolate the original element to new index
    a[i] = tmp;
  }
  
  /** 
   * function return the left child index
   * @param i the index of parent element
   */
  public static Integer leftChild(int i) {
    return 2 * i + 1;
  }
  
  /**
   * swap two elements 
   * @param a just an array
   * @param i1 index of element1
   * @param i2 index of element2
   */
  public static <AnyType> void swapRef(AnyType [] a, Integer i1, Integer i2) {
    AnyType tmp = a[i1];
    a[i1] = a[i2];
    a[i2] = tmp;
  }
  
  /** 
   * merge sort
   * @param a an array needed to be sorted
   */
  public static <AnyType extends Comparable <? super AnyType>>
  void mergeSort(AnyType [] a) {
    int left = 0;
    int right = a.length - 1;
    
    mergeSort(a, left, right);
  }
  
  /** 
   * merge sort
   * @param a the subarray
   * @param left the left most element of the subarray
   * @param right the right most element of the subarray
   */
  public static <AnyType extends Comparable <? super AnyType>>
  void mergeSort(AnyType [] a, int left, int right) {  
    if (left < right) {
      // find the middle index of sub array
      int middle = (left + right) / 2;
      
      // divide the array into 2 sub arrays
      // divide the left sub array
      mergeSort(a, left, middle);
      
      // divide the right sub array
      mergeSort(a, middle + 1, right);
      
      // merge the left sub array and right sub array
      merge(a, left, middle, right);
    }
  }
  
  /** 
   * merge two sub arrays
   * @param a the array we want to merge
   * @param left left most element in array
   * @param right right most element in array
   * @param middle middle element in array
   */
  public static <AnyType extends Comparable <? super AnyType>>
  void merge(AnyType [] a, int left, int middle, int right) {
    int i = left;         // pointer 1 in left sub array
    int j = middle + 1;   // pointer 2 in right sub array
    int k = 0;
    int length = right - left + 1;    // length of the temporary sorted array
    
    // create the temporary sorted array
    AnyType [] tempArray = (AnyType []) new Comparable [length]; 
    
    //for (int q = left; q <= right; q++) {
    	//System.out.print(a[q] + " ");
    //}
    
    while (i <= middle && j <= right) { 
      // compare pointer 1 and 2 element
      if (a[i].compareTo(a[j]) < 0) { 
        tempArray[k] = a[i];
        i++;
      } else {
        tempArray[k] = a[j];
        j++;
      }
      k++;
    }
    
    // find out the rest of either left or right sub arrays
    while (k < length) {
    	if (i <= middle) {
    		tempArray[k] = a[i];
    		i++;
    	}
    
    	if (j <= right) {
    		tempArray[k] = a[j];
    		j++;
    	}
    	
    	k++;
    }
    
    //System.out.println();
    //System.out.println("temp array after sort: ");
    //printArray(tempArray);
    
    // assign elements in temporary array back to original array
    k = 0;
    while (k < length) {
      a[left + k] = tempArray[k];
      k++;
    }
  }      
  
  /**
   * Quick Sort 
   * @param a an array with need to sort
   */
  public static <AnyType extends Comparable <? super AnyType>> 
  void quickSort(AnyType [] a) {
	  quickSort(a, 0, a.length - 1);
  }
  
  
  public static <AnyType extends Comparable <? super AnyType>> 
  void quickSort(AnyType [] a, int left, int right) {
	  if (left < right) {
		  int pivotPtr = partition(a, left, right);
		  quickSort(a, left, pivotPtr);
		  quickSort(a, pivotPtr + 1, right);
	  }
  }
  
  
  /** 
   * quick sort with left and right index
   * @param a an array with need to sort
   * @param left the left pointer
   * @param right the right pointer
   */
  public static <AnyType extends Comparable <? super AnyType>>
  int partition(AnyType [] a, int left, int right) {
	  AnyType pivotVal = a[left];	// pivot value is the value of left pointer
	  int i = left; 				// the pointer that will go toward the right
	  int j = right; 				// the pointer that will go toward the left
	  //System.out.println(pivotVal);
	  while (i < j) {
		  while (a[i].compareTo(pivotVal) <= 0 && i <= a.length)
			  i++;
		  while (a[j].compareTo(pivotVal) > 0)
			  j--;
		  if (i < j) 
			  swapRef(a, i, j);
	  }
	  
	  swapRef(a, left, j);
	  //printArray(a);
	  return j;
  }
  
  
  /**
   * printout a given array
   * @param a just an array
   */
  public static <AnyType> void printArray(AnyType [] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.print(a[i] + " ");
    }
    System.out.println();
  }
  
  public static void main(String [] args) {
    Integer [] a = {4, 2, 9, 3, 1, 5, 8, 7, 6, 0};
    printArray(a);
    heapSort(a);
    printArray(a);
   
    /*
    System.out.println();
    Integer [] b = {4, 2, 9, 3, 1, 5, 8, 7, 6, 0};
    printArray(b);
    mergeSort(b);
    printArray(b);
    */
    
	/*
    System.out.println();
    Integer [] c = {4, 2, 9, 3, 1, 5, 8, 7, 6, 0};
    printArray(c);
    quickSort(c);
    printArray(c);
	*/
  }
}
  
