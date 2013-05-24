package org.hibernate.search.jpa.example.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import net.paoding.analysis.analyzer.PaodingAnalyzer;

import org.hibernate.search.jpa.example.model.Author;
import org.hibernate.search.jpa.example.model.Book;
import org.hibernate.search.jpa.example.model.QueryResult;
import org.hibernate.search.jpa.example.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
	
	@Resource(name="bookServiceImpl")
	private BookService bookService;
	
	@RequestMapping("/search")
	public ModelAndView search(@RequestParam(value="keyword")String keyword,@RequestParam(value="start")int start,@RequestParam(value="pagesize")int pagesize){
		QueryResult<Book> queryResult= null;
		try {
			keyword=keyword==null?"":keyword.trim();
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
	@ResponseBody
	public String delete(@PathVariable(value="id")int id){
		
		try {
			bookService.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
			return "删除失败,"+e.getMessage();
		}
		
		return "删除成功!";
		
	}
	
	
	@RequestMapping(value="/save",method={RequestMethod.POST})
	@ResponseBody
	public String save(@RequestParam("name")String name,@RequestParam("description")String description,@RequestParam("author")String[] author) throws UnsupportedEncodingException{
		try {
			Book book = new Book();
			
			book.setName(name);
			book.setPublicationDate(new Date());
			book.setDescription(description);
			
			Set<Author> authors=new HashSet<Author>();
			
			if(author!=null&&author.length>0){
				for (int i = 0; i < author.length; i++) {
					authors.add(new Author(author[i]));
				}
			}
			
			book.setAuthors(authors);
			
			bookService.add(book);
		} catch (Exception e) {
			e.printStackTrace();
			return "保存失败,"+e.getMessage();
		}
		
		return "保存成功!";
	}
	
	
	
	@RequestMapping(value="/modify",method={RequestMethod.POST})
	@ResponseBody
	public String modify(@RequestParam("id")String id,@RequestParam("name")String name,@RequestParam("description")String description,@RequestParam("author")String[] author) throws UnsupportedEncodingException{
		try {
			Book book = new Book();
			book.setId(Integer.valueOf(id));
			book.setName(name);
			book.setPublicationDate(new Date());
			book.setDescription(description);
			
			Set<Author> authors=new HashSet<Author>();
			
			if(author!=null&&author.length>0){
				for (int i = 0; i < author.length; i++) {
					authors.add(new Author(author[i]));
				}
			}
			
			book.setAuthors(authors);
			
			bookService.update(book);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return "修改失败,"+e.getMessage();
		}
		return "修改成功!";
	}
		
	
	@RequestMapping("/load/{id}")
	@ResponseBody
	public Book load(@PathVariable(value="id")int id){
		Book book = bookService.load(id);
		return book;
	}
	
}
