package com.bhf.jmhresults;

import java.util.ArrayList;
import java.util.List;

import com.bhf.heuristics.DerivedHeuristicCalculator;
import com.bhf.heuristics.HeuristicFactory;
import com.bhf.heuristics.HeuristicType;

public class HeuristicsEnricher
{

    HeuristicFactory heuristicFactory = new HeuristicFactory();

    public List<EnrichedResult> getEnrichedResults(List<JMHResult> results)
    {
        List<EnrichedResult> res = new ArrayList<EnrichedResult>();
     
        if(null!=results)
        {
            for (JMHResult r : results)
            {   
                EnrichedResult er=new EnrichedResult(r);
                res.add(er);
                
                for(HeuristicType h : HeuristicType.values()) {
                    DerivedHeuristicCalculator heuristic = heuristicFactory.getHeuristic(h);
                    if(null!=heuristic)
                    {
                        heuristic.calculateHeuristic(r);
                        double hv=heuristic.getHeuristicValue();
                        String name=heuristic.getHeuristicName();
                        er.addHeuristic(name, hv);
                    }
                }
            }
        }
     
        return res;
    }
}
