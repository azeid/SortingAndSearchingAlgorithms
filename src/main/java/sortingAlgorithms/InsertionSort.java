package sortingAlgorithms;

import org.junit.jupiter.api.Test;
import shared.SharedFunctions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InsertionSort
{
   public static void sortArray(int[] arr) {
      // insertion sort CLRS textbook
      // pre: arr != null
      if (null == arr )
      {
         throw new IllegalArgumentException("Input array cannot be null!");
      }
      if(arr.length>0)
      {
         // Sort the whole array
         final int kLeftIndex = 0;
         final int kRightInex = arr.length - 1;
         sortArrayInRange(arr, kLeftIndex, kRightInex);
      }
   }

   // This function was broken to be used in other sorting algorithm that has
   // insertion sort step.
   public static void sortArrayInRange(int[] arr, int leftIndex, int rightIndex)
   {
      // insertion sort CLRS textbook
      // pre: arr != null
      if (null == arr)
      {
         throw new IllegalArgumentException("Input array cannot be null!");
      }
      if(arr.length>0)
      {
         for (int i = leftIndex + 1; i <= rightIndex; ++i) {
            int key = arr[i];
            // insert arr[j] into the sorted sequence arr[0 .. j - 1]
            int j = i - 1;
            while (j >= leftIndex && arr[j] > key) {
               arr[j + 1] = arr[j];
               j--;
            }

            arr[j + 1] = key;
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

      //SharedFunctions.printArrayToConsole(originalArrCopy);
      //SharedFunctions.printArrayToConsole(arr);

      assertTrue(SharedFunctions.checksort(originalArrCopy, arr));
   }

   @Test public void checkSortCorrectnessComprehensive()
   {
      // Test multiple random inputs
      for (int i = 0; i < 100; ++i)
      {
         final int kSize = 100;
         int[] arr = SharedFunctions.getRandomArray(kSize, -100, 100);

         int[] originalArrCopy = new int[arr.length];

         System.arraycopy(arr, 0, originalArrCopy, 0, arr.length);

         sortArray(arr);

         assertTrue(SharedFunctions.checksort(originalArrCopy, arr));
      }
   }

   @Test public void TestNagativeSort() {
      int[] arr = {0, 1, 0, -1};
      sortArray(arr);
      assertArrayEquals(new int[]{-1, 0, 0, 1}, arr);

   }

   @Test public void TestEmptyArray() {
      int[] arr = {};
      sortArray(arr);
      assertArrayEquals(new int[]{}, arr);

   }

   @Test public void TestSortArrayInRange()
   {
      int[] arr = new int[] {3, 5, 2, 6, 8, 1, 4, 6};
      final int kLeftIndex = 2;
      final int kRightIndex = 5;
      sortArrayInRange(arr, kLeftIndex, kRightIndex);
      //SharedFunctions.printArrayToConsole(arr);
      assertArrayEquals(new int[]{3, 5, 1, 2, 6, 8, 4, 6}, arr);
   }
}


