package com.sanislo.movieapp.persistence.response.movieVideos;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class MovieVideosResponse {

	@SerializedName("id")
	private int id;

	@SerializedName("results")
	private List<VideoItem> results;

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setResults(List<VideoItem> results){
		this.results = results;
	}

	public List<VideoItem> getResults(){
		return results;
	}

	@Override
 	public String toString(){
		return 
			"MovieVideosResponse{" +
			"id = '" + id + '\'' + 
			",results = '" + results + '\'' + 
			"}";
		}
}