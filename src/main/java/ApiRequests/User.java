package ApiRequests;

import Endpoints.API;
import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import model.CreateUserRequest;
import model.UserLoginRequest;

import static ApiRequests.BaseApi.getSpecification;
import static ApiRequests.BaseApi.getAuth;
import static io.restassured.RestAssured.given;

public class User {
    @Step("registration")
    public static ValidatableResponse registrationUser(CreateUserRequest user) {
        return given()
                .spec(getSpecification())
                .body(user)
                .when()
                .post(API.CREATE_USER)
                .then();
    }

    @Step("authorization")
    public static ValidatableResponse authorizationUser(CreateUserRequest user) {
        return given()
                .spec(getSpecification())
                .body(user)
                .when()
                .post(API.LOGIN_USER)
                .then();
    }

    @Step("user delete")
    public static ValidatableResponse deleteUser(CreateUserRequest user, String token) {
        return given()
                .spec(getAuth(token))
                .body(user)
                .when()
                .delete(API.USER)
                .then();
    }

    @Step("change user with authorization")
    public static ValidatableResponse changeUserWithAuth(UserLoginRequest user, String token) {
        return given()
                .spec(getAuth(token))
                .body(user)
                .when()
                .patch(API.USER)
                .then();
    }

    @Step("change user without authorization")
    public static ValidatableResponse changeUserWithoutAuth(UserLoginRequest user) {
        return given()
                .spec(getSpecification())
                .body(user)
                .when()
                .patch(API.USER)
                .then();
    }
}