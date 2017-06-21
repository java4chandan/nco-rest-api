package com.nco.data;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "vendor_magnatune_song_info")
public class Songlist {
	private List<Song> song;

	public Songlist() {
	}

	public Songlist(List<Song> song) {
		super();
		this.song = song;
	}

	@XmlElement
	public List<Song> getSong() {
		return song;
	}

	public void setSong(List<Song> song) {
		this.song = song;
	}

}