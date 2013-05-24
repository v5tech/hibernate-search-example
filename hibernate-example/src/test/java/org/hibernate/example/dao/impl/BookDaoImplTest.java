package org.hibernate.example.dao.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.example.dao.BookDao;
import org.hibernate.example.model.Author;
import org.hibernate.example.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:applicationContext.xml")
@TransactionConfiguration(transactionManager="hibernate4TransactionManager", defaultRollback=false)
@Transactional
public class BookDaoImplTest {
	
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
	public void update(){
		Book book = bookDao.load(11);
		book.setName("研磨设计模式");
		book.setPublicationDate(new Date());
		book.setDescription("《研磨设计模式》完整覆盖gof讲述的23个设计模式并加以细细研磨。初级内容从基本讲起，包括每个模式的定义、功能、思路、结构、基本实现、运行调用顺序、基本应用示例等，让读者能系统、完整、准确地掌握每个模式，培养正确的“设计观”；中高级内容则深入探讨如何理解这些模式，包括模式中蕴涵什么样的设计思想，模式的本质是什么，模式如何结合实际应用，模式的优缺点以及与其他模式的关系等，以期让读者尽量去理解和掌握每个设计模式的精髓所在。");
		
		Set<Author> authors=new HashSet<Author>();
		authors.add(new Author("陈臣"));
		authors.add(new Author("王斌"));
		book.setAuthors(authors);
		bookDao.update(book);
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
	
	
}
