package HW2.modelHW2;

import com.phonebook.model.User;

public class UserHW2 {
    private  String email;
    private  String password;

    public UserHW2 setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserHW2 setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
