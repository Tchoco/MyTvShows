package com.example.mytvshows.Models.Recommendation;

import java.util.List;

public class RecommendationApiResponse
{
    List<RecommendationArrayObjects> results = null;

    public List<RecommendationArrayObjects> getResults() {
        return results;
    }

    public void setResults(List<RecommendationArrayObjects> results) {
        this.results = results;
    }
}
