import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InputFields extends BasePage{
    private final String url = "http://demo.seleniumeasy.com/basic-first-form-demo.html";
    private final By popupCloseX = By.xpath("//*[@id=\"at-cv-lightbox-close\"]");
    private final By inputFieldA = By.id("sum1");
    private final By inputFieldB = By.id("sum2");
    private final By getTotalButton = By.xpath("//*[@id=\"gettotal\"]/button");
    private final By totalValue = By.id("displayvalue");


    public InputFields(WebDriver driver) {
        super(driver);
    }

    public void navigateToPage(){
        webDriver.navigate().to(url);
    }

    public void closePopup(){
        webDriver.findElement(popupCloseX).click();
    }

    public void fillInputFieldA(String testDataA){
        webDriver.findElement(inputFieldA).sendKeys(testDataA);
    }

    public void fillInputFieldB(String testDataB){
        webDriver.findElement(inputFieldB).sendKeys(testDataB);
    }

    public void clickOnGetTotalButton(){
        webDriver.findElement(getTotalButton).click();
    }

    public String getTotalResult(){
        return webDriver.findElement(totalValue).getText();
    }
}
