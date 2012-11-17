/**
 * 
 */
package com.example.yami;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map.Entry;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class PlaygroundTestCase {
	WebDriver wd;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
		if (wd != null) {
			wd.quit();
		}
	}

	@Test
	public void test() throws MalformedURLException {
		String jenkinsURLString = System.getenv("JENKINS_URL");
		if (jenkinsURLString == null) {
			jenkinsURLString = "http://127.0.0.1/";
		}
		URL jenkinsURL = new URL(jenkinsURLString);
		URL hubURL = new URL(jenkinsURL.getProtocol() + "://"
				+ jenkinsURL.getHost() + ":4444/wd/hub");
		System.out.println("hubURL=" + hubURL);
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		for (Entry<String, String> entry : System.getenv().entrySet()) {
			System.out.println("  " + entry.getKey() + "=" + entry.getValue());
		}
		String browserName = System.getenv("SELENIUM_DESIRED_BROWSER_NAME");
		if (browserName == null) {
			browserName = "firefox";
			// browserName = "opera";
			// browserName = "chrome";
			// browserName = "internet explorer";
		}
		dc.setBrowserName(browserName);
		wd = new RemoteWebDriver(hubURL, dc);
		wd.navigate().to("http://www.lunaport.net/");
		wd.findElement(By.linkText("test")).click();
		Assert.assertTrue(wd.getCurrentUrl().startsWith(
				"http://www.lunaport.net/test/"));
	}
}
