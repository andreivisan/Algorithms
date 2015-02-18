import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;

/**
* Created by andrei on 2/17/15.
*/
public class CountInversions {

  private static BigDecimal total = new BigDecimal(0);

  public static void main(String[] args) {
    CountInversions ms = new CountInversions();
    int[] inputArray = new int[100000];
    try {
      inputArray = initializeInputArray("IntegerArray.txt");
    } catch (IOException e) {
      e.printStackTrace();
    }
    int[] sortedArray = ms.divideAndConquer(inputArray);
    System.out.println("Number of inversions: " + total);
  }

  public static int[] initializeInputArray(String fileName) throws NumberFormatException, IOException {
    FileInputStream fileInputStream = new FileInputStream(fileName);
    DataInputStream dataInputStream = new DataInputStream(fileInputStream);
    BufferedReader bufferReader = new BufferedReader(new InputStreamReader(dataInputStream));

    int[] inputArray = new int[100000];
    for(int i = 0; i < inputArray.length; i++) {
      String line = bufferReader.readLine();
      if(line != null) {
        inputArray[i] = Integer.parseInt(line);
      }
    }
    dataInputStream.close();
    return inputArray;
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
        total = total.add(new BigDecimal(leftArray.length - i));
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
