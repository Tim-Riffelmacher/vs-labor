package hka.vertsys.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hka.vertsys.user.data.Role;
import hka.vertsys.user.data.RoleRepository;
import hka.vertsys.user.data.User;
import hka.vertsys.user.data.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RoleRepository roleRepo;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody() User userToAdd) {
		this.userRepo.save(userToAdd);
		return new ResponseEntity<>("registered user", HttpStatus.OK);
	}

	@GetMapping("/byName/{username}")
	public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username) {
		return new ResponseEntity<>(this.userRepo.findByUsername(username), HttpStatus.OK);
	}

	@GetMapping("/deleteById/{userId}")
	public ResponseEntity<Boolean> deleteUserById(@PathVariable("userId") Integer userId) {
		this.userRepo.deleteById(userId);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}

	@GetMapping("/role/{level}")
	public ResponseEntity<Role> getRoleByLevel(@PathVariable("level") Integer level) {
		return new ResponseEntity<>(this.roleRepo.findByLevel(level), HttpStatus.OK);
	}

	@GetMapping("/exists/{id}")
	public ResponseEntity<Boolean> doesUserAlreadyExist(@PathVariable("id") Integer userId) {
		return new ResponseEntity<>(this.userRepo.existsById(userId), HttpStatus.OK);
	}

}
