package user;

import ApiRequests.User;
import io.restassured.response.ValidatableResponse;
import model.CreateUserRequest;
import model.UserLoginRequest;
import org.junit.After;
import org.junit.Before;

public class testBaseUserTest {

    protected CreateUserRequest createUserRequest;
    protected UserLoginRequest userLoginRequest;
    protected CreateUserRequest user;
    protected String token;
    protected ValidatableResponse responseUpdate;
    protected ValidatableResponse responseTwice;
    protected ValidatableResponse response;
    protected ValidatableResponse login;


    @Before
    public void setup() {
        user = CreateUserRequest.getRandomUser();
    }

    @After
    public void cleanUp() {
        if (token == null) return;
        User.deleteUser(user, token);
    }

    @Before
    public void setUp() {
        createUserRequest = CreateUserRequest.getRandomUser();
    }

    @After
    public void tearDown() {
        if (token == null) return;
        User.deleteUser(createUserRequest, token);
    }
}
