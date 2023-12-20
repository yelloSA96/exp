package me.thomassuebwicha;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chrome.ChromeDriver;

public class firstTest {
    public static void main(String[] args) {
//        Chrome Settings
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
//        chromeOptions.addArguments("--headless=new");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);


        WebDriver driver = new ChromeDriver(chromeOptions);
        try {
            // Navigate to Url
            driver.get("https://github.com");
        } finally {
            driver.quit();
        }
    }
}