package sortingAlgorithms;

import org.junit.jupiter.api.Test;
import shared.SharedFunctions;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Reference: https://www.geeksforgeeks.org/heap-sort/
public class HeapSort
{
   public static void sortArray(int arr[])
   {

      if( arr !=null && arr.length>0)
      {
         int n = arr.length;

         // Build heap (rearrange array)
         for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

         // One by one extract an element from heap
         for (int i = n - 1; i >= 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
         }
      }
   }

   // To heapify a subtree rooted with node i which is
   // an index in arr[]. n is size of heap
   private static void heapify(int arr[], int n, int i)
   {
      int largest = i; // Initialize largest as root
      int l = 2*i + 1; // left = 2*i + 1
      int r = 2*i + 2; // right = 2*i + 2

      // If left child is larger than root
      if (l < n && arr[l] > arr[largest])
         largest = l;

      // If right child is larger than largest so far
      if (r < n && arr[r] > arr[largest])
         largest = r;

      // If largest is not root
      if (largest != i)
      {
         int swap = arr[i];
         arr[i] = arr[largest];
         arr[largest] = swap;

         // Recursively heapify the affected sub-tree
         heapify(arr, n, largest);
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
            kArraySize, SharedFunctions.eSortingAlgorithm.kHeapSort,
            kCheckCorrectness);
   }
}

