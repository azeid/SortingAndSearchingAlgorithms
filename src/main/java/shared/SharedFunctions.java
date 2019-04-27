package shared;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SharedFunctions
{
   public static void printArrayToConsole(int arr[])
   {
      System.out.println(arrayToString(arr));
   }

   public static String arrayToString(int arr[])
   {
      StringBuilder sb = new StringBuilder();

      int size = arr.length;
      sb.append("Number of Elements: " + size);
      sb.append("\n");

      sb.append("[");

      for (int i = 0; i < size; ++i)
      {
         sb.append(arr[i]);

         if (i != (size - 1))
         {
            sb.append(", ");
         }
      }

      sb.append("]");

      return sb.toString();
   }

   public static boolean checksort(int[] pre, int[] post)
   {
      // 1. post has elements in sorted order <=
      // 2. pre and post have the same length
      // 3. pre is a permutation of post

      if (pre.length != post.length)
      {
         return false;
      }

      for (int i = 0; i < post.length - 1; i++)
      {
         if (post[i] > post[i + 1])
         {
            return false;
         }
      }

      for (int e : pre)
      {
         if (count(pre, e) != count(post, e))
         {
            return false;
         }
      }

      return true;
   }

   public static int count(int[] arr, int x)
   {
      // returns the #occurrences of x in arr
      int count = 0;
      for (int e : arr)
      {
         if (e == x) count++;
      }
      return count;
   }

   public static boolean checkIfArrayIsSorted(int[] arr)
   {
      for (int i = 0; i < arr.length - 1; ++i)
      {
         if (arr[i] > arr[i + 1])
         {
            return false;
         }
      }

      return true;
   }

   public static int[] readFromInputFile(String fileName)
   {
      BufferedReader reader;
      try (Stream<String> stream = Files.lines(Paths.get(fileName)))
      {
         // This remove data from stream object into fileLines
         final List<String> fileLines = stream.collect(Collectors.toList());

         final int kSize = fileLines.size();
         int arr[] = new int[kSize];

         for (int lineIndex = 0; lineIndex < kSize; ++lineIndex)
         {
            arr[lineIndex] = Integer.parseInt(fileLines.get(lineIndex));
         }

         return arr;

      } catch (Exception e)
      {
         System.out.println("Exception While Reading File: " + e.toString());
         System.exit(-1);
      }

      return null;
   }

   public static void writeToOutputFile(int arr[], String fileName)
   {
      try
      {
         BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

         StringBuilder sb = new StringBuilder();

         int size = arr.length;

         for (int i = 0; i < size; ++i)
         {
            sb.append(arr[i]);

            if (i != (size - 1))
            {
               sb.append("\n");
            }
         }

         writer.write(sb.toString());
         writer.close();
      } catch (Exception e)
      {
         System.out.println("Exception While Writing To File: " + e.toString());
         System.exit(-1);
      }

   }

   // Reference: https://www.mkyong.com/java/java-generate-random-integers-in-a-range/
   public static int getRandomNumberInRange(int min, int max)
   {
      if (min >= max)
      {
         throw new IllegalArgumentException("max must be greater than min");
      }

      Random r = new Random();
      return r.nextInt((max - min) + 1) + min;
   }

   public static int[] getRandomArray(int size, int lowerRange, int upperRange)
   {
      int[] arr = new int[size];

      for (int i = 0; i < arr.length; ++i)
      {
         arr[i] = SharedFunctions.getRandomNumberInRange(lowerRange, upperRange);
      }

      return arr;
   }

   @Test
   public void testRandomOrderCase()
   {
      final int kLowerRange = 0;
      final int kUpperRange = 1000;
      final int kSize = 100;

      int[] arr = getRandomArray(kSize, kLowerRange, kUpperRange);

      printArrayToConsole(arr);

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

   public static void swapRandomIndicesInArray(int[] arr, double percentageOfElements)
   {
      if (0 == arr.length)
      {
         throw new IllegalArgumentException("Array must not be empty!");
      }

      final int kNumberOfElementsToSwap = (int)((percentageOfElements/100) * arr.length);

      for (int i = 0; i < kNumberOfElementsToSwap; ++i)
      {
         // Get random index in range
         int firstIndex = getRandomNumberInRange(0, arr.length - 1);
         int secondIndex = getRandomNumberInRange(0, arr.length - 1);

         // swap elements
         int temp = arr[firstIndex];
         arr[firstIndex] = arr[secondIndex];
         arr[secondIndex] = temp;
      }
   }

   @Test
   public void testSwapRandomIndicesInArray()
   {
      int[] arr = new int[100];
      // Generic sort best case is when numbers are ordered
      for (int i = 0; i < arr.length; ++i)
      {
         arr[i] = i;
      }

      printArrayToConsole(arr);

      double percentage = 10;
      swapRandomIndicesInArray(arr, percentage);

      printArrayToConsole(arr);
   }
}
