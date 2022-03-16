package portMan;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage mainWindow;

    @Override
    public void start(Stage primaryStage) throws Exception{
        mainWindow = primaryStage;
        FXMLLoader newScene = new FXMLLoader(getClass().getResource("startScene.fxml"));
        Parent root = newScene.load();
        Controller controller = newScene.getController();
        Scene scene = new Scene(root);
        mainWindow.setTitle("Portfolio Manager");
        mainWindow.setScene(scene);
        controller.setWelcomeText(controller.welcomeText);
        mainWindow.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
