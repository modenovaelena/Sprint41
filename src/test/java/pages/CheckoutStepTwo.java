package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutStepTwo {
    private final WebDriver driver;

    private final By dateSelector = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By periodSelector = By.className("Dropdown-placeholder");

    private final By randomDay = By.className("react-datepicker__day");

    private final By submitSelector = By.xpath(".//div[contains(@class, 'Order_Buttons')]/button[text()='Заказать']");;

    private final By confirmOrderSelector = By.xpath(".//button[text()='Да']");


    public CheckoutStepTwo(WebDriver driver) {
        this.driver = driver;
    }

    public void fillTheFormAndContinue(String date, String period) {

        driver.findElement(this.dateSelector).click();
        WebElement dob = driver.findElement(this.randomDay);
        dob.click();


        WebElement e = driver.findElement(this.periodSelector);
        e.click();

        driver.findElement(By.xpath(".//*[text()='" + period + "']")).click();

        driver.findElement(this.submitSelector).click();
    }

    public void confirmOrder() {
        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(this.confirmOrderSelector));

        driver.findElement(this.confirmOrderSelector).click();
    }

}
