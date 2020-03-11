package sample;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    public static SceneController control = new SceneController();

    @Override
    public void start(Stage primaryStage) throws Exception{
        HibernateUtil.getSessionFactory();
        control.setPrimaryStage(primaryStage);
        control.changeScene("/views/loginScreen.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
