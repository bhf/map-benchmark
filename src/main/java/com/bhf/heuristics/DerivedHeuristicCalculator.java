package com.bhf.heuristics;

import com.bhf.jmhresults.JMHResult;

/**
 * Calculate derived heuristics from the JMH+perf output
 */
public interface DerivedHeuristicCalculator {
    
    public String getHeuristicName();

    public void calculateHeuristic(JMHResult res);

    public double getHeuristicValue();
}
