package HW2;

import org.testng.annotations.Test;

public class AddItemToCartTests extends TestBaseHW2 {

    @Test
    public void createAccountL () {
        appHW2.getUserHelperHW2().NewRegister("Andrii",
                "Lion",
                "b5555@gmail.com",
                "b5555@gmail.com",
                "b5555@gmail.com");
        appHW2.getUserHelperHW2().logoutAfterRegister();
        appHW2.getUserHelperHW2().login("b5555@gmail.com",
                "b5555@gmail.com");
        appHW2.getUserHelperHW2().logoutAfterLogin();

        System.out.println("Вроде все удачно прошло");

    }

    @Test
    public void loginAccountL (){
        appHW2.getUserHelperHW2().login("b5555@gmail.com",
                "b5555@gmail.com");


        appHW2.getCartHW2().selectProducts();
        appHW2.getCartHW2().selectShoppingCart();

        appHW2.getUserHelperHW2().logoutAfterLogin();

        System.out.println("Юху вроде ОК");
    }

    @Test
    public void checkingItemInCatt () {
        appHW2.getUserHelperHW2().login("b5555@gmail.com",
                "b5555@gmail.com");

        appHW2.getCartHW2().selectProducts();
        appHW2.getCartHW2().selectShoppingCart();
        appHW2.getCartHW2().isItemInCart();

        System.out.println("Успешный Успех!! ");

    }
}
