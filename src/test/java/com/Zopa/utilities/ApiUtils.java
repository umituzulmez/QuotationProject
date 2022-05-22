package com.Zopa.utilities;

import com.Zopa.utilities.model.dto.PostMemberAddress;
import com.Zopa.utilities.model.dto.PostMemberRequest;
import com.Zopa.utilities.model.dto.User;

import static io.restassured.RestAssured.given;

public class ApiUtils {

    // generates token for the project in case we need

    /*
    public static String generateToken(String email,String password){

        Response response = given().accept(ContentType.JSON)
                .queryParam("email", email)
                .queryParam("password", password)
                .when().get(TestDataReader.get("quotationEngineUri"));

        String token = response.path("accessToken");

        return token;
    }
    */

    public static PostMemberRequest buildPostMemberRequest(User user) {
        return PostMemberRequest.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .emailAddress(user.getEmail())
                .address(PostMemberAddress.builder()
                        .firstLine(user.getFirstLine())
                        .town(user.getTown())
                        .postCode(user.getPostCode())
                        .build())
                .jobTitle(user.getJobTitle())
                .build();
    }
}
