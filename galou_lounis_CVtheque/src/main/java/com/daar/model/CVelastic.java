package com.daar.model;

import javax.persistence.Id;

import org.springframework.data.elasticsearch.annotations.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Document(indexName = "fileindex")
@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
public class CVelastic {
	
	@Id
    private Long id;
	
	private String nom;
	
	
	private String content;

	public Long getId() {
		return id;
	}

	public CVelastic(Long id, String nom, String content) {
		super();
		this.id = id;
		this.nom = nom;
		this.content = content;
	}
	
}
