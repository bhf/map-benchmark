package com.bhf.heuristics;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

import com.bhf.jmhresults.JMHResult;

class L1SummaryHeuristicTest {

	@Test
	void test() {
		L1SummaryHeuristic heuristicCalc=new L1SummaryHeuristic();
		String benchmarkName="TestHeuristic";
		double primaryScore=123.4;
		JSONObject secondaryScore=null;
		JSONObject params=null;
		JMHResult jmhRes=new JMHResult(benchmarkName, primaryScore, secondaryScore, params);
		heuristicCalc.calculateHeuristic(jmhRes);
		double badVal=heuristicCalc.getHeuristicValue();
		assertTrue(0==badVal);
	}

}
