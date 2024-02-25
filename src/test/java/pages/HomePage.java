package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;

    private final String accordionButtonSelectorXPath
            = ".//div[@class='accordion__button' and text() = '%s']";

    private final By startCheckoutButtonSelector
            = By.xpath(".//button[text()='Заказать']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }
    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru");
    }

    public void confirmCookies() {
        driver.findElement(By.id("rcc-confirm-button")).click();
    }

    public void clickQuestionByName(String question)
    {
        By accordionButtonSelector = By.xpath(String.format(accordionButtonSelectorXPath,
                question));

        WebElement e = driver.findElement(accordionButtonSelector);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", e);
        e.click();
    }

    public void startCheckout() {
        driver.findElements(startCheckoutButtonSelector).get(0).click();
    }
}
