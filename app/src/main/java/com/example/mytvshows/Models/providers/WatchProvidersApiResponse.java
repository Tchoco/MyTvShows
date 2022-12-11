package com.example.mytvshows.Models.providers;

import java.util.List;

public class WatchProvidersApiResponse
{
    int id = 0;
    Results results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
