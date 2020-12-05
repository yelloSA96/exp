package com.lutoke.pond.selenium.schedule;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ViewConsultants {

	private WebDriver driver;
	private WebDriverWait wait;

	@Before
	public void setup() {
		driver = Util.getChromeDriver();
		driver.manage().window().maximize();
		driver.get(Util.getUrl());
		wait = new WebDriverWait(driver, 3); // set time out
	}

	@AfterEach
	public void close() {
		driver.close();
	}

	@Test
	public void test_UserCanSearchConsultants() throws Exception {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div/div[2]/div/input")));

		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div/div[2]/div/input")).sendKeys("Tod");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div/div[2]/div/input")).clear();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div/div[2]/div/input")).sendKeys("Ted");
	}

	@Test
	public void test_UserCanViewConsultantsSchedule() throws Exception {
		String element = "//*[@id=\"root\"]/div/div/div/div/div[2]/div[2]/table/tbody/tr/td[2]/a";
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
		driver.findElement(By.xpath(element)).click();
		Thread.sleep(100);
	}

	@Test
	public void test_searchConsultants_ViewFirstConsultant_afterSearch() throws Exception {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div/div[2]/div/input")));

		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[1]/div/div[2]/div/input")).sendKeys("Ted");
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div[2]/div[2]/table/tbody/tr/td[2]/a")).click();
		Thread.sleep(100);
	}

}
