package org.hibernate.search.jpa.example.dao.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.apache.lucene.analysis.Analyzer;
import org.hibernate.search.jpa.example.dao.BookDao;
import org.hibernate.search.jpa.example.model.Author;
import org.hibernate.search.jpa.example.model.Book;
import org.hibernate.search.jpa.example.model.QueryResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
public class BookDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Resource(name="bookDaoImpl")
	private BookDao bookDao ;
	
	@Test
	public void query(){
		List<Book> list = bookDao.query(5, 5);
		for (Book book : list) {
			System.out.println(book.getName());
		}
	}
	
	
	@Test
	public void delete(){
		bookDao.delete(11);
	}
	
	@Test
	public void save(){
		Book book = new Book();
		book.setName("微信营销解密:移动互联网时代的营销革命");
		book.setPublicationDate(new Date());
		book.setDescription("《微信营销解密:移动互联网时代的营销革命》由资深微信营销专家、微信营销布道者、微信营销理论奠基人亲自撰写。根据机构、企业和个人做微信营销的需求，从理论层面对微信营销的本质、要义、核心价值进行了深入的探讨，系统总结了微信营销的原则、方法、步骤、技巧，以及营销效果的量化与评估方法；从实操层面对10余个行业的微信营销前景进行了全面的解读并给出了解决方案，对13个成功的经典微信营销案例的实施过程进行了深度剖析，还对微信营销与其他营销媒介的整合进行了阐述，极具启发意义和可操作性。");
		
		Set<Author> authors=new HashSet<Author>();
		authors.add(new Author("李国建"));
		authors.add(new Author("程小永"));
		book.setAuthors(authors);
		bookDao.add(book);
	}
	
	@Test
	public void search(){
		int start=0;
		int pagesize=5;
		Analyzer analyzer=new PaodingAnalyzer();
		String[] field=new String[]{"name","description","authors.name"};
		QueryResult<Book> queryResult= null;
		try {
			queryResult = bookDao.query("实战", start, pagesize, analyzer, field);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("共检索到["+queryResult.getSearchresultsize()+"]条记录!");
		
		for (Book book : queryResult.getSearchresult()) {
			System.out.println("书名:"+book.getName()+"\n描述:"+book.getDescription()+"\n出版日期:"+book.getPublicationDate());
			System.out.println("----------------------------------------------------------");
		}
	}
	
}
