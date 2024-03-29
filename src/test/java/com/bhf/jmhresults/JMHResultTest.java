package com.bhf.jmhresults;

import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;

class JMHResultTest {

    @Test
    void testNullSecondaryScore() {
        String benchmarkName = "TestHeuristic";
        double primaryScore = 100000;
        JSONObject secondaryScore = null;
        JSONObject params = null;
        JSONObject pctiles = null;
        JMHResult jmhRes = new JMHResult(benchmarkName, primaryScore, secondaryScore, params, pctiles);
        try {
			jmhRes.parseSecondaryScores(secondaryScore);
			assert(true);	// no exception from null secondary score
		} catch (Exception e) {
			assert(false);
			e.printStackTrace();
		}
        
    }

    @Test
    void testSecondaryScoreWithVals() {
        String benchmarkName = "TestHeuristic";
        double primaryScore = 100000;
        JSONObject secondaryScore = new JSONObject();
        int stalls = 10000;
        JSONObject sc = new JSONObject();
        sc.put("score", stalls);
        secondaryScore.put("CYCLE_ACTIVITY.STALLS_TOTAL", sc);

        JSONObject params = null;
        JSONObject pctiles = null;
        JMHResult jmhRes = new JMHResult(benchmarkName, primaryScore, secondaryScore, params, pctiles);
        jmhRes.parseSecondaryScores(secondaryScore);
        assertEquals(jmhRes.STALLS_TOTAL, stalls);
    }

    @Test
    void testSecondaryScoreNoVal() {
        String benchmarkName = "TestHeuristic";
        double primaryScore = 100000;
        JSONObject secondaryScore = new JSONObject();
        JSONObject sc = new JSONObject();
        secondaryScore.put("CYCLE_ACTIVITY.STALLS_TOTAL", sc);
        JSONObject params = null;
        JSONObject pctiles = null;
        JMHResult jmhRes = new JMHResult(benchmarkName, primaryScore, secondaryScore, params, pctiles);
        jmhRes.parseSecondaryScores(secondaryScore);
        assertEquals(jmhRes.STALLS_TOTAL, 0);
    }
}
