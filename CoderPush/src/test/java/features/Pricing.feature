Feature: Sleek Pricing
  Scenario: Scheduled Talking To Expert Verification
    Given I navigate to Sleek SG
    When I click on "Pricing" link
    And I click on "Talk to an expert" button
    And select a date "10" days from now
    And select time zone as "UTC +08:00 China, Hong Kong, Singapore"
    And set time as "11:30 am"
    Then Verify date and time value is correct in Your information section