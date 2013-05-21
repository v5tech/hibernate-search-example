package org.hibernate.search.hibernate.example.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.lucene.analysis.Analyzer;
import org.hibernate.search.hibernate.example.dao.BookDao;
import org.hibernate.search.hibernate.example.model.Book;
import org.hibernate.search.hibernate.example.model.QueryResult;
import org.hibernate.search.hibernate.example.service.BookService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value="bookServiceImpl")
@Transactional
public class BookServiceImpl implements BookService {

	@Resource(name="bookDaoImpl")
	private BookDao bookDao;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void add(Book book) {
		bookDao.add(book);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Book> query(int start, int pagesize) {
		return bookDao.query(start, pagesize);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Book book) {
		bookDao.update(book);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Book book) {
		bookDao.delete(book);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(int id) {
		bookDao.delete(id);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public QueryResult<Book> query(String keyword, int start, int pagesize,Analyzer analyzer, String... field) throws Exception {
		return bookDao.query(keyword, start, pagesize, analyzer, field);
	}


}
