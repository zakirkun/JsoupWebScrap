package com.example.myapplication.adapters.animeadapters.viewpageradapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.myapplication.fragments.anime_fragments.anime_bookmark_page.AnimeBookmarkFragment;
import com.example.myapplication.fragments.anime_fragments.anime_history.AnimeHistoryFragment;
import com.example.myapplication.fragments.anime_fragments.anime_new_releases_mvp.AnimeNewReleaseFragment;
import com.example.myapplication.fragments.anime_fragments.genre_and_search_mvp.GenreAndSearchAnimeFragment;

/*
 * Created by Rosinante24 on 2019-05-30.
 */
public class ViewPagerAnimeMenuTabAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAnimeMenuTabAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new AnimeNewReleaseFragment();
        } else if (position == 1) {
            return new GenreAndSearchAnimeFragment();
        } else if (position == 2) {
            return new AnimeBookmarkFragment();
        } else if (position == 3) {
            return new AnimeHistoryFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
