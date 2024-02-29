package com.bhf.jmhresults.output;

import java.util.List;

import com.bhf.jmhresults.EnrichedResult;
import com.bhf.jmhresults.HeuristicsEnricher;
import com.bhf.jmhresults.JMHResult;
import com.bhf.jmhresults.JMHResultLoader;

public class OutputGenerator
{

    public static void main(String[] args)
    {
        String jmhResultsFile=args[0];
        String outputCSV=args[1];
        JMHResultLoader l=new JMHResultLoader();
        List<JMHResult> jmhResults = l.getJMHResults(jmhResultsFile);
        System.out.println("Total results="+jmhResults.size());
        CSVFilePersistance p=new CSVFilePersistance(outputCSV);
        HeuristicsEnricher enricher=new HeuristicsEnricher();
        List<EnrichedResult> enriched=enricher.getEnrichedResults(jmhResults);
        System.out.println("Enriched results="+jmhResults.size());
        p.persistEnrichedResults(enriched);
    }
    
}
