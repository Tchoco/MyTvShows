package com.example.mytvshows.Listeners;

import com.example.mytvshows.Models.PopularApiResponse;

public interface OnPopularApiListeners
{
    void OnResponse(PopularApiResponse response);
    void onError(String message);

}
