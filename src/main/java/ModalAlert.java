import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ModalAlert extends BasePage{

    private final String url = "https://demo.seleniumeasy.com/bootstrap-modal-demo.html";
    private final By popupCloseX = By.xpath("//*[@id=\"at-cv-lightbox-close\"]");
    private final By launchModal = By.xpath("//*[@href=\"#myModal0\"]");
    private final By modalBody = By.xpath("//*[@id=\"myModal0\"]//*[@class=\"modal-body\"]");
    private final By closeButton = By.xpath("//*[@id=\"myModal0\"]//*[text()=\"Close\"]");

    public ModalAlert(WebDriver driver) {
        super(driver);
    }

    public void navigateToPage(){
        webDriver.navigate().to(url);
    }

    public void closePopup(){
        webDriver.findElement(popupCloseX).click();
    }

    public void clickOnLaunchModal(){
        webDriver.findElement(launchModal).click();
    }

    public String getModalText(){
        return webDriver.findElement(modalBody).getText();
    }

    public void clickOnCloseButton(){
        webDriver.findElement(closeButton).click();
    }
}
