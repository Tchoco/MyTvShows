package com.example.mytvshows.Models.providers;

import org.json.JSONObject;

import java.util.List;

public class FR
{
    String link ="";
    List<Streaming> flatrate;

    public FR(){

    }

    public List<Streaming> getFlatrate() {
        return flatrate;
    }

    public void setFlatrate(List<Streaming> flatrate) {
        this.flatrate = flatrate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
