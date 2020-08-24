package com.webcrawl.model;

import javax.persistence.*;

@Entity
@Table(name = "website")
public class Article {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String website;
	@Column
	private String url;
	@Column
	private String title;
	@Column
	private String author;
	@Column
	private String dateposted;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDateposted() {
		return dateposted;
	}

	public void setDateposted(String dateposted) {
		this.dateposted = dateposted;
	}

}
