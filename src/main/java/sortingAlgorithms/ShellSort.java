package sortingAlgorithms;

// https://www.tutorialspoint.com/data_structures_algorithms/shell_sort_algorithm.htm

import org.junit.jupiter.api.Test;
import shared.SharedFunctions;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


// The idea behind this sorting algorithm is to compare elements within a certain
// gap in case we have high numbers in the beginning or array and low number at
// the end which reduces the number of swapping and moving elements the way insertion
// sort works.
public class ShellSort
{
   public static void sortArray(int[] arr)
   {
      if (null == arr)
      {
         throw new IllegalArgumentException("Input array cannot be null!");
      }
      if(arr.length>0) {
         // Start with a large gap and keep reducing it until we reach 0
         for (int gap = (arr.length / 2); gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; ++i) {
               int temp = arr[i];

               // Compare i against elements that are within gap distance from it
               // and keep going until we either don't have elements within gap or
               // the number is not higher than our temp value.
               int j = i;
               for (; j >= gap && arr[j - gap] > temp; j -= gap) {
                  // If we get here, then arr[j - gap] is greater then temp
                  // Swap
                  arr[j] = arr[j - gap];
               }

               // Put temp in correct location
               arr[j] = temp;
            }
         }
      }
   }

   @Test
   public void checkSortCorrectness()
   {
      final int kSize = 10;
      int[] arr = SharedFunctions.getRandomArray(kSize, -100, 100);

      int[] originalArrCopy = new int[arr.length];

      System.arraycopy(arr, 0, originalArrCopy, 0, arr.length);

      sortArray(arr);

      assertTrue(SharedFunctions.checksort(originalArrCopy, arr));

      SharedFunctions.printArrayToConsole(originalArrCopy);
      SharedFunctions.printArrayToConsole(arr);
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
}
