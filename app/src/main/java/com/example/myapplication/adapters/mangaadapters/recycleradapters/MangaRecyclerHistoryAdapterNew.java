package com.example.myapplication.adapters.mangaadapters.recycleradapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.activities.mangapage.manga_detail_mvp.MangaDetailActivity;
import com.example.myapplication.activities.mangapage.read_manga_mvp.ReadMangaActivity;
import com.example.myapplication.databinding.ItemListAnimeNewBinding;
import com.example.myapplication.databinding.ItemListMangaNewBinding;
import com.example.myapplication.databinding.ItemListSelectChapterDetailBinding;
import com.example.myapplication.localstorages.manga_local.read_history.MangaHistoryModel;
import com.example.myapplication.localstorages.manga_local.read_history.MangaHistoryModel;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import static com.example.myapplication.MyApp.cookiesz;
import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MangaRecyclerHistoryAdapterNew extends RecyclerView.Adapter<MangaRecyclerHistoryAdapterNew.ViewHolder> {
    private Context context;
    private List<MangaHistoryModel> animeDiscoverResultModelList;

    public MangaRecyclerHistoryAdapterNew(Context context, List<MangaHistoryModel> animeDiscoverResultModelList) {
        this.context = context;
        this.animeDiscoverResultModelList = animeDiscoverResultModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ViewHolder viewHolder;
        ItemListAnimeNewBinding itemListBinding = ItemListAnimeNewBinding.inflate(layoutInflater, parent, false);
        viewHolder = new ViewHolder(itemListBinding);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemListBinding.textGenre.setVisibility(View.GONE);
        holder.itemListBinding.linearRating.setVisibility(View.GONE);
        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .addInterceptor(chain -> {
                    final Request original = chain.request();
                    final Request authorized = original.newBuilder()
                            .addHeader("Cookie", String.valueOf(cookiesz))
                            .addHeader("User-Agent", "")
                            .build();
                    return chain.proceed(authorized);
                })
                .build();
        Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .build();
        picasso.load(animeDiscoverResultModelList.get(position).getChapterThumb())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .placeholder(Objects.requireNonNull(ResourcesCompat.getDrawable(context.getResources(), R.drawable.imageplaceholder, context.getTheme())))
                .error(Objects.requireNonNull(ResourcesCompat.getDrawable(context.getResources(), R.drawable.error, context.getTheme())))
                .into(holder.itemListBinding.imageViewBackground);
        if (animeDiscoverResultModelList.get(position).getChapterType().equalsIgnoreCase(context.getResources().getString(R.string.manga_string))) {
            holder.itemListBinding.seriesType.setSlantedBackgroundColor(context.getResources().getColor(R.color.manga_color));
            holder.itemListBinding.seriesType.setText(context.getResources().getString(R.string.manga_string));
        } else if (animeDiscoverResultModelList.get(position).getChapterType().equalsIgnoreCase(context.getResources().getString(R.string.manhwa_string))) {
            holder.itemListBinding.seriesType.setSlantedBackgroundColor(context.getResources().getColor(R.color.manhwa_color));
            holder.itemListBinding.seriesType.setText(context.getResources().getString(R.string.manhwa_string));
        } else if (animeDiscoverResultModelList.get(position).getChapterType().equalsIgnoreCase(context.getResources().getString(R.string.manhua_string))) {
            holder.itemListBinding.seriesType.setSlantedBackgroundColor(context.getResources().getColor(R.color.manhua_color));
            holder.itemListBinding.seriesType.setText(context.getResources().getString(R.string.manhua_string));
        } else if (animeDiscoverResultModelList.get(position).getChapterType().equalsIgnoreCase(context.getResources().getString(R.string.mangaoneshot_string))) {
            holder.itemListBinding.seriesType.setSlantedBackgroundColor(context.getResources().getColor(R.color.manga_color));
            holder.itemListBinding.seriesType.setText(context.getResources().getString(R.string.mangaoneshot_string));
        } else if (animeDiscoverResultModelList.get(position).getChapterType().equalsIgnoreCase(context.getResources().getString(R.string.oneshot_string))) {
            holder.itemListBinding.seriesType.setSlantedBackgroundColor(context.getResources().getColor(R.color.manga_color));
            holder.itemListBinding.seriesType.setText(context.getResources().getString(R.string.oneshot_string));
        }
        holder.itemListBinding.textTitle.setText(animeDiscoverResultModelList.get(position).getChapterTitle());
        holder.itemListBinding.relativeItem.setOnClickListener(v -> {
            Intent intent = new Intent(context.getApplicationContext(), ReadMangaActivity.class);
            intent.putExtra("chapterURL", animeDiscoverResultModelList.get(position).getChapterURL());
            intent.putExtra("appBarColorStatus", animeDiscoverResultModelList.get(position).getChapterType());
            intent.putExtra("chapterTitle", animeDiscoverResultModelList.get(position).getChapterTitle());
            intent.putExtra("chapterThumb", animeDiscoverResultModelList.get(position).getChapterThumb());
            intent.putExtra("readFrom", "MangaHistory");
            context.startActivity(intent);
//            ((MangaReleaseListActivity) context).finish();
        });
    }

    @Override
    public int getItemCount() {
        return animeDiscoverResultModelList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemListAnimeNewBinding itemListBinding;

        public ViewHolder(final ItemListAnimeNewBinding itemViewList) {
            super(itemViewList.getRoot());
            this.itemListBinding = itemViewList;
        }
    }
}
