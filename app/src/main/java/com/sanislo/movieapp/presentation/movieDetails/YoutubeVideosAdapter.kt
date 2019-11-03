package com.sanislo.movieapp.presentation.movieDetails

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.sanislo.movieapp.R
import com.sanislo.movieapp.domain.model.YoutubeVideoModel

class YoutubeVideosAdapter(diffCallback: DiffUtil.ItemCallback<YoutubeVideoModel>, private val mClickInteractor: ClickInteractor?) : ListAdapter<YoutubeVideoModel, YoutubeVideosAdapter.ViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_youtube_video, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind()
    }

    interface ClickInteractor {
        fun onClick(youtubeVideoModel: YoutubeVideoModel)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val ivYoutubeThumbnail: ImageView

        init {
            itemView.setOnClickListener { mClickInteractor?.onClick(getItem(adapterPosition)) }
            ivYoutubeThumbnail = itemView.findViewById(R.id.iv_youtube_thumbnail)
        }

        fun bind() {
            Glide.with(itemView.context)
                    .load(getItem(adapterPosition).url)
                    .into(ivYoutubeThumbnail)
        }
    }
}
