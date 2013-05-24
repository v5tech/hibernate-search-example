package org.hibernate.example.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.example.dao.BookDao;
import org.hibernate.example.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository(value="bookDaoImpl")
public class BookDaoImpl implements BookDao {
	
	@Autowired
    @Qualifier("hibernate4sessionFactory")
	private SessionFactory sessionFactory;
	
	public Session getSession() {
        return sessionFactory.getCurrentSession();
    }
	

	@Override
	public void add(Book book) {
		this.getSession().persist(book);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> query(int start, int pagesize) {
		return getSession().createCriteria(Book.class).setFirstResult(start).setMaxResults(pagesize).list();
	}

	@Override
	public void update(Book book) {
		getSession().merge(book);
	}

	@Override
	public void delete(Book book) {
		getSession().delete(book);
	}

	@Override
	public void delete(int id) {
		
		getSession().delete(load(id));
		
	}

	@Override
	public Book load(int id) {
		return (Book) getSession().get(Book.class, id);
	}

	
}
