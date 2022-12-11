package com.example.mytvshows.Listeners;

import com.example.mytvshows.Models.Cast.CastMembers;

public interface OnCastMembersApiListeners
{
    void onResponse(CastMembers response);
    void onError(String message);
}
