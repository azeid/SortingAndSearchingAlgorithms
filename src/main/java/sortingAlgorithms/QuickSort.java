package sortingAlgorithms;

// Reference: https://www.geeksforgeeks.org/quick-sort/

import org.junit.jupiter.api.Test;
import shared.SharedFunctions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuickSort
{
   /* This function takes last element as pivot,
   places the pivot element at its correct
   position in sorted array, and places all
   smaller (smaller than pivot) to left of
   pivot and all greater elements to right
   of pivot */
   private static int partition(int arr[], int low, int high)
   {
      int pivot = arr[high];
      int i = (low-1); // index of smaller element
      for (int j=low; j<high; j++)
      {
         // If current element is smaller than or
         // equal to pivot
         if (arr[j] <= pivot)
         {
            i++;

            // swap arr[i] and arr[j]
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
         }
      }

      // swap arr[i+1] and arr[high] (or pivot)
      int temp = arr[i+1];
      arr[i+1] = arr[high];
      arr[high] = temp;

      return i+1;
   }


   /* The main function that implements QuickSort()
   arr[] --> Array to be sorted,
   low --> Starting index,
   high --> Ending index */
   private static void sort(int arr[], int low, int high)
   {
      if (low < high)
      {
			/* pi is partitioning index, arr[pi] is
			now at right place */
         int pi = partition(arr, low, high);

         // Recursively sort elements before
         // partition and after partition
         sort(arr, low, pi-1);
         sort(arr, pi+1, high);
      }
   }

   public static void sortArray(int arr[])
   {
      if(arr!=null && arr.length>0) {
         final int kStartIndex = 0;
         final int kEndIndex = arr.length - 1;
         sort(arr, kStartIndex, kEndIndex);
      }
   }

   @Test
   public void checkSortCorrectness()
   {
      final int kSize = 100;
      int[] arr = SharedFunctions.getRandomArray(kSize, -100, 100);

      int[] originalArrCopy = new int[arr.length];

      System.arraycopy(arr, 0, originalArrCopy, 0, arr.length);

      sortArray(arr);

      assertTrue(SharedFunctions.checksort(originalArrCopy, arr));
   }

   @Test public void checkSortPerformance()
   {
      final int kArraySize = 100000;
      final boolean kCheckCorrectness = false;
      SharedFunctions.benchmarkSortingAlgorithm(
            kArraySize, SharedFunctions.eSortingAlgorithm.kQuickSort,
            kCheckCorrectness);
   }
}
/*This code is contributed by Rajat Mishra */

