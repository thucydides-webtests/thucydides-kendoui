Scenario: should be able to upload files
Given I am on the kendoui upload page
When I upload the files somefile.txt, someotherfile.txt
Then somefile.txt should appear in the uploaded files
And someotherfile.txt should appear in the uploaded files


