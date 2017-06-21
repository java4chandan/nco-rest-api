package com.nco.dao;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.nco.model.SongInfo;

@Repository
public class MongoDAO {

	@Autowired
	MongoTemplate mongoTemplate;

	public <T> List<T> findWithFilters(Map<String, List<String>> filters, Class<T> className) {
		getResults(filters, "");
		return mongoTemplate.find(buildQuery(filters), className);
	}
	
	/*public String getResults(Map<String, List<String>> filters, String... fields) {
		TypedAggregation<?> aggregation = new TypedAggregation<>(SongInfo.class, Aggregation.match(buildFilter()),
				Aggregation.group("artist"));
		AggregationResults<SongInfo> groupResults = mongoTemplate.aggregate(aggregation, SongInfo.class);
		Object result = groupResults.getRawResults().get("result");
		return result.toString();
	} chandan 01-06-2017*/
	
	public String getResults(Map<String, List<String>> filters, String... fields) {
		TypedAggregation<?> aggregation = new TypedAggregation<>(SongInfo.class, Aggregation.match(buildFilter()),
				getGroupOperation());
		AggregationResults<SongInfo> groupResults = mongoTemplate.aggregate(aggregation, SongInfo.class);
		Object result = groupResults.getRawResults().get("result");
		String queryResult=result.toString();
		return queryResult;
	}
	
	private GroupOperation getGroupOperation() {
		return group("artist").count().as("songcount");
	}
	
	private Query buildQuery(Map<String, List<String>> filterMap) {
        Query query = new Query();
        for (Entry<String, List<String>> filter : filterMap.entrySet()) {
        	if (filter.getKey().equalsIgnoreCase("artist")) {
                query.addCriteria(Criteria.where("artist").in(filter.getValue()));
               continue;
         }
               query.addCriteria(Criteria.where(filter.getKey()).is(filter.getValue()));
        }
        return query;
    }
	
	private Criteria buildFilter(){
		Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("artist").is("American Baroque"));
        return criteria;
	}
	
}
