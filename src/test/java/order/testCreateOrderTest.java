package order;

import ApiRequests.Order;
import ApiRequests.User;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import model.OrderRequest;
import org.apache.http.HttpStatus;
import org.junit.Test;


import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class testCreateOrderTest extends testBaseOrderTest {

    @Test
    @DisplayName("Создание заказа с авторизацией")
    public void orderIngredientsAuthorizationTest() {
        order = new OrderRequest(ingredientList());
        response = User.registrationUser(createUserRequest);
        token = response.extract().path("accessToken");
        validatableResponse = Order.createOrderWithAuth(token, order);
        validatableResponse
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("creating an order with ingredients and without authorization /api/orders")
    public void orderIngredientsWithoutAuthorizationTest() {
        order = new OrderRequest(ingredientList());
        validatableResponse = Order.createOrderWithoutAuth(order);
        validatableResponse
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("success", equalTo(true));
    }

    @Test
    @DisplayName("заказ без ингридентов")
    public void orderWithoutIngredientsAuthorizationTest() {
        response = User.registrationUser(createUserRequest);
        token = response.extract().path("accessToken");
        validatableResponse = Order.createOrderWithAuth(token, order);
        validatableResponse
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("Ingredient ids must be provided"));
    }

    @Test
    @DisplayName("создание заказа с плохих хэшем")
    public void orderWithBadIngredientHashTest() {
        order = new OrderRequest(badIngredientList());
        response = User.registrationUser(createUserRequest);
        token = response.extract().path("accessToken");
        validatableResponse = Order.createOrderWithAuth(token, order);
        validatableResponse
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Step("получение списка ингредиентов")
    protected List<String> ingredientList() {
        response = Order.getIngredients();
        List<String> list = response.extract().path("data._id");
        List<String> ingredients = List.of(list.get(0), list.get(2), list.get(4), list.get(0));
        return ingredients;
    }

    @Step("Получение плохого списка ингредиентов")
    protected List<String> badIngredientList() {
        response = Order.getIngredients();
        List<String> list = response.extract().path("data._id");
        List<String> ingredients = List.of(list.get(0), list.get(2).repeat(2), list.get(4).repeat(2), list.get(0));
        return ingredients;
    }
}