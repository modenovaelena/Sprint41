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
import pages.HomePage;

@RunWith(Parameterized.class)
public class ImportantQuestionsTest {
    private final String question;
    private WebDriver driver;

    public static final boolean USE_FIREFOX = false;

    public ImportantQuestionsTest(String question) {
        this.question = question;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][] {
                {"Сколько это стоит? И как оплатить?"},
                {"Хочу сразу несколько самокатов! Так можно?"} ,
                {"Как рассчитывается время аренды?"},
                {"Можно ли заказать самокат прямо на сегодня?"},
                {"Можно ли продлить заказ или вернуть самокат раньше?"},
                {"Вы привозите зарядку вместе с самокатом?"},
                {"Можно ли отменить заказ?"},
                {"Я жизу за МКАДом, привезёте?"}
        };
    }


    @Before
    public void setup() {
        if (USE_FIREFOX) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("-headless");
            driver = new FirefoxDriver(options);
        } else {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
            driver = new ChromeDriver(options);
        }
    }

    @Test
    public void checkQuestions () {
        // переход на страницу тестового приложения
        HomePage homePage= new HomePage(driver);
        homePage.open();
        homePage.confirmCookies();
        homePage.clickQuestionByName(this.question);

        Assert.assertTrue("The element is not displayed", driver.findElement(By.xpath("//div[@class='accordion__button' and text() = '" +
                this.question + "']/parent::div/parent::div/div[@class='accordion__panel']")).isDisplayed());
    }

    @After
    public void teardown() {
        // Закрой браузер
        driver.quit ();
    }

}

