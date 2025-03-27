package HW01;

import org.testng.annotations.Test;
//Тест чтоб понять

public class CreateAccountNew extends TestBase01 {
    @Test
    public void createAccountPositive (){
        register("abc1235@gmail.com","Pas123@@");
        logout();
        login("abc1235@gmail.com","Pas123@@");

        System.out.println("Создал и Вышел. Вообще Молодец");
    }
}
