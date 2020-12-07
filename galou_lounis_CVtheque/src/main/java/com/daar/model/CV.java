package com.daar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.websocket.Decoder.Binary;

@Entity
@Table(name = "cvs")
public class CV {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "nomfichier")
	private String nom;
	
	@Lob
	@Column(name = "contentBin")
	private byte[] contentBin;

	public CV(String nom, byte[] contentBin) {
		super();
		this.nom = nom;
		this.contentBin = contentBin;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public byte[] getContentBin() {
		return contentBin;
	}

	public void setContentBin(byte[] contentBin) {
		this.contentBin = contentBin;
	}
	@Override
	public boolean equals(Object obj) {
		CVelastic b = (CVelastic) obj;
		return this.id==b.getId();
	}
}
