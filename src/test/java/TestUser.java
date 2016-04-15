import UserService.implementation.User;

import org.junit.Test;
import static junit.framework.Assert.assertEquals;

public class TestUser {

    @Test
    public void TestSetName(){
        User user= new User(123,"test");
        user.setName("new name");
        assertEquals("new name",user.getName());
    }

    @Test
    public void TestGetName(){
        User user= new User(123,"test");
        assertEquals("test", user.getName());
    }

    @Test
    public void TestGetUserId(){
        User user= new User(123,"test");
        assertEquals((Integer)123, user.getUserId());
    }


}
