package sortingAlgorithms;

import org.junit.jupiter.api.Test;
import shared.SharedFunctions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BubbleSort
{
   static public void sortArray(int arr[])
   {
      if (null == arr)
      {
         throw new IllegalArgumentException("Input array cannot be null!");
      }
      if(arr.length>0) {

         int n = arr.length;

         for (int i = 0; i < n - 1; ++i) {
            // ith element is already in the correct position
            for (int j = 0; j < (n - i - 1); ++j) {
               if (arr[j] > arr[j + 1]) {
                  // Swap items
                  int temp = arr[j];
                  arr[j] = arr[j + 1];
                  arr[j + 1] = temp;
               }
            }
         }
      }
   }

   @Test public void checkSortCorrectness()
   {
      final int kSize = 100;
      int[] arr = SharedFunctions.getRandomArray(kSize, -100, 100);

      int[] originalArrCopy = new int[arr.length];

      System.arraycopy(arr, 0, originalArrCopy, 0, arr.length);

      sortArray(arr);

      assertTrue(SharedFunctions.checksort(originalArrCopy, arr));
   }

}
