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
        Assert.assertEquals( tablePage.ifComputerExist("Acorn Archimedes", "Acorn computer", "-", discountedDate), true);
    }

    @Test
    public void testComputerNotExist() throws Exception {
        ComputerPage computerPage = new ComputerPage(webDriver);
        String discountedDate = computerPage.createComputer("Everest Home", "Amstrad", "2020-12-16");

        TablePage tablePage = new TablePage(webDriver);
        Assert.assertEquals(tablePage.ifComputerExist("Everest Home", "Amstrad", "2020-12-16", discountedDate), false);
    }
}
