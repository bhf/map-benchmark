package com.bhf.heuristics;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HeuristicFactoryTest {

    @Test
    void testNullFactory() {
        HeuristicFactory factory = new HeuristicFactory();
        DerivedHeuristicCalculator heuristic = factory.getHeuristic(null);
        assertNull(heuristic);
    }

    @Test
    void testL1SummaryFactory() {
        HeuristicFactory factory = new HeuristicFactory();
        DerivedHeuristicCalculator heuristic = factory.getHeuristic(HeuristicType.L1_SUMMARY);
        assertNotNull(heuristic);
        assertTrue(heuristic instanceof L1SummaryHeuristic);
    }

    @Test
    void testL2SummaryFactory() {
        HeuristicFactory factory = new HeuristicFactory();
        DerivedHeuristicCalculator heuristic = factory.getHeuristic(HeuristicType.L2_SUMMARY);
        assertNotNull(heuristic);
        assertTrue(heuristic instanceof L2SummaryHeuristic);
    }

    @Test
    void testL3SummaryFactory() {
        HeuristicFactory factory = new HeuristicFactory();
        DerivedHeuristicCalculator heuristic = factory.getHeuristic(HeuristicType.L3_SUMMARY);
        assertNotNull(heuristic);
        assertTrue(heuristic instanceof L3SummaryHeuristic);
    }

    @Test
    void testL1L2Factory() {
        HeuristicFactory factory = new HeuristicFactory();
        DerivedHeuristicCalculator heuristic = factory.getHeuristic(HeuristicType.RATIO_L1_L2);
        assertNotNull(heuristic);
        assertTrue(heuristic instanceof RatioHeuristic);

        RatioHeuristic ratio = (RatioHeuristic) heuristic;
        DerivedHeuristicCalculator numerator = ratio.getNumerator();
        DerivedHeuristicCalculator denominator = ratio.getDenominator();
        assertTrue(numerator instanceof L1SummaryHeuristic);
        assertTrue(denominator instanceof L2SummaryHeuristic);
    }

    @Test
    void testL1L3Factory() {
        HeuristicFactory factory = new HeuristicFactory();
        DerivedHeuristicCalculator heuristic = factory.getHeuristic(HeuristicType.RATIO_L1_L3);
        assertNotNull(heuristic);
        assertTrue(heuristic instanceof RatioHeuristic);

        RatioHeuristic ratio = (RatioHeuristic) heuristic;
        DerivedHeuristicCalculator numerator = ratio.getNumerator();
        DerivedHeuristicCalculator denominator = ratio.getDenominator();
        assertTrue(numerator instanceof L1SummaryHeuristic);
        assertTrue(denominator instanceof L3SummaryHeuristic);
    }

    @Test
    void testL2L3Factory() {
        HeuristicFactory factory = new HeuristicFactory();
        DerivedHeuristicCalculator heuristic = factory.getHeuristic(HeuristicType.RATIO_L2_L3);
        assertNotNull(heuristic);
        assertTrue(heuristic instanceof RatioHeuristic);

        RatioHeuristic ratio = (RatioHeuristic) heuristic;
        DerivedHeuristicCalculator numerator = ratio.getNumerator();
        DerivedHeuristicCalculator denominator = ratio.getDenominator();
        assertTrue(numerator instanceof L2SummaryHeuristic);
        assertTrue(denominator instanceof L3SummaryHeuristic);
    }

    @Test
    void testThroughputL1Factory() {
        HeuristicFactory factory = new HeuristicFactory();
        DerivedHeuristicCalculator heuristic = factory.getHeuristic(HeuristicType.RATIO_THROUGHPUT_L1);
        assertNotNull(heuristic);
        assertTrue(heuristic instanceof RatioHeuristic);

        RatioHeuristic ratio = (RatioHeuristic) heuristic;
        DerivedHeuristicCalculator numerator = ratio.getNumerator();
        DerivedHeuristicCalculator denominator = ratio.getDenominator();
        assertTrue(numerator instanceof ThroughputHeuristic);
        assertTrue(denominator instanceof L1SummaryHeuristic);
    }

    @Test
    void testThroughputL2Factory() {
        HeuristicFactory factory = new HeuristicFactory();
        DerivedHeuristicCalculator heuristic = factory.getHeuristic(HeuristicType.RATIO_THROUGHPUT_L2);
        assertNotNull(heuristic);
        assertTrue(heuristic instanceof RatioHeuristic);

        RatioHeuristic ratio = (RatioHeuristic) heuristic;
        DerivedHeuristicCalculator numerator = ratio.getNumerator();
        DerivedHeuristicCalculator denominator = ratio.getDenominator();
        assertTrue(numerator instanceof ThroughputHeuristic);
        assertTrue(denominator instanceof L2SummaryHeuristic);
    }

    @Test
    void testThroughputL3Factory() {
        HeuristicFactory factory = new HeuristicFactory();
        DerivedHeuristicCalculator heuristic = factory.getHeuristic(HeuristicType.RATIO_THROUGHPUT_L3);
        assertNotNull(heuristic);
        assertTrue(heuristic instanceof RatioHeuristic);

        RatioHeuristic ratio = (RatioHeuristic) heuristic;
        DerivedHeuristicCalculator numerator = ratio.getNumerator();
        DerivedHeuristicCalculator denominator = ratio.getDenominator();
        assertTrue(numerator instanceof ThroughputHeuristic);
        assertTrue(denominator instanceof L3SummaryHeuristic);
    }
}
