package com.bhf.jmhresults;

import java.math.BigDecimal;
import org.json.simple.JSONObject;

public class JMHResult {

	public final String benchmarkName;
	public final BigDecimal primaryScore;
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

	public JMHResult(String benchmarkName, BigDecimal primaryScore, JSONObject secondaryScores, JSONObject params) {
		this.benchmarkName = benchmarkName;
		this.primaryScore = primaryScore;
		this.secondaryScores = secondaryScores;
		this.params = params;

	}
}
