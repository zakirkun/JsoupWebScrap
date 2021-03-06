package com.example.myapplication.adapters.mangaadapters.recycleradapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemListMangaContentBinding;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.List;
import java.util.Objects;

import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;

import static com.example.myapplication.MyApp.cookiesz;

public class RecyclerReadMangaAdapter extends RecyclerView.Adapter<RecyclerReadMangaAdapter.ViewHolder> {
    private Context context;
    private List<String> imageContent;
    private ClickItemListener clickListener;

    public RecyclerReadMangaAdapter(Context context, List<String> imageContent) {
        this.context = context;
        this.imageContent = imageContent;
        if (context instanceof RecyclerAllChapterAdapter.ClickListener) {
            clickListener = (ClickItemListener) context;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        ItemListMangaContentBinding itemListBinding = ItemListMangaContentBinding.inflate(layoutInflater);
        return new ViewHolder(itemListBinding);
    }

    @SneakyThrows
    @SuppressLint({"SetTextI18n", "SetJavaScriptEnabled", "ClickableViewAccessibility"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
        Transformation transformation = new Transformation() {
            @Override
            public Bitmap transform(Bitmap source) {
                int targetWidth = holder.itemListBinding.imageMangaContentItem.getWidth();
                double aspectRatio = (double) source.getHeight() / (double) source.getWidth();
                int targetHeight = (int) (targetWidth * aspectRatio);
                Bitmap result = Bitmap.createScaledBitmap(source, targetWidth, targetHeight, false);
                if (result != source) {
                    // Same bitmap is returned if sizes are the same
                    source.recycle();
                }
                return result;
            }

            @Override
            public String key() {
                return "transformation" + " desiredWidth";
            }
        };
        Picasso picasso = new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(client))
                .build();
        picasso.load(imageContent.get(position))
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .transform(transformation)
                .placeholder(Objects.requireNonNull(ResourcesCompat.getDrawable(context.getResources(), R.drawable.imageplaceholder, context.getTheme())))
                .error(Objects.requireNonNull(ResourcesCompat.getDrawable(context.getResources(), R.drawable.error, context.getTheme())))
                .into(holder.itemListBinding.imageMangaContentItem);
        holder.itemListBinding.imageMangaContentItem.setOnClickListener(v -> clickListener.onItemClickMangaContent());
    }

    @Override
    public int getItemCount() {
        return imageContent.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemListMangaContentBinding itemListBinding;

        public ViewHolder(final ItemListMangaContentBinding itemView) {
            super(itemView.getRoot());
            this.itemListBinding = itemView;
        }
    }

    public interface ClickItemListener {
        void onItemClickMangaContent();
    }
}
