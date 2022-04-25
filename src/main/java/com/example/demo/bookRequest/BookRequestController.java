package com.example.demo.bookRequest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "requests")
public class BookRequestController {
		private final BookRequestService bookRequestService;
		
		
		@Autowired
		public BookRequestController(BookRequestService bookRequestService) {
			this.bookRequestService = bookRequestService;
		}
		
		@GetMapping
		  public List<BookRequest>getBookRequests(){
			  return bookRequestService.getBookRequests();
		  }
	
		@PostMapping(path = "{bookId}/{lenderId}/{borrowerId}")
		public void createBookRequest(@PathVariable("bookId") Long bookId, @PathVariable("lenderId") Long lenderId, @PathVariable("borrowerId") Long borrowerId) {
        bookRequestService.generateRequest(bookId, lenderId, borrowerId);
		}
		
		@GetMapping(path = "request/{requestId}")
		public BookRequest getBookRequestById(@PathVariable("requestId")Long requestId){
			return bookRequestService.getBookRequestsById(requestId);
		}
		
		@GetMapping(path = "lender/{lenderId}")
		public List<BookRequest>getBookRequestByLenderId(@PathVariable("lenderId")Long lenderId){
			return bookRequestService.getBookRequestByLenderId(lenderId);
		}
		
		@GetMapping(path = "borrower/{borrowerId}")
		public List<BookRequest>getBookRequestByBorrowerId(@PathVariable("borrowerId")Long borrowerId){
			return bookRequestService.getBookRequestByBorrowerId(borrowerId);
		}
		
		@GetMapping(path = "lender/{lenderId}/status/{status}")
		public List<BookRequest>getBookRequestByLenderIdAndStatus(@PathVariable("lenderId") Long lenderId, @PathVariable("status") String status){
			return bookRequestService.getBookRequestByLenderIdAndStatus(lenderId, status);
		}
		
		@GetMapping(path = "borrower/{borrowerId}/status/{status}")
		public List<BookRequest>getBookRequestByBorrowerIdAndStatus(@PathVariable("borrowerId") Long borrowerId, @PathVariable("status") String status){
			return bookRequestService.getBookRequestByBorrowerIdAndStatus(borrowerId, status);
		}
		
		@GetMapping(path = "borrower/{borrowerId}/lender/{lenderId}")
		public List<BookRequest>getBookRequestByBorrowerId(@PathVariable("borrowerId")Long borrowerId, @PathVariable("lenderId")Long lenderId){
			return bookRequestService.getBookRequestByBorrowerAndLenderId(borrowerId, lenderId);
		}
		
		@GetMapping(path = "status/{status}")
		public List<BookRequest>getBookRequestByStatus(@PathVariable("status")String status){
			return bookRequestService.getBookRequestByStatus(status);
		}
		
    }
