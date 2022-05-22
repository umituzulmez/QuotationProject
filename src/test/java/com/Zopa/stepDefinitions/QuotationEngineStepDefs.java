package com.Zopa.stepDefinitions;

import com.Zopa.engines.QuotationEngine;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

public class QuotationEngineStepDefs {

    private String firstName;
    private String lastName;
    private String firstLine;
    private String town;
    private String postCode;
    private String email;
    private String jobTitle;
    private Response createMemberResponse;
    private Response getExistingMemberResponse;
    private Response createQuoteResponse;
    private Response response;
    private String memberId;
    private String quoteId;
    private int currentSalary;
    private int amountToBorrow;
    private int termLength;
    private float interestRate;

    @Given("Customer has following details")
    public void customer_has_following_details(Map<String,String> userData) {
        firstName = userData.get("firstname");
        lastName = userData.get("lastname");
        firstLine = userData.get("firstLine");
        town = userData.get("town");
        postCode = userData.get("postCode");
        email = userData.get("email");
        jobTitle = userData.get("jobTitle");
    }

    @When("Customer makes a request to be a qanat member")
    public void customer_makes_a_request_to_be_a_qanat_member() {
       createMemberResponse = QuotationEngine.createNewMember(firstName, lastName, firstLine, town, postCode, email, jobTitle);
       response = createMemberResponse;
    }

    @Then("Customer should get response with {int} status code")
    public void customer_should_get_response_with_status_code(int statusCode) {
        Assert.assertEquals(statusCode, response.statusCode());
    }

    @Then("New Qanat Member should be created")
    public void new_Qanat_Member_should_be_created() {
        memberId = createMemberResponse.body().path("memberId");
        Assert.assertNotNull(memberId);
    }

    @When("Existing member details are retrieved")
    public void existing_member_details_are_retrieved() {
        getExistingMemberResponse = QuotationEngine.getExistingMember(memberId);
    }

    @Then("Customer details should be matching")
    public void customer_details_should_be_matching() {
        String qanatMemberId = createMemberResponse.body().path("memberId");
        String qanatMemberFullname = createMemberResponse.body().path("memberFullName");
        Object qanatMemberAddress = createMemberResponse.body().path("address");
        String qanatMemberEmail = createMemberResponse.body().path("emailAddress");
        String qanatMemberJobTitle = createMemberResponse.body().path("jobTitle");

        String existingMemberId = getExistingMemberResponse.body().path("memberId");
        String existingMemberFullname = getExistingMemberResponse.body().path("memberFullName");
        Object existingMemberAddress = getExistingMemberResponse.body().path("address");
        String existingMemberEmail = getExistingMemberResponse.body().path("emailAddress");
        String existingMemberJobTitle = getExistingMemberResponse.body().path("jobTitle");

        Assert.assertEquals(qanatMemberId, existingMemberId);
        Assert.assertEquals(qanatMemberFullname, existingMemberFullname);
        Assert.assertEquals(qanatMemberAddress, existingMemberAddress);
        Assert.assertEquals(qanatMemberEmail, existingMemberEmail);
        Assert.assertEquals(qanatMemberJobTitle, existingMemberJobTitle);
    }

    @Given("Customer is registered as a new qanat member")
    public void customer_is_registered_as_a_new_qanat_member() throws InterruptedException {
        createMemberResponse = QuotationEngine.createNewMember();
        memberId = createMemberResponse.body().path("memberId");

        Thread.sleep(1000);
        Assert.assertEquals(201, createMemberResponse.statusCode());
    }

    @When("Qanat member with {int} current salary requested {int} loan for {int} term length")
    public void qanat_member_with_current_salary_requested_loan_for_term_length(int salary, int requestedLoan, int term) {
        currentSalary = salary;
        amountToBorrow = requestedLoan;
        termLength = term;
        createQuoteResponse = QuotationEngine.createNewQuote(memberId, currentSalary, amountToBorrow, termLength);
        response = createQuoteResponse;
    }

    @Then("Quote request should be accepted with a quoteId")
    public void quote_request_should_be_accepted_with_a_quoteId() throws InterruptedException {
        Thread.sleep(1000);
        quoteId = createQuoteResponse.body().path("quoteId");

        Assert.assertNotNull(quoteId);
    }

    @Then("Quote decision should be returned with an interest rate")
    public void quote_decision_should_be_returned_with_an_interest_rate() {
        interestRate = createQuoteResponse.body().path("interestRate");

        Assert.assertNotNull(interestRate);
    }

    @Then("Loan amount should be matching with requested amount")
    public void loan_amount_should_be_matching_with_requested_amount() {
        int loanAmount = createQuoteResponse.body().path("amountToBorrow");

        Assert.assertEquals(amountToBorrow, loanAmount);
    }

    @Then("Request should be rejected with following message")
    public void request_should_be_rejected_with_following_message(String message) {
        String expectedMessage = message;
        String actualMessage = response.body().path("message");

        Assert.assertEquals(expectedMessage,actualMessage);
    }

    @Given("Customer is not registered as a new qanat member")
    public void customer_is_not_registered_as_a_new_qanat_member() {
       String nonRegisteredMemberId = "a4316f56-3cb6-4618-89a1-9d735c85a346";
       memberId = nonRegisteredMemberId;
    }

    @Then("A quote decision should be returned where {string} is {string}")
    public void a_quote_decision_should_be_returned_where_is(String field, String value) {
        String actualQuoteDecision = createQuoteResponse.body().path("field");
        String expectedQuoteDecision = value;

        Assert.assertEquals(expectedQuoteDecision, actualQuoteDecision);
    }

}