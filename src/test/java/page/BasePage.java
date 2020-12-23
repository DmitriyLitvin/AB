package page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class BasePage {
    WebDriver webDriver;
    Actions action;

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.action = new Actions(webDriver);
    }

    public void sendTextWithAction(String text, String xpath, int timeout) throws InterruptedException, TimeoutException {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);

        wait.until((WebDriver dr) -> {
            try {
                WebElement webElement = dr.findElement(By.xpath(xpath));
                webElement.sendKeys(text);
                return  dr.findElement(By.xpath(xpath)).getAttribute("value").length() != 0;
            } catch (Exception e) {
                return false;
            }
        });
    }

    public void sendTextWithActionAndEnter(String text, String xpath, int timeout) throws TimeoutException, InterruptedException {
        this.sendTextWithAction(text, xpath, timeout);
        action.moveToElement(webDriver.findElement(By.xpath(xpath)))
                .sendKeys(Keys.ENTER)
                .perform();
    }

    public boolean clickWithAction(String xpath, int timeout) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);

        return wait.until((WebDriver dr) -> {
            try {
                dr.findElement(By.xpath(xpath)).click();
                return true;
            } catch (Exception e) {
                return false;
            }
        });
    }

    public String minusYear(Date date, int years) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(date.toInstant(),
                ZoneId.systemDefault()).minusYears(years);
        Date newDate = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());

        return dateFormat.format(newDate);
    }
}
