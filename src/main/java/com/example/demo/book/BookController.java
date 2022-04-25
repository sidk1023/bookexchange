package com.example.demo.book;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "books")
public class BookController {
  private final BookService bookService;
  
  @Autowired
  public BookController(BookService bookService) {
	  this.bookService = bookService;
  }
  
  @GetMapping
  public List<Book>getBooks(){
	  return bookService.getBooks();
  }
  
 
  
  @GetMapping(path = "{bookId}")
  public Book getBookById(@PathVariable("bookId") Long bookId){
	  return bookService.getBookById(bookId);
  }
  
  @GetMapping(path = "status/{status}")
  public List<Book>getBookByStatus(@PathVariable("status")String status){
	  return bookService.getBookByStatus(status);
  }
  
  @GetMapping(path = "lender/{lenderId}")
  public List<Book>getBookByLenderId(@PathVariable("lenderId")Long lenderId){
	  return bookService.getBookByLenderId(lenderId);
  }
  
  @GetMapping(path = "author/{author}")
  public List<Book>getBookByAuthor(@PathVariable("author") String author){
	 return  bookService.getBookByAuthor(author);
  }
  @GetMapping(path = "title/{title}")
  public List<Book>getBookByTitle(@PathVariable("title") String title){
	 return bookService.getBookByTitle(title);
  }
  
  @PostMapping(path = "{userId}")
  public void addBook(@RequestBody Book book,@PathVariable("userId") Long userId ) {
	  bookService.addNewBook(book,userId);
  }
  
  @PutMapping(path = "{bookId}")
  public void editBook(
		  @PathVariable("bookId") Long bookId,
		  @RequestParam(required = false)String title,
		  @RequestParam(required = false)String author,
		  @RequestParam(required = false)String description
		  ) {
	  bookService.editBook(bookId, title, author, description);
  }
  
  @PutMapping(path = "{bookId}/{status}")
  public void editBookStatus(
		  @PathVariable("bookId") Long bookId,
		  @PathVariable("status") String status
		  ) {
         bookService.editBookStatus(bookId, status);
  }
  
  @DeleteMapping(path = "{bookId}")
	  public void deleteBook(
			 @PathVariable("bookId") Long bookId ) {
		  bookService.deleteBook(bookId);
	  }
	  
  
}
