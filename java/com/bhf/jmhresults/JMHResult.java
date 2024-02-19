package com.bhf.jmhresults;

import java.math.BigDecimal;

public class JMHResult {

	public final String benchmarkName;
	public final BigDecimal primaryScore;
	public final BigDecimal l1_dcache_loads;
	public final BigDecimal LLC_loads;
	public final BigDecimal LLC_load_misses;
	public final BigDecimal L2_RQSTS_ALL_DEMAND_DATA_RD;
	public final BigDecimal CYCLE_ACTIVITY_STALLS_TOTAL;
	public final BigDecimal CYCLE_ACTIVITY_STALLS_L1D_MISS;
	public final BigDecimal CYCLE_ACTIVITY_STALLS_L2_MISS;
	public final BigDecimal CYCLE_ACTIVITY_STALLS_L3_MISS;
	public final BigDecimal CYCLE_ACTIVITY_CYCLES_L1D_MISS;
	public final BigDecimal CYCLE_ACTIVITY_CYCLES_L2_MISS;
	public final BigDecimal CYCLE_ACTIVITY_CYCLES_L3_MISS;
	public final BigDecimal CYCLE_ACTIVITY_STALLS_L1D_PENDING;
	public final BigDecimal CYCLE_ACTIVITY_STALLS_L2_PENDING;
	public final BigDecimal CYCLE_ACTIVITY_STALLS_LDM_PENDING;
	
	public JMHResult(String name, BigDecimal primaryScore, BigDecimal l1_dcache_loads, BigDecimal lLC_loads,
			BigDecimal lLC_load_misses, BigDecimal l2_RQSTS_ALL_DEMAND_DATA_RD, BigDecimal cYCLE_ACTIVITY_STALLS_TOTAL,
			BigDecimal cYCLE_ACTIVITY_STALLS_L1D_MISS, BigDecimal cYCLE_ACTIVITY_STALLS_L2_MISS,
			BigDecimal cYCLE_ACTIVITY_STALLS_L3_MISS, BigDecimal cYCLE_ACTIVITY_CYCLES_L1D_MISS,
			BigDecimal cYCLE_ACTIVITY_CYCLES_L2_MISS, BigDecimal cYCLE_ACTIVITY_CYCLES_L3_MISS,
			BigDecimal cYCLE_ACTIVITY_STALLS_L1D_PENDING, BigDecimal cYCLE_ACTIVITY_STALLS_L2_PENDING,
			BigDecimal cYCLE_ACTIVITY_STALLS_LDM_PENDING) {
		super();
		this.benchmarkName=name;
		this.primaryScore = primaryScore;
		this.l1_dcache_loads = l1_dcache_loads;
		LLC_loads = lLC_loads;
		LLC_load_misses = lLC_load_misses;
		L2_RQSTS_ALL_DEMAND_DATA_RD = l2_RQSTS_ALL_DEMAND_DATA_RD;
		CYCLE_ACTIVITY_STALLS_TOTAL = cYCLE_ACTIVITY_STALLS_TOTAL;
		CYCLE_ACTIVITY_STALLS_L1D_MISS = cYCLE_ACTIVITY_STALLS_L1D_MISS;
		CYCLE_ACTIVITY_STALLS_L2_MISS = cYCLE_ACTIVITY_STALLS_L2_MISS;
		CYCLE_ACTIVITY_STALLS_L3_MISS = cYCLE_ACTIVITY_STALLS_L3_MISS;
		CYCLE_ACTIVITY_CYCLES_L1D_MISS = cYCLE_ACTIVITY_CYCLES_L1D_MISS;
		CYCLE_ACTIVITY_CYCLES_L2_MISS = cYCLE_ACTIVITY_CYCLES_L2_MISS;
		CYCLE_ACTIVITY_CYCLES_L3_MISS = cYCLE_ACTIVITY_CYCLES_L3_MISS;
		CYCLE_ACTIVITY_STALLS_L1D_PENDING = cYCLE_ACTIVITY_STALLS_L1D_PENDING;
		CYCLE_ACTIVITY_STALLS_L2_PENDING = cYCLE_ACTIVITY_STALLS_L2_PENDING;
		CYCLE_ACTIVITY_STALLS_LDM_PENDING = cYCLE_ACTIVITY_STALLS_LDM_PENDING;
	}

	
	
}
