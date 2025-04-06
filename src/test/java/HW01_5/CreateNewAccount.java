package HW01_5;

import org.testng.annotations.Test;

public class CreateNewAccount extends TestBase_1_5 {
    @Test
    public void createAccountL () {
        NewRegister("Andrii",
                "Lion",
                "b5555@gmail.com",
                "b5555@gmail.com",
                "b5555@gmail.com");
        logoutAfterRegister();
        login("b5555@gmail.com",
                "b5555@gmail.com");
        logoutAfterLogin();

        System.out.println("Вроде все удачно прошло");

    }

}
