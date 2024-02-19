package com.bhf.jmhresults;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMHResultLoader {
	
	Logger logger=LoggerFactory.getLogger(getClass());

    public List<JMHResult> getJMHResults(String jsonFile) {

        List<JMHResult> res = new ArrayList<JMHResult>();
        JSONParser p = new JSONParser();
        try {
            Object parsed = p.parse(new FileReader(jsonFile));
            JSONArray testDets = (JSONArray) parsed;

            for (int i = 0; i < testDets.size(); i++) {
                JSONObject el = (JSONObject) testDets.get(i);
                JSONObject primaryMetric = (JSONObject) el.get("primaryMetric");
                String primaryScoreVal = primaryMetric.get("score").toString();
                BigDecimal primScore = new BigDecimal(primaryScoreVal);
                JSONObject secondaryMetrics = (JSONObject) el.get("secondaryMetrics");
                JSONObject params = (JSONObject) el.get("params");
                String benchmark = el.get("benchmark").toString();
                JMHResult r = new JMHResult(benchmark, primScore, secondaryMetrics, params);
                res.add(r);
            }

        } catch (IOException e) {
        	logger.error("Got IOException on file: "+jsonFile,e);
            throw new RuntimeException(e);
        } catch (ParseException e) {
        	logger.error("Got ParseException on file: "+jsonFile,e);
            throw new RuntimeException(e);
        }

        return res;
    }
}
