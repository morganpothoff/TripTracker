import java.sql.*;

public class GUIModel {
    private LoginController loginController;
    private DBManager dbManager;


    public GUIModel() {
        loginController = new LoginController();
        dbManager = new DBManager();
    }
    public LoginController getLoginController() {
        return loginController;
    }

    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    public DBManager getDbManager() {
        return dbManager;
    }

    public void setDbManager(DBManager dbManager) {
        this.dbManager = dbManager;
    }
}
