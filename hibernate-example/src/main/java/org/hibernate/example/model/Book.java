package org.hibernate.example.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(catalog="hibernate_search",name="Book")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="org.hibernate.search.hibernate.example.model.Book")  
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	private String name;
	
	private String description;
	
	private Date publicationDate;

	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE},fetch=FetchType.EAGER)
	@JoinTable(
			catalog="hibernate_search",
			name="Book_Author",
			joinColumns={@JoinColumn(name = "book_id")},
			inverseJoinColumns = {@JoinColumn(name = "author_id")}
	)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="org.hibernate.search.hibernate.example.model.Author")
	private Set<Author> authors = new HashSet<Author>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Book() {
	}

}
