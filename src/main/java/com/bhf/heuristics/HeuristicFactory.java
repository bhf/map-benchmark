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
            case RATIO_L1_L2: {
                return new RatioHeuristic(new L1SummaryHeuristic(), new L2SummaryHeuristic());
            }
            case RATIO_L1_L3: {
                return new RatioHeuristic(new L1SummaryHeuristic(), new L3SummaryHeuristic());
            }
            default:
        }

        return null;
    }
}
