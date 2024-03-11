package be.kuleuven.candycrush;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginApplication extends Application {
    private static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(CandycrushApplication.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 300, 400);
        stage.setTitle("LoginScreen");
        stage.setScene(scene);
        stage.show();

    }



    public static void main(String[] args) {
        launch();
    }

    public static void close() {
        stage.close();
    }

}
