package org.hibernate.search.jpa.example.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.analysis.Analyzer;
import org.hibernate.search.jpa.example.dao.BookDao;
import org.hibernate.search.jpa.example.model.Book;
import org.hibernate.search.jpa.example.model.QueryResult;
import org.hibernate.search.jpa.example.service.BookService;
import org.springframework.stereotype.Service;

@Service(value="bookServiceImpl")
public class BookServiceImpl implements BookService {

	@Resource(name="bookDaoImpl")
	private BookDao bookDao;
	
	@Override
	public void add(Book book) {
		bookDao.add(book);
	}

	@Override
	public List<Book> query(int start, int pagesize) {
		return bookDao.query(start, pagesize);
	}

	@Override
	public void update(Book book) {
		bookDao.update(book);
	}

	@Override
	public void delete(Book book) {
		bookDao.delete(book);
	}

	@Override
	public void delete(int id) {
		bookDao.delete(id);
	}

	@Override
	public QueryResult<Book> query(String keyword, int start, int pagesize,Analyzer analyzer, String... field) throws Exception {
		return bookDao.query(keyword, start, pagesize, analyzer, field);
	}

	@Override
	public Book load(int id) {
		return bookDao.load(id);
	}


}
