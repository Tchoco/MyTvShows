package com.example.mytvshows.Listeners;

import com.example.mytvshows.Models.SearchDetails.DetailsApiResponse;

public interface OnDetailsApiListeners {
    void onResponse(DetailsApiResponse response);
    void onError(String message);
}
