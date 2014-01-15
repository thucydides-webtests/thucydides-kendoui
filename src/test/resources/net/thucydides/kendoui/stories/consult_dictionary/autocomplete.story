Scenario: should be able to check displayed autocomplete values
Given I am on the kendoui autocomplete demo page
When I enter 'A' into the country field
Then I should see the following values:
| country    |
| Albania    |
| Andorra    |
| Armenia    |
| Austria    |
| Azerbaijan |


Scenario: should be able to read a displayed value from an autocomplete field
Given I am on the kendoui autocomplete demo page
When I select 'Albania' from the suggestions
And I select 'Armenia' from the suggestions
Then the displayed value should be 'Albania, Armenia'