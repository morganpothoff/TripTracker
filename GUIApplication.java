// Chris Hutcherson
import java.io.IOException;

public class GUIApplication {
    public static void main(String[] args) throws IOException {
        // Assemble all the pieces of the MVC
        new GUIController(new GUIModel(), new GUIView("LoginPage")).initController();
    }
}
