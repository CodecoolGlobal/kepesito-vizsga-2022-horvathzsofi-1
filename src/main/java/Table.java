import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Table extends BasePage{
    private final String url = "https://demo.seleniumeasy.com/table-data-download-demo.html";
    private final By popupCloseX = By.xpath("//*[@id=\"at-cv-lightbox-close\"]");
    private final By nameColumnData = By.xpath("//tr//td[1]");

    public Table(WebDriver driver) {
        super(driver);
    }

    public void navigateToPage(){
        webDriver.navigate().to(url);
    }

    public void closePopup(){
        webDriver.findElement(popupCloseX).click();
    }

    public String[] getPeoplesName() {
        List<WebElement> nameElements = webDriver.findElements(nameColumnData);
        String[] peoplesName = new String[nameElements.size()];

        for (int i = 0; i < nameElements.size(); i++) {
            peoplesName[i] = nameElements.get(i).getText();
        }
        return peoplesName;
    }
}
