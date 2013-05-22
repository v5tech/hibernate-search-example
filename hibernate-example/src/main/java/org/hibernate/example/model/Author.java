package org.hibernate.example.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Entity
@Table(catalog="hibernate_search",name="Author")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="org.hibernate.search.hibernate.example.model.Author")
public class Author {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	@ManyToMany(fetch=FetchType.EAGER,mappedBy="authors",cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE})
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="org.hibernate.search.hibernate.example.model.Book")
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
