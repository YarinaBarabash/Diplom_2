package ApiRequests;

import Endpoints.API;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseApi {
    public static RequestSpecification getSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(API.BASE_URL)
                .setContentType(ContentType.JSON)
                .build()
                .given();
    }

    public static RequestSpecification getAuth(String token) {
        return new RequestSpecBuilder()
                .setBaseUri(API.BASE_URL)
                .setContentType(ContentType.JSON)
                .addHeader("Authorization", token)
                .build()
                .given();
    }
}