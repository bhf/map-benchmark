package com.bhf.jmhresults;

import java.util.HashMap;

public class EnrichedResult
{
    final JMHResult result;
    final HashMap<String,Double> heuristicsToValues=new HashMap<String, Double>();
    
    public EnrichedResult(JMHResult result)
    {
        super();
        this.result = result;
    }
 
    public void addHeuristic(String name, double val) {
        heuristicsToValues.put(name, val);
    }
    
}