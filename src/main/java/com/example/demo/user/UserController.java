package com.example.demo.user;

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
@RequestMapping(path = "users")
public class UserController {
	private final UserService userService;
    @Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	@GetMapping
	public List<Users> getUsers(){
		return userService.getUsers();
}
	@GetMapping(path = "{userId}")
	public Users getUserbyId(@PathVariable("userId") Long userId){
		return userService.getUserById(userId);
	}
	
	@PostMapping
	public void registerNewUser(@RequestBody  Users user) {
		userService.addNewUser(user);
	}
	
	@DeleteMapping(path = "{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		userService.deleteUser(userId);
	}
	
	@PutMapping(path = "{userId}")
	public void updateUser(
			@PathVariable("userId") Long userId,
			@RequestParam(required = false)String name,
			@RequestParam(required = false) String email,
	@RequestParam(required = false) String phoneNumber,@RequestParam(required = false) String password)  {
		userService.updateUser(userId,name,email,phoneNumber,password);
	}
	
	@PutMapping(path = "wallet/{userId}/{amount}")
	public void updateWallet(
			@PathVariable("userId") Long userId,
			@PathVariable("amount") int amount
			) {
		userService.updateWallet(userId,amount);
	}
	

}
