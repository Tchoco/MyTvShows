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


import com.example.mytvshows.Listeners.OnWatchProvidersApiListeners;
import com.example.mytvshows.Models.providers.Streaming;
import com.example.mytvshows.Models.providers.WatchProvidersApiResponse;
import com.example.mytvshows.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProviderRecyclerAdapter extends RecyclerView.Adapter<ProviderViewHolder>
{
    Context context;
    List<Streaming> list;
    OnWatchProvidersApiListeners listeners;

    public ProviderRecyclerAdapter(Context context, List<Streaming> list, OnWatchProvidersApiListeners listeners) {
        this.context = context;
        this.list = list;
        this.listeners = listeners;
    }

    @NonNull
    @Override
    public ProviderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProviderViewHolder(LayoutInflater.from(context).inflate(R.layout.watch_providers_list,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProviderViewHolder holder, int position) {
        holder.textView_providers.setText(list.get(position).getProvider_name());
        holder.textView_providers.setSelected(true);
        Picasso.get().load(list.get(position).getLogo_path()).into(holder.imageview_poster);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class ProviderViewHolder extends RecyclerView.ViewHolder
{
    ImageView imageview_poster;
    TextView  textView_providers;
    CardView  provider_container;

    public ProviderViewHolder(@NonNull View itemView)
    {
        super(itemView);
        imageview_poster = itemView.findViewById(R.id.imageView_WatchProviders);
        textView_providers = itemView.findViewById(R.id.WatchProvider_name_container);
        provider_container = itemView.findViewById(R.id.WatchProviders_container);
    }
}

