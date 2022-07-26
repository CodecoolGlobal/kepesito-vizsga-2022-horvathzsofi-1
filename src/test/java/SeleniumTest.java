import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

public class SeleniumTest {
    WebDriver webDriver = null;


    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox");
        options.addArguments("--incognito");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");

        webDriver = new ChromeDriver(options);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().window().maximize();
    }


    @AfterEach
    public void tearDown(){
        webDriver.quit();
    }


    /*
    Tölts be a böngészőbe az alábbi oldalt: https://demo.seleniumeasy.com/basic-first-form-demo.html
    Írj tesztesetet két szám összegének ellenőrzésére a mellékelt dokumentumban, majd a tesztlépések alapján írj automatizált tesztet. Az oldalon, a Two Input Fields” szekcióban két beviteli mezőjét töltsd ki tetszőleges számokkal, majd végezd el a számok összeadásának ellenőrzését kiolvasva az oldalon megjelenő összeget.
    Használj tetszőleges tesztadatot
     */
    @Test
    public void TestInput() {
        InputFields inputField = new InputFields(webDriver);
        int a = 7;
        int b = 18;
        String expected = "25";

        inputField.navigateToPage();
        try {
            inputField.closePopup();
        } catch (Exception e) {
        }
        inputField.fillInputFieldA(String.valueOf(a));
        inputField.fillInputFieldB(String.valueOf(b));
        inputField.clickOnGetTotalButton();

        String actual = inputField.getTotalResult();

        Assertions.assertEquals(expected, actual);
    }

    /*
    Töltsd be az alábbi oldalt a böngészőbe: zhttps://demo.seleniumeasy.com/basic-select-dropdown-demo.html
    Írj tesztesetet a mellékelt dokumentumban, majd a tesztlépések alapján írj automatizált tesztet a következők szerint: a Select List Demo szekció lenyíló mezőjében válaszd ki a hét utolsó napját és ellenőrizd, hogy az oldalon helyesen jelenik meg a kiválasztott érték
    Tesztadatként használd az hét utolsó napját
     */
    @Test
    public void SelectDayTest() {
        DropdownList dropdownList = new DropdownList(webDriver);
        dropdownList.navigateToPage();
        try {
            dropdownList.closePopup();
        } catch (Exception e) {
        }
        dropdownList.openDropdownList();
        dropdownList.selectLastElement();
        String actual = dropdownList.getResultValue();
        String expected = "Day selected :- Saturday";
        Assertions.assertEquals(expected, actual);
    }

    /*
    Töltsd be az alábbi oldalt a böngészőbe: https://demo.seleniumeasy.com/bootstrap-modal-demo.html#
    Írj tesztesetet a mellékelt dokumentumban, majd a tesztlépések alapján írj automatizált tesztet. A tesztesetben ellenőrizd a modal alert ablak szöveges tartalmát összahasonlítva egy általad definiált elvárt eredménnyel. Nyisd meg a Single Modal ablakot, tárolt el az ablakon megjelenő szöveget egy változóba és zárd be az ablakot a bezárás gombbal
     */
    @Test
    public void AlertTest() {
        ModalAlert modalAlert = new ModalAlert(webDriver);
        modalAlert.navigateToPage();
        try {
            modalAlert.closePopup();
        } catch (Exception e) {
        }
        modalAlert.clickOnLaunchModal();
        String actual = modalAlert.getModalText();
        modalAlert.clickOnCloseButton();

        String expected = "A felugróablak megjelent";
        Assertions.assertNotEquals(expected, actual);
    }

    /*
    Töltsd be az alábbi oldalt a böngészőbe: https://demo.seleniumeasy.com/data-list-filter-demo.html
    Írj tesztesetet a mellékelt dokumentumban, majd a tesztlépések alapján írj automatizált tesztet. A teszteset ellenőrizze a névjegykártyák tartalmát.Olvasd ki a neveket a megjelenő névjegykártyákról és ellenőrzésként hasonlítsd össze egy elvárt eredményként megadott listával.
    Használj relatív útvonalat a névjegykártya név elemeinek kiolvasásához.
     */
    @Test
    public void NamecardTest() {
        String [] expected = {
                "Tyreese Burn",
                "Brenda Tree",
                "Glenn Pho shizzle",
                "Brian Hoyies",
                "Glenn Pho shizzle",
                "Arman Cheyia"
        };
        Namecard namecard = new Namecard(webDriver);
        namecard.navigateToPage();
        try {
            namecard.closePopup();
        }catch (Exception e){}
        String[] actual = namecard.getNamesOnCard();

        Assertions.assertArrayEquals(expected, actual);
    }

    /*
    Töltsd be az alábbi oldalt a böngészőbe: https://demo.seleniumeasy.com/table-data-download-demo.html
    Írj tesztesetet a mellékelt dokumentumban, majd a tesztlépések alapján írj automatizált tesztet. A tesztesetet ellenőrizze a táblázatból a neveket, amelyeket a táblázat első oszlop tartalmaz. Gyűjtsd össze a neveket és tárold le a names.txt fájlba majd a tesztesetben a fájl tartalmát hasonlítsd össze egy elvárt eredménnyel.
     */
    @Test
    public void TableTest() {
        Table table = new Table(webDriver);
        FileHandler fileHandler = new FileHandler();
        String directoryPath = "src/test/saved-data";
        String filePath = "src/test/saved-data/names.txt";

        fileHandler.createOutputDirectory(directoryPath);
        fileHandler.deleteFile(filePath);

        table.navigateToPage();
        try {
            table.closePopup();
        }catch (Exception e){}

        String[] actual = table.getPeoplesName();

        for (String people : actual) {
            fileHandler.writeFile(people, filePath);
        }

        String result = fileHandler.readFile(filePath);
        String[] readFromFile = result.split("\n");

        Path content = Paths.get(filePath);
        try (InputStream is = Files.newInputStream(content)) {
            Allure.addAttachment("Names of people", is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] expected = {
                "Tiger Nixon",
                "Garrett Winters",
                "Ashton Cox",
                "Cedric Kelly",
                "Airi Satou",
                "Brielle Williamson",
                "Herrod Chandler",
                "Rhona Davidson",
                "Colleen Hurst",
                "Sonya Frost",
                "Jena Gaines",
                "Quinn Flynn",
                "Charde Marshall",
                "Haley Kennedy",
                "Tatyana Fitzpatrick",
                "Michael Silva",
                "Paul Byrd",
                "Gloria Little",
                "Bradley Greer",
                "Dai Rios",
                "Jenette Caldwell",
                "Yuri Berry",
                "Caesar Vance",
                "Doris Wilder",
                "Angelica Ramos",
                "Gavin Joyce",
                "Jennifer Chang",
                "Brenden Wagner",
                "Fiona Green",
                "Shou Itou",
                "Michelle House"};
        Assertions.assertArrayEquals(expected, readFromFile);
    }

}
