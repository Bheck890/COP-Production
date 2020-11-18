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

  /**
   * The FXML Loader of the fxml File
   */
  static FXMLLoader loader;

  /**
   * created as a attribute to pass information to the Controller from a separate Class
   */
  static Controller controller;

  @Override
  public void start(Stage primaryStage) throws Exception {
    loader = new FXMLLoader(getClass().getResource("/Product-Line.fxml"));
    Parent root = loader.load();
    controller = loader.getController();
    Scene scene = new Scene(root, 460, 467);

    primaryStage.setTitle("Production Line Tracker");
    primaryStage.setResizable(false);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Turns On the Add Product Button for the Final Submit to the Database
   */
  public void turnOnAddProduct() {
      controller.toggleAddProduct(false);
  }

  /**
   * Passes a Product Device to the Controller so that it is able to Retain the
   * Provided information from the User when they added the type of product they Selected.
   * @param device is-a Product class that extends Product and has further information
   *               about the Product to add the the Product Database.
   */
  public static void setProduct(Product device) {
    controller.setProduct(device);
  }

}
