package page;

import org.openqa.selenium.WebDriver;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeoutException;

public class ComputerPage extends BasePage {
    private String addNewComputerA = "//a[text()='Add a new computer']";
    private String nameInput = "//input[@id='name']";
    private String discontinuedInput = "//input[@id='discontinued']";
    private String introducedInput = "//input[@id='introduced']";
    private String selectCompanyDropdown = "//select[@id='company']";
    private String createComputerInput = "//input[@value='Create this computer']";
    private String baseURL = "http://computer-database.gatling.io/computers";

    public ComputerPage(WebDriver webDriver) {
        super(webDriver);
    }

    public String createComputer(String name, String firm, String introducedDate) throws TimeoutException, InterruptedException {
        webDriver.get(baseURL);

        clickWithAction(addNewComputerA, 5);

        sendTextWithAction(name, nameInput, 5);

        String discountedDate = null;
        if (introducedDate != "-") {
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

                Date date = dateFormat.parse(introducedDate);
                sendTextWithAction(introducedDate, discontinuedInput, 5);
                discountedDate = minusYear(date, 10);
                sendTextWithAction(discountedDate, introducedInput, 5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        clickWithAction(selectCompanyDropdown, 5);
        clickWithAction("//option[text()=\'" + firm + "\']", 5);
        clickWithAction(createComputerInput, 5);

        if (introducedDate == "-") {
            return "-";
        } else {
            return discountedDate;
        }
    }
}
