package sample;

import com.alledrogo.models.business.User;
import com.alledrogo.models.dao.UserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML private TextField surnameCreate = new TextField();
    @FXML private TextField nameCreate = new TextField();
    @FXML private Button confirmCreate = new Button();
    @FXML private TextField loginCreate = new TextField();
    @FXML private PasswordField passwordCreate = new PasswordField();
    @FXML private Label warning = new Label();
    @FXML private Button confirmButton = new Button();
    @FXML private Button backButton = new Button();
    @FXML private TextField loginText = new TextField();
    @FXML private TextField  passwordText = new TextField();
    @FXML private Button loginButton = new Button();
    @FXML private Button createButton = new Button();

    @FXML
    void initialize(){
        loginButton.setOnMouseClicked((e) -> {
            Main.control.changeScene("/views/dataScreen.fxml");
        });

        createButton.setOnMouseClicked((e) -> {
            Main.control.changeScene("/views/createScreen.fxml");
        });

        backButton.setOnMouseClicked((e) -> {
            Main.control.changeScene("/views/loginScreen.fxml");
        });

        confirmButton.setOnMouseClicked((e) -> {
            if(UserDAO.dataMatch(loginText.getText(), passwordText.getText())){
                String login = loginText.getText();
                Main.control.setMainUser(UserDAO.getUser(login));
                Main.control.changeScene("/views/home.fxml");
            }
        });

        confirmCreate.setOnMouseClicked((e -> {
            User newUser = new User(loginCreate.getText(), passwordCreate.getText(), nameCreate.getText(), "C", surnameCreate.getText(), 0);
            if(UserDAO.exists(newUser)){
                warning.setVisible(true);
            }
            else{
                UserDAO.createCommonUser(newUser);
                Main.control.changeScene("/views/loginScreen.fxml");
            }
        }));
    }

}
