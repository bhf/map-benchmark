package com.bhf.jmhresults;

import java.util.HashMap;

public class EnrichedResult
{
    public final JMHResult result;
    public final HashMap<String,Double> heuristicsToValues=new HashMap<String, Double>();
    
    public EnrichedResult(JMHResult result)
    {
        super();
        this.result = result;
    }
 
    public void addHeuristic(String name, double val) {
        heuristicsToValues.put(name, val);
    }
    
}
