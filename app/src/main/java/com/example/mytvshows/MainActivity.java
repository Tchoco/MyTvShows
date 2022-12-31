package com.example.mytvshows;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mytvshows.Adapters.HomeRecyclerAdapter;
import com.example.mytvshows.Adapters.PopularRecyclerAdapter;
import com.example.mytvshows.Listeners.OnPopularApiListeners;
import com.example.mytvshows.Listeners.OnSearchSeriesApiListeners;
import com.example.mytvshows.Listeners.OnSeriesClickListeners;
import com.example.mytvshows.Models.Popular.PopularApiResponse;
import com.example.mytvshows.Models.Search.SearchApiResponse;

public class MainActivity extends AppCompatActivity implements OnSeriesClickListeners {

    SearchView search_view;

    TextView search_serie_textview;

    RecyclerView home_recycler_view;

    Request_Manager manager, popular_manager;

    HomeRecyclerAdapter adapter;

    PopularRecyclerAdapter adapter_popular_series;

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // liaison entre les varibales créées et les éléments du layout
        search_view = findViewById(R.id.search_view);
        home_recycler_view = findViewById(R.id.home_recycler_view);
        search_serie_textview = findViewById(R.id.search_serie_text);

        dialog = new ProgressDialog(this);
        manager = new Request_Manager(this);
        popular_manager = new Request_Manager(this);

        dialog.setTitle("Patientez...");
        dialog.show();

        popular_manager.searchPopular(listeners);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search_serie_textview.setText("Résultats pour : " + query);
                dialog.setTitle("Patientez...");
                dialog.show();
                manager.searchSeries(listener, query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
// quoi faire en fonction de la réponse de l'API
    private final OnSearchSeriesApiListeners listener = new OnSearchSeriesApiListeners() {
        @Override
        public void onResponse(SearchApiResponse response) {
            dialog.dismiss();
            if (response == null) {
                Toast.makeText(MainActivity.this, "les données ne sont pas disponibles", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult(response);
        }

        @Override
        public void onError(String message)
        {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "Une erreur est survenue", Toast.LENGTH_SHORT).show();
            return;
        }
    };

    private final OnPopularApiListeners listeners = new OnPopularApiListeners() {
        @Override
        public void OnResponse(PopularApiResponse response) {
            dialog.dismiss();
            if (response == null) {
                Toast.makeText(MainActivity.this, "les données ne sont pas disponibles", Toast.LENGTH_SHORT).show();
                return;
            }
            showResult_popular_series(response);
        }
        @Override
        public void onError(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, "Une erreur est survenue", Toast.LENGTH_SHORT).show();
            return;

        }
    };
// fonction utilisant nos adapter et permettant de configurer certains paramètres de la recyclerview
    private void showResult(SearchApiResponse response)
    {
        home_recycler_view.setHasFixedSize(true);
        home_recycler_view.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        adapter = new HomeRecyclerAdapter(this, response.getResults(), this);
        home_recycler_view.setAdapter(adapter);

    }

    private void showResult_popular_series(PopularApiResponse response)
    {
        home_recycler_view.setHasFixedSize(true);
        home_recycler_view.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        adapter_popular_series = new PopularRecyclerAdapter(this,response.getResults(),this);
        home_recycler_view.setAdapter(adapter_popular_series);
    }
// un onclicklistener permettant de changer d'activité et sauvegarder la valeur "data" qui correspond à l'id de la série dans la series detail activity
    @Override
    public void onSerieClicked(String id)
    {
        startActivity(new Intent(MainActivity.this,SerieDetailsActivity.class)
                .putExtra("data", id));
    }
}
