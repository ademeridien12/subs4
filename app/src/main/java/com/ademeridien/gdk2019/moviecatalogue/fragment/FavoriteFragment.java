package com.ademeridien.gdk2019.moviecatalogue.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ademeridien.gdk2019.moviecatalogue.R;
import com.ademeridien.gdk2019.moviecatalogue.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

public class FavoriteFragment extends Fragment {

    public FavoriteFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite, container, false);

        TabLayout tabLayout = v.findViewById(R.id.tablayout_id);
        ViewPager viewPager = v.findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());

        adapter.AddFragment(new FavoriteMovieFragment());
        adapter.AddFragment(new FavoriteTVShowFragment());

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_movie).setText(R.string.tab_text_1);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_live_tv).setText(R.string.tab_text_2);
        tabLayout.setElevation(4);

        return v;
    }
}
