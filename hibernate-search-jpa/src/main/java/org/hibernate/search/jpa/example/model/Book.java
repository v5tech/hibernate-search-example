package org.hibernate.search.jpa.example.model;

import static org.hibernate.search.annotations.FieldCacheType.CLASS;
import static org.hibernate.search.annotations.FieldCacheType.ID;

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

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Analyzer;
import org.hibernate.search.annotations.Boost;
import org.hibernate.search.annotations.CacheFromIndex;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;

@Entity
@Table(catalog="hibernate_search",name="Book")
@Indexed(index="book")
//@Analyzer(impl=IKAnalyzer.class)
@Analyzer(impl=PaodingAnalyzer.class)
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE,region="org.hibernate.search.hibernate.example.model.Book")  
@Boost(2.0f)
@CacheFromIndex( { CLASS, ID } )
public class Book {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@DocumentId
	private Integer id;

	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.COMPRESS)
	@Boost(1.5f)
	private String name;
	
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.COMPRESS)
	@Boost(1.2f)
	private String description;
	
	@Field(index = Index.YES, analyze = Analyze.NO, store = Store.YES)
	@DateBridge(resolution = Resolution.DAY)
	private Date publicationDate;

	@IndexedEmbedded(depth=1)
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH,CascadeType.REMOVE},fetch=FetchType.LAZY)
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
