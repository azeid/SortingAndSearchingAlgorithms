package bench;

import org.openjdk.jmh.annotations.*;
import sortingAlgorithms.*;
import shared.*;

import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Benchmark)
public class BenchmarkAllSortTestCases {

   private static int[] inputArray;

   @Param({
         "testCases/SortedInAscendingOrderCase_100.txt",
         "testCases/SortedInDescendingOrderCase_100.txt",
         "testCases/RandomOrderCase_100.txt",
         "testCases/RandomHighOnFirstHalfAndLowOnSecondHalf_100.txt",
         "testCases/RandomLowOnFirstHalfAndHighOnSecondHalf_100.txt",
         "testCases/AscendingOrderHighOnFirstHalfAndLowOnSecondHalf_100.txt",
         "testCases/DescendingOrderHighOnFirstHalfAndLowOnSecondHalf_100.txt",
         "testCases/NearlySortedInAscendingOrderCase_100.txt",
         "testCases/NearlySortedInDescendingOrderCase_100.txt",
         "testCases/SameValueCase_100.txt",
         "testCases/MergeSortWorstCase_100.txt",
         "testCases/SortedInAscendingOrderCase_1000.txt",
         "testCases/SortedInDescendingOrderCase_1000.txt",
         "testCases/RandomOrderCase_1000.txt",
         "testCases/RandomHighOnFirstHalfAndLowOnSecondHalf_1000.txt",
         "testCases/RandomLowOnFirstHalfAndHighOnSecondHalf_1000.txt",
         "testCases/AscendingOrderHighOnFirstHalfAndLowOnSecondHalf_1000.txt",
         "testCases/DescendingOrderHighOnFirstHalfAndLowOnSecondHalf_1000.txt",
         "testCases/NearlySortedInAscendingOrderCase_1000.txt",
         "testCases/NearlySortedInDescendingOrderCase_1000.txt",
         "testCases/SameValueCase_1000.txt",
         "testCases/MergeSortWorstCase_1000.txt",
         "testCases/SortedInAscendingOrderCase_10000.txt",
         "testCases/SortedInDescendingOrderCase_10000.txt",
         "testCases/RandomOrderCase_10000.txt",
         "testCases/RandomHighOnFirstHalfAndLowOnSecondHalf_10000.txt",
         "testCases/RandomLowOnFirstHalfAndHighOnSecondHalf_10000.txt",
         "testCases/AscendingOrderHighOnFirstHalfAndLowOnSecondHalf_10000.txt",
         "testCases/DescendingOrderHighOnFirstHalfAndLowOnSecondHalf_10000.txt",
         "testCases/NearlySortedInAscendingOrderCase_10000.txt",
         "testCases/NearlySortedInDescendingOrderCase_10000.txt",
         "testCases/SameValueCase_10000.txt",
         "testCases/MergeSortWorstCase_10000.txt",
         "testCases/SortedInAscendingOrderCase_100000.txt",
         "testCases/SortedInDescendingOrderCase_100000.txt",
         "testCases/RandomOrderCase_100000.txt",
         "testCases/RandomHighOnFirstHalfAndLowOnSecondHalf_100000.txt",
         "testCases/RandomLowOnFirstHalfAndHighOnSecondHalf_100000.txt",
         "testCases/AscendingOrderHighOnFirstHalfAndLowOnSecondHalf_100000.txt",
         "testCases/DescendingOrderHighOnFirstHalfAndLowOnSecondHalf_100000.txt",
         "testCases/NearlySortedInAscendingOrderCase_100000.txt",
         "testCases/NearlySortedInDescendingOrderCase_100000.txt",
         "testCases/SameValueCase_100000.txt",
         "testCases/MergeSortWorstCase_100000.txt",
         "testCases/SortedInAscendingOrderCase_1000000.txt",
         "testCases/SortedInDescendingOrderCase_1000000.txt",
         "testCases/RandomOrderCase_1000000.txt",
         "testCases/RandomHighOnFirstHalfAndLowOnSecondHalf_1000000.txt",
         "testCases/RandomLowOnFirstHalfAndHighOnSecondHalf_1000000.txt",
         "testCases/AscendingOrderHighOnFirstHalfAndLowOnSecondHalf_1000000.txt",
         "testCases/DescendingOrderHighOnFirstHalfAndLowOnSecondHalf_1000000.txt",
         "testCases/NearlySortedInAscendingOrderCase_1000000.txt",
         "testCases/NearlySortedInDescendingOrderCase_1000000.txt",
         "testCases/SameValueCase_1000000.txt",
         "testCases/MergeSortWorstCase_1000000.txt",
         /*
         "testCases/SortedInAscendingOrderCase_10000000.txt",
         "testCases/SortedInDescendingOrderCase_10000000.txt",
         "testCases/RandomOrderCase_10000000.txt",
         "testCases/RandomHighOnFirstHalfAndLowOnSecondHalf_10000000.txt",
         "testCases/RandomLowOnFirstHalfAndHighOnSecondHalf_10000000.txt",
         "testCases/AscendingOrderHighOnFirstHalfAndLowOnSecondHalf_10000000.txt",
         "testCases/DescendingOrderHighOnFirstHalfAndLowOnSecondHalf_10000000.txt",
         "testCases/NearlySortedInAscendingOrderCase_10000000.txt",
         "testCases/NearlySortedInDescendingOrderCase_10000000.txt",
         "testCases/SortedInAscendingOrderCase_100000000.txt",
         "testCases/SortedInDescendingOrderCase_100000000.txt",
         "testCases/RandomOrderCase_100000000.txt",
         "testCases/RandomHighOnFirstHalfAndLowOnSecondHalf_100000000.txt",
         "testCases/RandomLowOnFirstHalfAndHighOnSecondHalf_100000000.txt",
         "testCases/AscendingOrderHighOnFirstHalfAndLowOnSecondHalf_100000000.txt",
         "testCases/DescendingOrderHighOnFirstHalfAndLowOnSecondHalf_100000000.txt",
         "testCases/NearlySortedInAscendingOrderCase_100000000.txt",
         "testCases/NearlySortedInDescendingOrderCase_100000000.txt",
         */
   })
   public String fileName;

