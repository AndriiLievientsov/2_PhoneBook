package HW2;

import org.testng.annotations.Test;

public class AddItemToCartTests extends BaseClass {

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

    @Test
    public void loginAccountL (){
        login("b5555@gmail.com",
                "b5555@gmail.com");


        selectProducts();
        selectShoppingCart();

        logoutAfterLogin();

        System.out.println("Юху вроде ОК");
    }
}
