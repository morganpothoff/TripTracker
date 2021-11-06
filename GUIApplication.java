public class GUIApplication {
    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
        GUIModel m = new GUIModel();
        GUIView v = new GUIView("LoginPage");
        GUIController c = new GUIController(m, v);
        c.initController();
    }


}
