package com.Zopa.engines;

import com.Zopa.utilities.model.dto.PostMemberAddress;
import com.Zopa.utilities.model.dto.PostMemberRequest;
import com.Zopa.utilities.model.dto.PostQuoteRequest;
import com.Zopa.utilities.TestDataReader;
import com.Zopa.utilities.TestUserUtil;
import com.Zopa.utilities.model.dto.User;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;

import static com.Zopa.utilities.ApiUtils.buildPostMemberRequest;
import static io.restassured.RestAssured.given;

public class QuotationEngine extends Base {

    public static Response getExistingMember(String memberId){

        Response response = given()
                .baseUri(TestDataReader.get("quotationEngineUri"))
                .basePath("/api/member")
                .queryParam("memberId", memberId)
                .log().all()
                .request(Method.GET).prettyPeek();

        return response;
    }

    public static Response createNewMember(){
        User user = TestUserUtil.createUser();
        PostMemberRequest postMemberRequest = buildPostMemberRequest(user);

        Response response = given()
                .baseUri(TestDataReader.get("quotationEngineUri"))
                .basePath("/api/member")
                .contentType(ContentType.JSON)
                .body(postMemberRequest)
                .log().all()
                .request(Method.POST).prettyPeek();

        return response;
    }

    public static Response createNewMember(String firstname, String lastname, String firstLine, String town, String postCode, String email, String jobTitle){
        User user = TestUserUtil.createUser();

        PostMemberRequest postMemberRequest = PostMemberRequest.builder()
                .firstName(firstname)
                .lastName(lastname)
                .address(PostMemberAddress.builder()
                        .firstLine(firstLine)
                        .town(town)
                        .postCode(postCode)
                        .build())
                .emailAddress(email)
                .jobTitle(jobTitle)
                .build();

        Response response = given()
                .baseUri(TestDataReader.get("quotationEngineUri"))
                .basePath("/api/member")
                .contentType(ContentType.JSON)
                .body(postMemberRequest)
                .log().all()
                .request(Method.POST).prettyPeek();

        return response;
    }

    public static Response createNewQuote(String memberId, int salary, int amount, int term){

        PostQuoteRequest postQuoteRequest = PostQuoteRequest.builder()
                .currentSalary(salary)
                .amountToBorrow(amount)
                .termLength(term)
                .build();

        Response response = given()
                .baseUri(TestDataReader.get("quotationEngineUri"))
                .basePath("/api/quote")
                .contentType(ContentType.JSON)
                .queryParam("memberId", memberId)
                .body(postQuoteRequest)
                .log().all()
                .request(Method.POST).prettyPeek();

        return response;
    }

}
