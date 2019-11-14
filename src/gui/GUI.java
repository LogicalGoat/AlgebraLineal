package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * GUI
 */
public class GUI extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGUI.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Algebra Lineal - Convertidor de Base");
        stage.getIcons().add(new Image("gui/icon.png"));
        stage.setMinWidth(750);
        stage.setMinHeight(550);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
