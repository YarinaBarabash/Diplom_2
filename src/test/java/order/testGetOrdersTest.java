package order;

import ApiRequests.Order;
import ApiRequests.User;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class testGetOrdersTest extends testBaseOrderTest {


    @Test
    @DisplayName("list orders with authorization /api/orders")
    public void ordersWithAuthorizationTest() {
        response = User.registrationUser(user);
        token = response.extract().path("accessToken");
        Order.createOrderWithAuth(token, order);
        ValidatableResponse responseReceivingOrder = Order.getOrderDataWithAuth(token);
        responseReceivingOrder
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("list orders without authorization /api/orders")
    public void ordersWithoutAuthorizationTest() {
        response = Order.getOrderDataWithoutAuth();
        response
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("You should be authorised"));
    }
}