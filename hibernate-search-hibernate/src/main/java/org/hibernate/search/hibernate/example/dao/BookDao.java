package org.hibernate.search.hibernate.example.dao;

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.hibernate.search.hibernate.example.model.Book;
import org.hibernate.search.hibernate.example.model.QueryResult;

public interface BookDao {
	
	void add(Book book);
	List<Book> query(int start,int pagesize);
	void update(Book book);
	void delete(Book book);
	void delete(int id);
	
	/**
	 * 
	 * @param keyword
	 * @param start
	 * @param pagesize
	 * @param analyzer
	 * @param field
	 * @return
	 * @throws Exception
	 */
	QueryResult<Book> query(String keyword, int start, int pagesize,Analyzer analyzer,String...field) throws Exception;
	
}
