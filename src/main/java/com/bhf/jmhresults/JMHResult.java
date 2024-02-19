package com.bhf.jmhresults;

import java.math.BigDecimal;

import org.json.simple.JSONObject;

public class JMHResult {

	public final String benchmarkName;
	public final BigDecimal primaryScore;
	public final JSONObject secondaryScores;
	public final JSONObject params;

	public JMHResult(String benchmarkName, BigDecimal primaryScore, JSONObject secondaryScores, JSONObject params) {
		this.benchmarkName = benchmarkName;
		
		
		
		
		
		
		
		
		this.primaryScore = primaryScore;
		this.secondaryScores = secondaryScores;
		this.params = params;
	}

}
