package com.nco.dao;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.nco.model.SongInfo;
import com.nco.model.SongInfoGrouping;
import com.nco.model.SongList;

@Repository
public class SongInfoDAOImpl implements SongInfoDAO {

	private static final Logger LOGGER = LoggerFactory.getLogger(SongInfoDAOImpl.class);

	private static final String SONGLIST_COLLECTION = "SongList";
	private static final String SONGS_COLLECTION = "magnatune_song_info";

	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public List<SongInfoGrouping> getArtistWiseSong() {
		Aggregation agg = newAggregation(group("artist").count().as("songcount"),
				Aggregation.project("songcount").and("artist").previousOperation());
		// Convert the aggregation result into a List
		AggregationResults<SongInfoGrouping> groupResults = mongoTemplate.aggregate(agg, SongInfo.class,
				SongInfoGrouping.class);
		List<SongInfoGrouping> result = groupResults.getMappedResults();
		return result;
	}

	@Override
	public void createSongList(SongList songList) {
		this.mongoTemplate.insert(songList, SONGLIST_COLLECTION);
		LOGGER.info("create Song List entry with information: {}" + songList);
	}

	@Override
	public int deleteSongListById(String songListId) {
		Query query = new Query(Criteria.where("_id").in(songListId));
		WriteResult result = this.mongoTemplate.remove(query, SONGLIST_COLLECTION);
		return result.getN();
	}

	@Override
	public int deleteSongFromSongList(String songListId, String songId) {
	/*	Query query = new Query(Criteria.where("_id").is(songListId));
		SongInfo s1=new SongInfo();
		s1.setId("5928207b55649882af1e6128");*/
		
		Query query = new Query(Criteria.where("_id").is(songListId));
		SongInfo s1=new SongInfo();
		
		s1.setId("5928207b55649882af1e613b");
		SongInfo s2=new SongInfo();
		s2.setId("5928207b55649882af1e6139");
		
		List<SongInfo> songInfo1=new ArrayList<>();
		songInfo1.add(s1);
		songInfo1.add(s2);
		
		SongList songList=new SongList();
		songList.setSongs(songInfo1);
        List<SongInfo>	songInfoList = songList.getSongs();
        
        SongInfo[] array = songInfoList.toArray(new SongInfo[songInfoList.size()]);
        
        DBObject eventObj = new BasicDBObject();
		mongoTemplate.getConverter().write(array, eventObj);
        Update update = new Update();
	    //update.push("songs",s1);
		update.push("songs", eventObj);
		WriteResult result = mongoTemplate.updateFirst(query, update, SongList.class);
		return result.getN();
	}

	@Override
	public List<SongList> getAllSongList() {
		
		Aggregation agg = newAggregation(
				Aggregation.project() //
			        .and("songs") //
			        .size() //
			        .as("songcount")
			);
		try {
			AggregationResults<SongInfoGrouping> groupResults = mongoTemplate.aggregate(
				    agg, SONGLIST_COLLECTION, SongInfoGrouping.class
				);
			List<SongInfoGrouping> result = groupResults.getMappedResults();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Query query = new Query();
		query.fields().exclude("songs");
		return mongoTemplate.find(query, SongList.class);
		
			
		
		
	}

	@Override
	public List<SongInfo> getAllSong() {
		Query query = new Query();
		query.fields().include("_id")
		.include("isrc")
		.include("artist")
		.include("artistdesc")
		.include("trackname")
		.include("albumname")
		.include("year");
		query.limit(100);
		return mongoTemplate.find(query, SongInfo.class);
	}

	@Override
	public SongList songListReadById(String id) {
		Query query = new Query(Criteria.where("_id").is(id));
		return this.mongoTemplate.findOne(query, SongList.class, SONGLIST_COLLECTION);
	}

	@Override
	public void updateSongList(SongList songList) {
		this.mongoTemplate.save(songList, SONGLIST_COLLECTION);
	}

	

	/*@Override
	public void updateSongList(String songListId, String songId) {
		Query query = new Query(Criteria.where("id").is(songListId).andOperator(Criteria.where("songs.id").is(songId)));
		List<SongList> s = mongoTemplate.find(query, SongList.class);
		Update update = new Update();
	    update.set("songs.id", songId);
	    SongList s = mongoTemplate.findAndModify(query, update, SongList.class);
	}*/

}
