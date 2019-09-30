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
import com.ademeridien.gdk2019.moviecatalogue.adapter.FavoriteMoviesAdapter;
import com.ademeridien.gdk2019.moviecatalogue.db.Helper;
import com.ademeridien.gdk2019.moviecatalogue.model.FavoriteMovie;

import java.util.ArrayList;

public class FavoriteMovieFragment extends Fragment {
    private FavoriteMoviesAdapter moviesAdapter;
    private Helper helper;
    private ProgressBar pb;

    public FavoriteMovieFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.list_fragment, container, false);

        helper = new Helper(getActivity());
        moviesAdapter = new FavoriteMoviesAdapter(getActivity());

        pb = v.findViewById(R.id.progressBar);
        pb.setVisibility(View.VISIBLE);

        RecyclerView rvMovie = v.findViewById(R.id.list_recyclerview);
        rvMovie.setLayoutManager(new LinearLayoutManager(getContext()));
        rvMovie.setAdapter(moviesAdapter);
        rvMovie.setHasFixedSize(true);

        inputData();

        return v;
    }

    private void inputData() {
        helper.open();
        ArrayList<FavoriteMovie> movies = helper.getAllFavoriteMovie();
        helper.close();
        moviesAdapter.setListMovie(movies);
        pb.setVisibility(View.GONE);
    }

    @Override
    public void onResume() {
        super.onResume();
        inputData();
    }
}
