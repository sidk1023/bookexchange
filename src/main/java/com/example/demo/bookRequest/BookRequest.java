package com.example.demo.bookRequest;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.demo.book.Book;
import com.example.demo.user.Users;
@Entity
@Table
public class BookRequest {
	@Id
	@SequenceGenerator(
			name = "book_request_sequence",
			sequenceName = "book_request_sequence",
			allocationSize = 1
			)
    @GeneratedValue(
    		strategy = GenerationType.SEQUENCE,
    		generator = "book_request_sequence"
    		)
  private long id;
	@ManyToOne
	private Users lender;
	
	@ManyToOne
	private Users borrower;
	
	@ManyToOne
	private Book book;
	
	private Date issueDate;
	private Date returnDate;
	private String status; //Requested, Accepted, Extension,Returned,Overdue
	public BookRequest(long id, Users lender, Users borrower, Book book, Date issueDate, Date returnDate,
			
			
			String status) {
		super();
		this.id = id;
		this.lender = lender;
		this.borrower = borrower;
		this.book = book;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.status = status;
	}
	public BookRequest(Users lender, Users borrower, Book book, String status) {
		super();
		this.lender = lender;
		this.borrower = borrower;
		this.book = book;
		this.status = status;
	}
	public BookRequest() {
		super();
	}
	public BookRequest(Users lender, Users borrower, Book book, Date issueDate, Date returnDate, String status) {
		super();
		this.lender = lender;
		this.borrower = borrower;
		this.book = book;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
		this.status = status;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Users getLender() {
		return lender;
	}
	public void setLender(Users lender) {
		this.lender = lender;
	}
	public Users getBorrower() {
		return borrower;
	}
	public void setBorrower(Users borrower) {
		this.borrower = borrower;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "BookRequest [id=" + id + ", lender=" + lender + ", borrower=" + borrower + ", book=" + book
				+ ", issueDate=" + issueDate + ", returnDate=" + returnDate + ", status=" + status + "]";
	}
	
   
}
