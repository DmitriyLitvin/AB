package allureReports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeoutException;

public class ComputerPage {
    private String addNewComputerA = "//a[text()='Add a new computer']";
    private String computerDatabaseA = "//a[text()='Computer database']";
    private String nameInput = "//input[@id='name']";
    private String discontinuedInput = "//input[@id='discontinued']";
    private String introducedInput = "//input[@id='introduced']";
    private String selectCompanyDropdown = "//select[@id='company']";
    private String createComputerInput = "//input[@value='Create this computer']";
    private String baseURL = "http://computer-database.gatling.io/computers";

    private BasicAction basicAction;
    private WebDriver webDriver;
    private Actions action;

    public ComputerPage(WebDriver webDriver, Actions action) {
        this.webDriver = webDriver;
        this.action = new Actions(webDriver);
        basicAction = new BasicAction(webDriver, action);
    }

    public String createComputer(String name, String firm, String introducedDate) throws TimeoutException, InterruptedException {
        webDriver.get(baseURL);

        WebElement addNewComputerButton = webDriver.findElement(By.xpath(addNewComputerA));
        addNewComputerButton.click();

        WebElement computerHeader = webDriver.findElement(By.xpath(computerDatabaseA));

        basicAction.isElementVisible(computerHeader, 5);
        basicAction.sendTextWithAction(name, nameInput, 5);

        String discountedDate = null;

        if (introducedDate != "-") {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

                Date date = dateFormat.parse(introducedDate);
                basicAction.sendTextWithAction(introducedDate, discontinuedInput, 5);
                discountedDate = basicAction.minusYear(date, 10);
                basicAction.sendTextWithAction(discountedDate, introducedInput, 5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        WebElement companyDropDown = webDriver.findElement(By.xpath(selectCompanyDropdown));
        action.moveToElement(companyDropDown).click().perform();

        WebElement optionDropDown = webDriver.findElement(By.xpath("//option[text()=\'" + firm + "\']"));
        optionDropDown.click();

        basicAction.clickWithAction(companyDropDown, 5);

        WebElement inputButton = webDriver.findElement(By.xpath(createComputerInput));
        inputButton.click();

        if (introducedDate == "-") {
            return "-";
        } else {
            return discountedDate;
        }
    }
}
