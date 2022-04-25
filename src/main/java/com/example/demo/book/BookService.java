package com.example.demo.book;
import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.bookRequest.BookRequest;
import com.example.demo.bookRequest.BookRequestRepository;
import com.example.demo.user.UserRepository;
import com.example.demo.user.Users;

@Service
public class BookService {
	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	private final BookRequestRepository bookRequestRepository;
	@Autowired
	public BookService(BookRepository bookRepository, UserRepository userRepository,BookRequestRepository bookRequestRepository) {
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
		this.bookRequestRepository = bookRequestRepository;
	}
	
	public List<Book> getBooks(){
		return bookRepository.findAll();
	}
	
	public void addNewBook(Book book, Long userId) {
	   //Check if user exists
		Users user = userRepository.findById(userId).get();
		if(user==null) {
			throw new IllegalStateException(
					"user with id "+ userId + " does not exist");
		}
		
		book.setLender(user);
		bookRepository.save(book);
	}
	
	@Transactional
	public void editBook (Long bookId, String title, String author, String description) {
		Book book = bookRepository.findById(bookId).orElseThrow(
	    		 ()-> new IllegalStateException(
	    				 "Book with id "+ bookId+" does not exit" ));
		 if(title!=null&&title.length()>0 && !Objects.equals(book.getTitle(),title)) {
	    	 book.setTitle(title);
	     }
		 if(author!=null&&author.length()>0 && !Objects.equals(book.getAuthor(),author)) {
	    	 book.setAuthor(author);
	     }
		 if(description!=null&&description.length()>0 && !Objects.equals(book.getDescription(),description)) {
	    	 book.setDescription(description);
	     }
	}
	
	@Transactional
	public void editBookStatus(Long bookId, String status) {
		Book book = bookRepository.findById(bookId).orElseThrow(
	    		 ()-> new IllegalStateException(
	    				 "Book with id "+ bookId+" does not exit" ));
		 if(status!=null&&status.length()>0 && !Objects.equals(book.getStatus(),status)) {
	    	 book.setStatus(status);
	     }
	}

	public void deleteBook(Long bookId) {
		boolean exists = bookRepository.existsById(bookId);
		if(!exists) {
			throw new IllegalStateException(
					"book with id "+ bookId+ "does not exist");
		}
		List<BookRequest> requests = bookRequestRepository.findBookRequestByBookId(bookId);
		System.out.println("Deleting Request");
		for(BookRequest request:requests) {
			bookRequestRepository.deleteById(request.getId());
		}
		System.out.println("Deleted Request");
		bookRepository.deleteById(bookId); 
	}

	public Book getBookById(Long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(
	    		 ()-> new IllegalStateException(
	    				 "Book with id "+ bookId+" does not exit" ));
		return book;
		
	}

	public List<Book> getBookByStatus(String status) {
		List <Book> bookList = bookRepository.findBookByStatus(status);
		return bookList;
	}
	
	public List<Book> getBookByLenderId(Long userId) {
		//Users user = userRepository.findById(userId).orElseThrow(
			//	()-> new IllegalStateException(
				//		"User with id " + userId+" does not exist."));
		List <Book> bookList = bookRepository.findBookByUser(userId);
		return bookList;
	}
	
	public List<Book> getBookByAuthor(String author){
		List <Book> bookList = bookRepository.findBookByAuthor(author);
		return bookList;
	}
	
	public List<Book> getBookByTitle(String title){
		List <Book> bookList = bookRepository.findBookByTitle(title);
		return bookList;
	}
	
	
   
}
