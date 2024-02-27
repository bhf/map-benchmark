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
    public void persistEnrichedResults(List<EnrichedResult> results)
    {
        try (FileWriter fstream = new FileWriter(outputFile))
        {
            BufferedWriter out = new BufferedWriter(fstream);

            StringBuilder sb = new StringBuilder();
            StringBuilder paramsHeaders=null;
            
            List<StringBuilder> sRes=new ArrayList<StringBuilder>();
            
            for (EnrichedResult r : results)
            {
                sb.append(r.result.benchmarkName);
                sb.append(r.result.primaryScore);

                StringBuilder paramsBuilder = new StringBuilder();
                
                if(null==paramsHeaders)
                {
                    paramsHeaders = new StringBuilder();
                }
                JSONObject params = r.result.params;

                if (null != params)
                {
                    for (Object o : params.keySet())
                    {
                        String sm = params.get(o).toString();
                        paramsBuilder.append(sm + ",");
                        
                        if(null!=paramsHeaders) {
                            paramsHeaders.append(o + ",");
                        }
                        
                    }
                }

                sb.append(paramsBuilder).append(CSV_SEPERATOR);
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
                sb.append(r.result.STALLS_LDM_PENDING).append(NEW_LINE);
                
                sRes.add(sb);
            }
            
            StringBuilder headers=new StringBuilder();
            headers.append("BENCHMARK,PRIMARY_SCORE,");
            headers.append(paramsHeaders.toString().toUpperCase()).append(CSV_SEPERATOR);
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
            headers.append("STALLS_LDM_PENDING").append(CSV_SEPERATOR).append(NEW_LINE);
            
            out.write(headers.toString());
            
            for(StringBuilder s : sRes) {
                out.write(s.toString());
            }
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
