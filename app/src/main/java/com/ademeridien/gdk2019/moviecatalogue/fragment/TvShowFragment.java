package com.ademeridien.gdk2019.moviecatalogue.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ademeridien.gdk2019.moviecatalogue.R;
import com.ademeridien.gdk2019.moviecatalogue.adapter.TVShowsAdapter;
import com.ademeridien.gdk2019.moviecatalogue.model.TVShow;
import com.ademeridien.gdk2019.moviecatalogue.viewmodels.TvShowViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TvShowFragment extends Fragment {
    private ArrayList<TVShow> tvShowArrayList = new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;
    private RecyclerView rvTv;
    private ProgressBar pb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);
        rvTv = v.findViewById(R.id.list_recyclerview);

        pb = v.findViewById(R.id.progressBar);

        TvShowViewModel tvShowViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(TvShowViewModel.class);
        tvShowViewModel.init();
        tvShowViewModel.getTVRepository().observe(this, tvResponse -> {
            List<TVShow> tvShows = tvResponse.getResults();
            tvShowArrayList.addAll(tvShows);
            tvShowsAdapter.notifyDataSetChanged();
            pb.setVisibility(View.GONE);
        });
        setupRecyclerView();

        super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }

    private void setupRecyclerView() {
        if (tvShowsAdapter == null) {
            pb.setVisibility(View.VISIBLE);
            tvShowsAdapter = new TVShowsAdapter(getContext(), tvShowArrayList);
            rvTv.setLayoutManager(new LinearLayoutManager(getContext()));
            rvTv.setAdapter(tvShowsAdapter);
            rvTv.setItemAnimator(new DefaultItemAnimator());
            rvTv.setNestedScrollingEnabled(true);
        } else {
            tvShowsAdapter.notifyDataSetChanged();
        }
    }
}
