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

import com.example.mytvshows.Listeners.OnSeriesClickListeners;
import com.example.mytvshows.Models.PopularArrayObjects;
import com.example.mytvshows.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PopularRecyclerAdapter extends RecyclerView.Adapter<PopularViewHolder>{

    Context context;
    List<PopularArrayObjects> list;
    OnSeriesClickListeners listener;

    public PopularRecyclerAdapter(Context context, List<PopularArrayObjects> list, OnSeriesClickListeners listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PopularViewHolder(LayoutInflater.from(context).inflate(R.layout.home_series_list,parent,false));    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position)
    {
        holder.textView_serie.setText(list.get(position).getName());
        holder.textView_serie.setSelected(true);
        Picasso.get().load(list.get(position).getPoster_path()).into(holder.imageView_poster);

        holder.home_container.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                listener.onSerieClicked(String.valueOf(list.get(position).getId()));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class PopularViewHolder extends RecyclerView.ViewHolder
{
    ImageView imageView_poster;
    TextView textView_serie;
    CardView home_container;
    public PopularViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_poster= itemView.findViewById(R.id.Image_View_poster);
        textView_serie = itemView.findViewById(R.id.Text_View_serie_container);
        home_container = itemView.findViewById(R.id.home_container);
    }
}