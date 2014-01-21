Scenario: should be able to select a value in a numeric text box
Given I am on the kendoui numeric text box demo page
When I enter '10.50' into the price field
Then the price field should have a value of 10.50

Scenario: should be able to get the displayed value
Given I am on the kendoui numeric text box demo page
When I enter '10.50' into the price field
Then the displayed price field should have a value of $10.50

Scenario: should be able to select a value in a percentage text box
Given I am on the kendoui numeric text box demo page
When I enter '0.07' into the percentage field
Then the percentage field should have a value of 0.07
