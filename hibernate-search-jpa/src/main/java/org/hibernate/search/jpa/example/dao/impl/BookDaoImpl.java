package org.hibernate.search.jpa.example.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.jpa.example.dao.BookDao;
import org.hibernate.search.jpa.example.model.Author;
import org.hibernate.search.jpa.example.model.Book;
import org.hibernate.search.jpa.example.model.QueryResult;
import org.springframework.stereotype.Repository;

@Repository(value="bookDaoImpl")
public class BookDaoImpl implements  BookDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void add(Book book) {
		
		em.persist(book);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> query(int start, int pagesize) {
		javax.persistence.Query query = em.createQuery(" from org.hibernate.search.jpa.example.model.Book ");
		query.setFirstResult(start);
		query.setMaxResults(pagesize);
		return query.getResultList();
	}

	@Override
	public void update(Book book) {
		em.merge(book);
	}

	@Override
	public void delete(Book book) {
		em.remove(book);
	}

	@Override
	public void delete(int id) {
		
		em.remove(load(id));
		
	}

	@Override
	public QueryResult<Book> query(String keyword, int start, int pagesize,Analyzer analyzer,String...field) throws Exception{
		
		QueryResult<Book> queryResult=new QueryResult<Book>();
		
		List<Book> books=new ArrayList<Book>();
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(em);
		
		//使用Hibernate Search api查询 从多个字段匹配 name、description、authors.name
		//QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Book.class ).get();
		//Query luceneQuery = qb.keyword().onFields(field).matching(keyword).createQuery();

		//使用lucene api查询 从多个字段匹配 name、description、authors.name
		
		MultiFieldQueryParser queryParser=new MultiFieldQueryParser(Version.LUCENE_36,new String[]{"name","description","authors.name"}, analyzer);
		Query luceneQuery=queryParser.parse(keyword);
		
		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(luceneQuery);
		int searchresultsize = fullTextQuery.getResultSize();
		queryResult.setSearchresultsize(searchresultsize);
				
		fullTextQuery.setFirstResult(start);
		fullTextQuery.setMaxResults(pagesize);
		
		//设置按id排序
		fullTextQuery.setSort(new Sort(new SortField("id", SortField.INT ,true)));
		
		//高亮设置
		SimpleHTMLFormatter formatter=new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
		QueryScorer queryScorer=new QueryScorer(luceneQuery);
		Highlighter highlighter=new Highlighter(formatter, queryScorer);

		@SuppressWarnings("unchecked")
		List<Book> tempresult = fullTextQuery.getResultList();
		for (Book book : tempresult) {
			String highlighterString=null;
			try {
				//高亮name
				highlighterString=highlighter.getBestFragment(analyzer, "name", book.getName());
				if(highlighterString!=null){
					book.setName(highlighterString);
				}
				//高亮authors.name
				Set<Author> authors = book.getAuthors();
				for (Author author : authors) {
					highlighterString=highlighter.getBestFragment(analyzer, "authors.name", author.getName());
					if(highlighterString!=null){
						author.setName(highlighterString);
					}
				}
				//高亮description
				highlighterString=highlighter.getBestFragment(analyzer, "description", book.getDescription());
				if(highlighterString!=null){
					book.setDescription(highlighterString);
				}
			} catch (Exception e) {
			}
			
			books.add(book);	
			
		}
		
		queryResult.setSearchresult(books);
		
		return queryResult;
	}

	@Override
	public Book load(int id) {
		return (Book) em.find(Book.class, id);
	}

	
}
