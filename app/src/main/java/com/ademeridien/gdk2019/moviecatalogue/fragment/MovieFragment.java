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
import com.ademeridien.gdk2019.moviecatalogue.adapter.MoviesAdapter;
import com.ademeridien.gdk2019.moviecatalogue.model.Movie;
import com.ademeridien.gdk2019.moviecatalogue.viewmodels.MovieViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MovieFragment extends Fragment {
    private ArrayList<Movie> movieArrayList = new ArrayList<>();
    private MoviesAdapter moviesAdapter;
    private RecyclerView rvMovie;
    private ProgressBar pb;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);
        rvMovie = v.findViewById(R.id.list_recyclerview);

        pb = v.findViewById(R.id.progressBar);

        MovieViewModel movieViewModel = ViewModelProviders.of(Objects.requireNonNull(getActivity())).get(MovieViewModel.class);
        movieViewModel.init();
        movieViewModel.getMovieRepository().observe(this, movieResponse -> {
            List<Movie> movies = movieResponse.getResults();
            movieArrayList.addAll(movies);
            moviesAdapter.notifyDataSetChanged();
            pb.setVisibility(View.GONE);
        });
        setupRecyclerView();

        super.onCreateView(inflater, container, savedInstanceState);
        return v;
    }

    private void setupRecyclerView() {
        if (moviesAdapter == null) {
            pb.setVisibility(View.VISIBLE);
            moviesAdapter = new MoviesAdapter(getContext(), movieArrayList);
            rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
            rvMovie.setAdapter(moviesAdapter);
            rvMovie.setItemAnimator(new DefaultItemAnimator());
            rvMovie.setNestedScrollingEnabled(true);
        } else {
            moviesAdapter.notifyDataSetChanged();
        }
    }
}