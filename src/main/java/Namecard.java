import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Namecard extends BasePage{
    private final String url = "https://demo.seleniumeasy.com/data-list-filter-demo.html";
    private final By popupCloseX = By.xpath("//*[@id=\"at-cv-lightbox-close\"]");
    private final By names = By.xpath("//*[@class=\"searchable-container\"]//h4");

    public Namecard(WebDriver driver) {
        super(driver);
    }

    public void navigateToPage(){
        webDriver.navigate().to(url);
    }

    public void closePopup(){
        webDriver.findElement(popupCloseX).click();
    }
    
    public String[] getNamesOnCard(){
        List<WebElement> nameElements = webDriver.findElements(names);
        String[] nameResults = new String[nameElements.size()];
        for (int i = 0; i < nameElements.size(); i++) {
            String[] temporaryElement = nameElements.get(i).getText().split("Name: ");
            nameResults[i] = temporaryElement[1];
        }

        return nameResults;
    } 
}
