package com.example.mytvshows.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mytvshows.Listeners.OnRecommendationApiListeners;
import com.example.mytvshows.Listeners.OnRecommendationClickListeners;
import com.example.mytvshows.Models.Recommendation.RecommendationArrayObjects;
import com.example.mytvshows.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecommendationRecyclerAdapter extends RecyclerView.Adapter<RecommendationViewHolder>
{
    Context context;
    List<RecommendationArrayObjects> list;
    OnRecommendationClickListeners listeners;

    public RecommendationRecyclerAdapter(Context context, List<RecommendationArrayObjects> list, OnRecommendationClickListeners listeners) {
        this.context = context;
        this.list = list;
        this.listeners = listeners;
    }

    @NonNull
    @Override
    public RecommendationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RecommendationViewHolder((LayoutInflater.from(context).inflate(R.layout.recommendation_series_list,parent,false)));
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendationViewHolder holder, int position) {
        holder.textView_serie.setText(list.get(position).getName());
        holder.textView_serie.setSelected(true);
        Picasso.get().load(list.get(position).getPoster_path()).into(holder.imageView_poster);

        holder.recommendation_container.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listeners.onRecommendationClicked(String.valueOf(list.get(position).getId()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class RecommendationViewHolder extends RecyclerView.ViewHolder{

    ImageView imageView_poster;
    TextView textView_serie;
    CardView recommendation_container;

    public RecommendationViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_poster = itemView.findViewById(R.id.Image_View_recommendation_poster);
        textView_serie = itemView.findViewById(R.id.Text_View_serie_recommendation_container);
        recommendation_container = itemView.findViewById(R.id.home_recommendation_container);
    }
}