package com.bhf.heuristics;

public class HeuristicFactory {

    public DerivedHeuristicCalculator getHeuristic(HeuristicType t) {

        switch (t) {
            case L1_SUMMARY: {
                return new L1SummaryHeuristic();
            }
            case L2_SUMMARY: {
                return new L2SummaryHeuristic();
            }
            case L3_SUMMARY: {
                return new L3SummaryHeuristic();
            }
            default:
        }

        return null;
    }
}
