package allureReports;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeoutException;

public class BasicAction {
    private WebDriver webDriver;
    private Actions action;

    public BasicAction(WebDriver webDriver, Actions action) {
        this.webDriver = webDriver;
        this.action = action;
    }


    public boolean sendTextWithAction(String text, String xpath) throws InterruptedException, TimeoutException {
        int retry = 0;
        WebElement nameInput;

        boolean isEmpty = true;
        while (isEmpty && retry < 5) {
            try {
                nameInput = webDriver.findElement(By.xpath(xpath));
                isEmpty = nameInput.getAttribute("value").isEmpty();
                if (isEmpty) {
                    action.moveToElement(nameInput).sendKeys(nameInput, text).perform();
                }

            } catch (Exception e) {
                nameInput = webDriver.findElement(By.xpath(xpath));
                isEmpty = nameInput.getAttribute("value").isEmpty();
                if (isEmpty) {
                    action.moveToElement(nameInput).sendKeys(nameInput, text).perform();
                }

            }

            retry++;
            Thread.sleep(1000);
        }

        return isEmpty;
    }

    public void sendTextWithActionAndEnter(String text, String xpath) throws TimeoutException, InterruptedException {
        this.sendTextWithAction(text, xpath);
        action.moveToElement(webDriver.findElement(By.xpath(xpath)))
                .sendKeys(Keys.ENTER)
                .perform();
    }

    public boolean isElementVisible(WebElement webElement) throws TimeoutException, InterruptedException {
        int retry = 0;

        while(!webElement.isEnabled()){
            retry++;
            if (retry > 5) {
                return false;
            }
            Thread.sleep(1000);
        }

        return true;
    }

    public boolean clickWithAction(WebElement webElement) throws InterruptedException {
        int retry = 0;
        while(!webElement.isDisplayed()) {
            webElement.click();
            retry++;
            if (retry > 5) {
                return false;
            }
            Thread.sleep(1000);
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
