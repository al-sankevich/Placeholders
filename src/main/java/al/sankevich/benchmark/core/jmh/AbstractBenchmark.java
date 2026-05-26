package al.sankevich.benchmark.core.jmh;

import org.openjdk.jmh.infra.Blackhole;

public interface AbstractBenchmark {

    void setup();

    void check(Blackhole blackhole);
}
