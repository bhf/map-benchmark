package com.bhf.jmhresults.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.bhf.jmhresults.EnrichedResult;
import com.bhf.jmhresults.HeuristicsEnricher;
import com.bhf.jmhresults.JMHResult;
import com.bhf.jmhresults.JMHResultLoader;

public class CSVFilePersistance implements ResultsPersistance
{
    
    public static void main(String[] args)
    {
        JMHResultLoader l=new JMHResultLoader();
        List<JMHResult> jmhResults = l.getJMHResults("/home/optimus/hft/jmh-playground/CacheCalculatingAveragesMultiArray.json");
        CSVFilePersistance p=new CSVFilePersistance("/tmp/sample.csv");
        HeuristicsEnricher enricher=new HeuristicsEnricher();
        List<EnrichedResult> enriched=enricher.getEnrichedResults(jmhResults);
        p.persistEnrichedResults(enriched);
    }
    

    private static final Object CSV_SEPERATOR = ",";
    private static final String NEW_LINE = "\n";
    private String outputFile;

    public CSVFilePersistance(String outputFile)
    {
        this.outputFile = outputFile;
    }

    @Override
    public void persistEnrichedResults(List<EnrichedResult> results)
    {
        try (FileWriter fstream = new FileWriter(outputFile))
        {
            BufferedWriter out = new BufferedWriter(fstream);

            StringBuilder sb = new StringBuilder();
            StringBuilder paramsHeaders = new StringBuilder();;
            StringBuilder heuristicHeaders=new StringBuilder();
            boolean paramHeadersWritten=false;
            boolean heuristicHeadersWritten=false;
            
            List<StringBuilder> sRes=new ArrayList<StringBuilder>();
            
            for (EnrichedResult r : results)
            {
                sb.append(r.result.benchmarkName).append(CSV_SEPERATOR);
                sb.append(r.result.primaryScore).append(CSV_SEPERATOR);

                StringBuilder paramsBuilder = new StringBuilder();
                
                JSONObject params = r.result.params;

                if (null != params)
                {
                    for (Object o : params.keySet())
                    {
                        String sm = params.get(o).toString();
                        paramsBuilder.append(sm).append(CSV_SEPERATOR);
                        
                        if(!paramHeadersWritten) {
                            paramsHeaders.append(o + ",");
                        }
                        
                    }
                    paramHeadersWritten=true;
                }

                sb.append(paramsBuilder.substring(0,paramsBuilder.length()-1)).append(CSV_SEPERATOR);
                sb.append(r.result.L1_dcache_loads).append(CSV_SEPERATOR);
                sb.append(r.result.LLC_loads).append(CSV_SEPERATOR);
                sb.append(r.result.LLC_load_misses).append(CSV_SEPERATOR);
                sb.append(r.result.L2_RQSTS_ALL_DEMAND_DATA_RD).append(CSV_SEPERATOR);
                sb.append(r.result.STALLS_TOTAL).append(CSV_SEPERATOR);
                sb.append(r.result.STALLS_L1D_MISS).append(CSV_SEPERATOR);
                sb.append(r.result.STALLS_L2_MISS).append(CSV_SEPERATOR);
                sb.append(r.result.STALLS_L3_MISS).append(CSV_SEPERATOR);
                sb.append(r.result.CYCLES_L1D_MISS).append(CSV_SEPERATOR);
                sb.append(r.result.CYCLES_L2_MISS).append(CSV_SEPERATOR);
                sb.append(r.result.CYCLES_L3_MISS).append(CSV_SEPERATOR);
                sb.append(r.result.STALLS_L1D_PENDING).append(CSV_SEPERATOR);
                sb.append(r.result.STALLS_L2_PENDING).append(CSV_SEPERATOR);
                sb.append(r.result.STALLS_LDM_PENDING).append(CSV_SEPERATOR);
                
                if(!heuristicHeadersWritten) {
                    for(String s: r.heuristicsToValues.keySet()) {
                        heuristicHeaders.append(s).append(CSV_SEPERATOR); 
                    }
                    heuristicHeadersWritten=true;
                }
                
                for(String s: r.heuristicsToValues.keySet()) {
                    double val=r.heuristicsToValues.get(s);
                    sb.append(val).append(CSV_SEPERATOR);
                }
                sb.append(NEW_LINE);
                sRes.add(sb);
            }
            
            StringBuilder headers=new StringBuilder();
            headers.append("BENCHMARK,PRIMARY_SCORE,");
            
            if(null!=paramsHeaders)
            {
                headers.append(paramsHeaders.substring(0,paramsHeaders.length()-1).toUpperCase()).append(CSV_SEPERATOR);
            }
            headers.append("L1_dcache_loads").append(CSV_SEPERATOR);
            headers.append("LLC_loads").append(CSV_SEPERATOR);
            headers.append("LLC_load_misses").append(CSV_SEPERATOR);
            headers.append("L2_RQSTS_ALL_DEMAND_DATA_RD").append(CSV_SEPERATOR);
            headers.append("STALLS_TOTAL").append(CSV_SEPERATOR);
            headers.append("STALLS_L1D_MISS").append(CSV_SEPERATOR);
            headers.append("STALLS_L2_MISS").append(CSV_SEPERATOR);
            headers.append("STALLS_L3_MISS").append(CSV_SEPERATOR);
            headers.append("CYCLES_L1D_MISS").append(CSV_SEPERATOR);
            headers.append("CYCLES_L2_MISS").append(CSV_SEPERATOR);
            headers.append("CYCLES_L3_MISS").append(CSV_SEPERATOR);
            headers.append("STALLS_L1D_PENDING").append(CSV_SEPERATOR);
            headers.append("STALLS_L2_PENDING").append(CSV_SEPERATOR);
            headers.append("STALLS_LDM_PENDING").append(CSV_SEPERATOR);
            
            if(null!=heuristicHeaders)
            {
                headers.append(heuristicHeaders.substring(0, heuristicHeaders.length() - 1)).append(NEW_LINE);
            }
            
            out.write(headers.toString());
            
            for(StringBuilder s : sRes) {
                out.write(s.substring(0, s.length()-1)+NEW_LINE);
            }
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
