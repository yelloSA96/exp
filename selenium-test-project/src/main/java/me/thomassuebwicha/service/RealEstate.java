package me.thomassuebwicha.service;

import me.thomassuebwicha.domain.property.Property;
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

public class RealEstate {
    private WebDriver driver;

    public RealEstate() {
    }

    public void exportToFile(ArrayList<String> propertyListingDetails) throws IOException {
        File file = new File(LocalDate.now().toString() + ".txt");
        try (FileWriter fr = new FileWriter(file, true)) {
        for (String data : propertyListingDetails) {
            fr.write(data + ',');
        }
        fr.write(System.lineSeparator());
        }
    }

    public Property extractDetails(WebElement data) {

        WebElement suburbSection = data.findElement(By.className("css-1pdlxcs")); // Header section
        String suburb = suburbSection.findElement(By.tagName("h3")).getText();

        String street = data.findElement(By.className("css-1b9txmm")).getText(); // Street Name

        WebElement houseTypeSection = data.findElement(By.className("css-dpwygs")); // house type section
        String houseType = houseTypeSection.findElement(By.tagName("span")).getText();

        WebElement bedroomSection = houseTypeSection.findElement(By.className("css-1g2pbs1")); // house type section
        String bedrooms = bedroomSection.getText().replaceAll(" beds?","");

        String sellingAll;
        if ( data.findElements(By.className("css-43wvni")).isEmpty() ){
            sellingAll = data.findElement(By.className("css-pczn8c")).getText();

        }else{
            sellingAll = data.findElement(By.className("css-43wvni")).getText();
        }


        return new Property(street, suburb, houseType,Integer.parseInt(bedrooms),sellingAll);
    }

    public ArrayList<Property> extractAlphaSection(WebDriver driver, String alaphabeticalSection) {
        WebElement sections  = driver.findElement(By.cssSelector(alaphabeticalSection));
        List<WebElement> listings = sections.findElements(By.className("css-3xqrp1"));
        ArrayList<Property> propertiesInAlphaSection = new ArrayList<>();
        for (WebElement listing : listings) {
            propertiesInAlphaSection.add(extractDetails(listing));
        }
        return propertiesInAlphaSection;
    }

    public ArrayList<Property> scrapeData() {
        char[] alphabet = {'A','B','C','D','E','F'};
        ArrayList<Property> result = new ArrayList<>();
        try {
            driver.get("https://www.domain.com.au/auction-results/melbourne");
            for (char character : alphabet) {
                ArrayList<Property> alphabeticalSectionOfProperties = extractAlphaSection(driver, "div[id='" + character + "']");
                result.addAll(alphabeticalSectionOfProperties);
            }
            System.out.println("Finished Extraction!");
            return result;
        } finally {
            driver.quit();
        }
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
}