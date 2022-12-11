package com.example.mytvshows.Listeners;

import com.example.mytvshows.Models.Search.SearchApiResponse;

public interface OnSearchSeriesApiListeners
{

    void onResponse(SearchApiResponse response);
    void onError(String message);

}
