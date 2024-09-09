import com.example.app.LoginController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginTest {

    @Test
    void testHashPassword() {
        String password = "mypassword";
        String hashedPassword = LoginController.hashPassword(password);

        // You can compare with an expected value (MD5 hash of "mypassword")
        assertEquals("34819d7beeabb9260a5c854bc85b3e44", hashedPassword);
    }
}