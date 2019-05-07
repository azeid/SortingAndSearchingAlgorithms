package bench;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

public class BenchmarkRunner {

    public static void main(String[] args) throws RunnerException {

        Options options = new OptionsBuilder()
                .include(BenchmarkAllSortTestCases.class.getSimpleName())
                .timeout(TimeValue.minutes(1))
                .warmupIterations(3) // Default is 5
                .warmupTime(TimeValue.seconds(5)) // Default is 10
                .forks(1)
                .build();

        new Runner(options).run();
    }
}
