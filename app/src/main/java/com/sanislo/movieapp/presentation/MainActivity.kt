package com.sanislo.movieapp.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.sanislo.movieapp.R
import com.sanislo.movieapp.presentation.upcomingMovies.UpcomingMoviesFragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector, HasDualPaneSupport {

    @Inject
    lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState != null) return
        supportFragmentManager.beginTransaction()
                .add(R.id.fl_movie_list_container, UpcomingMoviesFragment.newInstance())
                .commit()
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment>? {
        return fragmentDispatchingAndroidInjector
    }

    override fun isInDualPaneMode(): Boolean {
        return findViewById<View>(R.id.fl_movie_details_container) != null
    }

    override fun leftContainerId(): Int {
        return R.id.fl_movie_list_container
    }

    override fun rightContainer(): Int {
        return R.id.fl_movie_details_container
    }
}
