package model;


import com.github.javafaker.Faker;



public class UserLoginRequest {
    public static Faker faker = new Faker();

    private String email;
    private String name;

    public UserLoginRequest(String email) {
        this.email = email;
    }

    public UserLoginRequest(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public static UserLoginRequest getRandomUser() {
        String email = faker.internet().emailAddress();
        String name = faker.name().firstName();
        return new UserLoginRequest(email, name);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}