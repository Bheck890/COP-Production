import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main starting Java commands for the Production GUI.
 * @author Brandon Heck
 * @Date 10/9/20
*/

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {

    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
    Scene scene = new Scene(root, 460, 467);

    primaryStage.setTitle("Production Line Tracker");
    primaryStage.setScene(scene);
    primaryStage.show();

  }
}
