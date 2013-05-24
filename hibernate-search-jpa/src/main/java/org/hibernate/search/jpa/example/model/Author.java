package org.hibernate.search.jpa.example.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

@Entity
@Table(catalog="hibernate_search",name="Author")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="org.hibernate.search.hibernate.example.model.Author")
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Field(index=Index.YES,analyze=Analyze.NO,store=Store.COMPRESS)
	private String name;
	
	@ManyToMany(fetch=FetchType.LAZY,mappedBy="authors"/*,cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE}*/)
	@ContainedIn
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="org.hibernate.search.hibernate.example.model.Book")
	@JsonIgnore
	private Set<Book> books;
	
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
	
	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public Author() {
	}
	
	public Author(String name) {
		super();
		this.name = name;
	}
	
}
