/**
 * 
 */
package service;

import java.util.List;
import java.util.Optional;

import models.User;

import org.bson.types.ObjectId;

import service.impl.UserServiceImpl;

import com.google.inject.ImplementedBy;

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */

@ImplementedBy(UserServiceImpl.class)
public interface UserService {

	public ObjectId saveNewUser(User user);
	public List<User> findAllUsers();
	public Optional<User> findById(ObjectId id);
	public List<User> findAllUsers(int page, int pageSize);
	public Boolean removeById(ObjectId id);
	public long count();
}
