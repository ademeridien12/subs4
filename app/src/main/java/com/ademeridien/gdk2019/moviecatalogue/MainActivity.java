package com.ademeridien.gdk2019.moviecatalogue;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.ademeridien.gdk2019.moviecatalogue.fragment.FavoriteFragment;
import com.ademeridien.gdk2019.moviecatalogue.fragment.MovieFragment;
import com.ademeridien.gdk2019.moviecatalogue.fragment.TvShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        Fragment fragment;
        ActionBar actionBar = getSupportActionBar();
        switch (item.getItemId()) {
            case R.id.navigation_movie:
                fragment = new MovieFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                        .commit();
                if (actionBar != null) {
                    actionBar.setElevation(4);
                    actionBar.setSubtitle(R.string.btm_movie);
                }
                return true;
            case R.id.navigation_tvshow:
                fragment = new TvShowFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                        .commit();
                if (actionBar != null) {
                    actionBar.setElevation(4);
                    actionBar.setSubtitle(R.string.btm_tvshow);
                }
                return true;
            case R.id.navigation_favorite:
                fragment = new FavoriteFragment();
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container_layout, fragment, fragment.getClass().getSimpleName())
                        .commit();
                if (actionBar != null) {
                    actionBar.setElevation(0);
                    actionBar.setSubtitle(R.string.btm_favorite);
                }
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if (savedInstanceState == null) {
            navigation.setSelectedItemId(R.id.navigation_movie);
        }
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.language_settings){
            showLanguageSettingsDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void showLanguageSettingsDialog() {
        final String[] langItems = {"English", "Indonesia"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(R.string.select_language)
                .setSingleChoiceItems(langItems, -1, (dialogInterface, i) -> {
                    if(i == 0){
                        setLocale("en");
                        recreate();
                    }
                    else if(i == 1){
                        setLocale("id");
                        recreate();
                    }
                    dialogInterface.dismiss();
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("App_Lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences pref = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = pref.getString("App_Lang","");
        setLocale(language);
    }
}