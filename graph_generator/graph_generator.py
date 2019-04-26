##
# Graph generator for the Sorting Algorithms Benchmarking project.
#
# Author: Jose Carlos Martinez Garcia-Vaso
#

import argparse
import logging
import subprocess
import csv
import re
import matplotlib
matplotlib.use('Agg')   # Force matplotlib to not use any Xwindows backend.
import matplotlib.pyplot as plt


def parse_file(filename):
    """ Parse data files.

        The data in the files is expected to be organized as a CSV separated by whitespace with the following columns:
         * benchmark: we can get the algorithm name from here, e.i. BenchmarkAllSortTestCases.BubbleSort.
         * filename: we can get the array size and input type from here, e.i. testCases/RandomOrderCase_100.txt.
         * mode: ignore this column.
         * cnt: ignore this column.
         * score: we can get the sorting time from here, e.i. 0.002.
         * +-: ignore this column.
         * Error: ignore this column.
         * Units: ignore this column.

        The data is parsed to the following format:

        { input1: { alg1: { array_size: [ ], sorting_time: [ ] }, alg2: { ... }, ... , algn: { ... } },
          input2: { ... }, ... , inputn: { ... } }

        :param      file    Data file to parse from the `../results` directory.
        :returns    A dictionary of the data.
    """
    if not filename:
        return None

    # Trim the beginning of the file, and leave only the results in CSV format
    cmd = 'grep -A50000 -m1 -e "^Benchmark[[:space:]]\+(fileName)[[:space:]]\+Mode[[:space:]]\+Cnt[[:space:]]\+Score[[:space:]]\+Error[[:space:]]\+Units" {0} | tail -n+2 | head -n -2 | sed -n "s/ \+/,/gp" > {0}_trimmed.csv'.format(filename)
    subprocess.call(cmd, shell=True)

    data = dict()

    csv_header = ['algorithm', 'input', 'mode', 'cnt', 'time', '+-', 'error', 'units']
    with open('{0}_trimmed.csv'.format(filename), newline='') as fcsv:
        r = csv.DictReader(fcsv, delimiter=',', fieldnames=csv_header)
        for row in r:
            # Get data from the CSV row
            logging.debug('row = {0}'.format(row))
            input_str, size_str = row['input'].replace('testCases/', '').replace('.txt', '').split('_')
            alg_str = row['algorithm'].replace('BenchmarkAllSortTestCases.', '')
            time_str = row['time']
            logging.debug('input: {0}, algorithm = {1}, array_size: {2}, sorting_time: {3}'.format(input_str, alg_str,
                                                                                                  size_str, time_str))

            # Insert data
            if input_str not in data:
                data[input_str] = {}
            if alg_str not in data[input_str]:
                data[input_str][alg_str] = {}
            if 'array_size' not in data[input_str][alg_str]:
                data[input_str][alg_str]['array_size'] = []
            if 'sorting_time' not in data[input_str][alg_str]:
                data[input_str][alg_str]['sorting_time'] = []
            data[input_str][alg_str]['array_size'].append(float(size_str))
            try:
                data[input_str][alg_str]['sorting_time'].append(float(time_str))
            except ValueError:
                data[input_str][alg_str]['sorting_time'].append(0.0005)

        logging.debug('data = {0}'.format(data))

    return data


