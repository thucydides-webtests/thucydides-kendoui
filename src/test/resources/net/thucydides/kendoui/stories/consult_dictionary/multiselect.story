Scenario: should be able to select multiple entries in a list
Given I am on the kendoui multiselect demo page
When I select 'Laura Peacock' in the select list
Then I should see the following multiselect values: Anne King, Andrew Fuller, Laura Peacock
