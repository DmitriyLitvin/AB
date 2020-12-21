package allureReports;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DatabaseTest {
    WebDriver webDriver;
    Actions action;

    @BeforeSuite
    public void onStart(){
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        action = new Actions(webDriver);
        webDriver.manage().window().maximize();
    }

    @AfterSuite
    public void onEnd() {
        webDriver.close();
    }

    @Test
    public void testComputerExist() throws Exception {
        ComputerPage computerPage = new ComputerPage(webDriver,action);
        String discountedDate = computerPage.createComputer("Acorn Archimedes", "Acorn computer", "-");

        TablePage tablePage = new TablePage(webDriver,action);
        tablePage.ifComputerExist("Acorn Archimedes", "Acorn computer", "-", discountedDate);
    }

    @Test
    public void testComputerNotExist() throws Exception {
        ComputerPage computerPage = new ComputerPage(webDriver,action);
        String discountedDate = computerPage.createComputer("Everest Home", "Amstrad", "2020-12-16");

        TablePage tablePage = new TablePage(webDriver,action);
        tablePage.ifComputerExist("Everest Home", "Amstrad", "2020-12-16", discountedDate);
    }
}
