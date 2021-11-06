public class GUIApplication {
    public static void main(String[] args) {
        // Assemble all the pieces of the MVC
        new GUIController(new GUIModel(), new GUIView("LoginPage")).initController();
    }
}
