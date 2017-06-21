package com.nco.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapreduce.GroupByResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.mongodb.WriteResult;
import com.nco.dao.SongInfoDAO;
import com.nco.model.SongInfo;
import com.nco.model.SongInfoGrouping;
import com.nco.model.SongList;

@Service
public class SongInfoServiceImpl implements SongInfoService {
	
	@Autowired
	SongInfoDAO songInfoDao;
	
	@Override
	public List<SongInfoGrouping> getArtistWiseSong(){
		return songInfoDao.getArtistWiseSong();
	}
	

	@Override
	public void createSongList(SongList songList) {
		songInfoDao.createSongList(songList);
	}
	
	@Override
	public int deleteSongListById(String songListId) {
		return songInfoDao.deleteSongListById(songListId);
	}
	
	@Override
	public List<SongList> getAllSongList() {
		return songInfoDao.getAllSongList();
	}
	@Override
	public int deleteSongFromSongList(String songListId,String songId) {
		//songInfoDao.updateSongList(songListId, songId);
		return songInfoDao.deleteSongFromSongList(songListId, songId);
	}
	
	@Override
	public SongList songListReadById(String id) {
		return songInfoDao.songListReadById(id);
	}

	@Override
	public List<SongInfo> getAllSong() {
		return songInfoDao.getAllSong();
	}


	@Override
	public void updateSongList(SongList songList) {
		songInfoDao.updateSongList(songList);
		
	}
}
