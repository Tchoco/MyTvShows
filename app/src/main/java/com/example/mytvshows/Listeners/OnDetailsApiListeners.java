package com.example.mytvshows.Listeners;

import com.example.mytvshows.Models.DetailsApiResponse;

public interface OnDetailsApiListeners {
    void onResponse(DetailsApiResponse response);
    void onError(String message);
}
