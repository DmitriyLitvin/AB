package allureReports;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class BasicAction {
    private WebDriver webDriver;
    private Actions action;

    public BasicAction(WebDriver webDriver, Actions action) {
        this.webDriver = webDriver;
        this.action = action;
    }

    public void sendTextWithAction(String text, String xpath, int timeout) throws InterruptedException, TimeoutException {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        int retry = 0;
        do {
            WebElement webElement = webDriver.findElement(By.xpath(xpath));
            webElement.sendKeys(text);
            try {
                if(wait.until((ExpectedCondition<Boolean>) driver -> driver.findElement(By.xpath(xpath)).getAttribute("value").length() != 0)) {
                    break;
                }
            } catch (Exception e) {

            }
            retry++;
            Thread.sleep(1000);
        }while (retry < 5);
    }

    public void sendTextWithActionAndEnter(String text, String xpath, int timeout) throws TimeoutException, InterruptedException {
        this.sendTextWithAction(text, xpath, timeout);
        action.moveToElement(webDriver.findElement(By.xpath(xpath)))
                .sendKeys(Keys.ENTER)
                .perform();
    }

    public boolean clickWithAction(WebElement webElement, int timeout) throws InterruptedException {
        int retry = 0;
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        while (retry < 5) {
            try {
                if(wait.until(ExpectedConditions.elementToBeClickable(webElement)).isEnabled()) {
                    webElement.click();
                    break;
                }
            } catch (Exception e) {

            }
            Thread.sleep(1000);
            retry++;
        }

        return true;
    }

    public String minusYear(Date date, int years) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(),
                ZoneId.systemDefault()).minusYears(years);
        Date newDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return dateFormat.format(newDate);
    }
}
