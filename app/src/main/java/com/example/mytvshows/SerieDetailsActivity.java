package com.example.mytvshows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytvshows.Adapters.CastRecylerAdapter;
import com.example.mytvshows.Adapters.ProviderRecyclerAdapter;
import com.example.mytvshows.Adapters.RecommendationRecyclerAdapter;
import com.example.mytvshows.Listeners.OnCastMembersApiListeners;
import com.example.mytvshows.Listeners.OnDetailsApiListeners;
import com.example.mytvshows.Listeners.OnRecommendationApiListeners;
import com.example.mytvshows.Listeners.OnRecommendationClickListeners;
import com.example.mytvshows.Listeners.OnWatchProvidersApiListeners;
import com.example.mytvshows.Models.Cast.CastMembers;
import com.example.mytvshows.Models.SearchDetails.DetailsApiResponse;
import com.example.mytvshows.Models.Recommendation.RecommendationApiResponse;
import com.example.mytvshows.Models.providers.WatchProvidersApiResponse;
import com.squareup.picasso.Picasso;

import java.net.URL;

public class SerieDetailsActivity extends AppCompatActivity implements OnRecommendationClickListeners {
    WebView webView;
    String html;


    TextView textView_serie_name, textView_serie_first_air_date, textView_serie_last_air_date,textView_serie_runtime,textView_serie_rating, textView_serie_votes, textView_synopsis, textView_number_of_episode, textView_number_of_season, textView_nonproviders, trailer_text;

    ImageView imageView_serie_poster;
    RecyclerView recycler_recommendation, WatchProviders_recycler_view, recycler_serie_cast;
    CastRecylerAdapter cast_adapter;
    ProviderRecyclerAdapter provider_adapter;
    RecommendationRecyclerAdapter recommendation_adapter;
    Request_Manager Details_manager, cast_manager;
    Request_Manager Providers_manager,Recommendation_manager;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie_details);
// liaison entres les variables et les éléments du layout
        textView_synopsis       = findViewById(R.id.textView_serie_plot);
        textView_serie_name     = findViewById(R.id.textView_serie_name);
        textView_serie_rating   = findViewById(R.id.textView_serie_rating);
        textView_serie_first_air_date = findViewById(R.id.textView_serie_first_released);
        textView_serie_runtime  = findViewById(R.id.textView_serie_runtime);
        textView_serie_votes    = findViewById(R.id.textView_serie_votes);
        imageView_serie_poster  = findViewById(R.id.Image_View_serie_poster);
        textView_number_of_episode = findViewById(R.id.textView_number_of_episode);
        textView_number_of_season = findViewById(R.id.textView_number_of_seasons);
        textView_serie_last_air_date = findViewById(R.id.textView_serie_last_released);
        textView_nonproviders = findViewById(R.id.streaming);
        trailer_text =  findViewById(R.id.trailer_text);

        webView = findViewById(R.id.webview);
        webView.setInitialScale(253);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);

        WatchProviders_recycler_view = findViewById(R.id.watchProviders_recycler_view);
        recycler_recommendation = findViewById(R.id.recommendation_recycler_view);
        recycler_serie_cast = findViewById(R.id.recyler_serie_cast);



        Details_manager = new Request_Manager(this);
        Providers_manager = new Request_Manager(this);
        Recommendation_manager = new Request_Manager(this);
        cast_manager = new Request_Manager(this);

        String serie_id = getIntent().getStringExtra("data");

        dialog = new ProgressDialog(this);
        dialog.setTitle("Patientez...");
        dialog.show();

        Details_manager.searchSerieDetails(Details_listener,serie_id);
        Providers_manager.searchWatchProviders(Providers_listener,serie_id);
        Recommendation_manager.searchRecommendation(listeners,serie_id);
        cast_manager.searchCastMembers(Cast_listener,serie_id);

    }

// action en fonction de la réponse de l'API
    private OnDetailsApiListeners Details_listener = new OnDetailsApiListeners() {
        @Override
        public void onResponse(DetailsApiResponse response) {
            dialog.dismiss();
            if(response.equals(null)){
                Toast.makeText(SerieDetailsActivity.this,"Une erreur est survenue",Toast.LENGTH_SHORT).show();
                return;
            }
            showResults(response);
        }
        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(SerieDetailsActivity.this,"Une erreur est survenue",Toast.LENGTH_SHORT).show();
        }
    };

    private OnCastMembersApiListeners Cast_listener = new OnCastMembersApiListeners() {
        @Override
        public void onResponse(CastMembers response)
        {
            dialog.dismiss();
            if(response.equals(null)){
                Toast.makeText(SerieDetailsActivity.this,"Une erreur02 est survenue",Toast.LENGTH_SHORT).show();
                return;
            }
            showResults2(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(SerieDetailsActivity.this,"Une erreur02 est survenue",Toast.LENGTH_SHORT).show();
        }
    };

    private OnWatchProvidersApiListeners Providers_listener = new OnWatchProvidersApiListeners()
    {
        @Override
        public void onResponse(WatchProvidersApiResponse response) {
            dialog.dismiss();
            if(response.equals(null)){
                Toast.makeText(SerieDetailsActivity.this,"Une erreur03 est survenue",Toast.LENGTH_SHORT).show();
                return;
            }
            showResults3(response);
        }
        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(SerieDetailsActivity.this,"Une erreur03 est survenue",Toast.LENGTH_SHORT).show();
        }
    };

    private OnRecommendationApiListeners listeners = new OnRecommendationApiListeners() {
        @Override
        public void onResponse(RecommendationApiResponse response) {
            dialog.dismiss();
            if(response.equals(null)){
                Toast.makeText(SerieDetailsActivity.this,"Une erreur03 est survenue",Toast.LENGTH_SHORT).show();
                return;
            }
            showResults4(response);
        }

        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(SerieDetailsActivity.this,"Une erreur03 est survenue",Toast.LENGTH_SHORT).show();
        }
    };
