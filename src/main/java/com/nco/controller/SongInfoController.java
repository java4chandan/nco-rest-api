package com.nco.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nco.model.SongInfo;
import com.nco.model.SongInfoGrouping;
import com.nco.model.SongList;
import com.nco.service.ResultsService;
import com.nco.service.SongInfoService;
import com.nco.service.UserService;
import com.nco.util.UsersAuditUtil;

@RestController
@CrossOrigin
public class SongInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(SongInfoController.class);

	@Autowired
	SongInfoService songInfoService;

	@Autowired
	ResultsService service;

	@Autowired
	UserService userService;
	
	@RequestMapping(value = { "/getArtistWiseSong" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SongInfoGrouping> getArtistWiseSong() {
		return songInfoService.getArtistWiseSong();
	}

	@RequestMapping(value = "/getResults", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
	public List<SongInfo> getActivityState() {
		List<String> artistList=new ArrayList<String>();
		artistList.add("American Baroque");
		artistList.add("Antonio Ciacca Quartet");
		artistList.add("Adriano Fontana");
		Map<String, List<String>> queryMap=new HashMap<String,List<String>>();
		queryMap.put("artist",artistList);
		return service.getResults(queryMap);
		/*new ResponseEntity<String>("Success", HttpStatus.OK);*/
	}
	
	@RequestMapping(value = {"/songs" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SongInfo> getAllSong() {
		return songInfoService.getAllSong();
	}
	
	@RequestMapping(value = { "/songlist" }, method = RequestMethod.POST, produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	void createSongList(@RequestBody SongList songList) {
		LOGGER.info("Creating a new todo entry with information: {}", songList);
		songInfoService.createSongList(songList);
		userService.createActivityLog(UsersAuditUtil.getUsersAudit("Songlist Created"));
	}
	
	@RequestMapping(value = {"/songlist" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<SongList> getAllSongList() {
		return songInfoService.getAllSongList();
	}
	
	@RequestMapping(value = {"/songlist/{id}" }, method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody SongList songListReadById(@PathVariable String id) {
		return songInfoService.songListReadById(id);
	}
	
	@RequestMapping(value = "/songlist/{id}", method = RequestMethod.DELETE)
	public @ResponseBody Map<String,Object> deleteSongListById(@PathVariable("id") String songListId) {
		LOGGER.info("Deleting User entry with information: {}", songListId);
		int delete = songInfoService.deleteSongListById(songListId);
		Map<String,Object> result=new HashMap<String, Object>();
		if(delete==1){
			result.put("message", "delete successfully");
			result.put("songlist",songInfoService.getAllSongList());
			userService.createActivityLog(UsersAuditUtil.getUsersAudit("Songlist Deleted"));
		}else{
			result.put("message", "delete failed");
			result.put("songlist","");
		}
		return result;
	}

	/*@RequestMapping(value = "/songlist/song", method = RequestMethod.DELETE)
	public String deleteSongFromSongList(@RequestParam("id") String songListId, @RequestParam("songId") String songId) {
		LOGGER.info("Deleting User entry with information: {}", songListId +" " + songId);
 		int delete = songInfoService.deleteSongFromSongList(songListId,songId);
 		return delete == 1 ? "{ \"response\": \"delete successfully\"} " : "{ \"response\": \"delete failed\"} ";
	}*/
	
	@RequestMapping(value = "/songlist/song", method = RequestMethod.PUT)
	public  @ResponseBody void updateSongList(@RequestBody SongList songList) {
		LOGGER.info("Update User entry with information: {}" + songList);
 		songInfoService.updateSongList(songList);
 		userService.createActivityLog(UsersAuditUtil.getUsersAudit("Songlist Updated"));
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public String handleUserNotFound(Exception ex) {
		LOGGER.error("Handling error with message: {}", ex.getMessage());
		return "{ \"response\": \"System Error Occurred\"}";
	}

}
