/**
 * 
 */
package repository;

import repository.impl.UserRepoImpl;

import com.google.inject.ImplementedBy;

import models.User;

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */
@ImplementedBy(UserRepoImpl.class)
public interface UserRepo extends GenericRepo<User>{

}
