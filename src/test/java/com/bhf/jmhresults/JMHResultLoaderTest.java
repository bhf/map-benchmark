package com.bhf.jmhresults;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class JMHResultLoaderTest {

    final String JMH_SAMPLE = "src/test/resources/SampleJMH.json";
    final String JMH_SAMPLE_NON_EXISTANT = "src/test/resources/DontExist.json";
    final String JMH_SAMPLE_BAD_FORMAT = "src/test/resources/SampleJMHBad.json";

    @Test
    void testNullFile() {
        JMHResultLoader resLoader = new JMHResultLoader();
        List<JMHResult> res = resLoader.getJMHResults(null);
        assertNotNull(res);
        assertTrue(res.size() == 0);
    }

    @Test
    void testEmptyStringFile() {
        JMHResultLoader resLoader = new JMHResultLoader();
        List<JMHResult> res = resLoader.getJMHResults("");
        assertNotNull(res);
        assertTrue(res.size() == 0);
    }

    @Test
    void testNonNullFile() {
        JMHResultLoader resLoader = new JMHResultLoader();
        List<JMHResult> res = resLoader.getJMHResults(JMH_SAMPLE);
        assertNotNull(res);
        assertTrue(res.size() > 0);
    }

    @Test
    void testNonExistantFile() {
        JMHResultLoader resLoader = new JMHResultLoader();
        try {
            List<JMHResult> res = resLoader.getJMHResults(JMH_SAMPLE_NON_EXISTANT);
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }
    }

    @Test
    void testBadlyFormattedFile() {
        JMHResultLoader resLoader = new JMHResultLoader();
        try {
            List<JMHResult> res = resLoader.getJMHResults(JMH_SAMPLE_BAD_FORMAT);
        } catch (Exception e) {
            assertTrue(e instanceof RuntimeException);
        }
    }
}