def plot_data(data, filename, ignore_input=None, ignore_alg=None, norm_alg=None):
    """ Plot data.

        The data is expected in the following format:

        { input1: { alg1: { array_size: [ ], sorting_time: [ ] }, alg2: { ... }, ... , algn: { ... } },
          input2: { ... }, ... , inputn: { ... } }

        :param  data            Data to plot
        :param  filename        File that we used to obtain the data to save plots to the same directory.
        :param  ignore_input    List of inputs to ignore in the plots.
        :param  ignore_alg      List of algorithms to ignore in the plots.
        :param  norm_alg        Algorithm to use to normalize the data in the plots.
    """
    if not data:
        logging.error('Could not plot the data: no data provided')

    # Get directory to save the plot
    plot_path = '/'.join(filename.split('/')[:-1]) + '/{0}'

    for input_key, input_val in data.items():
        # ignore certain inputs
        if input_key in ignore_input:
            continue

        logging.info('Plotting data for input {0}...'.format(input_key))

        if norm_alg:
            logging.info('Normalizing the data using algorithm '.format(norm_alg))
            if norm_alg in input_val:
                norm_alg_times = list(input_val[norm_alg]['sorting_time'])
            else:
                logging.error('Algorithm {0} is not included in the data, so it will not be used for normalization'
                              .format(norm_alg))
                norm_alg = None

        for alg_key, alg_val in input_val.items():
            # Ignore certain algs
            if alg_key in ignore_alg:
                continue

            logging.info('Plotting algorithm {0}...'.format(alg_key))
            if norm_alg:
                plt.plot(alg_val['array_size'], [a/b for a,b in zip(alg_val['sorting_time'], norm_alg_times)],
                         label=' '.join(re.sub('(?!^)([A-Z][a-z]+)', r' \1', alg_key).split()))
            else:
                plt.plot(alg_val['array_size'], alg_val['sorting_time'],
                         label=' '.join(re.sub('(?!^)([A-Z][a-z]+)', r' \1', alg_key).split()))

        plt.xlabel('Input Array Size (entries)')
        if norm_alg:
            plt.ylabel('Sorting Time Normalized by {0}'.format(' '.join(re.sub('(?!^)([A-Z][a-z]+)', r' \1',
                                                                                    norm_alg).split())))
        else:
            plt.ylabel('Sorting Time (ms)')

        plt.title("Sorting Time vs. Input Array Size\n for {0}"
                  .format(' '.join(re.sub('(?!^)([A-Z][a-z]+)', r' \1', input_key).split())))

        plt.legend()

        logging.debug('Saving plot to: {0}'.format(plot_path.format('sorting_time_vs_input_array_size_{0}'
                                                                    .format(input_key))))
        plt.savefig(plot_path.format('sorting_time_vs_input_array_size_{0}'.format(input_key)))
        plt.close()


if __name__ == '__main__':
    # Parse command-line arguments
    parser = argparse.ArgumentParser(description='Graph generator for the Sorting Algorithms Benchmarking project.')
    parser.add_argument('-a', '--ignore-algorithms', nargs='+', default=[], help='List of algorithms to ignore.')
    parser.add_argument('-i', '--ignore-inputs', nargs='+', default=[], help='List of inputs to ignore.')
    parser.add_argument('-n', '--normalize', default=None, help='Algoritm to use to normalize the data.')
    parser.add_argument('-f', '--log-file', help='Logfile. Uses stdout by default.')
    parser.add_argument('-l', '--log-level', default='WARN',
                        help='Verbosity level of the logger. Uses `WARN` by default.')
    parser.add_argument('data_file', nargs='+', help='List of data files to process.')
    args = parser.parse_args()

    if args.log_file:
        logfile = args.logfile
    else:
        logfile = None

    try:
        loglevel = logging.getLevelName(args.log_level)
        if not isinstance(loglevel, int):
            raise ValueError('Invalid log level: {0}'.format(loglevel))
    except Exception as e:
        print('Could not set the log level: {0}'.format(e))
        loglevel = logging.INFO

    logging.basicConfig(format='%(asctime)s %(levelname)s:%(module)s:%(funcName)s: %(message)s', filename=logfile,
                        level=loglevel)

    logging.info('Log level: {0}'.format(logging.getLevelName(loglevel)))

    if args.data_file:
        for filename in args.data_file:
            logging.info('Processing file {0}...'.format(filename))
            logging.info('Ignoring: inputs: {0}, algorithms: {1}, normalize: {2}'
                         .format(args.ignore_inputs, args.ignore_algorithms, args.normalize))
            plot_data(parse_file(filename), filename, args.ignore_inputs, args.ignore_algorithms, args.normalize)
    else:
        logging.error('No data files provided')
