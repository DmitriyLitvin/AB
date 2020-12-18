package allureReports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class TablePage {
    private String addComputerButton = "//a[text()='Add a new computer']";
    private String searchBoxInput = "//input[@id='searchbox']";
    private String paginationDiv = "//div[@id='pagination']/ul/li[2]/a";
    private String computerTable = "//table[contains(@class, 'computers')]/tbody";
    private String nextA = "//a[contains(text(), 'Next')]";

    private WebDriver webDriver;
    private BasicAction basicAction;
    private Actions action;


    public TablePage(WebDriver webDriver, Actions action) {
        this.webDriver = webDriver;
        this.action = action;
        basicAction = new BasicAction(webDriver, action);
    }

    public void ifComputerExist(String name) throws InterruptedException, TimeoutException {
        WebElement addNewComputerButton = webDriver.findElement(By.xpath(addComputerButton));

        basicAction.isElementVisible(addNewComputerButton);
        basicAction.sendTextWithActionAndEnter(name, searchBoxInput);

        WebElement pagination;
        try {
            pagination = webDriver.findElement(By.xpath(paginationDiv));
        } catch (Exception e) {
            pagination = webDriver.findElement(By.xpath(paginationDiv));
        }

        String[] paginationArray = pagination.getText().split("to|of");
        int maxPaginationQty = Integer.valueOf(paginationArray[2].trim());
        int currentPaginationQty = Integer.valueOf(paginationArray[1].trim());

        WebElement table;
        boolean isExist = false;

        do {
            try {
                table =
                        webDriver.findElement(By.xpath(computerTable));
            } catch (Exception e) {
                table =
                        webDriver.findElement(By.xpath(computerTable));
            }

            List<WebElement> rows = table.findElements(By.tagName("tr"));

            List<List<String>> computerTable = new ArrayList<>();
            for (int i = 0; i < rows.size(); i++) {
                List<WebElement> columns =
                        rows.get(i).findElements(By.tagName("td"));
                List<String> rowList = new ArrayList<>();

                for (int j = 0; j < columns.size(); j++) {
                    rowList.add(columns.get(j).getText());
                }

                computerTable.add(rowList);
            }
            if (computerTable.isEmpty()) {
                break;
            }

            WebElement next = webDriver.findElement(By.xpath(nextA));
            next.click();

            pagination = webDriver.findElement(By.xpath(paginationDiv));
            paginationArray = pagination.getText().split("to|of");
            currentPaginationQty = Integer.valueOf(paginationArray[1].trim());

            for (int i = 0; i < computerTable.size(); i++) {
                if (computerTable.get(i).get(0).equals(name)) {
                    isExist = true;
                }
            }
        } while (currentPaginationQty < maxPaginationQty && !isExist);

        assert isExist;
    }
}
