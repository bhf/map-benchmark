package com.bhf.jmhresults.output;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;

import com.bhf.jmhresults.EnrichedResult;

public class CSVFilePersistance implements ResultsPersistance
{  
    private static final Object CSV_SEPERATOR = ",";
    private static final String NEW_LINE = "\n";
    private String outputFile;

    public CSVFilePersistance(String outputFile)
    {
        this.outputFile = outputFile;
    }

    @Override
    public void persistEnrichedResults(List<EnrichedResult> results, String libVersion)
    {
        try (FileWriter fstream = new FileWriter(outputFile))
        {
            BufferedWriter out = new BufferedWriter(fstream);

            
            StringBuilder paramsHeaders = new StringBuilder();;
            StringBuilder heuristicHeaders=new StringBuilder();
            boolean paramHeadersWritten=false;
            boolean heuristicHeadersWritten=false;
            
            List<StringBuilder> sRes=new ArrayList<StringBuilder>();
            
            for (EnrichedResult r : results)
            {
                StringBuilder sb = new StringBuilder();
                sb.append(r.result.benchmarkName).append(CSV_SEPERATOR);
                sb.append(libVersion).append(CSV_SEPERATOR);
                sb.append(r.result.primaryScore).append(CSV_SEPERATOR);
                
                StringBuilder pctilesBuilder=new StringBuilder();
                JSONObject pctiles=r.result.scorePctiles;
                
                String median=pctiles.containsKey("50.0") ? pctiles.get("50.0").toString() : "";
                String p90=pctiles.containsKey("90.0") ? pctiles.get("90.0").toString() : "";
                String p99=pctiles.containsKey("99.0") ? pctiles.get("99.0").toString() : "";
                pctilesBuilder.append(median).append(CSV_SEPERATOR);
                pctilesBuilder.append(p90).append(CSV_SEPERATOR);
                pctilesBuilder.append(p99).append(CSV_SEPERATOR);
                
                sb.append(pctilesBuilder);

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

                if(paramsBuilder.length()>0)
                {
                    sb.append(paramsBuilder.substring(0,paramsBuilder.length()-1)).append(CSV_SEPERATOR);
                }
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
            headers.append("BENCHMARK,VERSION,PRIMARY_SCORE,PRIM_MED,PRIM_P90,PRIM_P99,");
            
            if(paramsHeaders.length()>0)
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
            
            if(heuristicHeaders.length()>0)
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
