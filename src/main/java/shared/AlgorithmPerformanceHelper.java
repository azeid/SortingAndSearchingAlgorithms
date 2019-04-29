package shared;

import sortingAlgorithms.InsertionSort;
import sortingAlgorithms.MergeSort;
import sortingAlgorithms.QuickSort;
import sortingAlgorithms.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AlgorithmPerformanceHelper {
    public void testInsertionSort(int arr[],HashMap<String,String> Infolist, int size) {

        long start,finish,timeElapsed, totaltimeElapsed = 0;
        ArrayList<Long> totalTime= new ArrayList<Long>();
        String Algorithm= "InsertionSort_SortedInDescendingOrder"+Integer.toString(size);
        InsertionSort insert= new InsertionSort();
        for(int i=0;i<10;i++) {
            int []array=new int[size];
            array=Arrays.copyOf(arr, size);
            start = System.currentTimeMillis();
            insert.sortArray(array);
            finish = System.currentTimeMillis();
            timeElapsed = finish - start;
            System.out.println("InsertionSort time "+i+ ": " +timeElapsed);
            totalTime.add(timeElapsed);
            if(!isSorted(array)) {
                System.out.println("Array unsorted");
                System.exit(0);
            }
        }
        for(int j=0;j<totalTime.size();j++) {
            if(j>=3) {
                totaltimeElapsed+=totalTime.get(j);
            }
        }
        String time = "time: "+Long.toString(totaltimeElapsed/totalTime.size()-3);
        Infolist.put(Algorithm, time);
    }

    public void testMergeSort(int arr[],HashMap<String,String> Infolist, int size) {

        long start, finish, timeElapsed, totaltimeElapsed = 0;
        ArrayList<Long> totalTime = new ArrayList<Long>();
        String Algorithm = "MergeSort_" + Integer.toString(size);
        MergeSort merge = new MergeSort();
        for (int i = 0; i < 10; i++) {
            int[] array = Arrays.copyOf(arr, size);
            start = System.currentTimeMillis();
            merge.sortArray(array);
            finish = System.currentTimeMillis();
            timeElapsed = finish - start;
            System.out.println("MergeSort time " + i + ": " + timeElapsed);
            totalTime.add(timeElapsed);
            if (!isSorted(array)) {
                System.out.println("Array unsorted");
                System.exit(0);
            }
        }
        for (int j = 0; j < totalTime.size(); j++) {
            if (j >= 3) {
                totaltimeElapsed += totalTime.get(j);
            }
        }
        String time = "time: " + Long.toString(totaltimeElapsed / totalTime.size() - 3);
        Infolist.put(Algorithm, time);
    }
    public void testQuickSort(int arr[],HashMap<String,String> Infolist, int size) {

        long start,finish,timeElapsed, totaltimeElapsed =0;
        ArrayList<Long> totalTime= new ArrayList<Long>();
        String Algorithm= "QuickSort_"+Integer.toString(size);
        QuickSort quick= new QuickSort();
        for(int i=0;i<10;i++) {
            int []array= new int[size];
            array=Arrays.copyOf(arr, size);
            start = System.currentTimeMillis();
            quick.sortArray(array);
            finish = System.currentTimeMillis();
            timeElapsed = finish - start;
            System.out.println("QuickSort time "+i+ ": " +timeElapsed);
            totalTime.add(timeElapsed);
            if(!isSorted(array)) {
                System.out.println("Array unsorted");
                System.exit(0);
            }
        }
        for(int j=0;j<totalTime.size();j++) {
            if(j>=3) {
                totaltimeElapsed+=totalTime.get(j);
            }
        }
        String time = "time: "+Long.toString(totaltimeElapsed/totalTime.size()-3);
        Infolist.put(Algorithm, time);
    }
    public void testShellSort(int arr[],HashMap<String,String> Infolist, int size) {

        long start,finish,timeElapsed, totaltimeElapsed =0;
        ArrayList<Long> totalTime= new ArrayList<Long>();
        String Algorithm= "ShellSort_"+Integer.toString(size);
        ShellSort shell= new ShellSort();
        for(int i=0;i<10;i++) {
            int []array= new int[size];
            array=Arrays.copyOf(arr, size);
            start = System.currentTimeMillis();
            shell.sortArray(array);
            finish = System.currentTimeMillis();
            timeElapsed = finish - start;
            System.out.println("ShellSort time "+i+ ": " +timeElapsed);
            totalTime.add(timeElapsed);
            if(!isSorted(array)) {
                System.out.println("Array unsorted");
                System.exit(0);
            }
        }
        for(int j=0;j<totalTime.size();j++) {
            if(j>=3) {
                totaltimeElapsed+=totalTime.get(j);
            }
        }
        String time = "time: "+Long.toString(totaltimeElapsed/totalTime.size()-3);
        Infolist.put(Algorithm, time);
    }
    public void  testTimSort(int arr[],HashMap<String,String> Infolist, int size) {

        long start,finish,timeElapsed, totaltimeElapsed =0;
        ArrayList<Long> totalTime= new ArrayList<Long>();
        String Algorithm= "TimSort_SortedInDescendingOrder"+Integer.toString(size);
        TimSort tim= new TimSort();
        for(int i=0;i<10;i++) {
            int []array=new int[size];
            array=Arrays.copyOf(arr, size);
            start = System.currentTimeMillis();
            tim.sortArray(array);
            finish = System.currentTimeMillis();
            timeElapsed = finish - start;
            System.out.println("TimSort time "+i+ ": " +timeElapsed);
            totalTime.add(timeElapsed);
            if(!isSorted(array)) {
                System.out.println("Array unsorted");
                System.exit(0);
            }
        }
        for(int j=0;j<totalTime.size();j++) {
            if(j>=3) {
                totaltimeElapsed+=totalTime.get(j);
            }
        }
        String time = "time: "+Long.toString(totaltimeElapsed/totalTime.size()-3);
        Infolist.put(Algorithm, time);
    }
    public void testRadixSort(int arr[],HashMap<String,String> Infolist, int size) {

        long start,finish,timeElapsed, totaltimeElapsed =0;
        ArrayList<Long> totalTime= new ArrayList<Long>();
        String Algorithm= "Radix_"+Integer.toString(size);
        RadixSort radix= new RadixSort();
        for(int i=0;i<10;i++) {
            start = System.currentTimeMillis();
            radix.sortArray(Arrays.copyOf(arr, size));
            finish = System.currentTimeMillis();
            timeElapsed = finish - start;
            System.out.println("Radix time "+i+ ": " +timeElapsed);
            totalTime.add(timeElapsed);
        }
        for(int j=0;j<totalTime.size();j++) {
            if(j>=3) {
                totaltimeElapsed+=totalTime.get(j);
            }
        }
        String time = "time: "+Long.toString(totaltimeElapsed/totalTime.size()-3);
        Infolist.put(Algorithm, time);
    }
    public void testHeapSort(int arr[],HashMap<String,String> Infolist, int size) {

        long start,finish,timeElapsed, totaltimeElapsed =0;
        ArrayList<Long> totalTime= new ArrayList<Long>();
        String Algorithm= "Heap_"+Integer.toString(size);
        HeapSort heap= new HeapSort();
        for(int i=0;i<10;i++) {
            int []array=new int[size];
            array=Arrays.copyOf(arr, size);
            start = System.currentTimeMillis();
            heap.sortArray(array);
            finish = System.currentTimeMillis();
            timeElapsed = finish - start;
            System.out.println("Heap time "+i+ ": " +timeElapsed);
            totalTime.add(timeElapsed);
            if(!isSorted(array)) {
                System.out.println("Array unsorted");
                System.exit(0);
            }
        }
        for(int j=0;j<totalTime.size();j++) {
            if(j>=3) {
                totaltimeElapsed+=totalTime.get(j);
            }
        }
        String time = "time: "+Long.toString(totaltimeElapsed/totalTime.size()-3);
        Infolist.put(Algorithm, time);

    }

    public void TestJavaUtilSort(int arr[],HashMap<String,String> Infolist, int size) {
        long start,finish,timeElapsed, totaltimeElapsed =0;
        ArrayList<Long> totalTime= new ArrayList<Long>();
        String Algorithm= "JavaUtil_"+Integer.toString(size);

        for(int i=0;i<10;i++) {
            start = System.currentTimeMillis();
            Arrays.sort(Arrays.copyOf(arr, size));
            finish = System.currentTimeMillis();
            timeElapsed = finish - start;
            System.out.println("Heap time "+i+ ": " +timeElapsed);
            totalTime.add(timeElapsed);
        }
        for(int j=0;j<totalTime.size();j++) {
            if(j>=3) {
                totaltimeElapsed+=totalTime.get(j);
            }
        }
        String time = "time: "+Long.toString(totaltimeElapsed/totalTime.size()-3);
        Infolist.put(Algorithm, time);

    }
    public void testBubbleSort(int arr[],HashMap<String,String> Infolist, int size) {

        long start,finish,timeElapsed, totaltimeElapsed =0;
        ArrayList<Long> totalTime= new ArrayList<Long>();
        String Algorithm= "BubbleSort_"+Integer.toString(size);
        BubbleSort bubble= new BubbleSort();
        for(int i=0;i<10;i++) {
            int []array=new int[size];
            array=Arrays.copyOf(arr, size);
            start = System.currentTimeMillis();
            bubble.sortArray(array);
            finish = System.currentTimeMillis();
            timeElapsed = finish - start;
            System.out.println("BubbleSort  time "+i+ ": " +timeElapsed);
            totalTime.add(timeElapsed);
            if(!isSorted(array)) {
                System.out.println("Array unsorted");
                System.exit(0);
            }
        }
        for(int j=0;j<totalTime.size();j++) {
            if(j>=3) {
                totaltimeElapsed+=totalTime.get(j);
            }
        }
        String time = "time: "+Long.toString(totaltimeElapsed/totalTime.size()-3);
        Infolist.put(Algorithm, time);

    }

    public  boolean isSorted(int[] a)
    {
        int size= a.length;
        int []b= new int [size];
        b=a;
        int i;
        for(i = 0; i < b.length; i++){
            if (b[i] < b[i+1]) {
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
