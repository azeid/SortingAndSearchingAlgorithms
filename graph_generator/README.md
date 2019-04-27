Graph Generator
===============


Requirements
------------

* Python 3

* matplotlib 3.0.3


Usage
-----

```
usage: graph_generator.py [-h] [-a IGNORE_ALGORITHMS [IGNORE_ALGORITHMS ...]]
                          [-i IGNORE_INPUTS [IGNORE_INPUTS ...]]
                          [-n NORMALIZE] [-f LOG_FILE] [-l LOG_LEVEL]
                          data_file [data_file ...]

Graph generator for the Sorting Algorithms Benchmarking project.

positional arguments:
  data_file             List of data files to process.

optional arguments:
  -h, --help            show this help message and exit
  -a IGNORE_ALGORITHMS [IGNORE_ALGORITHMS ...], --ignore-algorithms IGNORE_ALGORITHMS [IGNORE_ALGORITHMS ...]
                        List of algorithms to ignore.
  -i IGNORE_INPUTS [IGNORE_INPUTS ...], --ignore-inputs IGNORE_INPUTS [IGNORE_INPUTS ...]
                        List of inputs to ignore.
  -n NORMALIZE, --normalize NORMALIZE
                        Algoritm to use to normalize the data.
  -f LOG_FILE, --log-file LOG_FILE
                        Logfile. Uses stdout by default.
  -l LOG_LEVEL, --log-level LOG_LEVEL
                        Verbosity level of the logger. Uses `WARN` by default.
```

For example, the following command runs the utility to plot all algorithms except Bubble sort and Insertion sort,
and it normalizes the results by the Java util Arrays sort:

```
python3 graph_generator.py ../results/BenchMark_results_3.txt -a BubbleSort InsertionSort -n JavaUtilArraysSort
```

