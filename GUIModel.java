// Chris Hutcherson
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;

public class GUIModel {
    private LoginController loginController;
    private DBManager dbManager;
    private HashMap<String, HashMap<String, String>> employeeInfoMap;
    private Users currUser;
    private boolean isManager;
    private Trip currTrip;

    // constructor
    public GUIModel() throws IOException {
        loginController = new LoginController();
        dbManager = new DBManager();
        HashMap<String, HashMap<String, String>> employeeInfoMap = dbManager.getEmployeeInfoMap();
    }

    // create user that is logged in for quick reference
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

    public Users getCurrUser() {
        return currUser;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public void setCurrUser(Users currUser) {
        this.currUser = currUser;
    }

    public Trip getCurrTrip() {
        return currTrip;
    }

    public void setCurrTrip(Trip currTrip) {
        this.currTrip = currTrip;
    }
}
