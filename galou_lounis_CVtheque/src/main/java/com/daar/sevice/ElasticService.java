package com.daar.sevice;

import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.daar.model.CVelastic;
import com.google.gson.Gson;

@Service
public class ElasticService {

	@Autowired
	private Gson gson;
	
	@Autowired
    private RestHighLevelClient client;
	
	@GetMapping
	public  List<CVelastic> search(String mots) throws IOException {
		
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder().size(5000)
				.query(QueryBuilders.wildcardQuery("content", ("*"+mots+"*")));
	    SearchRequest searchRequest = new SearchRequest("fileindex");
        searchRequest.source(searchSourceBuilder);
        System.out.println(mots);
        System.out.println(client);
        SearchResponse searchResponse = client.search(searchRequest, RequestOptions.DEFAULT);
        return Stream.of(searchResponse.getHits().getHits())
                .map(hit -> hit.getSourceAsString())
                .map(cv -> gson.fromJson(cv, CVelastic.class))	
                .collect(toList());
    }
}
