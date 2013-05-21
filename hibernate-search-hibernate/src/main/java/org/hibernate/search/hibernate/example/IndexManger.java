package org.hibernate.search.hibernate.example;


import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class IndexManger {
	
	public static void main(String[] args) throws Exception{
		
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
		SessionFactory sessionFactory = applicationContext.getBean("hibernate4sessionFactory",SessionFactory.class);
		FullTextSession fullTextSession = Search.getFullTextSession(sessionFactory.openSession());
		fullTextSession.createIndexer().start();
	}
	
}
