package com.bhf.jmhresults;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JMHResultLoader {

    Logger logger = LoggerFactory.getLogger(getClass());

    public List<JMHResult> getJMHResults(String jsonFile) {

        List<JMHResult> res = new ArrayList<JMHResult>();

        if (null != jsonFile && jsonFile.length() > 0) {
            JSONParser p = new JSONParser();
            try {
                Object parsed = p.parse(new FileReader(jsonFile));
                JSONArray testDets = (JSONArray) parsed;
                processJMHJson(res, testDets);

            } catch (IOException e) {
                logger.error("Got IOException on file: " + jsonFile, e);
                throw new RuntimeException(e);
            } catch (ParseException e) {
                logger.error("Got ParseException on file: " + jsonFile, e);
                throw new RuntimeException(e);
            }
        }

        return res;
    }

    void processJMHJson(List<JMHResult> res, JSONArray testDets) {
        for (int i = 0; i < testDets.size(); i++) {
            JSONObject el = (JSONObject) testDets.get(i);
            JSONObject primaryMetric = (JSONObject) el.get("primaryMetric");
            String primaryScoreVal = primaryMetric.get("score").toString();
            JSONObject scorePctiles=null;
            
            if(primaryMetric.containsKey("scorePercentiles")) {
                scorePctiles = (JSONObject)primaryMetric.get("scorePercentiles");
            }
            
            double primScore = Double.parseDouble(primaryScoreVal);
            JSONObject secondaryMetrics = (JSONObject) el.get("secondaryMetrics");
            JSONObject params = (JSONObject) el.get("params");
            String benchmark = el.get("benchmark").toString();
            JMHResult r = new JMHResult(benchmark, primScore, secondaryMetrics, params, scorePctiles);
            res.add(r);
        }
    }
}
