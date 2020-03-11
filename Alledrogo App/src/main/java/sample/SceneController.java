package sample;

import com.alledrogo.models.business.User;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {
    private Stage primaryStage;
    private int currentCategory;

    public User getMainUser() {
        return mainUser;
    }

    public void setMainUser(User mainUser) {
        this.mainUser = mainUser;
    }

    private User mainUser = new User();



    public SceneController(){
    }


    public void changeScene(String nameUrl){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(nameUrl));
            primaryStage.setTitle("Alledrogo");
            primaryStage.setScene(new Scene(root, 1280, 720));
            primaryStage.show();
        }
        catch(Exception e){

        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
