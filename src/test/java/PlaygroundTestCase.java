/**
 * 
 */
package com.example.yami;

import java.net.MalformedURLException;
import java.net.URL;

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
		String u = System.getenv("JENKINS_URL");
		if (u == null) {
			u = "http://127.0.0.1/";
		}
		u += "wd/hub";
		DesiredCapabilities dc = DesiredCapabilities.firefox();
		// dc.setBrowserName("firefox");
		// dc.setBrowserName("opera");
		// dc.setBrowserName("chrome");
		dc.setBrowserName("internet explorer");
		wd = new RemoteWebDriver(new URL(u), dc);
		wd.navigate().to("http://www.lunaport.net/");
		wd.findElement(By.linkText("test")).click();
		Assert.assertTrue(wd.getCurrentUrl().startsWith(
				"http://www.lunaport.net/test/"));
	}
}
