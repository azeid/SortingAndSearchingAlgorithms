package shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCasesGenerator
{

   static final String kUsage = new String("Usage: TestCasesGenerator TestCase NumberOfItems \"outputFile.txt\"");
   static final String kExampleUsage = new String(
         "Example: TestCasesGenerator SortedInAscendingOrderCase 100 \"Sorted_AscendingOrder_100.txt\"");

   // TODO: add new cases here
   static final String kSortedInAscendingOrderCase = new String("SortedInAscendingOrderCase");
   static final String kSortedInDescendingOrderCase = new String("SortedInDescendingOrderCase");
   static final String kRandomOrderCase = new String("RandomOrderCase");
   static final String kRandomHighOnFirstHalfAndLowOnSecondHalf = new String("RandomHighOnFirstHalfAndLowOnSecondHalf");
   static final String kRandomLowOnFirstHalfAndHighOnSecondHalf = new String("RandomLowOnFirstHalfAndHighOnSecondHalf");
   static final String kAscendingOrderHighOnFirstHalfAndLowOnSecondHalf = new String("AscendingOrderHighOnFirstHalfAndLowOnSecondHalf");
   static final String kDescendingOrderHighOnFirstHalfAndLowOnSecondHalf = new String("DescendingOrderHighOnFirstHalfAndLowOnSecondHalf");
   static final String kNearlySortedInAscendingOrderCase = new String("NearlySortedInAscendingOrderCase");
   static final String kNearlySortedInDescendingOrderCase = new String("NearlySortedInDescendingOrderCase");
   static final String kSameValueCase = new String("SameValueCase");
   static final String kMergeSortWorstCase = new String("MergeSortWorstCase");
   static final String kGenerateAllTestCases = new String("GenerateAllTestCases");

   // TODO: add new cases here
   static final String[] kValidTestCases = new String[]{
         kSortedInAscendingOrderCase,
         kSortedInDescendingOrderCase,
         kRandomOrderCase,
         kRandomHighOnFirstHalfAndLowOnSecondHalf,
         kRandomLowOnFirstHalfAndHighOnSecondHalf,
         kAscendingOrderHighOnFirstHalfAndLowOnSecondHalf,
         kDescendingOrderHighOnFirstHalfAndLowOnSecondHalf,
         kNearlySortedInAscendingOrderCase,
         kNearlySortedInDescendingOrderCase,
         kSameValueCase,
         kMergeSortWorstCase,
         kGenerateAllTestCases,
   };

   public static String getValidTestCases()
   {
      StringBuilder sb = new StringBuilder();

      int size = kValidTestCases.length;

      for (int i = 0; i < size; ++i)
      {
         sb.append(kValidTestCases[i]);

         if (i != (size - 1))
         {
            sb.append(" ,");
         }
      }

      return sb.toString();
   }

   public static void generateTestCases(String testCase, int[] arr)
   {
      if (0 == testCase.compareToIgnoreCase(kSortedInAscendingOrderCase))
      {
         generateSortedInAscendingOrderCase(arr);
      } else if (0 == testCase.compareToIgnoreCase(kSortedInDescendingOrderCase))
      {
         generateSortedInDescendingOrderCase(arr);
      } else if (0 == testCase.compareToIgnoreCase(kRandomOrderCase))
      {
         generateRandomOrderCase(arr);
      } else if (0 == testCase.compareToIgnoreCase(kRandomHighOnFirstHalfAndLowOnSecondHalf))
      {
         generateRandomHighOnFirstHalfAndLowOnSecondHalf(arr);
      } else if (0 == testCase.compareToIgnoreCase(kRandomLowOnFirstHalfAndHighOnSecondHalf))
      {
         generateRandomLowOnFirstHalfAndHighOnSecondHalf(arr);
      } else if (0 == testCase.compareToIgnoreCase(kAscendingOrderHighOnFirstHalfAndLowOnSecondHalf))
      {
         generateAscendingOrderHighOnFirstHalfAndLowOnSecondHalf(arr);
      } else if (0 == testCase.compareToIgnoreCase(kDescendingOrderHighOnFirstHalfAndLowOnSecondHalf))
      {
         generateDescendingOrderHighOnFirstHalfAndLowOnSecondHalf(arr);
      } else if (0 == testCase.compareToIgnoreCase(kNearlySortedInAscendingOrderCase))
      {
         generateNearlySortedInAscendingOrderCase(arr);
      } else if (0 == testCase.compareToIgnoreCase(kNearlySortedInDescendingOrderCase))
      {
         generateNearlySortedInDescendingOrderCase(arr);
      } else if (0 == testCase.compareToIgnoreCase(kSameValueCase))
      {
         generateSameValueCase(arr);
      } else if (0 == testCase.compareToIgnoreCase(kMergeSortWorstCase))
      {
         generateMergeSortWorstCase(arr);
      } else
      {
         System.out.println("Invalid Inputs");
         System.out.println(kUsage);
         System.out.println(kExampleUsage);
         System.out.println("Valid TestCases Inputs: " + getValidTestCases());
         System.exit(-1);
      }

   }

   // Usage: TestCasesGenerator TestCase NumberOfItems "OutputFile.txt"
   // Example: TestCasesGenerator Generic_Sort_BestCase 10 "Generic_Sort_BestCase_10.txt"
   public static void main(String args[])
   {
      final int kExpectedNumberOfArguments = 3;

      String testCase = new String();

      if (args.length != 0)
      {
         testCase = args[0];
      }

      if (kExpectedNumberOfArguments == args.length)
      {
         final int kDataSetSize = Integer.parseInt(args[1]);
         final String kOutputFileName = args[2];

         int[] arr = new int[kDataSetSize];

         generateTestCases(testCase, arr);

         // Write case to file.
         SharedFunctions.writeToOutputFile(arr, kOutputFileName);
      } else if (0 == testCase.compareToIgnoreCase(kGenerateAllTestCases) || 0 == args.length)
      {
         // Will use one command to generate all test cases since it is
         // faster and can be automated.

         // 1 to 1 million
         final int[] kNumberOfElements = new int[]{
               //1,
               //10,
               100,
               1000,
               10000,
               100000,
               1000000,
               //10000000,
               //100000000,
               //1000000000
         };

         final String[] kTempTestCases = new String[]{
               kSameValueCase,
               kMergeSortWorstCase,
               kGenerateAllTestCases
         };

         for (int numberOfDataSizes = 0; numberOfDataSizes < kNumberOfElements.length; ++numberOfDataSizes)
         {

            final int kCurrentDataSize = kNumberOfElements[numberOfDataSizes];
            int[] arr = new int[kCurrentDataSize];
            // exclude the case for kGenerateAllTestCases
            for (int numberOfTestCases = 0; numberOfTestCases < (kTempTestCases.length - 1); ++numberOfTestCases)
            {
               final String kCurrentTestCase = kTempTestCases[numberOfTestCases];

               // build file name
               // format: TestCase_NumberOfElements.txt
               // Example: SortedInAscendingOrderCase_100.txt
               StringBuilder outputFileName = new StringBuilder();
               outputFileName.append(kCurrentTestCase);
               outputFileName.append("_");
               outputFileName.append(Integer.toString(kCurrentDataSize));
               outputFileName.append(".txt");

               System.out.println(outputFileName.toString());

               generateTestCases(kCurrentTestCase, arr);

               // Write case to file.
               SharedFunctions.writeToOutputFile(arr, outputFileName.toString());
            }
         }
      } else
      {
         System.out.println("Invalid Number of Arguments");
         System.out.println(kUsage);
         System.out.println(kExampleUsage);
         System.out.println("Valid TestCases Inputs: " + getValidTestCases());
         System.exit(-1);
      }
   }

   public static void generateSortedInAscendingOrderCase(int[] arr)
   {
      // Generic sort best case is when numbers are ordered
      for (int i = 0; i < arr.length; ++i)
      {
         arr[i] = i;
      }
   }

   @Test
   public void verifySortedInAscendingOrderCaseOutput()
   {
      int[] sortedInAscending = {0, 1, 2, 3};
      int[] arr = new int[4];

      generateSortedInAscendingOrderCase(arr);

      assertArrayEquals(sortedInAscending, arr);
   }

   public static void generateSortedInDescendingOrderCase(int[] arr)
   {
      // Generic sort best case is when numbers are ordered
      for (int i = (arr.length - 1), j = 0; i >= 0; --i, ++j)
      {
         arr[j] = i;
      }
   }

   @Test
   public void verifySortedInDescendingOrderCaseOutput()
   {
      int[] sortedInAscending = {3, 2, 1, 0};
      int[] arr = new int[4];

      generateSortedInDescendingOrderCase(arr);

      assertArrayEquals(sortedInAscending, arr);
   }

   public static void generateRandomOrderCase(int[] arr)
   {
      final int kLowerRange = 0;
      final int kUpperRange = 1000;
      final int kSize = arr.length;
      int[] tempArr = SharedFunctions.getRandomArray(kSize, kLowerRange, kUpperRange);
      System.arraycopy(tempArr, 0, arr, 0, kSize);
   }

   @Test
   public void testRandomOrderCase()
   {
      int[] arr = new int[10];
      generateRandomOrderCase(arr);
      SharedFunctions.printArrayToConsole(arr);

      // Make sure array is not in ascending or descending order and they are not the same
      boolean numbersAreInAscendingOrder = true;
      for (int i = 0; i < arr.length - 1; ++i)
      {
         if (arr[i] > arr[i + 1])
         {
            numbersAreInAscendingOrder = false;
            break;
         }
      }

      boolean numbersAreInDescendingOrder = true;
      for (int i = 0; i < arr.length - 1; ++i)
      {
         if (arr[i] < arr[i + 1])
         {
            numbersAreInDescendingOrder = false;
            break;
         }
      }

      boolean numbersreTheSame = true;
      for (int i = 0; i < arr.length - 1; ++i)
      {
         if (arr[i] != arr[i + 1])
         {
            numbersreTheSame = false;
            break;
         }
      }

      assertTrue(!numbersAreInAscendingOrder && !numbersAreInDescendingOrder && !numbersreTheSame);
   }

   public static void generateRandomHighOnFirstHalfAndLowOnSecondHalf(int[] arr)
   {
      // This case needs high numbers on the first half of array and
      // lower numbers on the second half of array
      final int kArrayHalfCutOff = arr.length / 2;
      for (int i = 0; i < arr.length; ++i)
      {
         if (i < kArrayHalfCutOff)
         {
            // Lower half. Put high numbers
            arr[i] = SharedFunctions.getRandomNumberInRange(10000, 1000000);
         } else
         {
            // Upper half (i >= arr.length/2 ). Put low numbers
            arr[i] = SharedFunctions.getRandomNumberInRange(0, 1000);
         }
      }
   }

   @Test
   public void verifyRandomHighOnFirstHalfAndLowOnSecondHalfOutput()
   {
      int[] arr = new int[20];

      generateRandomHighOnFirstHalfAndLowOnSecondHalf(arr);

      final int kArrayHalfCutOff = arr.length / 2;

      for (int i = 0; i < arr.length; ++i)
      {
         if (i < kArrayHalfCutOff)
         {
            // Lower half. Must be high numbers
            for (int j = (arr.length - 1); j >= arr.length / 2; --j)
            {
               // check that all numbers in the upper half are lower
               assertTrue(arr[i] > arr[j]);
            }
         } else
         {
            // Upper half (i >= arr.length/2 ). Must be low numbers
            for (int j = 0; j < kArrayHalfCutOff; ++j)
            {
               // check that all numbers in the upper half are lower
               assertTrue(arr[i] < arr[j]);
            }
         }
      }
   }

   public static void generateRandomLowOnFirstHalfAndHighOnSecondHalf(int[] arr)
   {
      // This case needs high numbers on the first half of array and
      // lower numbers on the second half of array
      final int kArrayHalfCutOff = arr.length / 2;
      for (int i = 0; i < arr.length; ++i)
      {
         if (i < kArrayHalfCutOff)
         {
            // Lower half. Put low numbers
            arr[i] = SharedFunctions.getRandomNumberInRange(0, 1000);
         } else
         {
            // Upper half (i >= arr.length). Put high numbers
            arr[i] = SharedFunctions.getRandomNumberInRange(10000, 1000000);
         }
      }
   }

   @Test
   public void verifyRandomLowOnFirstHalfAndHighOnSecondHalfOutput()
   {
      int[] arr = new int[10];

      generateRandomLowOnFirstHalfAndHighOnSecondHalf(arr);

      final int kArrayHalfCutOff = arr.length / 2;

      for (int i = 0; i < arr.length; ++i)
      {
         if (i < kArrayHalfCutOff)
         {
            // Lower half. Must be low numbers
            for (int j = (arr.length - 1); j >= arr.length / 2; --j)
            {
               // check that all numbers in the upper half are higher
               assertTrue(arr[i] < arr[j]);
            }
         } else
         {
            // Upper half (i >= arr.length/2 ). Must be high numbers
            for (int j = 0; j < kArrayHalfCutOff; ++j)
            {
               // check that all numbers in the upper half are higher
               assertTrue(arr[i] > arr[j]);
            }
         }
      }
   }

   public static void generateAscendingOrderHighOnFirstHalfAndLowOnSecondHalf(int[] arr)
   {
      // This case needs high numbers on the first half of array and
      // lower numbers on the second half of array
      final int kArrayHalfCutOff = arr.length / 2;
      for (int i = 0, j = (arr.length - 1); i < arr.length; ++i, --j)
      {
         if (i < kArrayHalfCutOff)
         {
            // Lower half. Put high numbers
            arr[i] = j;
         } else
         {
            // Upper half (i >= arr.length). Put low numbers
            arr[i] = kArrayHalfCutOff - j - 1;
         }
      }
   }

   @Test
   public void verifyAscendingOrderHighOnFirstHalfAndLowOnSecondHalfOutput()
   {
      final int[] kExpectedOutput = new int[]{9, 8, 7, 6, 5, 0, 1, 2, 3, 4};

      int[] arr = new int[10];
      generateAscendingOrderHighOnFirstHalfAndLowOnSecondHalf(arr);
      assertArrayEquals(kExpectedOutput, arr);
   }

   public static void generateDescendingOrderHighOnFirstHalfAndLowOnSecondHalf(int[] arr)
   {
      // This case needs high numbers on the first half of array and
      // lower numbers on the second half of array
      final int kArrayHalfCutOff = arr.length / 2;
      for (int i = 0, j = (arr.length - 1); i < arr.length; ++i, --j)
      {
         if (i < kArrayHalfCutOff)
         {
            // Lower half. Put high numbers
            arr[i] = kArrayHalfCutOff + i;
         } else
         {
            // Upper half (i >= arr.length). Put low numbers
            arr[i] = j;
         }
      }
   }

   @Test
   public void verifyDescendingOrderHighOnFirstHalfAndLowOnSecondHalf()
   {
      final int[] kExpectedOutput = new int[]{5, 6, 7, 8, 9, 4, 3, 2, 1, 0};

      int[] arr = new int[10];
      generateDescendingOrderHighOnFirstHalfAndLowOnSecondHalf(arr);
      assertArrayEquals(kExpectedOutput, arr);
   }

   public static void generateNearlySortedInAscendingOrderCase(int[] arr)
   {
      // Generic sort best case is when numbers are ordered
      for (int i = 0; i < arr.length; ++i)
      {
         arr[i] = i;
      }

      final double kPercentageToSwap = 10;
      SharedFunctions.swapRandomIndicesInArray(arr, kPercentageToSwap);
   }

   @Test
   public void verifygenerateNearlySortedInAscendingOrderCase()
   {
      int[] arr = new int[100];
      generateNearlySortedInAscendingOrderCase(arr);

      SharedFunctions.printArrayToConsole(arr);

      // Make sure array is not in ascending or descending order and they are not the same
      boolean numbersAreInAscendingOrder = true;
      for (int i = 0; i < arr.length - 1; ++i)
      {
         if (arr[i] > arr[i + 1])
         {
            numbersAreInAscendingOrder = false;
            break;
         }
      }

      boolean numbersAreInDescendingOrder = true;
      for (int i = 0; i < arr.length - 1; ++i)
      {
         if (arr[i] < arr[i + 1])
         {
            numbersAreInDescendingOrder = false;
            break;
         }
      }

      boolean numbersreTheSame = true;
      for (int i = 0; i < arr.length - 1; ++i)
      {
         if (arr[i] != arr[i + 1])
         {
            numbersreTheSame = false;
            break;
         }
      }

      assertTrue(!numbersAreInAscendingOrder && !numbersAreInDescendingOrder && !numbersreTheSame);
   }

   public static void generateNearlySortedInDescendingOrderCase(int[] arr)
   {
      // Generic sort best case is when numbers are ordered
      for (int i = (arr.length - 1), j = 0; i >= 0; --i, ++j)
      {
         arr[j] = i;
      }

      final double kPercentageToSwap = 10;
      SharedFunctions.swapRandomIndicesInArray(arr, kPercentageToSwap);
   }

   @Test
   public void verifygenerateNearlySortedInDescendingOrderCase()
   {
      int[] arr = new int[100];
      generateNearlySortedInDescendingOrderCase(arr);

      SharedFunctions.printArrayToConsole(arr);

      // Make sure array is not in ascending or descending order and they are not the same
      boolean numbersAreInAscendingOrder = true;
      for (int i = 0; i < arr.length - 1; ++i)
      {
         if (arr[i] > arr[i + 1])
         {
            numbersAreInAscendingOrder = false;
            break;
         }
      }

      boolean numbersAreInDescendingOrder = true;
      for (int i = 0; i < arr.length - 1; ++i)
      {
         if (arr[i] < arr[i + 1])
         {
            numbersAreInDescendingOrder = false;
            break;
         }
      }

      boolean numbersreTheSame = true;
      for (int i = 0; i < arr.length - 1; ++i)
      {
         if (arr[i] != arr[i + 1])
         {
            numbersreTheSame = false;
            break;
         }
      }

      assertTrue(!numbersAreInAscendingOrder && !numbersAreInDescendingOrder && !numbersreTheSame);
   }

   public static void generateSameValueCase(int[] arr)
   {
      final int kConstValue = 10;
      for (int i = 0; i < arr.length - 1; ++i)
      {
         arr[i] = kConstValue;
      }
   }

   @Test
   public void verifySameValueCase()
   {
      int[] arr = new int[20];
      generateSameValueCase(arr);

      final int kFirstValue = arr[0];
      for (int i = 1; i < arr.length - 1; ++i)
      {
         assertTrue(kFirstValue == arr[i]);
      }
   }

   // Function to generate Worst Case of Merge Sort
   static void generateMergeSortWorstCase(int arr[])
   {
      // First generate values in ascending order before
      // building the worst case.
      generateSortedInAscendingOrderCase(arr);
      generateMergeSortWorstCaseHelperFunction(arr, 0, arr.length - 1);
   }

   // Reference: https://www.geeksforgeeks.org/find-a-permutation-that-causes-worst-case-of-merge-sort/
   // Function to join left and right subarray
   static void join(int arr[], int left[], int right[],
                    int l, int m, int r)
   {
      int i;
      for (i = 0; i <= m - l; i++)
         arr[i] = left[i];

      for (int j = 0; j < r - m; j++)
         arr[i + j] = right[j];
   }

   // Function to store alternate elemets in left
   // and right subarray
   static void split(int arr[], int left[], int right[],
                     int l, int m, int r)
   {
      for (int i = 0; i <= m - l; i++)
         left[i] = arr[i * 2];

      for (int i = 0; i < r - m; i++)
         right[i] = arr[i * 2 + 1];
   }

   // Function to generate Worst Case of Merge Sort
   static void generateMergeSortWorstCaseHelperFunction(int arr[], int l, int r)
   {
      if (l < r)
      {
         int m = l + (r - l) / 2;

         // create two auxillary arrays
         int[] left = new int[m - l + 1];
         int[] right = new int[r - m];

         // Store alternate array elements in left
         // and right subarray
         split(arr, left, right, l, m, r);

         // Recurse first and second halves
         generateMergeSortWorstCaseHelperFunction(left, l, m);
         generateMergeSortWorstCaseHelperFunction(right, m + 1, r);

         // join left and right subarray
         join(arr, left, right, l, m, r);
      }
   }

   // Reference: https://www.geeksforgeeks.org/find-a-permutation-that-causes-worst-case-of-merge-sort/
   @Test public void verifyMergeSortWorstCaseGenerator()
   {
      // sorted array
      int arr[] = new int[16];
      generateMergeSortWorstCase(arr);
      final int[] kExpectedOutput = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
      SharedFunctions.printArrayToConsole(arr);
      assertArrayEquals(kExpectedOutput, arr);
   }
}