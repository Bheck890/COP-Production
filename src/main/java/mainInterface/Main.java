package mainInterface;

import devices.SpeakerType;
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

  static FXMLLoader loader;
  static Controller controller;

  @Override
  public void start(Stage primaryStage) throws Exception {
    loader = new FXMLLoader(getClass().getResource("/Product-Line.fxml"));
    //Parent root = FXMLLoader.load(getClass().getResource("/Product-Line.fxml"));
    Parent root = loader.load();
    controller = loader.getController();
    Scene scene = new Scene(root, 460, 467);

    primaryStage.setTitle("Production Line Tracker");
    primaryStage.setResizable(false);
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  public void Toggle() {
    controller.toggleAddProduct();
  }

  public static void setProduct(Product device) {
    controller.setProduct(device);
  }


}
