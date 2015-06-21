/**
 * 
 */
package repository.impl;


import com.google.inject.Singleton;

import repository.UserRepo;
import models.User;

/**
 * @author Harmeet Singh(Taara)
 * @version 1.0
 */

@Singleton
public class UserRepoImpl extends GenericRepoImpl<User> implements UserRepo{

}
