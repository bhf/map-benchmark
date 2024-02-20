package com.bhf.heuristics;

import com.bhf.jmhresults.JMHResult;

public class L1SummaryHeuristic implements DerivedHeuristicCalculator {

    double heuristicVal;

    @Override
    public void calculateHeuristic(JMHResult res) {
        if (null != res && res.STALLS_L1D_MISS > 0 && res.L1_dcache_loads > 0) {
            heuristicVal = res.STALLS_L1D_MISS / res.L1_dcache_loads;
        }
    }

    @Override
    public double getHeuristicValue() {
        return heuristicVal;
    }
}
