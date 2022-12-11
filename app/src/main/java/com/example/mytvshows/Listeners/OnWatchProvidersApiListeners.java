package com.example.mytvshows.Listeners;

import com.example.mytvshows.Models.providers.WatchProvidersApiResponse;

public interface OnWatchProvidersApiListeners
{
    void onResponse(WatchProvidersApiResponse response);
    void onError(String message);
}
