package me.thomassuebwicha;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class realEstate {


    public static String[] extractDetails( WebElement data) {
        String[] result = new String[0];

        WebElement suburbSection = data.findElement(By.className("css-1pdlxcs")); // Header section
        String suburb = suburbSection.findElement(By.tagName("h3")).getText();

        String street = data.findElement(By.className("css-1b9txmm")).getText(); // Street Name

        WebElement houseTypeSection = data.findElement(By.className("css-dpwygs")); // house type section
        String houseType = houseTypeSection.findElement(By.tagName("span")).getText();

        WebElement bedroomSection = houseTypeSection.findElement(By.className("css-1g2pbs1")); // house type section
        String bedrooms = bedroomSection.getText();

        String sellingAll;
        if ( data.findElements(By.className("css-43wvni")).isEmpty() ){
            sellingAll = data.findElement(By.className("css-pczn8c")).getText();

        }else{
            sellingAll = data.findElement(By.className("css-43wvni")).getText();
        }
        System.out.println(suburb + "," + street + "," + houseType + "," + bedrooms +","+sellingAll);
        return result;
    }

    public static void extractAlphaSection(WebDriver driver, String alaphabeticalSection) {
        WebElement sections  = driver.findElement(By.cssSelector(alaphabeticalSection));
        List<WebElement> listings = sections.findElements(By.className("css-3xqrp1"));
        for (WebElement listing : listings) {
            String[] details = extractDetails(listing);
        }
    }

    public static void main(String[] args) {
//        Chrome Settings
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);


        WebDriver driver = new ChromeDriver(chromeOptions);
        try {
            driver.get("https://www.domain.com.au/auction-results/melbourne");

            extractAlphaSection(driver,"div[id='A']");

            System.out.println("Finished Extraction!");
        } finally {
            driver.quit();
        }
    }
}