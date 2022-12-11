package com.example.mytvshows.Listeners;

import com.example.mytvshows.Models.Recommendation.RecommendationApiResponse;
import com.example.mytvshows.Models.SearchApiResponse;

public interface OnRecommendationApiListeners
{
    void onResponse(RecommendationApiResponse response);
    void onError(String message);
}
