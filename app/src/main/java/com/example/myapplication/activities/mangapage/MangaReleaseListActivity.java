package com.example.myapplication.activities.mangapage;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.res.ColorStateList;
import android.os.Bundle;

import com.example.myapplication.adapters.ViewPagerAdminTabAdapter;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ActivityMangaReleaseListBinding;
import com.google.android.material.tabs.TabLayout;

public class MangaReleaseListActivity extends AppCompatActivity {
    ActivityMangaReleaseListBinding mangaReleaseListBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mangaReleaseListBinding = DataBindingUtil.setContentView(this, R.layout.activity_manga_release_list);
        UISettings();
        addUIEvents();
    }

    private void addUIEvents() {
        mangaReleaseListBinding.tabHome.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mangaReleaseListBinding.viewPagerTabs.setCurrentItem(tab.getPosition());
                if (tab.getPosition() == 0) {
                    setTitle("Read Manga/Manhua/Manhwa");
                } else if (tab.getPosition() == 1) {
                    setTitle("Discover Manga");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void UISettings() {
        mangaReleaseListBinding.tabHome.addTab(mangaReleaseListBinding.tabHome.newTab().setIcon(getResources().getDrawable(R.drawable.ic_home_white_24dp)));
        mangaReleaseListBinding.tabHome.addTab(mangaReleaseListBinding.tabHome.newTab().setIcon(getResources().getDrawable(R.drawable.ic_view_list_white_24dp)));
        mangaReleaseListBinding.tabHome.setTabIconTint(ColorStateList.valueOf(getResources().getColor(android.R.color.white)));
        mangaReleaseListBinding.viewPagerTabs.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mangaReleaseListBinding.tabHome));
        mangaReleaseListBinding.viewPagerTabs.setAdapter(new ViewPagerAdminTabAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new AlertDialog.Builder(this)
                .setMessage(getResources().getString(R.string.log_out_message_text))
                .setCancelable(false)
                .setPositiveButton(getString(R.string.yes_text), (dialog, which) -> MangaReleaseListActivity.this.finish())
                .setNegativeButton(getString(R.string.no_text), (dialog, which) -> dialog.dismiss())
                .show();
    }

}
