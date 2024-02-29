package com.bhf.heuristics;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bhf.jmhresults.JMHResult;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

class L3SummaryHeuristicTest {

    private static final int HEURISTIC_DEFAULT_VALUE = 0;

    @Test
    void testMissingValues() {
        L3SummaryHeuristic heuristicCalc = new L3SummaryHeuristic();
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
        L3SummaryHeuristic heuristicCalc = new L3SummaryHeuristic();
        String benchmarkName = "TestHeuristic";
        double primaryScore = 123.4;
        JSONObject secondaryScore = null;
        JSONObject params = null;
        JSONObject pctiles = null;
        JMHResult jmhRes = new JMHResult(benchmarkName, primaryScore, secondaryScore, params, pctiles);
        jmhRes.CYCLES_L3_MISS = 10;
        jmhRes.STALLS_L3_MISS = 5;
        heuristicCalc.calculateHeuristic(jmhRes);
        double goodVal = heuristicCalc.getHeuristicValue();
        assertTrue(0.5 == goodVal);
    }

    @Test
    void testNull() {
        L3SummaryHeuristic heuristicCalc = new L3SummaryHeuristic();
        heuristicCalc.calculateHeuristic(null);
        double goodVal = heuristicCalc.getHeuristicValue();
        assertTrue(HEURISTIC_DEFAULT_VALUE == goodVal);
    }
}
