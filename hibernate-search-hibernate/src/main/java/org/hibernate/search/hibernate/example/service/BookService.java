package org.hibernate.search.hibernate.example.service;

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.hibernate.search.hibernate.example.model.Book;

public interface BookService {
	
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
	List<Book> query(String keyword, int start, int pagesize,Analyzer analyzer,String...field) throws Exception;
	
}
