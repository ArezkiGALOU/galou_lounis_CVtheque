package com.daar.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.daar.model.CVelastic;

public interface CVelasticRepository extends ElasticsearchRepository<CVelastic, Long> {

}
