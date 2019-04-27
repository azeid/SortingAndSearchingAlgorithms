package sortingAlgorithms;

import org.junit.jupiter.api.Test;
import shared.SharedFunctions;
import shared.TestCasesGenerator;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JavaUtilArraysSort
{
   public static void sortArray(int[] arr) {
      /* Arrays.sort() from Java Collections Framework, see
       * https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/Arrays.html#sort(int%5B%5D)
       *
       * The sorting algorithm is a Dual-Pivot Quicksort by Vladimir Yaroslavskiy, Jon Bentley, and Joshua Bloch. This
       * algorithm offers O(n log(n)) performance on many data sets that cause other quicksorts to degrade to quadratic
       * performance, and is typically faster than traditional (one-pivot) Quicksort implementations.
       *
       * pre: arr != null
       */
      if (null == arr )
      {
         throw new IllegalArgumentException("Input array cannot be null!");
      }
      if(arr.length>0) {
         // Sort the whole array
         Arrays.sort(arr);
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

   @Test public void checkSortPerformance()
   {
      final int kArraySize = 10000000;
      final boolean kCheckCorrectness = false;
      SharedFunctions.benchmarkSortingAlgorithm(
            kArraySize, SharedFunctions.eSortingAlgorithm.kJavaUtilSort,
            kCheckCorrectness);
   }
}


