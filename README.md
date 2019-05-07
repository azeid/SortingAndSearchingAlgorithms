# SortingAndSearchingAlgorithms
This repository contains different sorting algorithm implementations in order to test how they perform against each other given different input test cases and data-set sizes. The project also generates the test cases used.

# IDE Used
Java and IntelliJ IDE will be used when working on this project

# Configuration Files
Please make sure not to submit IDE configuration files

#Test Cases Generator

```
Usage

java TestCasesGenerator TestCase NumberOfItems "outputFile.txt"
Example: TestCasesGenerator SortedInAscendingOrderCase 100 "Sorted_AscendingOrder_100.txt"
```

# Valid Test Cases
```
SortedInAscendingOrderCase
SortedInDescendingOrderCase
RandomOrderCase
RandomHighOnFirstHalfAndLowOnSecondHalf
RandomLowOnFirstHalfAndHighOnSecondHalf
AscendingOrderHighOnFirstHalfAndLowOnSecondHalf
DescendingOrderHighOnFirstHalfAndLowOnSecondHalf
NearlySortedInAscendingOrderCase
NearlySortedInDescendingOrderCase
SameValueCase
MergeSortWorstCase
GenerateAllTestCases
```

# Running Benchmarks
In order to run benchmarks, IntelliJ IDE must be used due to some dependencies required for the benchmarking library we are using. In IntelliJ IDE right, build bench component then right click on 'BenchmarkRunner.java' and Run it. Currently it takes ~36 hours to run all the cases.