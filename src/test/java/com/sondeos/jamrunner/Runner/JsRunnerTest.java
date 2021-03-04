package com.sondeos.jamrunner.Runner;

import com.sondeos.jamrunner.JamRunnerApplication;
import com.sondeos.jamrunner.Runner.JsRunner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JamRunnerApplication.class})
@WebAppConfiguration
public class JsRunnerTest {
    private String workspace = "/tmp/";

    @Test
    public void can_run_an_exists_project() {
        String repo = "https://github.com/AlejoLespaul/factorialize";
        String name = "GroupA";
        String result = new JsRunner(repo, name).run();

        assertTrue(result.contains("passing"));
        assertFalse(new File(workspace + name).exists());
    }

    @Test
    public void fail_if_the_repo_not_exists() {
        String repo = "https://github.com/AlejoLespaul/anInexistsProject";
        String name = "GroupA";
        String message = "";
        try {
            new JsRunner(repo, name).run();
            fail();
        } catch (RuntimeException e) {
            message = e.getMessage();
        }
        assertEquals(message, JsRunner.REPO_NOT_EXISTS);
    }

    @Test
    public void can_know_how_many_lines_of_code_has_index_file() {
        String repo = "https://github.com/AlejoLespaul/factorialize";
        String name = "GroupA";
        String result = new JsRunner(repo, name).run();

        assertTrue(result.contains("11 lines"));
        assertFalse(new File(workspace + name).exists());
    }
}
