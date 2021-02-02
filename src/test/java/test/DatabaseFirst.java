package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.ComputerPage;
import page.TablePage;

public class DatabaseFirst extends BaseTest {

    @Test
    public void testComputerExist() throws Exception {
        ComputerPage computerPage = new ComputerPage(webDriver);
        String discountedDate = computerPage.createComputer("a", "Acorn computer", "-");

        TablePage tablePage = new TablePage(webDriver);
        Assert.assertFalse(tablePage.ifComputerExist("a", "Acorn computer", "-", discountedDate));
    }
}
