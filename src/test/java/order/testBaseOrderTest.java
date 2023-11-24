package order;

import ApiRequests.User;
import io.restassured.response.ValidatableResponse;
import model.CreateUserRequest;
import model.OrderRequest;
import org.junit.After;
import org.junit.Before;

public class testBaseOrderTest {

    protected CreateUserRequest createUserRequest;
    protected CreateUserRequest user;
    protected OrderRequest order;
    protected String token;
    protected ValidatableResponse validatableResponse;
    protected ValidatableResponse response;


    @Before
    public void setUp() {
        createUserRequest = CreateUserRequest.getRandomUser();
        user = CreateUserRequest.getRandomUser();
        order = new OrderRequest();
    }

    @After
    public void cleanUp() {
        if (token == null) return;
        User.deleteUser(createUserRequest, token);
        if (token == null) return;
        User.deleteUser(user, token);
    }
}
