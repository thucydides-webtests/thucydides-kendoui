Scenario: should be able to open a closed entry in a tree
Given I am on the kendoui tree page
When I open the Decor entry
Then the Decor entry should be expanded
And I should see Bed Linen

Scenario: should be able to select entries in a tree
Given I am on the kendoui tree page
When I select the Sofas entry
Then the Sofas entry should be selected

Scenario: should be able to select the root entry in a tree
Given I am on the kendoui tree page
When I select the entry root
Then the Furniture entry should be selected

Scenario: should be able to drag an element to another node within a tree
Given I am on the kendoui tree page
When I drag the Sofas entry to Decor
Then the Sofas entry should appear underneath Decor

Scenario: should be able to drag an element to another tree
Given I am on the kendoui tree page
When I drag the Sofas entry across the righthand tree to Storage
Then the Sofas entry should appear in the righthand tree underneath Storage