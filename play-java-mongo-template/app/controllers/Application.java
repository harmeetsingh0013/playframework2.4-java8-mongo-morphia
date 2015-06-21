package controllers;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.bson.types.ObjectId;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import service.UserService;

@Singleton
public class Application extends Controller {

	@Inject
	private UserService userService;
	
    public Result index() {
    	User user = new User();
    	user.setName("James");
    	ObjectId id = new ObjectId("55865c7263a09e1076a206e8");
    	//userService.saveNewUser(user);
    	//return ok(Boolean.valueOf(userService.removeById(id)).toString());
    	//return ok(Integer.valueOf(userService.findAllUsers(2, 10).size()).toString());
    	return ok(Long.valueOf(userService.count()).toString());
    }

}
