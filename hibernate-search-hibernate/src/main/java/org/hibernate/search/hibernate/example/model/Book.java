package org.hibernate.search.hibernate.example.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.CacheFromIndex;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

import static org.hibernate.search.annotations.FieldCacheType.CLASS;
import static org.hibernate.search.annotations.FieldCacheType.ID;

@Entity
@Table(catalog="hibernate_search",name="Book")
@Indexed(index="book")
//@Analyzer(impl=IKAnalyzer.class)
@Analyzer(impl=PaodingAnalyzer.class)
@CacheFromIndex( {CLASS,ID} )
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@DocumentId
	private Integer id;

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.COMPRESS)
	private String name;
	
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.COMPRESS)
	private String description;
	
	@Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
	@DateBridge(resolution = Resolution.DAY)
	private Date publicationDate;

	@IndexedEmbedded
	@ManyToMany(cascade=CascadeType.ALL)
	@JoinTable(
			catalog="hibernate_search",
			name="Book_Author",
			joinColumns={@JoinColumn(name = "book_id",nullable=false,referencedColumnName="id",table="Book")},
			inverseJoinColumns = {@JoinColumn(name = "author_id",nullable=false,referencedColumnName="id",table="Author")}
	)
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
