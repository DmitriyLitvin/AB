package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.ComputerPage;
import page.TablePage;

public class DatabaseTest extends BaseTest {

    @Test
    public void testComputerExist() throws Exception {
        ComputerPage computerPage = new ComputerPage(webDriver);
        String discountedDate = computerPage.createComputer("Acorn Archimedes", "Acorn computer", "-");

        TablePage tablePage = new TablePage(webDriver);
        Assert.assertTrue(tablePage.ifComputerExist("Acorn Archimedes", "Acorn computer", "-", discountedDate));
    }

    @Test
    public void testComputerNotExist() throws Exception {
        String computerName = "Everest Home";
        String computerFirm = "Amstrad";
        String introducedDate = "2020-12-16";

        ComputerPage computerPage = new ComputerPage(webDriver);
        String discountedDate = computerPage.createComputer(computerName, computerFirm, introducedDate);

        TablePage tablePage = new TablePage(webDriver);
        Assert.assertFalse(tablePage.ifComputerExist(computerName, computerFirm, introducedDate, discountedDate),
                String.format("computer with name %s and firm %s and introduce date %s", computerName, computerFirm, introducedDate));
    }
}
