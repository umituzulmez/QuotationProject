@QuotationEngineTest @CreateMemberTest
Feature: Create New Member Tests

  @CreateMemberTest @Positive
  Scenario: New Member should be created with valid details
    Given Customer has following details
      | firstname | James                |
      | lastname  | Dan                  |
      | firstLine | 47-49 Cottons Centre |
      | town      | London               |
      | postCode  | SE1 2QG              |
      | email     | jamesdan@zopa.com    |
      | jobTitle  | Tester/Breaker       |
    When Customer makes a request to be a qanat member
    Then Customer should get response with 201 status code
    And New Qanat Member should be created

# Bug;
# New Member should be created as JobTitle is under max length 30 as AC requires
# Requested is rejected with statusCode 400 error message saying title must be less than 20 instead of 30
  @CreateMemberTest @Positive
  Scenario: New Member should be created when jobTitle is under max Length 30
    Given Customer has following details
      | firstname | Haley                            |
      | lastname  | Green                            |
      | firstLine | 47-49 Cottons Centre             |
      | town      | London                           |
      | postCode  | SE1 2QG                          |
      | email     | haleygreen@zopa.com              |
      | jobTitle  | Information Security Analyst     |
    When Customer makes a request to be a qanat member
    Then Customer should get response with 201 status code
    And New Qanat Member should be created

# Bug;
# New Member should not be created as JobTitle is over max length 30 as AC requires
# However error message should say title must be less than 30 instead of 20
  @CreateMemberTest @Negative
  Scenario: New Member should not be created when jobTitle is over max Length 30
    Given Customer has following details
      | firstname | Haley                            |
      | lastname  | Green                            |
      | firstLine | 47-49 Cottons Centre             |
      | town      | London                           |
      | postCode  | SE1 2QG                          |
      | email     | haleygreen@zopa.com              |
      | jobTitle  | Help Desk Worker/Desktop Support |
    When Customer makes a request to be a qanat member
    Then Customer should get response with 400 status code
    And  Request should be rejected with following message
      | jobTitle must be <= 30 characters |