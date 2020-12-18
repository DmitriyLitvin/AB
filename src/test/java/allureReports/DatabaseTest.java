package allureReports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class DatabaseTest {
    WebDriver webDriver;
    Actions action;

    @BeforeSuite
    public void onStart(){
        System.setProperty("webdriver.chrome.driver", "D:/chrome/chromedriver.exe");
        webDriver = new ChromeDriver();
        action = new Actions(webDriver);
        webDriver.manage().window().maximize();
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
        String discountedDate = computerPage.createComputer("Amiga", "Amstrad", "2020-12-16");

        TablePage tablePage = new TablePage(webDriver,action);
        tablePage.ifComputerExist("Amiga", "Amstrad", "2020-12-16", discountedDate);
    }
}
