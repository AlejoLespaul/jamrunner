package com.sondeos.jamrunner.Dto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoResult {
    public static final String REGEX_PASSING = "([0-9]{1}|[0-9]{2}|[0-9]{3})?\\spassing";
    public static final String REGEX_FAILING = "([0-9]{1}|[0-9]{2}|[0-9]{3})?\\sfailing";
    private static final String REGEX_LINES = "([0-9]{1}|[0-9]{2}|[0-9]{3})?\\slines";
    public static final String REGEX_PERFORMANCE = "([0-9]{1}ms|[0-9]{2}ms|[0-9]{3}ms|[0-9]{4}ms|[0-9]{5}ms|[0-9]{6}ms)";
    Integer testPassed;
    Integer testFailed;
    String performance;
    Integer lines;

    public InfoResult(String response) {
        testPassed = parseTestPassed(response);
        testFailed = parseTestFailed(response);
        lines = parseLinesOfCode(response);
        performance = parsePerformanse(response);
    }

    private String parsePerformanse(String response) {
        return parseResponse(response, REGEX_PERFORMANCE, "-");
    }

    private Integer parseLinesOfCode(String response) {
        return parseResponse(response, REGEX_LINES, 0);
    }

    private Integer parseTestFailed(String response) {
        return parseResponse(response, REGEX_FAILING, 0);
    }

    private Integer parseTestPassed(String response) {
        return parseResponse(response, REGEX_PASSING,0);
    }

    private Integer parseResponse(String response, String regexToParse, Integer defaultValue) {
        Pattern pattern = Pattern.compile(regexToParse);
        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            return Integer.valueOf(matcher.group(1));
        }
        return defaultValue;
    }

    private String parseResponse(String response, String regexToParse, String defaultValue) {
        Pattern pattern = Pattern.compile(regexToParse);
        Matcher matcher = pattern.matcher(response);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return defaultValue;
    }

    public Integer getTestPassed() {
        return testPassed;
    }

    public Integer getTestFailed() {
        return testFailed;
    }

    public String getPerformance() {
        return performance;
    }

    public Integer getLines() {
        return lines;
    }

    @Override
    public String toString() {
        return "{\n" +
                "\t\"testPassed\":" + testPassed + ",\n"+
                "\t\"testFailed\":" + testFailed + ",\n"+
                "\t\"performance\":\"" + performance + "\",\n"+
                "\t\"lines\":" + lines + "\n" +
                '}';
    }

    /**
     *
     {
     testPassed: 4,
     testFailed: 1,
     performance: "54ms",
     lines: 12
     }
     */
}
