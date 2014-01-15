Scenario: should be able to select a date
Given I am on the kendoui datepicker demo page
When I enter '10/31/2012' into the date field
Then the date field should have a value of '10/31/2012'