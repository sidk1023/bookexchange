package com.example.demo.web;



import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.book.Book;
import com.example.demo.book.BookService;
import com.example.demo.bookRequest.BookRequestService;
import com.example.demo.user.UserService;
import com.example.demo.user.Users;

@Controller
@RequestMapping("/web")
public class Router{
			private final BookService bookService;
			private final UserService userService;
			private final BookRequestService bookRequestService;
			
	        @Autowired
	        public Router(BookService bookService, UserService userService,BookRequestService bookRequestService) {
	        	this.bookService = bookService;
	        	this.userService = userService;
	        	this.bookRequestService = bookRequestService;
	        }
	        
	        
	        @GetMapping(path = "home")
	        public String viewBooks(Model model,HttpSession session) {
	        	System.out.println("Get called");
	        	Long id = (Long) session.getAttribute("id");
	        	model.addAttribute("user",userService.getUserById(id));
	      	  model.addAttribute("bookList", bookService.getBookByStatus("Available"));
	      	  return "home";
	        }
	        
	        @GetMapping(path = "users")
	        public String viewUsers(Model model) {
	        	model.addAttribute("userList", userService.getUsers());
	        	return "userlist";
	        }
	        
	        @GetMapping(path = "login")
	        public String login() {
	        	//model.addAttribute("userList", userService.getUsers());
	        	return "login";
	        }
	        
	        @GetMapping(path = "signup")
	        public String signup() {
	        	return "register";
	        }
	        
	        @PostMapping(path = "login")
	        public String handleSignUpForm(HttpServletRequest request, HttpSession session,Model model) {
	        	System.out.println("Post called");
	        	String email = request.getParameter("email");
	        	String password = request.getParameter("password");
	        	String name = request.getParameter("name");
	        	String address = request.getParameter("address");
	        	String number = request.getParameter("phoneNumber");
	        	Long phoneNumber = Long.parseLong(number);
	        	int wallet = 0;
	        	boolean isAdmin = false;
	        	Users user = new Users(name,phoneNumber,email,address,password,isAdmin,wallet);
	        	userService.addNewUser(user);
	        	return "login";
	        }
	        
	        @GetMapping(path = "signout")
	        public String signOut(HttpSession session) {
	        	session.invalidate();
	        	return "login";
	        }
	        
	        
	        
	        @PostMapping(path = "home")
	        	public String handleForm(HttpServletRequest request, HttpSession session,Model model) {
	        	System.out.println("Post called");
	        	String email = request.getParameter("email");
	        	String password = request.getParameter("password");
	        	System.out.println(email+ " "+ password);
	        	Users user = userService.signIn(email, password);
	        	session.setAttribute("email", email);
	        	session.setAttribute("id", user.getId());
	        	model.addAttribute("user", user);
	        	model.addAttribute("bookList", bookService.getBooks());
	        	return "home";
	        }
	        
	        @GetMapping(path = "mybooks")
	        public String myBooks(HttpSession session,Model model) {
	        	Long id = (Long) session.getAttribute("id");
	        	model.addAttribute("bookList", bookService.getBookByLenderId(id));
	        	return "mybooks";
	        }
	        
	        @GetMapping(path = "addbook")
	        public String addBook(Model model,HttpSession session) {
	        	Long id = (Long) session.getAttribute("id");
	        	model.addAttribute("user",userService.getUserById(id));
	      	  return "addbook";
	        }
	        
	        @PostMapping(path = "addbook")
	        public String handleAddBook(HttpServletRequest request, HttpSession session,Model model) {
	        	String title = request.getParameter("title");
	        	String author = request.getParameter("author");
	        	String description = request.getParameter("description");
	        	Long id = (Long) session.getAttribute("id");
	        	String status = "Available";
	        	//Users user = userService.getUserById(id);
	        	Book book = new Book(title,author,description,status);
	        	bookService.addNewBook(book, id);
	        	model.addAttribute("bookList", bookService.getBookByLenderId(id));
	        	return "mybooks";
	        }
	        
	        @PostMapping(path = "editbook")
	        public String editBook(HttpServletRequest request, HttpSession session,Model model) {
	        	String book = request.getParameter("bookId");
	        	
	        	Long bookId = Long.parseLong(book);
	        	System.out.println(bookId);
	        	model.addAttribute("book", bookService.getBookById(bookId));
	        	return "editbook";
	        }
	        
