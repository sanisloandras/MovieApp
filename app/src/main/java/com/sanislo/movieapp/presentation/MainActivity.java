package com.sanislo.movieapp.presentation;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.sanislo.movieapp.R;
import com.sanislo.movieapp.presentation.upcomingMovies.UpcomingMoviesFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity
        implements HasSupportFragmentInjector, HasDualPaneSupport {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) return;
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fl_movie_list_container, UpcomingMoviesFragment.newInstance())
                .commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public boolean isInDualPaneMode() {
        return findViewById(R.id.fl_movie_details_container) != null;
    }

    @Override
    public int getLeftContainerId() {
        return R.id.fl_movie_list_container;
    }

    @Override
    public int getRightContainer() {
        return R.id.fl_movie_details_container;
    }
}
