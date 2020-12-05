package com.lutoke.pond.selenium.schedule;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class InterviewForm {
	
	@Value("${react.origin:http://localhost:3000}")
	private String reactOrigin;
	
	private String driverPath;
	private WebDriver driver;
	private WebDriverWait wait;

	@Before
	public void setup() {
		driver = Util.getChromeDriver();
		driver.manage().window().maximize();
		
	}
	
	@AfterEach
	public void teardown() {
		driver.close();
	}

	@Test
	public void testInterviewForm() {
		driver.get(reactOrigin+"/opportunity");
		WebDriverWait wait = new WebDriverWait(driver, 3);
		driver.get(Util.getUrl() + "/opportunity");
		wait = new WebDriverWait(driver, 3); // set time out

		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"root\"]/table/tbody/tr")));
		driver.findElement(By.xpath("//*[@id=\"root\"]/table/tbody/tr/td[4]/button")).click();
	}

	@AfterEach
	public void close() {
		driver.close();
	}

	@Test
	public void test_basic_pass() {
		WebElement interviewForm = driver.findElement(By.id("interview-form"));
		interviewForm.findElement(By.xpath("//span[1]/input")).sendKeys("1: Ted");
		interviewForm.findElement(By.xpath("//span[2]/input")).sendKeys("Face-to-face");
		interviewForm.findElement(By.xpath("//input[4]")).sendKeys("Sydney");
		interviewForm.findElement(By.xpath("//span[3]/div[1]/div/input")).click();
		interviewForm.findElement(By.xpath("//span[3]/div[1]/div/input")).sendKeys(Keys.ENTER);
		interviewForm.findElement(By.xpath("//span[4]/div[1]/div/input")).click();
		interviewForm.findElement(By.xpath("//span[4]/div[1]/div/input")).sendKeys(Keys.ENTER);
	}

	@Test
	public void test_invalid_consultant() {
		test_basic_pass();
		WebElement interviewForm = driver.findElement(By.id("interview-form"));
		interviewForm.findElement(By.xpath("//span[1]/input")).clear();
		interviewForm.findElement(By.xpath("//span[1]/input")).sendKeys("Ted");
		driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/div/div/div[3]/button[1]")).click();
	}

}
