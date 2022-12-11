package com.example.mytvshows.Models.Popular;

import java.util.List;

public class PopularApiResponse
{
    List<PopularArrayObjects> results = null;

    public List<PopularArrayObjects> getResults() {
        return results;
    }

    public void setResults(List<PopularArrayObjects> results) {
        this.results = results;
    }
}
