package com.bhf.map;

import java.util.Map;

import org.openjdk.jmh.infra.Blackhole;

/**
 * Benchmark get operations
 */
public interface MapGetBenchmark<K,V> {

    /**
     * Get the expected Map key and values
     * for unit testing map implementation
     * @return
     */
    Map<K,V> getExpectedMap();
    
    /**
     * Initialise the map being benchmarked with the 
     * keys and values from the inputData - must be
     * annotated as @Setup(Level.*)
     * @param inputData
     */
    void initMap(Map<K,V> inputData);
    
    void benchGet(Blackhole bh);
}
