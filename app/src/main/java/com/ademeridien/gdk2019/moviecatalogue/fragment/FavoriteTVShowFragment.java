package com.ademeridien.gdk2019.moviecatalogue.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ademeridien.gdk2019.moviecatalogue.R;
import com.ademeridien.gdk2019.moviecatalogue.adapter.FavoriteTVShowsAdapter;
import com.ademeridien.gdk2019.moviecatalogue.db.Helper;
import com.ademeridien.gdk2019.moviecatalogue.model.FavoriteTVShow;

import java.util.ArrayList;

public class FavoriteTVShowFragment extends Fragment {
    private FavoriteTVShowsAdapter tvShowsAdapter;
    private Helper helper;
    private ProgressBar pb;

    public FavoriteTVShowFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);

        helper = new Helper(getActivity());
        tvShowsAdapter = new FavoriteTVShowsAdapter(getActivity());

        pb = v.findViewById(R.id.progressBar);
        pb.setVisibility(View.VISIBLE);

        RecyclerView rvTV = v.findViewById(R.id.list_recyclerview);
        rvTV.setLayoutManager(new LinearLayoutManager(getContext()));
        rvTV.setAdapter(tvShowsAdapter);
        rvTV.setHasFixedSize(true);

        inputData();

        return v;
    }

    private void inputData() {
        helper.open();
        ArrayList<FavoriteTVShow> tvShows = helper.getAllFavoriteTVShow();
        helper.close();
        tvShowsAdapter.setListTVShows(tvShows);
        pb.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        inputData();
    }
}
