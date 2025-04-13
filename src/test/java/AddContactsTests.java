import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactsTests extends TestBase {

    private final String CONTACT_NAME = "TestName";

    @BeforeMethod
    public void preCondition() {
        login("anlii2025test.2025@gmail.com", "Password101$");
    }

    @Test(invocationCount = 1)
    public void addContactPositiveTest() {
        addNewContactPositiveData(CONTACT_NAME);
        Assert.assertTrue(isContactAdded(CONTACT_NAME));
    }

    @AfterMethod
    public void postCondition () {
        deleteOneContact();
    }

}
