// Chris Hutcherson
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class GUIModel {
    private LoginController loginController;
    private DBManager dbManager;
    private HashMap<String, HashMap<String, String>> employeeInfoMap;
    private Users currUser;

    public GUIModel() throws IOException {
        loginController = new LoginController();
        dbManager = new DBManager();
        HashMap<String, HashMap<String, String>> employeeInfoMap = dbManager.getEmployeeInfoMap();
    }

    public void setUser(Users u){
        currUser = u;
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

    public HashMap<String, HashMap<String, String>> getEmployeeInfoMap() {
        return employeeInfoMap;
    }
}
