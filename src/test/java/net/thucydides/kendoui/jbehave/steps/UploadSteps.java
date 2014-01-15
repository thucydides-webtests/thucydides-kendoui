package net.thucydides.kendoui.jbehave.steps;

import net.thucydides.kendoui.jbehave.pages.UploadPage;
import org.hamcrest.Matcher;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.File;
import java.net.URISyntaxException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;

/**
 * A description goes here.
 * User: john
 * Date: 15/01/2014
 * Time: 11:11 AM
 */
public class UploadSteps {

    UploadPage uploadPage;

    @Given("I am on the kendoui upload page")
    public void onUploadPage() {
        uploadPage.open();
    }

    @When("I upload the files $filenames")
    public void uploadFile(List<String> filenames) throws URISyntaxException {
        for(String filename : filenames) {
            File fileToUpload = new File(getClass().getResource("/" + filename).toURI());
            uploadPage.scheduleFileForUpload(fileToUpload.getAbsolutePath());
        }
        uploadPage.uploadFiles();
    }

    @Then("$filename should appear in the uploaded files")
    public void shouldSeeUploaded(String filename) {
        List<String> uploadedFiles = uploadPage.getUploadedFiles();
        assertThat(uploadedFiles, (Matcher<? super List<String>>) hasItem(containsString(filename)));

    }
}
