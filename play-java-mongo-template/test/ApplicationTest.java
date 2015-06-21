import static org.junit.Assert.assertEquals;

import java.io.File;

import models.User;

import org.junit.Test;

import play.Application;
import play.Environment;
import play.Mode;
import play.inject.guice.GuiceApplicationBuilder;
import service.impl.UserServiceImpl;


/**
*
* Simple (JUnit) tests that can call all parts of a play app.
* If you are interested in mocking a whole application, see the wiki for more details.
*
*/
public class ApplicationTest {

	Application application = new GuiceApplicationBuilder()
    .in(new Environment(new File("/home/james/play-java-mongo-template"), this.getClass().getClassLoader(), Mode.TEST))
    .build();
	
    @Test
    public void simpleCheck() {
        int a = 1 + 1;
        assertEquals(2, a);
    }

    @Test
    public void renderTemplate() {
    	UserServiceImpl serviceImpl = new UserServiceImpl();
    	User user = new User();
    	user.setName("James");
    	serviceImpl.saveNewUser(user);
    }


}
