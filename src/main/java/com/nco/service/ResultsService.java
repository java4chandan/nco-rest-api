package com.nco.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.nco.dao.MongoDAO;
import com.nco.model.SongInfo;

@Service
public class ResultsService {

	@Autowired
	MongoDAO dao;
	
	public <T> List<SongInfo> getResults(Map<String, List<String>> filters) {
		return dao.findWithFilters(filters, SongInfo.class);
	}
	
	public Map<String, String> validateAndcreateFilterMap(String actor, String verb, String activityId, String activityType,
			String since, String until, String limit) {
		Map<String, String> filterMap = new HashMap<String, String>(3);

		// actor
		if (!StringUtils.isEmpty(actor)) {
			filterMap.put("", verb);
		}
        //verb
		if (!StringUtils.isEmpty(verb)) {
			filterMap.put("", verb);
		}
		
		// activity
		if (!StringUtils.isEmpty(activityId)) {
			filterMap.put("", activityId);
		}

		if (!StringUtils.isEmpty(activityType)) {
			filterMap.put("", activityType);
		}
		
		if (!StringUtils.isEmpty(since)) {
			filterMap.put("", since);
		}

		if (!StringUtils.isEmpty(until)) {
			filterMap.put("", until);
		}

		return filterMap;
	}
	
}
