package com.example.mytvshows.Models.providers;

public class Streaming
{
    String logo_path = "";
    int provider_id =0;
    String provider_name = "";
    int display_priority = 0 ;

    public Streaming(){

    }
    public String getLogo_path() {
        String logo = "https://image.tmdb.org/t/p/w500/" + logo_path;
        return logo;
    }

    public void setLogo_path(String logo_path) {
        this.logo_path = logo_path;
    }

    public int getProvider_id() {
        return provider_id;
    }

    public void setProvider_id(int provider_id) {
        this.provider_id = provider_id;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public void setProvider_name(String provider_name) {
        this.provider_name = provider_name;
    }

    public int getDisplay_priority() {
        return display_priority;
    }

    public void setDisplay_priority(int display_priority) {
        this.display_priority = display_priority;
    }
}
