package page;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class TablePage extends BasePage {
    private String addComputerButton = "//a[text()='Add a new computer']";
    private String searchBoxInput = "//input[@id='searchbox']";
    private String paginationDiv = "//div[@id='pagination']/ul/li[2]/a";
    private String computerTable = "//table[contains(@class, 'computers')]/tbody";
    private String nextA = "//a[contains(text(), 'Next')]";

    public TablePage(WebDriver webDriver) {
        super(webDriver);
    }

    public boolean ifComputerExist(String name, String firm, String introducedDate, String discountedDate)
            throws InterruptedException, TimeoutException {
        List<String> addedComputer = Arrays.asList(name, introducedDate, discountedDate, firm);
        new WebDriverWait(webDriver, 5).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(addComputerButton)));

        sendTextWithActionAndEnter(name, searchBoxInput, 5);

        new WebDriverWait(webDriver, 5).ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(By.xpath(paginationDiv)));
        WebElement pagination = webDriver.findElement(By.xpath(paginationDiv));

        String[] paginationArray = pagination.getText().split("to|of");
        int maxPaginationQty = Integer.valueOf(paginationArray[2].trim());
        int currentPaginationQty  = 0;

        WebElement table;
        List<WebElement> rows = new ArrayList<>();
        boolean isExist = false;

        while (currentPaginationQty < maxPaginationQty){
            try {
                new WebDriverWait(webDriver, 5).ignoring(StaleElementReferenceException.class)
                        .until(ExpectedConditions.elementToBeClickable(By.xpath(computerTable)));
                table = webDriver.findElement(By.xpath(computerTable));
                rows = table.findElements(By.tagName("tr"));
            } catch (Exception e) {

            }

            List<List<String>> computerTable = new ArrayList<>();
            for (int i = 0; i < rows.size(); i++) {
                List<WebElement> columns = rows.get(i).findElements(By.tagName("td"));
                List<String> rowList = new ArrayList<>();

                for (int j = 0; j < columns.size(); j++) {
                    rowList.add(columns.get(j).getText());
                }

                computerTable.add(rowList);
            }

            WebElement next = webDriver.findElement(By.xpath(nextA));
            next.click();

            currentPaginationQty += computerTable.size();


            for (int i = 0; i < computerTable.size(); i++) {
                if (computerTable.get(i).equals(addedComputer)) {
                    isExist = true;
                }
            }
        };

        return isExist;
    }
}

