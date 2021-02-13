package com.sondeos.jamrunner;

public class InfoRepo {
    private String repo;
    private String name;

    public String getRepo() {
        return repo;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "{" +
                "repo='" + repo + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
