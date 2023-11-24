package ApiRequests;

import Endpoints.API;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.OrderRequest;

import static ApiRequests.BaseApi.getSpecification;
import static ApiRequests.BaseApi.getAuth;
import static io.restassured.RestAssured.given;

public class Order {
    @Step("getting ingredients")
    public static ValidatableResponse getIngredients() {
        return given()
                .spec(getSpecification())
                .when()
                .get(API.INGREDIENTS)
                .then();
    }

    @Step("new order with authorization")
    public static ValidatableResponse createOrderWithAuth(String token, OrderRequest order) {
        return given()
                .spec(getAuth(token))
                .body(order)
                .when()
                .post(API.CREATE_ORDER)
                .then();
    }

    @Step("new order without authorization")
    public static ValidatableResponse createOrderWithoutAuth(OrderRequest order) {
        return given()
                .spec(getSpecification())
                .body(order)
                .when()
                .post(API.CREATE_ORDER)
                .then();
    }

    @Step("data about the order of a specific user with authorization")
    public static ValidatableResponse getOrderDataWithAuth(String token) {
        return given()
                .spec(getAuth(token))
                .when()
                .get(API.CREATE_ORDER)
                .then();
    }

    @Step("data about a specific user's order without authorization")
    public static ValidatableResponse getOrderDataWithoutAuth() {
        return given()
                .spec(getSpecification())
                .when()
                .get(API.CREATE_ORDER)
                .then();
    }
}