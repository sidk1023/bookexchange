package com.example.demo.bookRequest;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.book.Book;
import com.example.demo.book.BookRepository;
import com.example.demo.user.UserRepository;
import com.example.demo.user.Users;

@Service
public class BookRequestService {
	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	private final BookRequestRepository bookRequestRepository;
	
	@Autowired
	public BookRequestService(BookRepository bookRepository, UserRepository userRepository, BookRequestRepository bookRequestRepository )
	{
		this.bookRepository = bookRepository;
		this.userRepository = userRepository;
		this.bookRequestRepository = bookRequestRepository;
	}
	
	public List<BookRequest> getBookRequests(){
		return bookRequestRepository.findAll();
	}
	
	public BookRequest getBookRequestsById(Long id){
		BookRequest bookRequest = bookRequestRepository.findById(id).orElseThrow(
	    		 ()-> new IllegalStateException(
	    				 "Book Request with id "+ id+" does not exit" ));
		
		return bookRequest;
	}
	
	public List<BookRequest> getBookRequestByLenderId(Long id){
		List <BookRequest> bookRequestList = bookRequestRepository.findBookRequestByLenderId(id);
		return bookRequestList;
	}
	
	
	public List<BookRequest> getBookRequestByBorrowerId(Long id){
		List <BookRequest> bookRequestList = bookRequestRepository.findBookRequestByBorrowerId(id);
		return bookRequestList;
	}
	
	public List<BookRequest> getBookRequestByBorrowerAndLenderId(Long borrwerId, Long lenderId){
		List <BookRequest> bookRequestList = bookRequestRepository.findBookRequestByBorrowerAndLenderId(borrwerId, lenderId);
		return bookRequestList;
	}
	
	public List<BookRequest> getBookRequestByStatus(String status){
		List <BookRequest> bookRequestList = bookRequestRepository.findBookRequestByStatus(status);
		return bookRequestList;
	}

	public List<BookRequest> getBookRequestByLenderIdAndStatus(Long lenderId,String status){
		List <BookRequest> bookRequestList = bookRequestRepository.findBookRequestByLenderIdAndStatus(lenderId, status);
		return bookRequestList;
	}
	
	public List<BookRequest> getBookRequestByBorrowerIdAndStatus(Long borrowerId,String status){
		List <BookRequest> bookRequestList = bookRequestRepository.findBookRequestByBorrowerIdAndStatus(borrowerId, status);
		return bookRequestList;
	}
	
	public void generateRequest(Long bookId, Long lenderId, Long borrowerId) {
		Users lender = userRepository.findById(lenderId).get();
		if(lender==null) {
			throw new IllegalStateException(
					"user with id "+ lenderId + " does not exist");
		}
		
		Users borrower = userRepository.findById(borrowerId).get();
		if(borrower==null) {
			throw new IllegalStateException(
					"user with id "+ borrowerId + " does not exist");
		}
		
		Book book = bookRepository.findById(bookId).get();
		if(book==null) {
			throw new IllegalStateException(
					"book with id "+ bookId + " does not exist");
		}
		if (!book.getStatus().equals("Available")) {
			System.out.println(book.getStatus());
			throw new IllegalStateException(
					"book with id "+ bookId + " is not available to be borrowed");
		}
		
		String status = "Requested";
		
		BookRequest bookReq = new BookRequest(lender,borrower, book, status);
		bookRequestRepository.save(bookReq);	
	}
	
	
	
	
	@Transactional
	public void acceptRequest(Long requestId, Long lenderId) {
	
		BookRequest bookReq = bookRequestRepository.findById(requestId).get();
		if (bookReq == null) {
			throw new IllegalStateException(
					"Book Request with id " + requestId+ " does not exist");
		}
		if (!bookReq.getBook().getStatus().equals("Available")) {
			throw new IllegalStateException(
					"Book with id "+ bookReq.getBook().getId()+ "is not Available"
					);
		}
		
		Users lender = userRepository.findById(lenderId).get();
		if(lender==null) {
			throw new IllegalStateException(
					"user with id "+ lenderId + " does not exist");
		}
		
		if (!bookReq.getStatus().equals("Requested")) {
			throw new IllegalStateException(
					"Book Request status is not 'Requested'"
					);
		}
		
		
		if (lenderId == bookReq.getLender().getId()) {
			Date today = new Date();
			long ltime = today.getTime()+15*24*60*60*1000;
			Date today15 = new Date(ltime);
			bookReq.setStatus("Accepted");
			bookReq.setIssueDate(today);
			bookReq.setReturnDate(today15);
			bookReq.getBook().setStatus("Borrowed");
		} 
		else {
          throw new IllegalStateException(
        		 "Mismatch of lenderId" 
        		  );		
	}
	}
	
	@Transactional
	public void rejectRequest(Long requestId, Long lenderId) {
	
		BookRequest bookReq = bookRequestRepository.findById(requestId).get();
		if (bookReq == null) {
			throw new IllegalStateException(
					"Book Request with id " + requestId+ " does not exist");
		}
		
		Users lender = userRepository.findById(lenderId).get();
		if(lender==null) {
			throw new IllegalStateException(
					"user with id "+ lenderId + " does not exist");
		}
		
		if (!bookReq.getStatus().equals("Requested")) {
			throw new IllegalStateException(
					"Book Request status is not 'Requested'"
					);
		}
		if (lenderId == bookReq.getLender().getId()) {
			bookReq.setStatus("Rejected");
		} 
		else {
          throw new IllegalStateException(
        		 "Mismatch of lenderId" 
        		  );		
	}
	}
	