	        @PostMapping(path = "submiteditbook")
	        public String submitEditBook(HttpServletRequest request, HttpSession session,Model model) {
	        	String book = request.getParameter("bookId");
	        	Long bookId = Long.parseLong(book);
	        	String title = request.getParameter("title");
	        	String author = request.getParameter("author");
	        	String description = request.getParameter("description");
	        	bookService.editBook(bookId, title, author, description);
	        	Long id = (Long) session.getAttribute("id");
	        	System.out.println(bookId);
	        	model.addAttribute("bookList", bookService.getBookByLenderId(id));
	        	return "mybooks";
	        }
	        
	        @PostMapping(path = "deletebook")
	        public String deleteBook(HttpServletRequest request, HttpSession session,Model model) {
	        	String book = request.getParameter("bookId"); 	
	        	Long bookId = Long.parseLong(book);
	        	Long id = (Long) session.getAttribute("id");
	        	bookService.deleteBook(bookId); 
	        	model.addAttribute("bookList", bookService.getBookByLenderId(id));
	        	return "mybooks";
	        }
	        
	        @GetMapping(path = "myprofile")
	        public String myProfile(HttpSession session,Model model) {
	        	Long id = (Long) session.getAttribute("id");
	        	model.addAttribute("user",userService.getUserById(id));
	        	return "myprofile";
	        }
	        
	        @GetMapping(path = "addmoney")
	        public String addMoney(HttpSession session,Model model) {
	        	Long id = (Long) session.getAttribute("id");
	        	model.addAttribute("user",userService.getUserById(id));
	        	return "addmoney";
	        }
	        
	        @PostMapping(path = "addtowallet")
	        public String addToWallet(HttpServletRequest request, HttpSession session,Model model) {
	        	String amountString = request.getParameter("quantity");
	        	int amount = Integer.parseInt(amountString);
	        	Long id = (Long) session.getAttribute("id");
	        	userService.updateWallet(id, amount);
	        	model.addAttribute("user",userService.getUserById(id));
	        	return "myprofile";
	        }
	        
	        @GetMapping(path = "edituser")
	        public String editUserForm( HttpSession session,Model model) {
	        	Long id = (Long) session.getAttribute("id");
	        	model.addAttribute("user",userService.getUserById(id));
	        	return "edituser";
	        }
	        @PostMapping(path = "edituser")
	        public String editUser(HttpServletRequest request,HttpSession session,Model model) {
	        	Long id = (Long) session.getAttribute("id");
	        	String name = request.getParameter("name");
	        	String email = request.getParameter("email");
	        	String phoneNumber = request.getParameter("phoneNumber");
	        	String password = request.getParameter("password");
	        	System.out.println(password+ " "+phoneNumber);
	            userService.updateUser(id, name, email, phoneNumber, password);
	        	model.addAttribute("user",userService.getUserById(id));
	        	return "myprofile";
	        }
	             
