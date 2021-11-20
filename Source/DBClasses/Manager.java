

public class Manager extends Users {

    Manager(int id) throws Exception {
        super(id);
    }


    Manager(String email) throws Exception {
        super(email);
    }

    //Variables
    protected int managerID;

    //Methods
    public int getManagerID()    {
        return managerID;
    }//End of getManagerID

} //End of Manager
