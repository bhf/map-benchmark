package com.bhf.jmhresults;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JMHJSONToCSVProcessor {

 
  public void processJSON(String jsonFile, boolean printHeaders) {
    JSONParser p = new JSONParser();
    try {
      Object parsed = p.parse(new FileReader(jsonFile));
      JSONArray testDets = (JSONArray) parsed;

      String singleHeaders = null;
      List<StringBuilder> allLines = new ArrayList<StringBuilder>();

      for (int i = 0; i < testDets.size(); i++) {
        JSONObject el = (JSONObject) testDets.get(i);
        JSONObject primaryMetric = (JSONObject) el.get("primaryMetric");
        String primaryScoreVal = primaryMetric.get("score").toString();
        JSONObject secondaryMetrics = (JSONObject) el.get("secondaryMetrics");

        StringBuilder secondaryBuilder = new StringBuilder();
        StringBuilder secondaryHeaders = new StringBuilder();
        for (Object o : secondaryMetrics.keySet()) {

          JSONObject sm = (JSONObject) secondaryMetrics.get(o);
          String scoreVal = sm.get("score").toString();
          secondaryBuilder.append(scoreVal + ",");
          secondaryHeaders.append(o + ",");
        }

        StringBuilder paramsBuilder = new StringBuilder();
        StringBuilder paramsHeaders = new StringBuilder();
        JSONObject params = (JSONObject) el.get("params");

        if(null!=params)
        {
          for (Object o : params.keySet()) {

            String sm = params.get(o).toString();
            paramsBuilder.append(sm + ",");
            paramsHeaders.append(o + ",");
          }
        }

        String benchmark = el.get("benchmark").toString();
        StringBuilder rem = new StringBuilder();
        StringBuilder headers = new StringBuilder();
        headers.append("benchmark" + ",primaryScore,");
        rem.append(benchmark + "," + primaryScoreVal + ",");

        headers.append(paramsHeaders).append(secondaryHeaders);
        rem.append(paramsBuilder).append(secondaryBuilder);
        allLines.add(rem);

        if (null == singleHeaders) {
          singleHeaders = headers.toString();
        }
      }

      if (printHeaders) {
        System.out.println(singleHeaders);
      }
      for (StringBuilder sb : allLines) {
        System.out.println(sb);
      }

    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ParseException e) {
      throw new RuntimeException(e);
    }
  }
}
