package org.hibernate.example.dao;

import java.util.List;

import org.hibernate.example.model.Book;

public interface BookDao {
	
	void add(Book book);
	
	Book load(int id);
	
	List<Book> query(int start,int pagesize);
	void update(Book book);
	void delete(Book book);
	void delete(int id);
	
}