// fonction nous permettant d'utiliser les reponses aux requête faites
    private void showResults(DetailsApiResponse response)
    {
        textView_serie_name.setText(response.getTitle());
        textView_serie_first_air_date.setText("Date de sortie du premier épisode : " + response.getFirst_air_date());
        textView_serie_last_air_date.setText("Date de sortie du dernier épisode : " + response.getLast_air_date());
        if(response.getEpisode_run_time().size() >= 1 )
        {
            textView_serie_runtime.setText("Durée d'un épisode : " + response.getEpisode_run_time().get(0) + " min");
        }
        else
        {
            textView_serie_runtime.setText("Durée d'un épisode : non communiqué");
        }
        textView_synopsis.setText("Synopsis : " + response.getOverview());
        textView_serie_votes.setText("Nombre de votes : " + response.getVote_count() + " (sur TMDB)");
        textView_serie_rating.setText("Note : " + response.getVote_average() + "/10 (sur TMDB)");
        textView_number_of_episode.setText("Nombre total d'épisodes : " + response.getNumber_of_episodes());
        textView_number_of_season.setText ("Nombre de saisons : " + response.getNumber_of_seasons());

        if (response.getId() == 31910)//naruto shippuden
        {
            html = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/tj1k4uURzUM\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
            webView.loadData(html,"text/html",null);
            trailer_text.setVisibility(View.VISIBLE);
        }
        if (response.getId() == 46260) //naruto
        {
            html = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/yKELA1qBAKA\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
            webView.loadData(html,"text/html",null);
            trailer_text.setVisibility(View.VISIBLE);
        }
        if(response.getId() == 37854)//one piece
        {
            html = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/VsKIrHlTvXg\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
            webView.loadData(html, "text/html", null);
            trailer_text.setVisibility(View.VISIBLE);
        }
        if(response.getId() == 1429)//snk
        {
            html = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/ymDAiSQFIrg\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
            webView.loadData(html, "text/html", null);
            trailer_text.setVisibility(View.VISIBLE);
        }
        if(response.getId() == 85937)// kimetsu no yaiba
        {
            html ="<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/VQGCKyvzIM4\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
            webView.loadData(html, "text/html", null);
            trailer_text.setVisibility(View.VISIBLE);
        }
        if(response.getId() == 65930)//my hero academia
        {
            html = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/KxDgJE0OEBI\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
            webView.loadData(html, "text/html", null);
            trailer_text.setVisibility(View.VISIBLE);
        }
        if(response.getId() == 1399)//games of thrones
        {
            html = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/gcTkNV5Vg1E\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
            webView.loadData(html, "text/html", null);
            trailer_text.setVisibility(View.VISIBLE);
        }
        if(response.getId() == 1396)//breaking bad
        {
            html = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/VFLkMDEO-Xc\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
            webView.loadData(html, "text/html", null);
            trailer_text.setVisibility(View.VISIBLE);
        }
        if(response.getId() == 95479)//jujutsu kaisen
        {
            html = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/eUTtCcHVVOA\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
            webView.loadData(html, "text/html", null);
            trailer_text.setVisibility(View.VISIBLE);
        }
        if(response.getId() == 94605)//LOL Arcane
        {
            html = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/3Svs_hl897c\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>";
            webView.loadData(html, "text/html", null);
            trailer_text.setVisibility(View.VISIBLE);
        }


        try
        {
            Picasso.get().load(response.getPoster_path()).into(imageView_serie_poster);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    private void showResults2(CastMembers response)
    {
        recycler_serie_cast.setHasFixedSize(true);
        recycler_serie_cast.setLayoutManager(new GridLayoutManager(this,1));
        cast_adapter = new CastRecylerAdapter(this,response.getCast());
        recycler_serie_cast.setAdapter(cast_adapter);
    }
   private void showResults3(WatchProvidersApiResponse response)
   {
       if(response.getResults().getFR() != null)
       {
           if(response.getResults().getFR().getFlatrate() != null)
           {
               WatchProviders_recycler_view.setHasFixedSize(true);
               WatchProviders_recycler_view.setLayoutManager(new GridLayoutManager(this, 2));
               provider_adapter = new ProviderRecyclerAdapter(this, response.getResults().getFR().getFlatrate(), Providers_listener);
               WatchProviders_recycler_view.setAdapter(provider_adapter);
           }
           else
           {
               textView_nonproviders.setText("Plateforme non communiquée");
           }
       }
       else
       {
           textView_nonproviders.setText("Plateforme non communiquée");
       }

   }
    private void showResults4(RecommendationApiResponse response)
    {
        recycler_recommendation.setHasFixedSize(true);
        recycler_recommendation.setLayoutManager(new GridLayoutManager(this,3));
        recommendation_adapter = new RecommendationRecyclerAdapter(this,response.getResults(),this);
        recycler_recommendation.setAdapter(recommendation_adapter);

    }
    @Override
    public void onRecommendationClicked(String serie_ID) {
        Intent i = new Intent(SerieDetailsActivity.this, SerieDetailsActivity.class).putExtra("data",serie_ID);
        finish();
        overridePendingTransition(0, 0);
        startActivity(i);
        overridePendingTransition(0, 0);
    }
}