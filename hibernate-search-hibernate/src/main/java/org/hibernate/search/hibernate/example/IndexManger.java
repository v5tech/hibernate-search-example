package org.hibernate.search.hibernate.example;


import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * @author Administrator
 *
 */
public class IndexManger implements InitializingBean{
	
	@Autowired
	@Qualifier("hibernate4sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void afterPropertiesSet() throws Exception {
		//ÖØ½¨Ë÷Òý
		FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.openSession());
		
		fullTextSession.createIndexer().startAndWait();
	}
}
