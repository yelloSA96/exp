package com.lutoke.pond.selenium.schedule;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Util {

	private static final String URL = "localhost:3000";

	public static WebDriver getChromeDriver() {
		String driverName = System.getProperty("os.name").toLowerCase().contains("mac") ?
				"chromedriverMac" : "chromedriver.exe";

		File file = new File("src/main/resources/" + driverName);
		String driverPath = file.getAbsolutePath();
		System.setProperty("webdriver.chrome.driver", driverPath);
		return new ChromeDriver();
	}

	public static WebDriver getSafariDriver() {
		return new SafariDriver();
	}

	public static String getUrl() {
		return URL;
	}

}
