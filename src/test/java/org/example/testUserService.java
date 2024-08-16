package org.example;

import org.junit.Before;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;



@DisplayName("Testing for user service class")
public class testUserService {

    @Mock
    UserService userService = new UserService();
    User newUser = new User("notDuck","password","test@test.com");
    User newUser2 = new User(null,null,null);
    //test to see if edge case is working
//    User newUser = new User("","","");

    @BeforeAll
    static void beforeAll(){
        System.out.println("Before all user service tests");

    }

    @AfterAll
    static void afterAll() {
        System.out.println("After all user service tests");
    }


    public void setUp(){

    }

    @Test
    void testUserRegisterTrue(){
        assertTrue(userService.registerUser(newUser));
    }

    @Test
    void testUserRegisterFalse(){
        assertFalse(!userService.registerUser(newUser));
    }

    @Test
    void testUserRegisterEdge(){
        if (newUser.getUsername().isEmpty() || newUser.getPassword().isEmpty() || newUser.getEmail().isEmpty()) {
            assertFalse(userService.registerUser(newUser));
        }else{
            assertTrue(userService.registerUser(newUser));
        }
    }

    @Test
    void testUserLoginTrue(){
        assertNotNull(newUser.getUsername(),newUser.getPassword());
        userService.loginUser(newUser.getUsername(),newUser.getPassword());
    }

    @Test
    void testUserLoginFalse(){
        assertNull(newUser2.getUsername());
    }

    @Test
    void testUserLoginEdge(){
        if(newUser2.getUsername()==null || newUser2.getPassword()==null) {
            assertNull(newUser2.getUsername(),newUser2.getPassword());
        }else {
            userService.loginUser(newUser2.getUsername(),newUser2.getPassword());
        }
    }

}
