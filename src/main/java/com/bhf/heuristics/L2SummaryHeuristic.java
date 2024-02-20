package com.bhf.heuristics;

import com.bhf.jmhresults.JMHResult;

public class L2SummaryHeuristic implements DerivedHeuristicCalculator {

    double heuristicVal;

    @Override
    public void calculateHeuristic(JMHResult res) {
        if (null != res && res.STALLS_L2_MISS > 0 && res.CYCLES_L2_MISS > 0) {
            heuristicVal = res.STALLS_L2_MISS / res.CYCLES_L2_MISS;
        }
    }

    @Override
    public double getHeuristicValue() {
        return heuristicVal;
    }
}
