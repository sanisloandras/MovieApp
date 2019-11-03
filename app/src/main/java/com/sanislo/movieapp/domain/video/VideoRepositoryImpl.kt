package com.sanislo.movieapp.domain.video

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sanislo.movieapp.domain.mapper.MovieVideosReponseToVideoEntity
import com.sanislo.movieapp.domain.mapper.VideoEntityToVideoModelMapper
import com.sanislo.movieapp.domain.model.VideoModel
import com.sanislo.movieapp.domain.model.YoutubeVideoModel
import com.sanislo.movieapp.persistence.api.MovieAppApi
import com.sanislo.movieapp.persistence.dao.VideoDao
import com.sanislo.movieapp.persistence.entity.VideoEntity
import com.sanislo.movieapp.persistence.response.movieVideos.MovieVideosResponse
import io.reactivex.Single
import java.util.*

class VideoRepositoryImpl(private val movieAppApi: MovieAppApi, private val videoDao: VideoDao) : VideoRepository {

    private val movieVideosResponseToVideoEntity = MovieVideosReponseToVideoEntity()
    private val videoEntityToVideoModelMapper = VideoEntityToVideoModelMapper()

    override fun videos(movieId: Int): Single<MovieVideosResponse> {
        return movieAppApi.movieVideos(movieId)
                .doOnSuccess { result -> videoDao.insert(movieVideosResponseToVideoEntity.map(result)) }
    }

    override fun videosLiveData(movieId: Int): LiveData<List<VideoModel>> {
        return Transformations.map(videoDao.getVideosForMovie(movieId)) { input -> videoEntityToVideoModelMapper.map2(input) }
    }

    override fun youtubeVideosLiveData(movieId: Int): LiveData<List<YoutubeVideoModel>> {
        return Transformations.map(videoDao.getVideosForMovieBySiteLiveData(movieId, SITE_YOUTUBE)) { input ->
            val youtubeVideoModels = ArrayList<YoutubeVideoModel>()
            for (videoEntity in input) {
                youtubeVideoModels.add(mapVideoEntityToYoutubeVideoModel(videoEntity))
            }
            youtubeVideoModels
        }
    }

    private fun mapVideoEntityToYoutubeVideoModel(videoEntity: VideoEntity): YoutubeVideoModel {
        return YoutubeVideoModel(
                videoEntity.site,
                videoEntity.size,
                videoEntity.iso31661,
                videoEntity.name,
                videoEntity.id,
                videoEntity.movieId,
                videoEntity.type,
                videoEntity.iso6391,
                videoEntity.key,
                String.format(youtubeThumbnailFormat, videoEntity.key)
        )
    }

    companion object {
        val TAG = VideoRepository::class.java.simpleName

        private const val SITE_YOUTUBE = "YouTube"
        private const val youtubeThumbnailFormat = "https://img.youtube.com/vi/%s/0.jpg"
    }
}
