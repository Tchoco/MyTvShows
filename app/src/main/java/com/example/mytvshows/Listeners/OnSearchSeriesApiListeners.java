package com.example.mytvshows.Listeners;

import com.example.mytvshows.Models.SearchApiResponse;

public interface OnSearchSeriesApiListeners
{

    void onResponse(SearchApiResponse response);
    void onError(String message);

}
