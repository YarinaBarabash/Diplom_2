package model;


import com.github.javafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;


public class CreateUserRequest {

    public static Faker faker = new Faker();
    private String email;
    private String password;
    private String name;

    public CreateUserRequest(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public CreateUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static CreateUserRequest getRandomUser() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String name = faker.name().firstName();
        return new CreateUserRequest(email, password, name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}