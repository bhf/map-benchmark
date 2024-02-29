package com.bhf.heuristics;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bhf.jmhresults.JMHResult;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

class L1SummaryHeuristicTest {

    private static final int HEURISTIC_DEFAULT_VALUE = 0;

    @Test
    void testMissingValues() {
        L1SummaryHeuristic heuristicCalc = new L1SummaryHeuristic();
        String benchmarkName = "TestHeuristic";
        double primaryScore = 123.4;
        JSONObject secondaryScore = null;
        JSONObject params = null;
        JSONObject pctiles = null;
        JMHResult jmhRes = new JMHResult(benchmarkName, primaryScore, secondaryScore, params, pctiles);
        heuristicCalc.calculateHeuristic(jmhRes);
        double badVal = heuristicCalc.getHeuristicValue();
        assertTrue(HEURISTIC_DEFAULT_VALUE == badVal);
    }

    @Test
    void testKnownValues() {
        L1SummaryHeuristic heuristicCalc = new L1SummaryHeuristic();
        String benchmarkName = "TestHeuristic";
        double primaryScore = 123.4;
        JSONObject secondaryScore = null;
        JSONObject params = null;
        JSONObject pctiles = null;
        JMHResult jmhRes = new JMHResult(benchmarkName, primaryScore, secondaryScore, params, pctiles);
        jmhRes.STALLS_L1D_MISS = 1;
        jmhRes.L1_dcache_loads = 1;
        heuristicCalc.calculateHeuristic(jmhRes);
        double goodVal = heuristicCalc.getHeuristicValue();
        assertTrue(1 == goodVal);
    }

    @Test
    void testNull() {
        L1SummaryHeuristic heuristicCalc = new L1SummaryHeuristic();
        heuristicCalc.calculateHeuristic(null);
        double goodVal = heuristicCalc.getHeuristicValue();
        assertTrue(HEURISTIC_DEFAULT_VALUE == goodVal);
    }
}
