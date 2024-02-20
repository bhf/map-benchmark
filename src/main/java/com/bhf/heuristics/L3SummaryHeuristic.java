package com.bhf.heuristics;

import com.bhf.jmhresults.JMHResult;

public class L3SummaryHeuristic implements DerivedHeuristicCalculator {

    double heuristicVal;

    @Override
    public void calculateHeuristic(JMHResult res) {
        if (null != res && res.STALLS_L3_MISS > 0 && res.CYCLES_L3_MISS > 0) {
            heuristicVal = res.STALLS_L3_MISS / res.CYCLES_L3_MISS;
        }
    }

    @Override
    public double getHeuristicValue() {
        return heuristicVal;
    }
}
