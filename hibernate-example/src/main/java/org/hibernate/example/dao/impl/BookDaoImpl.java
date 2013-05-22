package org.hibernate.example.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.example.dao.BookDao;
import org.hibernate.example.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository(value="bookDaoImpl")
@Transactional
public class BookDaoImpl implements BookDao {
	
	@Resource(name="hibernate4sessionFactory")
	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void add(Book book) {
		this.getSessionFactory().getCurrentSession().persist(book);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public List<Book> query(int start, int pagesize) {
		Session session = this.getSessionFactory().getCurrentSession();
		return session.createCriteria(Book.class).setFirstResult(start).setMaxResults(pagesize).list();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(Book book) {
		Session session = this.getSessionFactory().getCurrentSession();
		session.merge(book);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(Book book) {
		this.getSessionFactory().getCurrentSession().delete(book);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(int id) {
		
		Session session = this.getSessionFactory().getCurrentSession();
		session.delete(load(id));
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Book load(int id) {
		return (Book) this.getSessionFactory().getCurrentSession().get(Book.class, id);
	}

	
}
