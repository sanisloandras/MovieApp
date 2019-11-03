package com.sanislo.movieapp.presentation.upcomingMovies;

import android.arch.paging.PagedListAdapter;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.graphics.Palette;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.sanislo.movieapp.R;
import com.sanislo.movieapp.domain.model.MovieListItemModel;
import com.sanislo.movieapp.persistence.entity.MovieListItemEntity;

import java.util.List;

public class UpcomingMoviesAdapter extends PagedListAdapter<MovieListItemModel, UpcomingMoviesAdapter.ViewHolder> {
    private ClickInteractor mClickInteractor;
    private final int movieTitleText;
    private final int movieTitleBackground;

    public UpcomingMoviesAdapter(@NonNull DiffUtil.ItemCallback<MovieListItemModel> diffCallback,
                                 int movieTitleText,
                                 int movieTitleBackground,
                                 ClickInteractor mClickInteractor) {
        super(diffCallback);
        this.movieTitleText = movieTitleText;
        this.movieTitleBackground = movieTitleBackground;
        this.mClickInteractor = mClickInteractor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind();
    }

    public interface ClickInteractor {
        void onItemClick(MovieListItemModel movieListItemModel);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        ImageView ivPoster;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_movie_title);
            ivPoster = itemView.findViewById(R.id.iv_poster);
            itemView.setOnClickListener(v -> {
                if (mClickInteractor != null) {
                    mClickInteractor.onItemClick(getItem(getAdapterPosition()));
                }
            });
        }

        public void bind() {
            tvTitle.setTextColor(movieTitleText);
            tvTitle.setBackgroundColor(movieTitleBackground);
            MovieListItemModel movieListItem = getItem(getAdapterPosition());
            if (movieListItem == null) {
                tvTitle.setText("LOADING LOADING LOADING LOADING LOADING LOADING");
            } else {
                tvTitle.setText(movieListItem.getTitle());
            }
            //"https://image.tmdb.org/t/p/original/wwemzKWzjKYJFfCeiB57q3r4Bcm.png"
            RequestOptions requestOptions = new RequestOptions()
                    .placeholder(R.drawable.movie_poster_placeholder);

            Glide.with(itemView.getContext())
                    .applyDefaultRequestOptions(requestOptions)
                    .asBitmap()
                    .load(movieListItem != null ?
                            "https://image.tmdb.org/t/p/original/" + movieListItem.getPosterPath()
                            : "")
                    .listener(new RequestListener<Bitmap>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                            Palette palette = Palette.from(resource).generate();
                            if (palette.getDominantSwatch() != null) {
                                Palette.Swatch swatch = palette.getDominantSwatch();
                                tvTitle.setTextColor(swatch.getBodyTextColor());
                                tvTitle.setBackgroundColor(swatch.getRgb());
                            }
                            return false;
                        }
                    })
                    .into(ivPoster);
        }
    }
}
