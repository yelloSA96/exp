package me.thomassuebwicha.service;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class realEstate {
    public void exportToFile(ArrayList<String> propertyListingDetails) throws IOException {
        File file = new File(LocalDate.now().toString() + ".txt");
        try (FileWriter fr = new FileWriter(file, true)) {
        for (String data : propertyListingDetails) {
            fr.write(data + ',');
        }
        fr.write(System.lineSeparator());
        }
    }

    public void extractDetails(WebElement data) {
        ArrayList<String> result = new ArrayList<String>();

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

        result.add(street);
        result.add(suburb);
        result.add(houseType);
        result.add(bedrooms);
        result.add(sellingAll);
        System.out.println(street + "," + suburb + "," + houseType + "," + bedrooms +","+sellingAll);
        try {
            exportToFile(result);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void extractAlphaSection(WebDriver driver, String alaphabeticalSection) {
        WebElement sections  = driver.findElement(By.cssSelector(alaphabeticalSection));
        List<WebElement> listings = sections.findElements(By.className("css-3xqrp1"));
        for (WebElement listing : listings) {
            extractDetails(listing);
        }
    }

    public void execution() {
//        Chrome Settings
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);


        WebDriver driver = new ChromeDriver(chromeOptions);
        char[] alphabet = {'A','B','C','D','E','F'};

        try {
            driver.get("https://www.domain.com.au/auction-results/melbourne");

            for (char character : alphabet) {
                extractAlphaSection(driver, "div[id='" + character + "']");
            }
            System.out.println("Finished Extraction!");
        } finally {
            driver.quit();
        }
    }

}