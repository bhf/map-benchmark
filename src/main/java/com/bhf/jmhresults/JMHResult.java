package com.bhf.jmhresults;

import org.json.simple.JSONObject;

public class JMHResult {

    public final String benchmarkName;
    public final double primaryScore;
    public final JSONObject secondaryScores;
    public final JSONObject params;
    public double L1_dcache_loads;
    public double LLC_loads;
    public double LLC_load_misses;
    public double L2_RQSTS_ALL_DEMAND_DATA_RD;
    public double STALLS_TOTAL;
    public double STALLS_L1D_MISS;
    public double STALLS_L2_MISS;
    public double STALLS_L3_MISS;
    public double CYCLES_L1D_MISS;
    public double CYCLES_L2_MISS;
    public double CYCLES_L3_MISS;
    public double STALLS_L1D_PENDING;
    public double STALLS_L2_PENDING;
    public double STALLS_LDM_PENDING;

    public JMHResult(String benchmarkName, double primaryScore, JSONObject secondaryScores, JSONObject params) {
        this.benchmarkName = benchmarkName;
        this.primaryScore = primaryScore;
        this.secondaryScores = secondaryScores;
        this.params = params;

        if (null != secondaryScores) {
            parseSecondaryScores(secondaryScores);
        }
    }

    public void parseSecondaryScores(JSONObject secondaryScores2) {
        L1_dcache_loads = getValue(secondaryScores, "");
        LLC_loads = getValue(secondaryScores, "");
        LLC_load_misses = getValue(secondaryScores, "");
        L2_RQSTS_ALL_DEMAND_DATA_RD = getValue(secondaryScores, "");
        STALLS_TOTAL = getValue(secondaryScores, "");
        STALLS_L1D_MISS = getValue(secondaryScores, "");
        STALLS_L2_MISS = getValue(secondaryScores, "");
        STALLS_L3_MISS = getValue(secondaryScores, "");
        CYCLES_L1D_MISS = getValue(secondaryScores, "");
        CYCLES_L2_MISS = getValue(secondaryScores, "");
        CYCLES_L3_MISS = getValue(secondaryScores, "");
        STALLS_L1D_PENDING = getValue(secondaryScores, "");
        STALLS_L2_PENDING = getValue(secondaryScores, "");
        STALLS_LDM_PENDING = getValue(secondaryScores, "");
    }

    private double getValue(JSONObject secondaryScores, String scoreKey) {
        if (secondaryScores.containsKey(scoreKey)) {
            return Double.parseDouble(secondaryScores.get(scoreKey).toString());
        }
        return 0;
    }
}