	@Transactional
	public void returnRequest(Long requestId, Long borrowerId) {
	
		BookRequest bookReq = bookRequestRepository.findById(requestId).get();
		if (bookReq == null) {
			throw new IllegalStateException(
					"Book Request with id " + requestId+ " does not exist");
		}
		
		Users borrower = userRepository.findById(borrowerId).get();
		if(borrower==null) {
			throw new IllegalStateException(
					"user with id "+ borrowerId + " does not exist");
		}
		
		if (!bookReq.getStatus().equals("Accepted")) {
			throw new IllegalStateException(
					"Book Request status is not 'Accepted'"
					);
		}
		if (borrowerId != bookReq.getBorrower().getId()) {
		
          throw new IllegalStateException(
        		 "Mismatch of borrowerId" 
        		  );		
		}
		
        bookReq.setStatus("Returned"); 
        bookReq.getBook().setStatus("Available");
	
	}
	
	
	@Transactional
	public void extendRequest(Long requestId, Long borrowerId) {
	
		BookRequest bookReq = bookRequestRepository.findById(requestId).get();
		if (bookReq == null) {
			throw new IllegalStateException(
					"Book Request with id " + requestId+ " does not exist");
		}
		
		Users borrower = userRepository.findById(borrowerId).get();
		if(borrower==null) {
			throw new IllegalStateException(
					"user with id "+ borrowerId + " does not exist");
		}
		
		if (!bookReq.getStatus().equals("Accepted")) {
			System.out.println(bookReq.getStatus());
			throw new IllegalStateException(
					"Book Request status is not 'Accepted'"
					);
		}
		if (borrowerId != bookReq.getBorrower().getId()) {
		
          throw new IllegalStateException(
        		 "Mismatch of borrowerId" 
        		  );		
		}
        bookReq.setStatus("Extension"); 
	
	}
	
	
	
	@Transactional
	public void acceptExtension(Long requestId, Long lenderId) {
	
		BookRequest bookReq = bookRequestRepository.findById(requestId).get();
		if (bookReq == null) {
			throw new IllegalStateException(
					"Book Request with id " + requestId+ " does not exist");
		}
		
		
		Users lender = userRepository.findById(lenderId).get();
		if(lender==null) {
			throw new IllegalStateException(
					"user with id "+ lenderId + " does not exist");
		}
		
		if (!bookReq.getStatus().equals("Extension")) {
			throw new IllegalStateException(
					"Book Request status is not 'Extension'"
					);
		}
		
		/*if(bookReq.getBook().getStatus()!= "Borrowed") {
			throw new IllegalStateException(
					"Book is not Borrowed to ask for extension."
					);
		}*/
		
		
		if (lenderId == bookReq.getLender().getId()) {
			Date today = new Date();
			long ltime = today.getTime()+15*24*60*60*1000;
			Date today15 = new Date(ltime);
			bookReq.setStatus("Accepted");
			bookReq.setIssueDate(today);
			bookReq.setReturnDate(today15);
			
		} 
		else {
          throw new IllegalStateException(
        		 "Mismatch of lenderId" 
        		  );		
	}
	}
	
	@Transactional
	public void rejectExtension(Long requestId, Long lenderId) {
	
		BookRequest bookReq = bookRequestRepository.findById(requestId).get();
		if (bookReq == null) {
			throw new IllegalStateException(
					"Book Request with id " + requestId+ " does not exist");
		}
		
		
		Users lender = userRepository.findById(lenderId).get();
		if(lender==null) {
			throw new IllegalStateException(
					"user with id "+ lenderId + " does not exist");
		}
		
		if (!bookReq.getStatus().equals("Extension")) {
			throw new IllegalStateException(
					"Book Request status is not 'Extension'"
					);
		}
		
		/*if(bookReq.getBook().getStatus()!= "Borrowed") {
			throw new IllegalStateException(
					"Book is not Borrowed to ask for extension."
					);
		}*/
		
		
		if (lenderId == bookReq.getLender().getId()) {
			bookReq.setStatus("Accepted");
		} 
		else {
          throw new IllegalStateException(
        		 "Mismatch of lenderId" 
        		  );		
	}
	}
	
	
	@Transactional
	public void chargePenalty(Long requestId, Long lenderId) {
		BookRequest bookReq = bookRequestRepository.findById(requestId).get();
		if (bookReq == null) {
			throw new IllegalStateException(
					"Book Request with id " + requestId+ " does not exist");
		}
		
		if(bookReq.getLender().getId()!= lenderId) {
			throw new IllegalStateException("LenderId Mismatch");
		}
		
		if (bookReq.getStatus()!="Accepted"||bookReq.getStatus()!="Extension") {
			throw new IllegalStateException(
				"Book Status is not Accepted or Extension. Unable to charge penalty");
		}
		
		if (bookReq.getReturnDate().before(new Date())){
			int penalty = 10;
			int purse = bookReq.getBorrower().getWallet();
			bookReq.getBorrower().setWallet(purse - penalty);
			bookReq.setStatus("Overdue");
		}
		else {
			throw new IllegalStateException("Book is not overdue");
		}
	}
   	
}


	
	
	
	
	
	
	

