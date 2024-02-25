import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import pages.CheckoutStepOne;
import pages.CheckoutStepTwo;
import pages.HomePage;

@RunWith(Parameterized.class)
public class CheckoutTest {

    private WebDriver driver;

    String name, surname, address, station, phone, period ;

    public static final boolean USE_FIREFOX = true;

    public CheckoutTest(String name, String surname, String address,
                        String station, String phone, String period) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.station = station;
        this.phone = phone;
        this.period = period;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Елена","Петрова", "Гагарина 233",
                        "Преображенская площадь", "+79127233922", "трое суток"},
                {"Денис","Сидоров", "Алмазная 33",
                        "Преображенская площадь", "+79127233445", "трое суток"}
        };
    }


    @Before
    public void setup() {
        if (USE_FIREFOX) {
            FirefoxOptions options = new FirefoxOptions();
            //options.addArguments("-headless");
            driver = new FirefoxDriver(options);
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
            //options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        }
    }

    @Test
    public void checkQuestions () {
        // переход на страницу тестового приложения
        HomePage homePage= new HomePage(driver);
        homePage.open();
        homePage.confirmCookies();

        homePage.startCheckout();

        CheckoutStepOne step1 = new CheckoutStepOne(driver);
        step1.fillTheFormAndContinue(name, surname, address, station, phone);


        CheckoutStepTwo step2 = new CheckoutStepTwo(driver);
        step2.fillTheFormAndContinue(period);
        step2.confirmOrder();


        Assert.assertTrue("Order was not captured", driver.findElement(By.xpath(".//*[text()='Заказ оформлен']")).isDisplayed());
        

    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit ();
    }

}

