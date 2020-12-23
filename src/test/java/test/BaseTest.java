package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
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
}
