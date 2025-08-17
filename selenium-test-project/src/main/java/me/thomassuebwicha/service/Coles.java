package me.thomassuebwicha.service;

import me.thomassuebwicha.domain.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Coles {

    protected static String weblink = "https://www.coles.com.au/on-special";
    protected List<String> productList = new ArrayList<>();
    ChromeOptions chromeOptions;
    WebDriver driver;
    List<Product> parsedProducts;


    Coles() {
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--headless");
        driver = new ChromeDriver(this.chromeOptions);
        parsedProducts = new ArrayList<Product>();
    }


    public void extractName(WebElement colestargetingProductTileHeaderWrapper, Product currentProduct) {
        WebElement product__header = colestargetingProductTileHeaderWrapper.findElement(By.className("product__header"));
        WebElement product_title_area = product__header.findElement(By.className("product__message-title_area"));
        WebElement product__link = product_title_area.findElement(By.className("product__link"));
        WebElement product__title = product__link.findElement(By.className("product__title"));
        String name = product__title.getText();
        if (name.contains("|")) {
            name = name.substring(0, name.indexOf("|"));
        }else if (name.contains("...")) {
            name = name.substring(0, name.lastIndexOf("."));
        }else {
            System.out.println("No | or ... found.");
        }
        currentProduct.setName(name);
//        System.out.println("name :" + name);
    }

    public void extractPrice(WebElement product__cta_section, Product currentProduct) {
        WebElement product__pricing_area = product__cta_section.findElement(By.className("product__pricing_area"));
        WebElement product__pricing = product__pricing_area.findElement(By.className("product__pricing"));
        WebElement price = product__pricing.findElement(By.className("price"));
        WebElement price__value = price.findElement(By.className("price__value"));
        String price_value = price__value.getText();
        currentProduct.setStatedPrice(Double.parseDouble(price_value.substring(1)));
//        System.out.println("Price :" + price_value);
    }
    public String extractSavingPrice(WebElement product__cta_section ) {
        // Logic is if this has saving, extract the saving rpice otherwise return 0
        WebElement product__pricing_area = product__cta_section.findElement(By.className("product__pricing_area"));
        WebElement product__pricing = product__pricing_area.findElement(By.className("product__pricing"));
        WebElement price = product__pricing.findElement(By.className("price"));
        try {
            WebElement saving_price = price.findElement(By.className("badge-label"));
            String saving_price_value = saving_price.getText();
            return saving_price_value;
        } catch (Exception e) {
//            System.out.println("No saving here.");
            return "0";
        }
    }

    public void extractWeight(WebElement colestargetingProductTileHeaderWrapper, Product currentProduct) {
        WebElement product__header = colestargetingProductTileHeaderWrapper.findElement(By.className("product__header"));
        WebElement product_title_area = product__header.findElement(By.className("product__message-title_area"));
        WebElement product__link = product_title_area.findElement(By.className("product__link"));
        WebElement product__title = product__link.findElement(By.className("product__title"));
        String weight = product__title.getText();
        weight = weight.substring(weight.indexOf("|")+1);
        currentProduct.setWeight(weight);
    }

    public String extractWasPrice() {
        System.out.println("extractWasPrice");
        return new String();
    }
    public void extraction(WebDriver driver, String supermarket) {
        List<WebElement> products = driver.findElements(By.className("coles-targeting-ProductTileProductTileWrapper"));

        String price;
        String savingPrice, trimmedSavingPrice;
        for (final WebElement product: products) {
            Product currentProduct = new Product();
            extractName(product.findElement(By.className("coles-targeting-ProductTileHeaderWrapper")), currentProduct);
            extractWeight(product.findElement(By.className("coles-targeting-ProductTileHeaderWrapper")), currentProduct);
            extractPrice(product.findElement(By.className("product__cta_section")), currentProduct);

            savingPrice = extractSavingPrice(product.findElement(By.className("product__cta_section")));
            trimmedSavingPrice = savingPrice.substring(savingPrice.indexOf("$")+1);
            currentProduct.setSavingPrice(Double.parseDouble(trimmedSavingPrice));

            currentProduct.setSupermarket(supermarket);

            parsedProducts.add(currentProduct);

        }
    }

    public void execute() {
        try {
            for (int i = 1; i < 2; i++) {
                this.driver.get(weblink+"?page="+i);
                extraction(driver,"Coles");
            }
        } finally {
            Iterator iterator = parsedProducts.iterator();
            int counter = 0;
            while(iterator.hasNext()){
//                iterator.next();
                System.out.println(iterator.next());
                counter++;
            }
            System.out.println("Total products: " + counter);
            driver.quit();
        }
    }

    public List<Product> getParsedProducts() {
        return parsedProducts;
    }
}
