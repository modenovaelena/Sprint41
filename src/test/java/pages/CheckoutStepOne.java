package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOne {
    private final WebDriver driver;

    private final By nameSelector = By.xpath(".//input[@placeholder='* Имя']");
    private final By surnameSelector = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressSelector = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By stationSelector = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneSelector = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextSelector = By.xpath(".//button[text()='Далее']");;


    public CheckoutStepOne(WebDriver driver) {
        this.driver = driver;
    }

    public void fillTheFormAndContinue(String name, String surname, String address,
                                       String station, String phone) {




        driver.findElement(this.nameSelector).clear();
        driver.findElement(this.nameSelector).sendKeys(name);

        driver.findElement(this.surnameSelector).clear();
        driver.findElement(this.surnameSelector).sendKeys(surname);

        driver.findElement(this.addressSelector).clear();
        driver.findElement(this.addressSelector).sendKeys(address);

        driver.findElement(this.stationSelector).sendKeys(station);
        driver.findElement(By.xpath(".//*[text()='" + station + "']")).click();

        driver.findElement(this.phoneSelector).clear();
        driver.findElement(this.phoneSelector).sendKeys(phone);

        driver.findElement(this.nextSelector).click();
    }

}
