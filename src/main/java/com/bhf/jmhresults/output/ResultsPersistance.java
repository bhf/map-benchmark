package com.bhf.jmhresults.output;

import java.util.List;

import com.bhf.jmhresults.EnrichedResult;
import com.bhf.jmhresults.JMHResult;

public interface ResultsPersistance
{

    public void persistEnrichedResults(List<EnrichedResult> results, String meta);
    
}
