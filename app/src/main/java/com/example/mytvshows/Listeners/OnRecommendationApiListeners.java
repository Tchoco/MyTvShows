package com.example.mytvshows.Listeners;

import com.example.mytvshows.Models.Recommendation.RecommendationApiResponse;

public interface OnRecommendationApiListeners
{
    void onResponse(RecommendationApiResponse response);
    void onError(String message);
}
