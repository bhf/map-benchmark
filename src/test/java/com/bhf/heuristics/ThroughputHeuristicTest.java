package com.bhf.heuristics;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bhf.jmhresults.JMHResult;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

class ThroughputHeuristicTest {

    private static final int HEURISTIC_DEFAULT_VALUE = 0;

    @Test
    void testMissingValues() {
        ThroughputHeuristic heuristicCalc = new ThroughputHeuristic();
        String benchmarkName = "TestThroughputHeuristic"+heuristicCalc.getHeuristicName();
        double primaryScore = 0;
        JSONObject secondaryScore = null;
        JSONObject params = null;
        JMHResult jmhRes = new JMHResult(benchmarkName, primaryScore, secondaryScore, params);
        heuristicCalc.calculateHeuristic(jmhRes);
        double badVal = heuristicCalc.getHeuristicValue();
        assertTrue(HEURISTIC_DEFAULT_VALUE == badVal);
    }

    @Test
    void testKnownValues() {
        ThroughputHeuristic heuristicCalc = new ThroughputHeuristic();
        String benchmarkName = "TestHeuristic";
        double primaryScore = 123.4;
        JSONObject secondaryScore = null;
        JSONObject params = null;
        JMHResult jmhRes = new JMHResult(benchmarkName, primaryScore, secondaryScore, params);
        jmhRes.STALLS_L1D_MISS = 1;
        jmhRes.L1_dcache_loads = 1;
        heuristicCalc.calculateHeuristic(jmhRes);
        double goodVal = heuristicCalc.getHeuristicValue();
        assertTrue(primaryScore == goodVal);
    }

    @Test
    void testNull() {
        ThroughputHeuristic heuristicCalc = new ThroughputHeuristic();
        heuristicCalc.calculateHeuristic(null);
        double goodVal = heuristicCalc.getHeuristicValue();
        assertTrue(HEURISTIC_DEFAULT_VALUE == goodVal);
    }
}
