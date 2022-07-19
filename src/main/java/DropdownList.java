import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DropdownList extends BasePage {
    private final String url = "https://demo.seleniumeasy.com/basic-select-dropdown-demo.html";
    private final By popupCloseX = By.xpath("//*[@id=\"at-cv-lightbox-close\"]");
    private final By dropdownList = By.id("select-demo");
    private final By lastElement = By.xpath("//*[@id=\"select-demo\"]//option[last()]");
    private final By result = By.className("selected-value");

    public DropdownList(WebDriver driver) {
        super(driver);
    }

    public void navigateToPage(){
        webDriver.navigate().to(url);
    }

    public void closePopup(){
        webDriver.findElement(popupCloseX).click();
    }

    public void openDropdownList(){
        webDriver.findElement(dropdownList).click();
    }

    public void selectLastElement(){
        webDriver.findElement(lastElement).click();
    }

    public String getResultValue(){
        return webDriver.findElement(result).getText();
    }
}
