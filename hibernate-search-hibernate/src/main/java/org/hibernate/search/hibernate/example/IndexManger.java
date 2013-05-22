package org.hibernate.search.hibernate.example;


import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.InitializingBean;

/**
 * 随着Spring容器的的创建而创建索引
 * @author Administrator
 *
 */
public class IndexManger implements InitializingBean{
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		//重建索引
		FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.getCurrentSession());
		
		fullTextSession.createIndexer().start();
	}
}