	        @GetMapping(path = "myrequests")
	        public String getMyRequests(HttpSession session,Model model) {
	        	Long id = (Long) session.getAttribute("id");
	        	model.addAttribute("requestListBorrow",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLend",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLendAccepted",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Accepted"));
	        	model.addAttribute("requestListBorrowAccepted",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Accepted"));
	        	model.addAttribute("extensionRequestsBorrower",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Extension"));
	        	model.addAttribute("extensionRequestsLender",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Extension"));
	        	model.addAttribute("user", userService.getUserById(id));
	        	return "myrequests";
	        }
	        
	        @Transactional
	        @PostMapping(path = "requestbook")
	        public String generateBookRequest(HttpServletRequest request,HttpSession session,Model model) {
	        	String book = request.getParameter("book");
	        	Long bookId = Long.parseLong(book);
	        	String borrower = request.getParameter("borrower");
	        	Long borrowerId = Long.parseLong(borrower);
	        	String lender = request.getParameter("lender");
	        	Long lenderId = Long.parseLong(lender);
	        	Long id = (Long) session.getAttribute("id");
	        	int amount = userService.getUserById(id).getWallet();
	        	Users user = userService.getUserById(id);
	        	if(amount<30) {
	        		model.addAttribute("user",user);
		        	return "addmoney";
	        	}
	        	
	        	bookRequestService.generateRequest(bookId, lenderId, borrowerId);
	        	user.setWallet(amount-30);
	        	model.addAttribute("requestListBorrow",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLend",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLendAccepted",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Accepted"));
	        	model.addAttribute("requestListBorrowAccepted",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Accepted"));
	        	model.addAttribute("extensionRequestsBorrower",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Extension"));
	        	model.addAttribute("extensionRequestsLender",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Extension"));
	        	model.addAttribute("user", user);
	        	return "myrequests";
	        }
	        
	        @PostMapping(path = "acceptrequest")
	        public String acceptBookRequest(HttpServletRequest request,HttpSession session,Model model) {
	        	String requestString = request.getParameter("request");
	        	Long requestId = Long.parseLong(requestString);
	        	String lender = request.getParameter("lender");
	        	Long lenderId = Long.parseLong(lender);
	        	Long id = (Long) session.getAttribute("id");
	        	bookRequestService.acceptRequest(requestId, lenderId);
	        	model.addAttribute("requestListBorrow",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLend",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLendAccepted",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Accepted"));
	        	model.addAttribute("requestListBorrowAccepted",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Accepted"));
	        	model.addAttribute("extensionRequestsBorrower",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Extension"));
	        	model.addAttribute("extensionRequestsLender",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Extension"));
	        	model.addAttribute("user", userService.getUserById(id));
	        	return "myrequests";
	        }
	        
	        @Transactional
	        @PostMapping(path = "declinerequest")
	        public String declineBookRequest(HttpServletRequest request,HttpSession session,Model model) {
	        	String requestString = request.getParameter("request");
	        	Long requestId = Long.parseLong(requestString);
	        	String lender = request.getParameter("lender");
	        	Long lenderId = Long.parseLong(lender);
	        	Long id = (Long) session.getAttribute("id");
	        	bookRequestService.rejectRequest(requestId, lenderId);
	        	int amount = bookRequestService.getBookRequestsById(requestId).getBorrower().getWallet();
	        	amount = amount+30;
	        	bookRequestService.getBookRequestsById(requestId).getBorrower().setWallet(amount);
	        	model.addAttribute("requestListBorrow",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLend",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLendAccepted",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Accepted"));
	        	model.addAttribute("requestListBorrowAccepted",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Accepted"));
	        	model.addAttribute("extensionRequestsBorrower",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Extension"));
	        	model.addAttribute("extensionRequestsLender",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Extension"));
	        	model.addAttribute("user", userService.getUserById(id));
	        	return "myrequests";
	        }
	        
	        @PostMapping(path = "extendrequest")
	        public String extendBookRequest(HttpServletRequest request,HttpSession session,Model model) {
	        	String requestString = request.getParameter("request");
	        	Long requestId = Long.parseLong(requestString);
	        	String borrower = request.getParameter("borrower");
	        	Long borrowerId = Long.parseLong(borrower);
	        	Long id = (Long) session.getAttribute("id");
	        	bookRequestService.extendRequest(requestId, borrowerId);
	        	model.addAttribute("requestListBorrow",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLend",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLendAccepted",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Accepted"));
	        	model.addAttribute("requestListBorrowAccepted",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Accepted"));
	        	model.addAttribute("extensionRequestsBorrower",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Extension"));
	        	model.addAttribute("extensionRequestsLender",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Extension"));
	        	model.addAttribute("user", userService.getUserById(id));
	        	return "myrequests";
	        }
	        @PostMapping(path = "acceptextension")
	        public String acceptextendBookRequest(HttpServletRequest request,HttpSession session,Model model) {
	        	String requestString = request.getParameter("request");
	        	Long requestId = Long.parseLong(requestString);
	        	String lender = request.getParameter("lender");
	        	Long lenderId = Long.parseLong(lender);
	        	Long id = (Long) session.getAttribute("id");
	        	bookRequestService.acceptExtension(requestId, lenderId);
	        	model.addAttribute("requestListBorrow",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLend",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLendAccepted",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Accepted"));
	        	model.addAttribute("requestListBorrowAccepted",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Accepted"));
	        	model.addAttribute("extensionRequestsBorrower",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Extension"));
	        	model.addAttribute("extensionRequestsLender",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Extension"));
	        	model.addAttribute("user", userService.getUserById(id));
	        	return "myrequests";
	        }
	        
	        @PostMapping(path = "rejectextension")
	        public String rejectextendBookRequest(HttpServletRequest request,HttpSession session,Model model) {
	        	String requestString = request.getParameter("request");
	        	Long requestId = Long.parseLong(requestString);
	        	String lender = request.getParameter("lender");
	        	Long lenderId = Long.parseLong(lender);
	        	Long id = (Long) session.getAttribute("id");
	        	bookRequestService.rejectExtension(requestId, lenderId);
	        	model.addAttribute("requestListBorrow",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLend",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLendAccepted",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Accepted"));
	        	model.addAttribute("requestListBorrowAccepted",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Accepted"));
	        	model.addAttribute("extensionRequestsBorrower",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Extension"));
	        	model.addAttribute("extensionRequestsLender",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Extension"));
	        	model.addAttribute("user", userService.getUserById(id));
	        	return "myrequests";
	        }
	        
	        @Transactional
	        @PostMapping(path = "returnrequest")
	        public String returnBookRequest(HttpServletRequest request,HttpSession session,Model model) {
	        	String requestString = request.getParameter("request");
	        	Long requestId = Long.parseLong(requestString);
	        	String borrower = request.getParameter("borrower");
	        	Long borrowerId = Long.parseLong(borrower);
	        	Long id = (Long) session.getAttribute("id");
	        	bookRequestService.returnRequest(requestId, borrowerId);
	        	if(bookRequestService.getBookRequestsById(requestId).getReturnDate().after(new Date())) {
	        		int amount = bookRequestService.getBookRequestsById(requestId).getBorrower().getWallet();
		        	amount = amount+30;
		        	bookRequestService.getBookRequestsById(requestId).getBorrower().setWallet(amount);
	        	}
	        	model.addAttribute("requestListBorrow",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLend",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Requested"));
	        	model.addAttribute("requestListLendAccepted",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Accepted"));
	        	model.addAttribute("requestListBorrowAccepted",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Accepted"));
	        	model.addAttribute("extensionRequestsBorrower",bookRequestService.getBookRequestByBorrowerIdAndStatus(id,"Extension"));
	        	model.addAttribute("extensionRequestsLender",bookRequestService.getBookRequestByLenderIdAndStatus(id,"Extension"));
	        	model.addAttribute("user", userService.getUserById(id));
	        	return "myrequests";
	        }
	        
	        @GetMapping(path = "admin")
	        public String adminControls(Model model,HttpSession session) {
	        	Long id = (Long) session.getAttribute("id");
	        	if(!userService.getUserById(id).isAdmin()) {
	        		throw new IllegalStateException(
	    					"This Page Requires Admin Privilages.");
	    		}
	        	
	        	
	          model.addAttribute("admin",userService.getUserById(id));
	          model.addAttribute("userList",userService.getUsers());
	          model.addAttribute("bookList",bookService.getBooks());
	          model.addAttribute("requests",bookRequestService.getBookRequests());
	      	  return "admin";
	        }
	        
	        @PostMapping(path = "edituseradmin")
	        public String editUserAdmin(HttpServletRequest request,HttpSession session,Model model) {
	        	Long id = (Long) session.getAttribute("id");
	        	
	        	if(!userService.getUserById(id).isAdmin()) {
	        		throw new IllegalStateException(
	    					"This Page Requires Admin Privilages.");
	    		}	        	
	        	String userIdText = request.getParameter("userId");
	        	Long userId = Long.parseLong(userIdText);
	        	model.addAttribute("user",userService.getUserById(userId));
	        	return "edituseradmin";
	        }
	        
	        @PostMapping(path = "submitedituseradmin")
	        public String editUserAdminSubmit(HttpServletRequest request,HttpSession session,Model model) {
	        	Long id = (Long) session.getAttribute("id");
	        	if(!userService.getUserById(id).isAdmin()) {
	        		throw new IllegalStateException(
	    					"This Page Requires Admin Privilages.");
	    		}
	        	
	        	String userIdText = request.getParameter("userId");
	        	Long userId = Long.parseLong(userIdText);
	        	String name = request.getParameter("name");
	        	String email = request.getParameter("email");
	        	String phoneNumber = request.getParameter("phoneNumber");
	        	String password = request.getParameter("password");
	        	System.out.println(password+ " "+phoneNumber);
	            userService.updateUser(userId, name, email, phoneNumber, password);
	            model.addAttribute("admin",userService.getUserById(id));
		          model.addAttribute("userList",userService.getUsers());
		          model.addAttribute("bookList",bookService.getBooks());
		          model.addAttribute("requests",bookRequestService.getBookRequests());
	        	return "admin";
	        }
	        
	        @PostMapping(path = "deleteuser")
	        public String deleteUser(HttpServletRequest request,HttpSession session,Model model) {
	        	Long id = (Long) session.getAttribute("id");
	        	if(!userService.getUserById(id).isAdmin()) {
	        		throw new IllegalStateException(
	    					"This Page Requires Admin Privilages.");
	    		}
	        	
	        	System.out.println("inside user delete");
	        	String userIdText = request.getParameter("userId");
	        	Long userId = Long.parseLong(userIdText);
	        	if(userService.getUserById(userId).isAdmin()) {
	        		throw new IllegalStateException(
	    					"Can't Delete Admin");
	    		}
	        	System.out.println(userId);
	            userService.deleteUser(userId);
	            model.addAttribute("admin",userService.getUserById(id));
		          model.addAttribute("userList",userService.getUsers());
		          model.addAttribute("bookList",bookService.getBooks());
		          model.addAttribute("requests",bookRequestService.getBookRequests());
	        	return "admin";
	        }
	        
	        @PostMapping(path = "editbookadmin")
	        public String editBookAdmin(HttpServletRequest request, HttpSession session,Model model) {
	        	Long id = (Long) session.getAttribute("id");
	        	if(!userService.getUserById(id).isAdmin()) {
	        		throw new IllegalStateException(
	    					"This Page Requires Admin Privilages.");
	    		}
	        	String book = request.getParameter("bookId");
	        	
	        	Long bookId = Long.parseLong(book);
	        	System.out.println(bookId);
	        	model.addAttribute("book", bookService.getBookById(bookId));
	        	return "editbookadmin";
	        }
	        
	        @PostMapping(path = "submiteditbookadmin")
	        public String submitEditBookAdmin(HttpServletRequest request, HttpSession session,Model model) {
	        	Long id = (Long) session.getAttribute("id");
	        	if(!userService.getUserById(id).isAdmin()) {
	        		throw new IllegalStateException(
	    					"This Page Requires Admin Privilages.");
	    		}
	        	String book = request.getParameter("bookId");
	        	Long bookId = Long.parseLong(book);
	        	String title = request.getParameter("title");
	        	String author = request.getParameter("author");
	        	String description = request.getParameter("description");
	        	bookService.editBook(bookId, title, author, description);
	        	System.out.println(bookId);
	        	model.addAttribute("admin",userService.getUserById(id));
		          model.addAttribute("userList",userService.getUsers());
		          model.addAttribute("bookList",bookService.getBooks());
		          model.addAttribute("requests",bookRequestService.getBookRequests());
	        	return "admin";
	        }
	        @PostMapping(path = "deletebookadmin")
	        public String deleteBookAdmin(HttpServletRequest request, HttpSession session,Model model) {
	        	Long id = (Long) session.getAttribute("id");
	        	if(!userService.getUserById(id).isAdmin()) {
	        		throw new IllegalStateException(
	    					"This Page Requires Admin Privilages.");
	    		}
	        	String book = request.getParameter("bookId"); 	
	        	Long bookId = Long.parseLong(book);
	        	bookService.deleteBook(bookId); 
	        	model.addAttribute("admin",userService.getUserById(id));
		          model.addAttribute("userList",userService.getUsers());
		          model.addAttribute("bookList",bookService.getBooks());
		          model.addAttribute("requests",bookRequestService.getBookRequests());
	        	return "admin";
	        }
	        
	        @PostMapping(path = "userreport")
	        public String userReport(HttpServletRequest request,Model model,HttpSession session) {
	        	Long id = (Long) session.getAttribute("id");
	        	if(!userService.getUserById(id).isAdmin()) {
	        		throw new IllegalStateException(
	    					"This Page Requires Admin Privilages.");
	    		}
	        	String userIdText = request.getParameter("userId");
	        	Long userId = Long.parseLong(userIdText);
	        	
	          model.addAttribute("admin",userService.getUserById(id));
	          model.addAttribute("user",userService.getUserById(userId));
	          model.addAttribute("bookList",bookService.getBookByLenderId(userId));
	          model.addAttribute("requests",bookRequestService.getBookRequestByBorrowerId(userId));
	          model.addAttribute("requestLent",bookRequestService.getBookRequestByLenderId(userId));
	      	  return "userreport";
	        }
	        
	        
}

