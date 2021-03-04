package com.sondeos.jamrunner.Dto;

import com.sondeos.jamrunner.Dto.InfoResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class InfoResultTest {
    @Test
    public void testItCanParseResponse(){
        String result = "> ... return 5040  5 passing (5ms)  1 failing  1) Factorialize       (Test-5) 7 should return 5040:      AssertionError [ERR_ASSERTION]: Input A expected to strictly equal input B:+ expected - actual- 5040+ 10      + expected - actual      -5040      +10            at Context.it (test.js:61:16) 11 lines";

        InfoResult info = new InfoResult(result);

        assertEquals(Integer.valueOf(5), info.getTestPassed());
        assertEquals(Integer.valueOf(1), info.getTestFailed());
        assertEquals("5ms", info.getPerformance());
        assertEquals(Integer.valueOf(11), info.getLines());
    }
}
