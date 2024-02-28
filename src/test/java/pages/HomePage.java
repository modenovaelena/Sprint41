package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private final WebDriver driver;

    private final String accordionButtonByQuestionSelectorXPath
            = ".//div[@class='accordion__button' and text() = '%s']";

    private final String accordingPanelByQuestionSelectorXPath
            = "//div[@class='accordion__button' and text() = '%s']/parent::div/parent::div/div[@class='accordion__panel']";

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

    public By getQuestionButtonSelector(String question) {
        return By.xpath(String.format(accordionButtonByQuestionSelectorXPath,
                question));
    }

    public By getQuestionPanelSelector(String question) {
        return By.xpath(String.format(accordingPanelByQuestionSelectorXPath,
                question));
    }

    public void clickQuestionByName(String question)
    {
        WebElement e = driver.findElement(this.getQuestionButtonSelector(question));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", e);
        e.click();
    }

    public void startCheckoutUsingButtonIndex(int indx) {
        driver.findElements(startCheckoutButtonSelector).get(indx).click();
    }
}
