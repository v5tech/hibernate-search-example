package org.hibernate.search.jpa.example.service;

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.hibernate.search.jpa.example.model.Book;
import org.hibernate.search.jpa.example.model.QueryResult;

public interface BookService {
	
	void add(Book book);
	
	Book load(int id);
	
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
