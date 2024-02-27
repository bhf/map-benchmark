package com.bhf.heuristics;

import static org.junit.jupiter.api.Assertions.*;

import com.bhf.jmhresults.JMHResult;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

class RatioHeuristicTest {

    private static final String TEST_HEURISTIC = "TestHeuristic";
    private static final int HEURISTIC_DEFAULT_VALUE = 0;

    @Test
    void testMissingValues() {
        L3SummaryHeuristic numerator = new L3SummaryHeuristic();
        L2SummaryHeuristic denominator = new L2SummaryHeuristic();
        RatioHeuristic heuristicCal = new RatioHeuristic(numerator, denominator,TEST_HEURISTIC);

        String benchmarkName = "TestRatioHeuristic";
        double primaryScore = 123.4;
        JSONObject secondaryScore = null;
        JSONObject params = null;
        JMHResult jmhRes = new JMHResult(benchmarkName, primaryScore, secondaryScore, params);
        heuristicCal.calculateHeuristic(jmhRes);
        double badVal = heuristicCal.getHeuristicValue();
        assertTrue(HEURISTIC_DEFAULT_VALUE == badVal);
    }

    @Test
    void testKnownValues() {
        L3SummaryHeuristic numerator = new L3SummaryHeuristic();
        L2SummaryHeuristic denominator = new L2SummaryHeuristic();
        RatioHeuristic heuristicCal = new RatioHeuristic(numerator, denominator,TEST_HEURISTIC);

        String benchmarkName = "TestRatioHeuristic";
        double primaryScore = 123.4;
        JSONObject secondaryScore = null;
        JSONObject params = null;
        JMHResult jmhRes = new JMHResult(benchmarkName, primaryScore, secondaryScore, params);
        jmhRes.CYCLES_L3_MISS = 100;
        jmhRes.STALLS_L3_MISS = 50;

        jmhRes.CYCLES_L2_MISS = 100;
        jmhRes.STALLS_L2_MISS = 50;
        heuristicCal.calculateHeuristic(jmhRes);

        double goodVal = heuristicCal.getHeuristicValue();
        assertTrue(1.0 == goodVal);
    }

    @Test
    void testNull() {
        L3SummaryHeuristic numerator = new L3SummaryHeuristic();
        L2SummaryHeuristic denominator = new L2SummaryHeuristic();
        RatioHeuristic heuristicCal = new RatioHeuristic(numerator, denominator,TEST_HEURISTIC);

        JMHResult jmhRes = null;
        heuristicCal.calculateHeuristic(jmhRes);

        double goodVal = heuristicCal.getHeuristicValue();
        assertTrue(HEURISTIC_DEFAULT_VALUE == goodVal);
    }
}
