package com.example.demo.book;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.example.demo.user.Users;

@Entity
@Table
@OnDelete(action = OnDeleteAction.CASCADE)
public class Book {
     @Id
     @SequenceGenerator(
    		 name = "book_sequence",
    		 sequenceName = "book_sequence",
    		 allocationSize = 1
    		 )
     @GeneratedValue(
     		strategy = GenerationType.SEQUENCE,
     		generator = "book_sequence"
     		)
     private long id;
     private String title;
     private String author;
     private String description;
     @ManyToOne
     private Users lender;
     private String status; //Available, Borrowed
     
	public Book(long id, String title, String author, String description, Users lender, String status) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.description = description;
		this.lender = lender;
		this.status = status;
	}

	public Book(String title, String author, String description, String status) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.status = status;
	}

	public Book(String title, String author, String description, Users lender, String status) {
		super();
		this.title = title;
		this.author = author;
		this.description = description;
		this.lender = lender;
		this.status = status;
	}

	public Book() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Users getLender() {
		return lender;
	}

	public void setLender(Users lender) {
		this.lender = lender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author + ", description=" + description
				+ ", lender=" + lender + ", status=" + status + "]";
	}
     
     
}