   @Setup
   public void loadData()
   {
      String filePath = System.getProperty("user.dir");
      filePath += "/"+fileName;
      //System.out.println("current dir = " + filePath);
      inputArray = SharedFunctions.readFromInputFile(filePath);
   }

   @Benchmark
   public void BubbleSort()
   {
      // Needed because we always want to start with the test case
      // Without this inputArray would be sorted the first time and
      // consecutive calls will be wrong.
      int[] tempArray = new int[inputArray.length];
      System.arraycopy(inputArray, 0, tempArray, 0, tempArray.length);

      BubbleSort.sortArray(tempArray);
   }

   @Benchmark
   public void InsertionSort()
   {
      // Needed because we always want to start with the test case
      // Without this inputArray would be sorted the first time and
      // consecutive calls will be wrong.
      int[] tempArray = new int[inputArray.length];
      System.arraycopy(inputArray, 0, tempArray, 0, tempArray.length);

      InsertionSort.sortArray(tempArray);
   }

   @Benchmark
   public void ShellSort()
   {
      // Needed because we always want to start with the test case
      // Without this inputArray would be sorted the first time and
      // consecutive calls will be wrong.
      int[] tempArray = new int[inputArray.length];
      System.arraycopy(inputArray, 0, tempArray, 0, tempArray.length);

      ShellSort.sortArray(tempArray);
   }

   @Benchmark
   public void TimSort()
   {
      // Needed because we always want to start with the test case
      // Without this inputArray would be sorted the first time and
      // consecutive calls will be wrong.
      int[] tempArray = new int[inputArray.length];
      System.arraycopy(inputArray, 0, tempArray, 0, tempArray.length);

      ShellSort.sortArray(tempArray);
   }

   @Benchmark
   public void HeapSort()
   {
      // Needed because we always want to start with the test case
      // Without this inputArray would be sorted the first time and
      // consecutive calls will be wrong.
      int[] tempArray = new int[inputArray.length];
      System.arraycopy(inputArray, 0, tempArray, 0, tempArray.length);

      ShellSort.sortArray(tempArray);
   }

   @Benchmark
   public void MergeSort()
   {
      // Needed because we always want to start with the test case
      // Without this inputArray would be sorted the first time and
      // consecutive calls will be wrong.
      int[] tempArray = new int[inputArray.length];
      System.arraycopy(inputArray, 0, tempArray, 0, tempArray.length);

      ShellSort.sortArray(tempArray);
   }

   @Benchmark
   public void QuickSort()
   {
      // Needed because we always want to start with the test case
      // Without this inputArray would be sorted the first time and
      // consecutive calls will be wrong.
      int[] tempArray = new int[inputArray.length];
      System.arraycopy(inputArray, 0, tempArray, 0, tempArray.length);

      ShellSort.sortArray(tempArray);
   }

   @Benchmark
   public void RadixSort()
   {
      // Needed because we always want to start with the test case
      // Without this inputArray would be sorted the first time and
      // consecutive calls will be wrong.
      int[] tempArray = new int[inputArray.length];
      System.arraycopy(inputArray, 0, tempArray, 0, tempArray.length);

      ShellSort.sortArray(tempArray);
   }

   @Benchmark
   public void JavaUtilArraysSort()
   {
      // Needed because we always want to start with the test case
      // Without this inputArray would be sorted the first time and
      // consecutive calls will be wrong.
      int[] tempArray = new int[inputArray.length];
      System.arraycopy(inputArray, 0, tempArray, 0, tempArray.length);

      JavaUtilArraysSort.sortArray(tempArray);
   }
}
