package com.nco.model;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class SongInfoGrouping {
	// id will be used for storing MongoDB _id

	private Integer songcount;
	private String artist;
	
	public Integer getSongcount() {
		return songcount;
	}
	public void setSongcount(Integer songcount) {
		this.songcount = songcount;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
}
