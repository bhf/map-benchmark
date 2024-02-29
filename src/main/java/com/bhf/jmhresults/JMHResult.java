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
    public final JSONObject scorePctiles;

    public JMHResult(String benchmarkName, double primaryScore, JSONObject secondaryScores, JSONObject params, JSONObject scorePctiles) {
        this.benchmarkName = benchmarkName;
        this.primaryScore = primaryScore;
        this.secondaryScores = secondaryScores;
        this.params = params;
        this.scorePctiles=scorePctiles;

        if (null != secondaryScores) {
            parseSecondaryScores(secondaryScores);
        }
    }

    public void parseSecondaryScores(JSONObject secondaryScores2) {

        if (null != secondaryScores2) {
            L1_dcache_loads = getValue(secondaryScores, "L1-dcache-loads");
            LLC_loads = getValue(secondaryScores, "LLC-loads");
            LLC_load_misses = getValue(secondaryScores, "LLC-load-misses");
            L2_RQSTS_ALL_DEMAND_DATA_RD = getValue(secondaryScores, "L2-RQSTS-ALL-DEMAND-DATA-RD");
            STALLS_TOTAL = getValue(secondaryScores, "CYCLE_ACTIVITY.STALLS_TOTAL");
            STALLS_L1D_MISS = getValue(secondaryScores, "CYCLE_ACTIVITY.STALLS_L1D_MISS");
            STALLS_L2_MISS = getValue(secondaryScores, "CYCLE_ACTIVITY.STALLS_L2_MISS");
            STALLS_L3_MISS = getValue(secondaryScores, "CYCLE_ACTIVITY.STALLS_L3_MISS");
            CYCLES_L1D_MISS = getValue(secondaryScores, "CYCLE_ACTIVITY.CYCLES_L1D_MISS");
            CYCLES_L2_MISS = getValue(secondaryScores, "CYCLE_ACTIVITY.CYCLES_L2_MISS");
            CYCLES_L3_MISS = getValue(secondaryScores, "CYCLE_ACTIVITY.CYCLES_L3_MISS");
            STALLS_L1D_PENDING = getValue(secondaryScores, "CYCLE_ACTIVITY.STALLS_L1D_PENDING");
            STALLS_L2_PENDING = getValue(secondaryScores, "CYCLE_ACTIVITY.STALLS_L2_PENDING");
            STALLS_LDM_PENDING = getValue(secondaryScores, "CYCLE_ACTIVITY.STALLS_LDM_PENDING");
        }
    }

    double getValue(JSONObject secondaryScores, String scoreKey) {
        if (secondaryScores.containsKey(scoreKey)) {
            JSONObject secScore = (JSONObject) secondaryScores.get(scoreKey);
            String sc = secScore.containsKey("score") ? secScore.get("score").toString() : "0";
            return Double.parseDouble(sc);
        }
        return 0;
    }
}
