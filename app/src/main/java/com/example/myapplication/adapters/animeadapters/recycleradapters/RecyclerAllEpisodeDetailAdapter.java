package com.example.myapplication.adapters.animeadapters.recycleradapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.activities.animepage.watch_anime_mvp.WatchAnimeEpisodeActivity;
import com.example.myapplication.databinding.ItemListSelectChapterDetailBinding;
import com.example.myapplication.models.animemodels.AnimeDetailModel;
import com.example.myapplication.models.mangamodels.DetailMangaModel;

import java.util.List;

public class RecyclerAllEpisodeDetailAdapter extends RecyclerView.Adapter<RecyclerAllEpisodeDetailAdapter.ViewHolder> {
    private Context context;
    private List<DetailMangaModel.DetailAllChapterDatas> allChapterDatasArrayList;
    private AnimeDetailModel animeDetailModel;

    public RecyclerAllEpisodeDetailAdapter(Context context, List<DetailMangaModel.DetailAllChapterDatas> allChapterDatasArrayList, AnimeDetailModel animeDetailModel) {
        this.context = context;
        this.allChapterDatasArrayList = allChapterDatasArrayList;
        this.animeDetailModel = animeDetailModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ItemListSelectChapterDetailBinding itemListBinding = ItemListSelectChapterDetailBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(itemListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemListBinding.textViewChapterAllTitle.setText(allChapterDatasArrayList.get(position).getChapterTitle());
        holder.itemListBinding.textViewChapterAllReleaseTime.setVisibility(View.GONE);
        holder.itemListBinding.linearChapterDetail.setOnClickListener(v -> {
            Intent intent = new Intent(context.getApplicationContext(), WatchAnimeEpisodeActivity.class);
            intent.putExtra("animeEpisodeToWatch", allChapterDatasArrayList.get(position).getChapterURL());
            intent.putExtra("animeEpisodeTitle", animeDetailModel.getEpisodeTitle());
            intent.putExtra("animeEpisodeThumb", animeDetailModel.getEpisodeThumb());
            intent.putExtra("animeEpisodeType", animeDetailModel.getEpisodeType());
            intent.putExtra("animeEpisodeStatus", animeDetailModel.getEpisodeStatus());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return allChapterDatasArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemListSelectChapterDetailBinding itemListBinding;

        public ViewHolder(final ItemListSelectChapterDetailBinding itemView) {
            super(itemView.getRoot());
            this.itemListBinding = itemView;
        }
    }
}
