package org.hibernate.search.hibernate.example.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.hibernate.search.hibernate.example.model.Author;
import org.hibernate.search.hibernate.example.model.Book;
import org.hibernate.search.hibernate.example.model.QueryResult;
import org.hibernate.search.hibernate.example.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Service
public class BookController {
	
	@Resource(name="bookServiceImpl")
	private BookService bookService;
	
	@RequestMapping("/search/{keyword}/{start}/{pagesize}")
	public ModelAndView search(@PathVariable(value="keyword")String keyword,@PathVariable(value="start")int start,@PathVariable(value="pagesize")int pagesize){
		QueryResult<Book> queryResult= null;
		try {
			
			keyword=new String(keyword.getBytes("iso-8859-1"),"utf-8");
			
			queryResult = bookService.query(keyword, start, pagesize, new PaodingAnalyzer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView("list");
		modelAndView.addObject("queryResult", queryResult);
		return modelAndView;
	}
	
	
	
	
	@RequestMapping("/query/{start}/{pagesize}")
	public ModelAndView query(@PathVariable(value="start")int start,@PathVariable(value="pagesize")int pagesize){
		QueryResult<Book> queryResult = null;
		try {
			List<Book> lists = bookService.query(start, pagesize);
			queryResult= new QueryResult<Book>();
			queryResult.setSearchresultsize(lists.size());
			queryResult.setSearchresult(lists);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView modelAndView = new ModelAndView("list");
		modelAndView.addObject("queryResult", queryResult);
		return modelAndView;
	}
	
	
	
	@RequestMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable(value="id")int id){
		
		bookService.delete(id);
		
		return query(0, 5);
		
	}
	
	
	@RequestMapping("/modify/{id}")
	public void modify(@PathVariable(value="id")int id){
		Book book = bookService.load(id);
		
		book.setName("研磨设计模式");
		book.setPublicationDate(new Date());
		book.setDescription("《研磨设计模式》完整覆盖gof讲述的23个设计模式并加以细细研磨。初级内容从基本讲起，包括每个模式的定义、功能、思路、结构、基本实现、运行调用顺序、基本应用示例等，让读者能系统、完整、准确地掌握每个模式，培养正确的“设计观”；中高级内容则深入探讨如何理解这些模式，包括模式中蕴涵什么样的设计思想，模式的本质是什么，模式如何结合实际应用，模式的优缺点以及与其他模式的关系等，以期让读者尽量去理解和掌握每个设计模式的精髓所在。");
		
		Set<Author> authors=new HashSet<Author>();
		authors.add(new Author("陈臣"));
		authors.add(new Author("王斌"));
		book.setAuthors(authors);
		
		
		bookService.update(book);
		
	}
	
	
}
