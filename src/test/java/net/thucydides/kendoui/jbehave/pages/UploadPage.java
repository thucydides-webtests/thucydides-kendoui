package net.thucydides.kendoui.jbehave.pages;

import ch.lambdaj.function.convert.PropertyExtractor;
import net.thucydides.core.annotations.DefaultUrl;
import net.thucydides.core.pages.PageObject;

import java.util.List;

import static ch.lambdaj.Lambda.convert;

@DefaultUrl("http://demos.kendoui.com/web/upload/index.html")
public class UploadPage extends PageObject {



    public UploadPage() {
    }

    public void scheduleFileForUpload(String filepath) {
        upload(filepath).to($("#files"));
    }

    public void uploadFiles() {
        $("#files").submit();
    }

    public List<String> getUploadedFiles() {
        return convert(findAll(".t-file"), new PropertyExtractor("text"));
    }


}
