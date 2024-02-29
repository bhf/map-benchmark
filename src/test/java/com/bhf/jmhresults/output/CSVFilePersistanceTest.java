package com.bhf.jmhresults.output;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.bhf.jmhresults.EnrichedResult;
import com.bhf.jmhresults.HeuristicsEnricher;
import com.bhf.jmhresults.JMHResult;
import com.bhf.jmhresults.JMHResultLoader;

class CSVFilePersistanceTest
{

    final String JMH_SAMPLE = "src/test/resources/SampleJMH.json";
    final String JMH_SAMPLE_WITH_PARAMS = "src/test/resources/params.json";
    
    @Test
    void testFromFile()
    {
        JMHResultLoader l=new JMHResultLoader();
        List<JMHResult> jmhResults = l.getJMHResults(JMH_SAMPLE);
        CSVFilePersistance p=new CSVFilePersistance("/tmp/sample.csv");
        HeuristicsEnricher enricher=new HeuristicsEnricher();
        List<EnrichedResult> enriched=enricher.getEnrichedResults(jmhResults);
        p.persistEnrichedResults(enriched, "test-meta");
        
    }
    
    @Test
    void testWithParamsFile()
    {
        JMHResultLoader l=new JMHResultLoader();
        List<JMHResult> jmhResults = l.getJMHResults(JMH_SAMPLE_WITH_PARAMS);
        CSVFilePersistance p=new CSVFilePersistance("/tmp/sampleParams.csv");
        HeuristicsEnricher enricher=new HeuristicsEnricher();
        List<EnrichedResult> enriched=enricher.getEnrichedResults(jmhResults);
        p.persistEnrichedResults(enriched, "test-meta");
    }

}
