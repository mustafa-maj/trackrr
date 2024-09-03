import com.example.app.LoginController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class loginTest {

    private LoginController loginController;

    @BeforeEach
    public void setUp(){
        loginController = new LoginController();
    }

    @Test
    public void testLoginSuccess() {
        // Arrange
        loginController.setUsernameTextField(new TextField("mushi"));
        PasswordField passwordField = new PasswordField();
        passwordField.setText("admin");
        loginController.setPasswordPasswordField(passwordField);


        // Act
        boolean actual = loginController.validateLogin();

        // Assert
        assertEquals(true, actual);
    }

    @Test
    public void testSomeFunctionality2() {
        // Arrange
        int expected = 5;

        // Act
        int actual = 2 + 3;

        // Assert
        assertEquals(expected, actual);
    }
}