package com.bhf.heuristics;

import com.bhf.jmhresults.JMHResult;

public class ThroughputHeuristic implements DerivedHeuristicCalculator {

    double heuristicVal;

    @Override
    public void calculateHeuristic(JMHResult res) {
        if (null != res && res.primaryScore > 0) {
            heuristicVal = res.primaryScore;
        }
    }

    @Override
    public double getHeuristicValue() {
        return heuristicVal;
    }
}
