package user;

import ApiRequests.User;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.http.HttpStatus;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class testLoginUserTest extends testBaseUserTest {

    private static final String DATA_AUTH = "dmjspjdp";

    @Test
    @DisplayName("Логин юзера с валидными данными")
    @Description("Проверяем, что юзера можно зарегистрировать")
    public void loginTest() {
        response = User.registrationUser(user);
        token = response.extract().path("accessToken");
        login = User.authorizationUser(user);
        login
                .statusCode(HttpStatus.SC_OK)
                .assertThat()
                .body("success", equalTo(true))
                .and()
                .body("accessToken", is(notNullValue()));
    }

    @Test
    @DisplayName("Логин юзера с некорректным логином")
    @Description("Проверяем, что нельзя залогиниться с некорректным логином")
    public void loginWithIncorrectLoginFieldTest() {
        response = User.registrationUser(user);
        token = response.extract().path("accessToken");
        user.setEmail(user.getEmail() + DATA_AUTH);
        login = User.authorizationUser(user);
        login
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("email or password are incorrect"));
    }

    @Test
    @DisplayName("Логин с некорректным паролем")
    @Description("Проверяем, что нельзя залогиниться с некорректным парлем")
    public void loginWithIncorrectPasswordFieldTest() {
        response = User.registrationUser(user);
        token = response.extract().path("accessToken");
        user.setPassword(user.getPassword() + DATA_AUTH);
        login = User.authorizationUser(user);
        login
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .assertThat()
                .body("success", equalTo(false))
                .and()
                .body("message", equalTo("email or password are incorrect"));
    }
}