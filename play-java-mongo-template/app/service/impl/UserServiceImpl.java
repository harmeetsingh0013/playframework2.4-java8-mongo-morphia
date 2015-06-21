/**
 * 
 */
package service.impl;

import java.util.List;
import java.util.Optional;

import models.User;

import org.bson.types.ObjectId;

import repository.UserRepo;
import service.UserService;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */

@Singleton
public class UserServiceImpl implements UserService{

	@Inject
	private UserRepo userRepo;

	@Override
	public ObjectId saveNewUser(User user) {
		return userRepo.save(user).get();
	}

	@Override
	public List<User> findAllUsers() {
		return userRepo.findAll().get();
	}

	@Override
	public Optional<User> findById(ObjectId id) {
		return userRepo.findById(id);
	}

	@Override
	public List<User> findAllUsers(int page, int pageSize) {
		return userRepo.findAll(page, pageSize).get();
	}
	
	public Boolean removeById(ObjectId id){
		return userRepo.removeById(id).get();
	}

	@Override
	public long count() {
		return userRepo.count().get();
	}
}
