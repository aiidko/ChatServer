import UserService.implementation.User;
import UserService.interfaces.IUserView;
import UserService.implementation.UserService;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

public class TestUserService {

    public UserService userService;
    @Before
    public void initUsers(){
        userService = new UserService();
        userService.addNewUser(1);
        userService.addNewUser(2);
        userService.isClientIdentification(userService.getUser(2),"test");
    }

    @Test
    public void TestIsClientIdentification(){
        User user = new User(1, "test");
        assertEquals(true, userService.isClientIdentification(user, "newName"));
        assertEquals(false, userService.isClientIdentification(user, "newName"));
    }

    @Test
    public void TestGetUsersName(){
        assertEquals("test",userService.getUsersName().get(0));
    }

    @Test
    public void TestGetUser(){
        IUserView user1 = userService.getUser(1);
        assertNotNull(user1);
        IUserView user2 = userService.getUser(3);
        assertNull(user2);
    }

    @Test
    public void TestAddNewUser(){
        int beforeAdd = userService.getUsersOnServer().size();
        userService.addNewUser(4);
        int afterAdd = userService.getUsersOnServer().size();
        assertEquals(beforeAdd+1,afterAdd );
    }

    @Test
    public void TestRemoveUser(){
        IUserView user = userService.getUser(2);
        int beforeAdd = userService.getUsersOnServer().size();
        userService.removeUser(user);
        int afterAdd = userService.getUsersOnServer().size();
        assertEquals(beforeAdd-1,afterAdd );
        assertEquals(false,userService.getUsersName().contains(user.getName()));
    }

}
