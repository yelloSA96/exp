package com.lutoke.pond.selenium.schedule;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SelfLearningForm {

	private WebDriver driver;
	private WebDriverWait wait;

	@Before
	public void setup() {
		driver = Util.getChromeDriver();
		driver.manage().window().maximize();
		driver.get(Util.getUrl() + "/addSelfLearning");
		wait = new WebDriverWait(driver, 3); // set time out
	}

	@AfterEach
	public void close() {
		driver.close();
	}
	
	@AfterEach
	public void teardown() {
		driver.close();
	}

	@Test
	public void test_UserCannotSubmitForm_IfFieldsEmpty() throws Exception {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/form/button")));
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();
	}

	@Test
	public void test_UserCanSubmitForm_IfAllFieldsAreFilled() throws Exception {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"week\"]")));

		driver.findElement(By.xpath("//*[@id=\"week\"]")).sendKeys("This week I will be doing ....");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"react-select-3-input\"]")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"description\"]")).sendKeys("I Will be learning Java through ...");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"date\"]")).sendKeys("20082020");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
	}

	@Test
	public void test_ValidationForEach_FormComponent() throws Exception {
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/div/div/form/button")));

		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"week\"]")).sendKeys("This week I will be doing ....");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"react-select-3-input\"]")).sendKeys("React");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"react-select-3-input\"]")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"description\"]")).sendKeys("I Will be learning Java through ...");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"date\"]")).sendKeys("20082020");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/form/button")).click();
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
	}

}
