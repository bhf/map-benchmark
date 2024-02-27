package com.bhf.heuristics;

public class HeuristicFactory {

    public DerivedHeuristicCalculator getHeuristic(HeuristicType t) {

        if (null != t) {
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
                    return new RatioHeuristic(new L1SummaryHeuristic(), new L2SummaryHeuristic(),"L1-L2-RATIO");
                }
                case RATIO_L1_L3: {
                    return new RatioHeuristic(new L1SummaryHeuristic(), new L3SummaryHeuristic(),"L1-L3-RATIO");
                }
                case RATIO_L2_L3: {
                    return new RatioHeuristic(new L2SummaryHeuristic(), new L3SummaryHeuristic(),"L2-L3-RATIO");
                }
                case RATIO_THROUGHPUT_L1: {
                    return new RatioHeuristic(new ThroughputHeuristic(), new L1SummaryHeuristic(),"THROUGHPUT-L1-RATIO");
                }
                case RATIO_THROUGHPUT_L2: {
                    return new RatioHeuristic(new ThroughputHeuristic(), new L2SummaryHeuristic(),"THROUGHPUT-L2-RATIO");
                }
                case RATIO_THROUGHPUT_L3: {
                    return new RatioHeuristic(new ThroughputHeuristic(), new L3SummaryHeuristic(),"THROUGHPUT-L3-RATIO");
                }
                default:
            }
        }

        return null;
    }
}
