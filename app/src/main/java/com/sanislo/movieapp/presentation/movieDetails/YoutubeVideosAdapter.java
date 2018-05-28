package com.sanislo.movieapp.presentation.movieDetails;

import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.ListAdapter;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.sanislo.movieapp.R;
import com.sanislo.movieapp.domain.model.YoutubeVideoModel;

public class YoutubeVideosAdapter extends ListAdapter<YoutubeVideoModel, YoutubeVideosAdapter.ViewHolder> {
    private ClickInteractor mClickInteractor;

    public YoutubeVideosAdapter(@NonNull DiffUtil.ItemCallback<YoutubeVideoModel> diffCallback, ClickInteractor mClickInteractor) {
        super(diffCallback);
        this.mClickInteractor = mClickInteractor;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_youtube_video, parent, false);
        return new YoutubeVideosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind();
    }

    public interface ClickInteractor {
        public void onClick(YoutubeVideoModel youtubeVideoModel);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivYoutubeThumbnail;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                if (mClickInteractor != null) mClickInteractor.onClick(getItem(getAdapterPosition()));
            });
            ivYoutubeThumbnail = itemView.findViewById(R.id.iv_youtube_thumbnail);
        }

        public void bind() {
            Glide.with(itemView.getContext())
                    .load(getItem(getAdapterPosition()).getUrl())
                    .into(ivYoutubeThumbnail);
        }
    }
}
