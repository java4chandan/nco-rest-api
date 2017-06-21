package com.nco.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "SongList")
public class SongList {
	@Id
	private String id;
	private String songListName;
	private String description;
	
	@DBRef(lazy = true)
	private List<SongInfo> songs;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSongListName() {
		return songListName;
	}
	public List<SongInfo> getSongs() {
		return songs;
	}
	public void setSongs(List<SongInfo> songs) {
		this.songs = songs;
	}
	public void setSongListName(String songListName) {
		this.songListName = songListName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
