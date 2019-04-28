package shared;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RunPerformanceAnalyzer {

    static HashMap<String,String> InfoList = new HashMap<String,String>();

    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("/Users/utkarsh/ALGORITHMS/Project/SortingAndSearchingAlgorithms/testCases/SortedInDescendingOrderCase_10000000.txt"));
        int [] arr = new int [10000000];
        int i = 0;
        while(scanner.hasNextInt())
        {
            arr[i++] = scanner.nextInt();
        }
        //int size= arr.length;
        //PerformanceAnalyzer pa = new PerformanceAnalyzer();
        //pa.testHeapSort(arr, InfoList, size);
        testThread(arr);
        for(String key: InfoList.keySet()) {
            System.out.println(key +" " +InfoList.get(key));
        }
    }

    // TODO Auto-generated method stub
    public static void testThread(int []arr) throws FileNotFoundException
    {
        int size= arr.length;
        AlgorithmPerformanceHelper helper = new AlgorithmPerformanceHelper();
        //.create a callable for each method
        Callable<Void> callable1 = new Callable<Void>()
        {
            @Override
            public Void call() throws Exception
            {
                //   pa.testInsertionSort(arr, InfoList, size);
                return null;
            }
        };

        Callable<Void> callable2 = new Callable<Void>()
        {
            @Override
            public Void call() throws Exception
            {
                helper.testMergeSort(arr, InfoList, size);
                return null;
            }
        };

        Callable<Void> callable3 = new Callable<Void>()
        {
            @Override
            public Void call() throws Exception
            {
                helper.TestJavaUtilSort(arr, InfoList, size);
                return null;
            }
        };
        Callable<Void> callable4 = new Callable<Void>()
        {
            @Override
            public Void call() throws Exception
            {
                helper.testShellSort(arr, InfoList, size);
                return null;
            }
        };
        Callable<Void> callable5 = new Callable<Void>()
        {
            @Override
            public Void call() throws Exception
            {
                helper.testTimSort(arr, InfoList, size);
                return null;
            }
        };
        Callable<Void> callable6 = new Callable<Void>()
        {
            @Override
            public Void call() throws Exception
            {
                helper.testRadixSort(arr, InfoList, size);
                return null;
            }
        };
        Callable<Void> callable7 = new Callable<Void>()
        {
            @Override
            public Void call() throws Exception
            {
                helper.testHeapSort(arr, InfoList, size);
                return null;
            }
        };
        Callable<Void> callable8 = new Callable<Void>()
        {
            @Override
            public Void call() throws Exception
            {
              //   helper.testQuickSort(arr, InfoList, size);
                return null;
            }
        };
        Callable<Void> callable9 = new Callable<Void>()
        {
            @Override
            public Void call() throws Exception
            {
              //  helper.testBubbleSort(arr, InfoList, size);
                return null;
            }
        };


        //add to a list
        List<Callable<Void>> taskList = new ArrayList<Callable<Void>>();
        taskList.add(callable1);
        taskList.add(callable2);
        taskList.add(callable3);
        taskList.add(callable4);
        taskList.add(callable5);
        taskList.add(callable6);
        taskList.add(callable7);
        taskList.add(callable8);
        taskList.add(callable9);

        //create a pool executor with 3 threads
        ExecutorService executor = Executors.newFixedThreadPool(9);

        try
        {
            //start the threads and wait for them to finish
            executor.invokeAll(taskList);
        }
        catch (InterruptedException ie)
        {
            //do something if you care about interruption;
        }

    }



}
