@QuotationEngineTest @QuotationValidationTest
Feature: Generate Loan Quote Tests

  @LoanAmountTest @Positive
  Scenario: Customer should be able to get a loan quote with an interest rate when loan amount is between 1000 and 25000
    Given Customer is registered as a new qanat member
    When Qanat member with 30000 current salary requested 15000 loan for 24 term length
    Then Customer should get response with 200 status code
    And Quote request should be accepted with a quoteId
    And Quote decision should be returned with an interest rate
    And Loan amount should be matching with requested amount

# Bug;
# Error message should not be saying it accepts boundries inside the range as it is not included in AC
# Message should be like (1000 < TOTAL < 25000) but it says (1000 <= TOTAL <= 25000)
  @LoanAmountTest @Negative
  Scenario: Customer should not be able to get a loan quote with an interest rate when loan amount is under 1000
    Given Customer is registered as a new qanat member
    When Qanat member with 30000 current salary requested 1000 loan for 24 term length
    Then Customer should get response with 400 status code
    And Request should be rejected with following message
      | 1000 is outside the lending range: 1000 < TOTAL < 25000 |

# Bug;
# Error message should not be saying it accepts boundries inside the range as it is not included in AC
# Message should be like (1000 < TOTAL < 25000) but it says (1000 <= TOTAL <= 25000)
  @LoanAmountTest @Negative
  Scenario: Customer should not be able to get a loan quote with an interest rate when loan amount is over 25000
    Given Customer is registered as a new qanat member
    When Qanat member with 30000 current salary requested 25000 loan for 24 term length
    Then Customer should get response with 400 status code
    And Request should be rejected with following message
      | 25000 is outside the lending range: 1000 < TOTAL < 25000 |


# Bug;
# According to AC when quote decision is negative it is expected to provide a decision in response as quoteOffered=False
# However error message only tells about the limits about loan amount but not a decision as expected
  @QuoteDecisionTest @Negative
  Scenario: Negative quote decision should be returned when loan amount is out of limits
    Given Customer is registered as a new qanat member
    When Qanat member with 30000 current salary requested 30000 loan for 24 term length
    Then Customer should get response with 400 status code
    And A quote decision should be returned where "quoteOffered" is "False"


  @NonRegistered @Negative
  Scenario: Non-Existent Customer should not be able to get a loan quote
    Given Customer is not registered as a new qanat member
    When Qanat member with 30000 current salary requested 15000 loan for 24 term length
    Then Customer should get response with 404 status code
    And Request should be rejected with following message
      | No Member exists for memberId=a4316f56-3cb6-4618-89a1-9d735c85a346 |