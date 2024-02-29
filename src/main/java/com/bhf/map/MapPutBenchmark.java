package com.bhf.map;

import java.util.Map;

import org.openjdk.jmh.infra.Blackhole;

/**
 * Benchmark put operations
 */
public interface MapPutBenchmark<K,V> {

    /**
     * Get the expected Map after a put iteration
     * for unit testing map implementations
     * @return
     */
    Map<K,V> getExpectedMap();
    
    /**
     * Initialise the map being benchmarked with the 
     * keys and values from the inputData - must be
     * annotated as @Setup(Level.*)
     * @param inputData
     */
    void initMap();
    
    /**
     * Put operation on the map implementation being
     * benchmarked - must be annotated as @Benchmark
     */
    void benchPut(Blackhole bh);
}
