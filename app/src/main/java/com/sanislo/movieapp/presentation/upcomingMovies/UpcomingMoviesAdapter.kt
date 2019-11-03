package com.sanislo.movieapp.presentation.upcomingMovies

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.sanislo.movieapp.R
import com.sanislo.movieapp.domain.model.MovieListItemModel

//TODO use async diff util instead
class UpcomingMoviesAdapter(diffCallback: DiffUtil.ItemCallback<MovieListItemModel>,
                            private val movieTitleText: Int,
                            private val movieTitleBackground: Int,
                            private val mClickInteractor: ClickInteractor?) : PagedListAdapter<MovieListItemModel, UpcomingMoviesAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    interface ClickInteractor {
        fun onItemClick(movieListItemModel: MovieListItemModel?)
    }

    inner class ViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView
        private val ivPoster: ImageView

        init {
            tvTitle = itemView.findViewById(R.id.tv_movie_title)
            ivPoster = itemView.findViewById(R.id.iv_poster)
            mClickInteractor?.let { clickInteractor ->
                itemView.setOnClickListener {
                    clickInteractor.onItemClick(getItem(adapterPosition))
                }
            }
        }

        fun bind() {
            tvTitle.setTextColor(movieTitleText)
            tvTitle.setBackgroundColor(movieTitleBackground)
            val movieListItem = getItem(adapterPosition)
            if (movieListItem == null) {
                tvTitle.text = itemView.context.getString(R.string.loading)
            } else {
                tvTitle.text = movieListItem.title
            }
            val requestOptions = RequestOptions()
                    .placeholder(R.drawable.movie_poster_placeholder)
            Glide.with(itemView.context)
                    .applyDefaultRequestOptions(requestOptions)
                    .asBitmap()
                    .load(if (movieListItem != null)
                        "https://image.tmdb.org/t/p/original/" + movieListItem.posterPath
                    else
                        "")
                    .listener(object : RequestListener<Bitmap> {
                        override fun onLoadFailed(e: GlideException?, model: Any, target: Target<Bitmap>, isFirstResource: Boolean): Boolean {
                            return false
                        }

                        override fun onResourceReady(resource: Bitmap, model: Any, target: Target<Bitmap>, dataSource: DataSource, isFirstResource: Boolean): Boolean {
                            //TODO use async palette
                            val palette = androidx.palette.graphics.Palette.from(resource).generate()
                            if (palette.dominantSwatch != null) {
                                val swatch = palette.dominantSwatch
                                tvTitle.setTextColor(swatch!!.bodyTextColor)
                                tvTitle.setBackgroundColor(swatch.rgb)
                            }
                            return false
                        }
                    })
                    .into(ivPoster)
        }
    }
}
