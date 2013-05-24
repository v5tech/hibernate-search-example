package org.hibernate.search.hibernate.example.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.util.Version;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.hibernate.example.dao.BookDao;
import org.hibernate.search.hibernate.example.model.Author;
import org.hibernate.search.hibernate.example.model.Book;
import org.hibernate.search.hibernate.example.model.QueryResult;
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
		
		Session session=this.getSessionFactory().getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		Transaction transaction = fullTextSession.beginTransaction();
		session.persist(book);
		transaction.commit();
		
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
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		Transaction transaction = fullTextSession.beginTransaction();
		session.merge(book);
		transaction.commit();
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
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		
		int n = this.getSessionFactory().getCurrentSession().createSQLQuery("DELETE FROM book_author WHERE book_id= "+id).executeUpdate();
		
		System.out.println(n);
		
		int m = this.getSessionFactory().getCurrentSession().createSQLQuery("DELETE FROM book WHERE id= "+id).executeUpdate();
		
		System.out.println(m);
		
		session.delete(load(id));
		
		Transaction tx = fullTextSession.beginTransaction();
		fullTextSession.purge(Book.class, id);
		tx.commit();
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public QueryResult<Book> query(String keyword, int start, int pagesize,Analyzer analyzer,String...field) throws Exception{
		
		QueryResult<Book> queryResult=new QueryResult<Book>();
		
		List<Book> books=new ArrayList<Book>();
		
		Session session = this.getSessionFactory().getCurrentSession();
		FullTextSession fullTextSession = Search.getFullTextSession(session);
		
		//使用Hibernate Search api查询 从多个字段匹配 name、description、authors.name
		//QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(Book.class ).get();
		//Query luceneQuery = qb.keyword().onFields(field).matching(keyword).createQuery();

		//使用lucene api查询 从多个字段匹配 name、description、authors.name
		
		MultiFieldQueryParser queryParser=new MultiFieldQueryParser(Version.LUCENE_36,new String[]{"name","description","authors.name"}, analyzer);
		Query luceneQuery=queryParser.parse(keyword);
		
		FullTextQuery fullTextQuery = fullTextSession.createFullTextQuery(luceneQuery);
		int searchresultsize = fullTextQuery.getResultSize();
		queryResult.setSearchresultsize(searchresultsize);
		System.out.println("共查找到["+searchresultsize+"]条记录");
		
		fullTextQuery.setFirstResult(start);
		fullTextQuery.setMaxResults(pagesize);
		
		//设置按id排序
		fullTextQuery.setSort(new Sort(new SortField("id", SortField.INT ,true)));
		
		//高亮设置
		SimpleHTMLFormatter formatter=new SimpleHTMLFormatter("<b><font color='red'>", "</font></b>");
		QueryScorer queryScorer=new QueryScorer(luceneQuery);
		Highlighter highlighter=new Highlighter(formatter, queryScorer);

		@SuppressWarnings("unchecked")
		List<Book> tempresult = fullTextQuery.list();
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
			
			
			System.out.println("书名:"+book.getName()+"\n描述:"+book.getDescription()+"\n出版日期:"+book.getPublicationDate());
			System.out.println("----------------------------------------------------------");
		}
		
		queryResult.setSearchresult(books);
		
		fullTextSession.close();
		
		return queryResult;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Book load(int id) {
		return (Book) this.getSessionFactory().getCurrentSession().get(Book.class, id);
	}

	
}
