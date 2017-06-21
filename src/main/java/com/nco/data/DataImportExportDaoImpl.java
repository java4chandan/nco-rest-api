package com.nco.data;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.nco.dao.SongInfoDAOImpl;
@Repository
public class DataImportExportDaoImpl implements DataImportExportDao{
	private static final Logger LOGGER = LoggerFactory.getLogger(SongInfoDAOImpl.class);
	
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Override
	public void insertXmlToJson(List<Song> songList){
		    mongoTemplate.insert(songList, Song.class);
		    LOGGER.info("create Song List entry with information: {}" + songList);
	}
	

	public void ReadVendorMagnatuneSongInfo(List<Song> songList){
		    mongoTemplate.insert(songList, Song.class);
		    LOGGER.info("create Song List entry with information: {}" + songList);
	}
	
}
