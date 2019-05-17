package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Birki László
 */
public class Main extends Application {

    /**
     * Program start metódusa
     * @param primaryStage alap stage
     * @throws Exception kivétel dobása
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("Szotanulo");
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Program main metódus
     * @param args parancssori argumentumok
     */
    public static void main(String[] args) {
        launch(args);
    }


}
