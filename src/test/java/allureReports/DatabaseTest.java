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
    public void test() throws Exception {
        ComputerPage computerPage = new ComputerPage(webDriver,action);
        computerPage.createComputer("A", "IBM");

        TablePage tablePage = new TablePage(webDriver,action);
        tablePage.ifComputerExist("A");
    }

}
