package org.hibernate.search.hibernate.example.controller;

import javax.annotation.Resource;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

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
	
	
	@RequestMapping("/delete/{id}")
	public void search(@PathVariable(value="id")int id){
		bookService.delete(id);
	}
	
}
