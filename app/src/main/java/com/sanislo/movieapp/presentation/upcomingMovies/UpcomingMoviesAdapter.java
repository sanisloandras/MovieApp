package com.sanislo.movieapp.presentation.upcomingMovies;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sanislo.movieapp.R;
import com.sanislo.movieapp.domain.model.MovieListItemModel;
import com.sanislo.movieapp.persistence.entity.MovieListItemEntity;

public class UpcomingMoviesAdapter extends PagedListAdapter<MovieListItemModel, UpcomingMoviesAdapter.ViewHolder> {
    private ClickInteractor mClickInteractor;

    protected UpcomingMoviesAdapter(@NonNull DiffUtil.ItemCallback<MovieListItemModel> diffCallback) {
        super(diffCallback);
    }

    public UpcomingMoviesAdapter(@NonNull DiffUtil.ItemCallback<MovieListItemModel> diffCallback, ClickInteractor mClickInteractor) {
        super(diffCallback);
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
                    .load(movieListItem != null ?
                            "https://image.tmdb.org/t/p/original/" + movieListItem.getPosterPath()
                            : "")
                    .into(ivPoster);
        }
    }
}
