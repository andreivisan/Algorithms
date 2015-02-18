public class MergeSort {

  public static void main(String[] args) {
    MergeSort ms = new MergeSort();
    int[] inputArray =  {1, 3, 5, 2, 4, 6};
    int[] sortedArray = ms.divideAndConquer(inputArray);
    for (int i=0; i<sortedArray.length; i++) {
      System.out.println(sortedArray[i]);
    }
  }

  public int[] divideAndConquer(int[] inputArray) {
    int n = inputArray.length;
    if(n == 1) {
      return inputArray;
    }
    int mid = n/2;
    int[] leftArray = new int[mid];
    int[] rightArray = new int[n - mid];
    System.arraycopy(inputArray, 0, leftArray, 0, leftArray.length);
    System.arraycopy(inputArray, leftArray.length, rightArray, 0, rightArray.length);
    divideAndConquer(leftArray);
    divideAndConquer(rightArray);
    merge(leftArray, rightArray, inputArray);
    return inputArray;
  }

  public void merge(int[] leftArray,
  int[] rightArray,
  int[] sortedArray) {
    int leftArrayLength = leftArray.length;
    int rightArrayLength = rightArray.length;
    int i = 0;
    int j = 0;
    int k = 0;
    while(i < leftArrayLength && j < rightArrayLength) {
      if(leftArray[i] < rightArray[j]) {
        sortedArray[k] = leftArray[i];
        i++;
      } else {
        sortedArray[k] = rightArray[j];
        j++;
      }
      k++;
    }
    while(i < leftArrayLength) {
      sortedArray[k] = leftArray[i];
      i++;
      k++;
    }
    while(j < rightArrayLength) {
      sortedArray[k] = rightArray[j];
      j++;
      k++;
    }
  }

}
