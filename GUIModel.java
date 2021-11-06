import java.sql.*;

public class GUIModel {
    private LoginController loginController;

    public GUIModel(){
        loginController = new LoginController();
    }
    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }
}
