 package com.example.demo.user;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.book.Book;
import com.example.demo.book.BookService;

@Service
public class UserService {
	
    private final UserRepository userRepository;
    
    private final BookService bookService;
    @Autowired
    public UserService(UserRepository userRepository,BookService bookService) {
    	this.userRepository = userRepository;
    	this.bookService = bookService;
    }
     
	public List<Users> getUsers()
	{return userRepository.findAll();}

	public void addNewUser(Users user) {
		Optional <Users> userOptional = userRepository.findUserByEmail(user.getEmail());
		if(userOptional.isPresent()) {
			throw new IllegalStateException("email taken"); 
		}
		userRepository.save(user);
		
	}

	public void deleteUser(Long userId) {
		boolean exists = userRepository.existsById(userId);
		if(!exists) {
			throw new IllegalStateException(
					"user with id "+ userId+ "does not exist");
		}
		
		List<Book> books = bookService.getBookByLenderId(userId);
		for(Book book: books) {
			System.out.println("Deleting Book");
			bookService.deleteBook(book.getId());
			System.out.println("Deleted Book");
		}
		userRepository.deleteById(userId); 
	}

	@Transactional
	public void updateUser(Long userId, String name, String email, String phoneNumber, String password) {
	     Users user = userRepository.findById(userId).orElseThrow(
	    		 ()-> new IllegalStateException(
	    				 "User with id "+ userId+" does not exit" ));
	     if(name!=null&&name.length()>0 && !Objects.equals(user.getName(),name)) {
	    	 user.setName(name);
	     }
	     if(email!=null&&email.length()>0 && !Objects.equals(user.getEmail(),email)) {
	    	 Optional <Users> userOptional = userRepository.findUserByEmail(email);
	 		if(userOptional.isPresent()) {
	 			throw new IllegalStateException("email taken"); 
	 		}
	    	 user.setEmail(email);
	     }
	     if(phoneNumber!=null&&phoneNumber.length()>0 && !Objects.equals(user.getPhoneNumber(),phoneNumber)) {
	    	 user.setPhoneNumber(Long.parseLong(phoneNumber));
	     }
	     if(password!=null&&password.length()>0 && !Objects.equals(user.getPassword(),password)) {
	    	 user.setPassword(password);
	     }
	}

	public Users getUserById(Long userId) {
		Users user = userRepository.findById(userId).orElseThrow(
	    		 ()-> new IllegalStateException(
	    				 "User with id "+ userId+" does not exit" ));
		return user;
	}

	@Transactional
	public void updateWallet(Long userId, int amount) {
		Users user = userRepository.findById(userId).orElseThrow(
	    		 ()-> new IllegalStateException(
	    				 "User with id "+ userId+" does not exit" ));
		int wallet_amount = user.getWallet();
		wallet_amount+=amount;
		user.setWallet(wallet_amount);	
	}
	
	public Users signIn(String email, String password) {
		Users user = userRepository.findAndReturnUserByEmail(email);
		
		if (user==null) {
			throw(new IllegalStateException(
					"User with email "+ email +" does not exist"
					));
		}
		System.out.println(email+user.getEmail()+ password+user.getPassword());
		if(user.getPassword().equals(password)) {
			return user;
		}
		throw(new IllegalStateException(
				"Invalid Credentials"
				));
	}
	
	
}


