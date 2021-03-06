package com.example.myapplication.adapters.animeadapters.recycleradapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.activities.animepage.anime_detail_mvp.AnimeDetailActivity;
import com.example.myapplication.models.animemodels.AnimeNewReleaseResultModel;
import com.example.myapplication.R;
import com.example.myapplication.activities.animepage.watch_anime_mvp.WatchAnimeEpisodeActivity;
import com.example.myapplication.databinding.ItemListAnimeBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnimeRecyclerNewReleasesAdapter extends RecyclerView.Adapter<AnimeRecyclerNewReleasesAdapter.ViewHolder> {
    private Context context;
    private List<AnimeNewReleaseResultModel> animeNewReleaseResultModelList;

    public AnimeRecyclerNewReleasesAdapter(Context context, List<AnimeNewReleaseResultModel> animeNewReleaseResultModelList) {
        this.context = context;
        this.animeNewReleaseResultModelList = animeNewReleaseResultModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ItemListAnimeBinding itemListBinding = ItemListAnimeBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemListBinding);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String episode = animeNewReleaseResultModelList.get(position).getAnimeEpisodeNumber();
        if (episode.startsWith("0")) {
            episode = episode.substring(1);
        }
        holder.itemListBinding.textTitle.setText(animeNewReleaseResultModelList.get(position).getAnimeEpisode() + " Episode " + episode);
//        holder.itemListBinding.textTitle.setText(animeNewReleaseResultModelList.get(position).getAnimeEpisode());
        Picasso.get().load(animeNewReleaseResultModelList.get(position).getEpisodeThumb()).into(holder.itemListBinding.imageViewBackground);

        if (animeNewReleaseResultModelList.get(position).getAnimeEpisodeType().equalsIgnoreCase(context.getResources().getString(R.string.series_string))) {
            holder.itemListBinding.cardEpisodeType.setCardBackgroundColor(context.getResources().getColor(R.color.blue_series_color));
            holder.itemListBinding.textEpisodeType.setText(context.getResources().getString(R.string.series_string));
            holder.itemListBinding.cardEpisodeStatus.setVisibility(View.VISIBLE);
        } else if (animeNewReleaseResultModelList.get(position).getAnimeEpisodeType().equalsIgnoreCase(context.getResources().getString(R.string.ova_string))) {
            holder.itemListBinding.cardEpisodeType.setCardBackgroundColor(context.getResources().getColor(R.color.pink_series_color));
            holder.itemListBinding.textEpisodeType.setText(context.getResources().getString(R.string.ova_string));
            holder.itemListBinding.cardEpisodeStatus.setVisibility(View.VISIBLE);
        } else if (animeNewReleaseResultModelList.get(position).getAnimeEpisodeType().equalsIgnoreCase(context.getResources().getString(R.string.ona_string))) {
            holder.itemListBinding.cardEpisodeType.setCardBackgroundColor(context.getResources().getColor(R.color.purple_series_color));
            holder.itemListBinding.textEpisodeType.setText(context.getResources().getString(R.string.ona_string));
            holder.itemListBinding.cardEpisodeStatus.setVisibility(View.VISIBLE);
        } else if (animeNewReleaseResultModelList.get(position).getAnimeEpisodeType().equalsIgnoreCase(context.getResources().getString(R.string.la_string))) {
            holder.itemListBinding.cardEpisodeType.setCardBackgroundColor(context.getResources().getColor(R.color.red_series_color));
            holder.itemListBinding.textEpisodeType.setText(context.getResources().getString(R.string.la_string));
            holder.itemListBinding.cardEpisodeStatus.setVisibility(View.VISIBLE);
        } else if (animeNewReleaseResultModelList.get(position).getAnimeEpisodeType().equalsIgnoreCase(context.getResources().getString(R.string.movie_string))) {
            holder.itemListBinding.cardEpisodeType.setCardBackgroundColor(context.getResources().getColor(R.color.green_series_color));
            holder.itemListBinding.textEpisodeType.setText(context.getResources().getString(R.string.movie_string));
            holder.itemListBinding.cardEpisodeStatus.setVisibility(View.GONE);
        } else if (animeNewReleaseResultModelList.get(position).getAnimeEpisodeType().equalsIgnoreCase(context.getResources().getString(R.string.special_string))) {
            holder.itemListBinding.cardEpisodeType.setCardBackgroundColor(context.getResources().getColor(R.color.orange_series_color));
            holder.itemListBinding.textEpisodeType.setText(context.getResources().getString(R.string.special_string));
            holder.itemListBinding.cardEpisodeStatus.setVisibility(View.VISIBLE);
        }

        if (animeNewReleaseResultModelList.get(position).getAnimeEpisodeStatus().equalsIgnoreCase(context.getResources().getString(R.string.ongoing_text))) {
            holder.itemListBinding.cardEpisodeStatus.setCardBackgroundColor(context.getResources().getColor(R.color.ongoing_color));
            holder.itemListBinding.textEpisodeStatus.setText(context.getResources().getString(R.string.ongoing_text));
        } else if (animeNewReleaseResultModelList.get(position).getAnimeEpisodeStatus().equalsIgnoreCase(context.getResources().getString(R.string.completed_text))) {
            holder.itemListBinding.cardEpisodeStatus.setCardBackgroundColor(context.getResources().getColor(R.color.completed_color));
            holder.itemListBinding.textEpisodeStatus.setText(context.getResources().getString(R.string.completed_text));
        } else {
            holder.itemListBinding.cardEpisodeStatus.setVisibility(View.GONE);
            holder.itemListBinding.textEpisodeStatus.setVisibility(View.GONE);
        }

//        holder.itemListBinding.cardEpisodeNumber.setCardBackgroundColor(context.getResources().getColor(R.color.white_with_opacity));
//        holder.itemListBinding.textEpisodeNumber.setText("Episode " + animeNewReleaseResultModelList.get(position).getAnimeEpisodeNumber());
        holder.itemListBinding.relativeItem.setOnClickListener(v -> {
//            Intent intentToVideoWatchActivity = new Intent(context.getApplicationContext(), AnimeDetailActivity.class);
            Intent intentToVideoWatchActivity = new Intent(context.getApplicationContext(), WatchAnimeEpisodeActivity.class);
//            intentToVideoWatchActivity.putExtra("animeDetailURL", animeNewReleaseResultModelList.get(position).getEpisodeURL());
//            intentToVideoWatchActivity.putExtra("animeDetailTitle", animeNewReleaseResultModelList.get(position).getAnimeEpisode());
//            intentToVideoWatchActivity.putExtra("animeDetailType", animeNewReleaseResultModelList.get(position).getAnimeEpisodeType());
//            intentToVideoWatchActivity.putExtra("animeDetailStatus", animeNewReleaseResultModelList.get(position).getAnimeEpisodeStatus());
//            intentToVideoWatchActivity.putExtra("animeDetailThumb", animeNewReleaseResultModelList.get(position).getEpisodeThumb());
            intentToVideoWatchActivity.putExtra("animeEpisodeToWatch", animeNewReleaseResultModelList.get(position).getEpisodeURL());
            intentToVideoWatchActivity.putExtra("animeEpisodeTitle", animeNewReleaseResultModelList.get(position).getAnimeEpisode());
            intentToVideoWatchActivity.putExtra("animeEpisodeThumb", animeNewReleaseResultModelList.get(position).getEpisodeThumb());
            intentToVideoWatchActivity.putExtra("animeEpisodeType", animeNewReleaseResultModelList.get(position).getAnimeEpisodeType());
            intentToVideoWatchActivity.putExtra("animeEpisodeStatus", animeNewReleaseResultModelList.get(position).getAnimeEpisodeStatus());
            context.startActivity(intentToVideoWatchActivity);
        });
    }

    @Override
    public int getItemCount() {
        return animeNewReleaseResultModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemListAnimeBinding itemListBinding;

        public ViewHolder(final ItemListAnimeBinding itemView) {
            super(itemView.getRoot());
            this.itemListBinding = itemView;
        }

    }
}
