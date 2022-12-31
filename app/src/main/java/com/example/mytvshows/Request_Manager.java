package com.example.mytvshows;

import android.content.Context;
import android.widget.Toast;

import com.example.mytvshows.Listeners.OnCastMembersApiListeners;
import com.example.mytvshows.Listeners.OnDetailsApiListeners;
import com.example.mytvshows.Listeners.OnPopularApiListeners;
import com.example.mytvshows.Listeners.OnRecommendationApiListeners;
import com.example.mytvshows.Listeners.OnSearchSeriesApiListeners;
import com.example.mytvshows.Listeners.OnWatchProvidersApiListeners;
import com.example.mytvshows.Models.Cast.CastMembers;
import com.example.mytvshows.Models.SearchDetails.DetailsApiResponse;
import com.example.mytvshows.Models.Popular.PopularApiResponse;
import com.example.mytvshows.Models.Recommendation.RecommendationApiResponse;
import com.example.mytvshows.Models.Search.SearchApiResponse;
import com.example.mytvshows.Models.providers.WatchProvidersApiResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class Request_Manager
{
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Request_Manager(Context context)
    {
        this.context = context;
    }

    public void searchSeries(OnSearchSeriesApiListeners listener, String serie_name)
    {
        String key = "c46870fff6c94f30951b91811ae9238a";
        String page = "1";
        String include_adult = "false";
        //utilisation de la bibliothèque retrofit pour gérer nos requêtes
        getSeries getSeries = retrofit.create(Request_Manager.getSeries.class);
        Call<SearchApiResponse> call = getSeries.callSeries(key,page,serie_name,include_adult);
        System.out.print(call);

        call.enqueue(new Callback<SearchApiResponse>() {
            @Override
            public void onResponse(Call<SearchApiResponse> call, Response<SearchApiResponse> response)
            {
                if(!response.isSuccessful())
                {
                    Toast.makeText(context,"impossible d'acquerir les données0 !!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<SearchApiResponse> call, Throwable t)
            {
                listener.onError(t.getMessage());

            }
        });
    }
    public void searchSerieDetails(OnDetailsApiListeners listener, String serie_id)
    {
        String key = "c46870fff6c94f30951b91811ae9238a";
        String language = "fr";
        getSerieDetails getSerieDetails = retrofit.create(Request_Manager.getSerieDetails.class);
        Call<DetailsApiResponse> call = getSerieDetails.callSerieDetails(serie_id,key,language);

        call.enqueue(new Callback<DetailsApiResponse>() {
            @Override
            public void onResponse(Call<DetailsApiResponse> call, Response<DetailsApiResponse> response)
            {
                if(!response.isSuccessful())
                {
                    Toast.makeText(context,"impossible d'acquerir les données1 !!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<DetailsApiResponse> call, Throwable t)
            {
                listener.onError(t.getMessage());

            }
        });
    }

    public void searchWatchProviders(OnWatchProvidersApiListeners listener, String serie_id)
    {
        String key = "c46870fff6c94f30951b91811ae9238a";
        String page = "1";
        getWatchProviders getWatchProviders = retrofit.create(Request_Manager.getWatchProviders.class);
        Call<WatchProvidersApiResponse> call = getWatchProviders.callWatchProviders(serie_id,key,page);

        call.enqueue(new Callback<WatchProvidersApiResponse>() {
            @Override
            public void onResponse(Call<WatchProvidersApiResponse> call, Response<WatchProvidersApiResponse> response)
            {
                if(!response.isSuccessful())
                {
                    Toast.makeText(context,"impossible d'acquerir les données2 !!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<WatchProvidersApiResponse> call, Throwable t)
            {
                listener.onError(t.getMessage());

            }
        });
    }
    public void searchCastMembers(OnCastMembersApiListeners listener, String serie_id)
    {
        String key = "c46870fff6c94f30951b91811ae9238a";
        String page = "1";
        getCast getCast = retrofit.create(Request_Manager.getCast.class);
        Call<CastMembers> call = getCast.callCastMembers(serie_id,key,page);

        call.enqueue(new Callback<CastMembers>() {
            @Override
            public void onResponse(Call<CastMembers> call, Response<CastMembers> response)
            {
                if(!response.isSuccessful())
                {
                    Toast.makeText(context,"impossible d'acquerir les données !!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<CastMembers> call, Throwable t)
            {
                listener.onError(t.getMessage());

            }
        });
    }
    public void searchRecommendation(OnRecommendationApiListeners listener, String serie_id)
    {
        String key = "c46870fff6c94f30951b91811ae9238a";
        String page = "1";
        getRecommendation getRecommendation = retrofit.create(Request_Manager.getRecommendation.class);
        Call<RecommendationApiResponse> call = getRecommendation.callRecommendation(serie_id,key,page);

        call.enqueue(new Callback<RecommendationApiResponse>() {
            @Override
            public void onResponse(Call<RecommendationApiResponse> call, Response<RecommendationApiResponse> response)
            {
                if(!response.isSuccessful())
                {
                    Toast.makeText(context,"impossible d'acquerir les données5 !!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.onResponse(response.body());
            }

            @Override
            public void onFailure(Call<RecommendationApiResponse> call, Throwable t)
            {
                listener.onError(t.getMessage());

            }
        });
    }
    public void searchPopular(OnPopularApiListeners listener)
    {
        String key = "c46870fff6c94f30951b91811ae9238a";
        String page = "1";
        String language = "Fr";
        getPopularSeries getPopularSeries = retrofit.create(Request_Manager.getPopularSeries.class);
        Call<PopularApiResponse> call = getPopularSeries.callPopular(key,language,page);

        call.enqueue(new Callback<PopularApiResponse>() {
            @Override
            public void onResponse(Call<PopularApiResponse> call, Response<PopularApiResponse> response)
            {
                if(!response.isSuccessful())
                {
                    Toast.makeText(context,"impossible d'acquerir les données !!!",Toast.LENGTH_SHORT).show();
                    return;
                }
                listener.OnResponse(response.body());
            }

            @Override
            public void onFailure(Call<PopularApiResponse> call, Throwable t)
            {
                listener.onError(t.getMessage());

            }
        });
    }
    public interface getSeries
    {
        @Headers({
                "Accept: application/json",
        })
        @GET("search/tv")
        Call<SearchApiResponse> callSeries(
                @Query("api_key") String key,
                @Query("page") String page,
                @Query("query") String serie_name,
                @Query("include_adult") String include_adult
        );
    }
    public interface getSerieDetails
    {
        @Headers({
                "Accept: application/json",
        })
        @GET("tv/{serie_id}")
        Call<DetailsApiResponse> callSerieDetails(
                @Path("serie_id") String id,
                @Query("api_key") String key,
                @Query("language") String language
        );
    }
    public interface getWatchProviders
    {
        @Headers({
                "Accept: application/json",
        })
        @GET("tv/{serie_id}/watch/providers")
        Call<WatchProvidersApiResponse> callWatchProviders(
                @Path("serie_id") String id,
                @Query("api_key") String key,
                @Query("page") String page
        );
    }
    public interface getRecommendation
    {
        @Headers({
                "Accept: application/json",
        })
        @GET("tv/{serie_id}/recommendations")
        Call<RecommendationApiResponse> callRecommendation(
                @Path("serie_id") String id,
                @Query("api_key") String key,
                @Query("page") String page
        );

    }
    public interface getCast
    {
        @Headers({
                "Accept: application/json",
        })
        @GET("tv/{serie_id}/credits")
        Call<CastMembers> callCastMembers(
                @Path("serie_id") String id,
                @Query("api_key") String key,
                @Query("page") String page
        );
    }
    public interface getPopularSeries
    {
        @Headers({
                "Accept: application/json",
        })
        @GET("tv/top_rated")
        Call<PopularApiResponse> callPopular(
                @Query("api_key") String key,
                @Query("language") String language,
                @Query("page") String page
        );
    }

}
