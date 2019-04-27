package sortingAlgorithms;

import org.junit.jupiter.api.Test;
import shared.SharedFunctions;

import static org.junit.jupiter.api.Assertions.assertTrue;

// Reference: https://github.com/Code2Bits/Algorithms-in-Java/blob/master/sort/src/main/java/com/code2bits/algorithm/sort/QuickSort.java
public class QuickSort
{
   public static void sortArray(int[] collection)
   {
      if (collection != null) {
         quickSort(collection, 0, collection.length-1);
      } else {
         throw new IllegalArgumentException("Input paramenter for array to sort is null.");
      }
   }

   private static void quickSort(int[] collection, int firstPosition, int lastPosition)
   {
      if (firstPosition >= lastPosition)
      {
         return;
      } else {
         int pivotIndex = partition(collection, firstPosition, lastPosition);
         quickSort(collection, firstPosition, pivotIndex-1);
         quickSort(collection, pivotIndex+1, lastPosition);
      }
   }


   private static int partition(int[] collection, int firstPosition, int lastPosition)
   {
      int pivotIndex = selectPivot(firstPosition, lastPosition);
      swap (collection, pivotIndex, lastPosition);
      int store = firstPosition;
      pivotIndex = lastPosition;
      for (int i = firstPosition; i <= lastPosition-1 ; i++) {
         if (collection[i] <= collection[pivotIndex]) {
            swap (collection, i, store);
            store++;
         }
      }
      swap (collection, store, pivotIndex);
      pivotIndex = store;
      return pivotIndex;
   }


   private static void swap(int[] collection, int x, int y)
   {
      int temp = collection[x];
      collection[x] = collection[y];
      collection[y] = temp;
   }

   private static int selectPivot(int first, int last)
   {
      return (first+last)/2;
   }

   @Test
   public void checkSortCorrectness()
   {
      final int kSize = 100000;
      int[] arr = SharedFunctions.getRandomArray(kSize, -100, 100);

      int[] originalArrCopy = new int[arr.length];

      System.arraycopy(arr, 0, originalArrCopy, 0, arr.length);

      sortArray(arr);

      assertTrue(SharedFunctions.checksort(originalArrCopy, arr));
   }

   @Test public void checkSortPerformance()
   {
      final int kArraySize = 1000000;
      final boolean kCheckCorrectness = false;
      SharedFunctions.benchmarkSortingAlgorithm(
            kArraySize, SharedFunctions.eSortingAlgorithm.kQuickSort,
            kCheckCorrectness);
   }
}

