package org.hibernate.example.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.example.dao.BookDao;
import org.hibernate.example.model.Book;
import org.hibernate.example.service.BookService;
import org.springframework.stereotype.Service;

@Service(value="bookServiceImpl")
//@Transactional(propagation=Propagation.REQUIRED)
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
	public Book load(int id) {
		return bookDao.load(id);
	}


}
