package com.nco.service;

import java.util.List;

import org.springframework.data.mongodb.core.mapreduce.GroupByResults;

import com.nco.model.SongInfo;
import com.nco.model.SongInfoGrouping;
import com.nco.model.SongList;

public interface SongInfoService {
	public List<SongInfoGrouping> getArtistWiseSong();
	public void createSongList(SongList songList);
	public int deleteSongListById(String songListId);
	public List<SongList> getAllSongList();
	public int deleteSongFromSongList(String songListId, String songId);
	public SongList songListReadById(String id);
	public List<SongInfo> getAllSong();
	public void updateSongList(SongList songList);
}
