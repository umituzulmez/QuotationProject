@QuotationEngineTest @RegistrationTest
Feature: Qanat Member Registration Test

  @CutomerDetailsTest @Positive
  Scenario: Customer should be able to register
    Given Customer has following details
      | firstname | John                 |
      | lastname  | Doe                  |
      | firstLine | 47-49 Cottons Centre |
      | town      | town                 |
      | postCode  | SE1 2QG              |
      | email     | johndoe@zopa.com     |
      | jobTitle  | Tester/Breaker       |
    When Customer makes a request to be a qanat member
    Then Customer should get response with 201 status code
    And New Qanat Member should be created
    When Existing member details are retrieved
    Then Customer details should be matching



