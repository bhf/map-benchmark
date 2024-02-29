package com.bhf.jmhresults;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class HeuristicsEnricherTest
{

    @Test
    void testNullResults()
    {
        HeuristicsEnricher enricher=new HeuristicsEnricher();
        List<EnrichedResult> enrichedResults = enricher.getEnrichedResults(null);
        assertNotNull(enrichedResults);
    }
    
    @Test
    void testEmptyResults()
    {
        HeuristicsEnricher enricher=new HeuristicsEnricher();
        List<EnrichedResult> enrichedResults = enricher.getEnrichedResults(new ArrayList<JMHResult>());
        assertNotNull(enrichedResults);
    }
    
    @Test
    void testBasicResults()
    {
        HeuristicsEnricher enricher=new HeuristicsEnricher();
        ArrayList<JMHResult> res = new ArrayList<JMHResult>();
        res.add(new JMHResult("BenchmarkTest", 1000, null, null, null));   
        List<EnrichedResult> enrichedResults = enricher.getEnrichedResults(res);
        assertNotNull(enrichedResults);
    }

}
