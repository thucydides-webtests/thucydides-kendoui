Scenario: should be able to read grid cells
Given I am on the kendoui grid page
When I read values from row 3
Then I should get the following cell values:
| Contact Name    | Contact Title | Company Name            | Country  |
| Antonio Moreno  | Owner         | Antonio Moreno Taquería | Mexico   |

Scenario: should be able to navigate to another page
Given I am on the kendoui grid page
When I navigate to page 2
And I read values from row 1
Then I should get the following cell values:
| Contact Name      | Contact Title         | Company Name      | Country   |
| Victoria Ashworth	| Sales Representative	| B's Beverages	    |UK         |

Scenario: should be able to select a row
Given I am on the kendoui selectionable grid page
When I select row 2
Then the selected row should be 2


