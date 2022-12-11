package com.example.mytvshows.Models.Search;

public class SearchArrayObjectsForSearchMovies
{
    int id = 0;
    String poster_path ="";
    String name="";

    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        String path = "https://image.tmdb.org/t/p/w500/" + poster_path;
        return path;
    }
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
