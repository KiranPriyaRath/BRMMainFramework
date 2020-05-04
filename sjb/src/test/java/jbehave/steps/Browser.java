package jbehave.steps;

import org.jbehave.core.annotations.Given;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import pages.BrowserPage;

public class Browser {
	@Steps
    private BrowserPage browserPage;

    @Given("Launch the url '$url'")
    public void Launch_the_url(String url)  {
        browserPage.launch(url);
    }

    @Given("Verify the title is '$title'")
    public void Verify_the_title_is(String title)  {
        Assert.assertEquals(title, browserPage.getBrowserTitle());
    }

}
