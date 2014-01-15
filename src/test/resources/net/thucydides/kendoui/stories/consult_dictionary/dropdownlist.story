Scenario: should be able to select an item in a dropdown list
Given I am on the kendoui dropdownlist demo page
When I select 'Orange' into the cap color list
Then I should see the orange cap

Given I am on the kendoui dropdownlist demo page
When I select 'Orange' into the cap color list
Then the selected option in the dropdown list should be 'Orange'