import org.openqa.selenium.WebDriver;

public class BasePage {
    WebDriver webDriver;

    public BasePage(WebDriver driver) {
        this.webDriver = driver;
    }
}
